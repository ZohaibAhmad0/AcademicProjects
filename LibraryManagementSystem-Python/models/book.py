from .item import Item

class Book(Item):
    def __init__(self, id: str, title: str, author: str, page_count: int = 0, total_copies: int = 1):
        super().__init__(id, page_count, total_copies)
        self.title = title
        self.author = author

    def get_title(self) -> str:
        return self.title

    def get_author(self) -> str:
        return self.author

    def set_title(self, title: str):
        self.title = title

    def set_author(self, author: str):
        self.author = author

    def __str__(self) -> str:
        return f"{self.title} by {self.author} (ID: {self.id})"

    def to_dict(self) -> dict:
        return {
            'id': self.id,
            'title': self.title,
            'author': self.author,
            'page_count': self.page_count,
            'total_copies': self.total_copies,
            'available_copies': self.available_copies
        }
