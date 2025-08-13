from abc import ABC, abstractmethod
from dataclasses import dataclass

@dataclass
class DigitalCopy:
    """
    Represents a digital copy of a library item.

    Attributes:
        id (str): Unique identifier for the digital copy
        total_views (int): Total number of views
        total_downloads (int): Total number of downloads
    """
    id: str
    total_views: int = 0
    total_downloads: int = 0

    def view(self):
        """Increment the total views of the digital copy."""
        self.total_views += 1

    def download(self):
        """Increment the total downloads of the digital copy."""
        self.total_downloads += 1

class Item(ABC):
    """
    Abstract base class representing a library item.
    
    Attributes:
        id (str): Unique identifier for the item
        page_count (int): Number of pages in the item
        total_copies (int): Total number of copies available
        times_borrowed (int): Number of times the item has been borrowed
        damage_severity (int): Level of damage from 0-10, 0 being undamaged
        is_damaged (bool): Whether the item is damaged
        is_borrowed (bool): Whether the item is currently borrowed
        digital_copy (DigitalCopy): Digital version of the item if available
    """

    def __init__(self, id: str, page_count: int, total_copies: int):
        """
        Initialize a new library item.

        Args:
            id (str): Unique identifier for the item
            page_count (int): Number of pages in the item
            total_copies (int): Total number of copies available
        """
        self.id = id
        self.page_count = page_count
        self.total_copies = total_copies
        self.times_borrowed = 0
        self.is_damaged = False
        self.is_borrowed = False
        self.digital_copy = None
        self.damage_severity = 0  # New attribute for damage severity

    def borrow(self) -> bool:
        """
        Borrow the item if it is available and not damaged.

        Returns:
            bool: True if the item was successfully borrowed

        Raises:
            ItemException: If the item is damaged or no copies are available
        """
        if self.is_damaged:
            raise ItemException("Cannot borrow damaged item")
        if self.total_copies <= 0:
            raise ItemException("No copies available")
        self.total_copies -= 1
        self.times_borrowed += 1
        if self.total_copies == 0:
            self.is_borrowed = True
        return True

    def return_item(self, damaged: bool, severity: int = 0):
        """
        Return the item to the library.

        Args:
            damaged (bool): Whether the item is returned damaged
            severity (int): Damage severity level if the item is damaged
        """
        self.total_copies += 1
        if damaged:
            self.set_damage_severity(severity)
        if self.total_copies > 0:
            self.is_borrowed = False

    def set_damage_severity(self, severity: int):
        """
        Set the damage severity level of the item.

        Args:
            severity (int): Damage level from 0-10

        Raises:
            ItemException: If severity is not between 0 and 10
        """
        if not isinstance(severity, int) or severity < 0 or severity > 10:
            raise ItemException("Damage severity must be between 0 and 10")
        self.damage_severity = severity
        self.is_damaged = severity > 0

    def get_damage_severity(self) -> int:
        """
        Get the damage severity level of the item.

        Returns:
            int: Damage severity level from 0-10
        """
        return self.damage_severity

    def create_digital_copy(self):
        """
        Create a digital copy of the item if it is not a Thesis.

        Raises:
            ItemException: If the item is a Thesis
        """
        if isinstance(self, Thesis):
            raise ItemException("Thesis items cannot have digital copies")
        if self.digital_copy is None:
            self.digital_copy = DigitalCopy(self.id)
