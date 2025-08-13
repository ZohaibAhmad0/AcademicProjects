import json
from pathlib import Path
from typing import List, Optional
from models.item import Item
from models.member import Member, MemberCategory
from models.book import Book
from models.thesis import Thesis
from models.online_research_paper import OnlineResearchPaper

class LibrarySystem:
    """
    Main class for managing library operations.

    This class handles all library operations including item management,
    member management, borrowing/returning items, and statistical tracking.

    Attributes:
        items (List[Item]): Collection of all library items
        members (List[Member]): Collection of all library members
        data_file (Path): Path to the file for persistent storage
    """

    def __init__(self):
        """
        Initialize the library system.
        """
        self.items: List[Item] = []
        self.members: List[Member] = []
        self.archive_books: List[Item] = []
        self.repair_section = RepairSection()
        self.data_file = Path("library_data.json")
        self.load_data()

    def save_data(self):
        """
        Save library data to a file.
        """
        data = {
            'members': [
                {
                    'id': m.id,
                    'name': m.name,
                    'email': m.email,
                    'category': m.category.value
                } for m in self.members
            ],
            'items': [
                {
                    'id': i.id,
                    'type': i.__class__.__name__,
                    'title': i.title,
                    'author': i.author,
                } for i in self.items
            ]
        }
        with open(self.data_file, 'w') as f:
            json.dump(data, f)

    def load_data(self):
        """
        Load library data from a file.
        """
        if not self.data_file.exists():
            return
        try:
            with open(self.data_file, 'r') as f:
                data = json.load(f)
            for member_data in data.get('members', []):
                member = Member(
                    member_data['id'],
                    member_data['name'],
                    member_data['email'],
                    MemberCategory(member_data['category'])  # Convert string to enum
                )
                self.members.append(member)
            for item_data in data.get('items', []):
                item = Book(
                    item_data['id'],
                    item_data['title'],
                    item_data['author']
                )
                self.items.append(item)
        except Exception as e:
            print(f"Error loading data: {e}")

    def add_item(self, item: Item) -> None:
        """
        Add a new item to the library system.
        
        Args:
            item (Item): Item to add
            
        Raises:
            LibraryException: If item with same ID already exists
        """
        if self.find_item(item.id):
            raise LibraryException("Item ID already exists")
        self.items.append(item)
        self.save_data()

    def add_member(self, member: Member) -> None:
        """
        Add a new member to the library system.
        
        Args:
            member (Member): Member to add
            
        Raises:
            LibraryException: If member with same ID already exists
        """
        if self.find_member(member.id):
            raise LibraryException("Member ID already exists")
        self.members.append(member)
        self.save_data()

    def remove_item(self, item_id: str) -> bool:
        """Remove an item from the library."""
        item = self.find_item(item_id)
        if item and not item.is_borrowed:
            self.items.remove(item)
            self.save_data()
            return True
        return False

    def remove_member(self, member_id: str) -> bool:
        """Remove a member from the library."""
        member = self.find_member(member_id)
        if member and not member.borrowed_items:
            self.members.remove(member)
            self.save_data()
            return True
        return False

    def find_member(self, member_id: str) -> Optional[Member]:
        if not member_id:
            raise LibraryException("Member ID cannot be empty")
        if not member_id.startswith('M'):
            raise LibraryException("Invalid member ID format. Member IDs must start with 'M'")
        return next((m for m in self.members if m.id == member_id), None)

    def find_item(self, item_id: str) -> Optional[Item]:
        if not item_id:
            raise LibraryException("Item ID cannot be empty")
        if not item_id.startswith('I'):
            raise LibraryException("Invalid item ID format. Item IDs must start with 'I'")
        return next((i for i in self.items if i.id == item_id), None)

    def search_member_by_id(self, member_id: str) -> Optional[Member]:
        """
        Search for a member by their ID.
        
        Args:
            member_id (str): The ID of the member to search for
            
        Returns:
            Optional[Member]: The member if found, None otherwise
            
        Raises:
            LibraryException: If member ID format is invalid
        """
        if not member_id:
            raise LibraryException("Member ID cannot be empty")
        if not member_id.startswith('M'):
            raise LibraryException("Invalid member ID format. Member IDs must start with 'M'")
        return self.find_member(member_id)

    def search_member_by_name(self, name: str) -> List[Member]:
        """
        Search for members by their name.
        
        Args:
            name (str): Full or partial name to search for
            
        Returns:
            List[Member]: List of members matching the search criteria
        """
        if not name:
            raise LibraryException("Search name cannot be empty")
        return [m for m in self.members if name.lower() in m.name.lower()]

    def search_member_by_email(self, email: str) -> Optional[Member]:
        """
        Search for a member by their email address.
        
        Args:
            email (str): Email address to search for
            
        Returns:
            Optional[Member]: The member if found, None otherwise
        """
        if not email:
            raise LibraryException("Email cannot be empty")
        return next((m for m in self.members if m.email.lower() == email.lower()), None)

    def get_all_members(self) -> List[Member]:
        """Return all members in the library system."""
        return self.members.copy()

    def get_all_items(self) -> List[Item]:
        """Return all items in the library system."""
        return self.items.copy()

    def search_members(self, query: str, search_type: str = 'name') -> List[Member]:
        """
        Search members by different criteria.
        
        Args:
            query (str): Search query
            search_type (str): Type of search ('name', 'id', 'email')
            
        Returns:
            List[Member]: List of matching members
        """
        if not query:
            return self.get_all_members()
            
        if search_type == 'name':
            return self.search_member_by_name(query)
        elif search_type == 'id':
            member = self.search_member_by_id(query)
            return [member] if member else []
        elif search_type == 'email':
            member = self.search_member_by_email(query)
            return [member] if member else []
        return []

    def get_member_borrowing_history(self, member_id: str) -> List[Item]:
        """
        Get the borrowing history of a member.
        
        Args:
            member_id (str): ID of the member
            
        Returns:
            List[Item]: List of items borrowed by the member
        """
        member = self.find_member(member_id)
        if not member:
            raise LibraryException("Member not found")
        return member.total_borrowed_items

    def borrow_book(self, member: Member, item: Item) -> bool:
        """
        Process a book borrowing request.

        Args:
            member (Member): The member borrowing the item
            item (Item): The item being borrowed

        Returns:
            bool: True if borrowing was successful, False otherwise

        Raises:
            LibraryException: If member or item is invalid
        """
        if not member or not item:
            raise LibraryException("Invalid member or item")
        if member in self.members and member.borrow_book(item):
            self.save_data()
            return True
        return False

    def return_book(self, member: Member, item: Item, damaged: bool, severity: int = 0) -> bool:
        success = member.return_book(item, damaged, severity)
        if success:
            self.save_data()
        return success

    def periodic_repair_check(self) -> None:
        damaged_items = [item for item in self.items if item.is_damaged]
        for item in damaged_items:
            severity = item.get_damage_severity()
            self.items.remove(item)
            if severity < 5:
                self.archive_books.append(item)
            else:
                self.items.append(self.repair_section.repair(item))
        self.save_data()

    def find_most_viewed_item(self) -> Optional[Item]:
        return max(
            (i for i in self.items if i.digital_copy is not None),
            key=lambda x: x.digital_copy.total_views,
            default=None
        )

    def find_most_downloaded_item(self) -> Optional[Item]:
        return max(
            (i for i in self.items if i.digital_copy is not None),
            key=lambda x: x.digital_copy.total_downloads,
            default=None
        )

    def find_most_popular_item(self) -> Optional[Item]:
        return max(
            self.items,
            key=lambda x: x.times_borrowed + (x.digital_copy.total_downloads if x.digital_copy else 0),
            default=None
        )

    def find_most_active_member(self) -> Optional[Member]:
        return max(self.members, key=lambda m: len(m.total_borrowed_items), default=None)

    def search_by_author(self, author: str) -> List[Item]:
        return [
            item for item in self.items
            if (isinstance(item, (Book, Thesis, OnlineResearchPaper)) and 
                item.author.lower() == author.lower())
        ]

    def search_by_title(self, title: str) -> List[Item]:
        return [
            item for item in self.items
            if isinstance(item, Book) and item.title.lower() == title.lower()
        ]

class RepairSection:
    def __init__(self):
        self.total_books_count = 0

    def repair(self, item: Item) -> Item:
        if item.is_damaged:
            self.total_books_count += 1
            item.is_damaged = False
        return item
