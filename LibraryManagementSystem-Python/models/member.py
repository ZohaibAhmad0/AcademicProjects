from enum import Enum
from typing import List
from .item import Item

class MemberCategory(Enum):
    STUDENT = "Student"
    FACULTY = "Faculty"
    ALUMNI = "Alumni"

class Member:
    def __init__(self, id: str, name: str, email: str, category: MemberCategory):
        self.id = id
        self.name = name
        self.email = email
        self.category = category
        self.borrowed_items: List[Item] = []  # Currently borrowed items
        self.total_borrowed_items: List[Item] = []  # History of all borrowed items
        self.is_active = True
        self.suspension_end_date = None
        self.allowed_items = 3  # Default borrowing limit

    def borrow_book(self, item) -> bool:
        if not item.is_borrowed and item not in self.borrowed_items:
            self.borrowed_items.append(item)
            self.total_borrowed_items.append(item)
            item.is_borrowed = True
            item.borrowed_by = self
            return True
        return False

    def return_book(self, item, damaged: bool = False, damage_severity: int = 0) -> bool:
        if item in self.borrowed_items:
            self.borrowed_items.remove(item)
            item.is_borrowed = False
            item.borrowed_by = None
            if damaged:
                item.is_damaged = True
                item.damage_severity = damage_severity
            return True
        return False

    def get_borrowed_items(self):
        return self.borrowed_items.copy()

    def get_borrowing_history(self):
        return self.total_borrowed_items.copy()
