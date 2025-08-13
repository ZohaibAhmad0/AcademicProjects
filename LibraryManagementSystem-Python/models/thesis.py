from .item import Item

class Thesis(Item):
    def __init__(self, id: str, title: str, author: str, topic: str = "General", 
                 page_count: int = 0, total_copies: int = 1, defense_year: int = 2023):
        super().__init__(id, page_count, total_copies)
        self.title = title
        self.author = author
        self.topic = topic
        self.defense_year = defense_year

    def create_digital_copy(self):
        raise NotImplementedError("Thesis items are only available in physical format.")

    def view_online(self):
        raise NotImplementedError("Thesis items cannot be viewed online.")

    def download(self):
        raise NotImplementedError("Thesis items cannot be downloaded.")

    def borrow(self) -> bool:
        if self.get_total_copies() > 0:
            return super().borrow()
        return False
