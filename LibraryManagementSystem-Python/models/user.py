from abc import ABC, abstractmethod
from typing import List

class User(ABC):
    def __init__(self, id: str, name: str, email: str):
        self.id = id
        self.name = name
        self.email = email
        self.borrowed_items = []
        self.total_borrowed_items = []
        self.allowed_items = 3
