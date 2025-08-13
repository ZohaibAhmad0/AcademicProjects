from .item import Item

class OnlineResearchPaper(Item):
    """
    Represents an online research paper in the library system.

    This class handles online research papers that can be viewed and downloaded
    digitally, with tracking of views and downloads.

    Attributes:
        title (str): The paper's title
        author (str): The paper's author
        url (str): The URL of the paper
        digital_copy (DigitalCopy): Digital version of the paper
    """

    def __init__(self, id: str, title: str, author: str, url: str, total_copies: int = 1):
        """
        Initialize a new online research paper.

        Args:
            id (str): Unique identifier for the paper
            title (str): Title of the paper
            author (str): Name of the paper's author
            url (str): URL of the paper
            total_copies (int): Number of copies available
        """
        super().__init__(id, 0, total_copies)  # page_count is 0 for online papers
        self.title = title
        self.author = author
        self.url = url

    def borrow(self) -> bool:
        """
        Borrow the online research paper.

        Returns:
            bool: True if the paper can be borrowed, False otherwise.
        """
        if self.digital_copy:
            self.digital_copy.view()
        return True

    def view_online(self):
        """
        View the online research paper.

        Returns:
            bool: True if the paper can be viewed online, False otherwise.
        """
        if self.digital_copy:
            self.digital_copy.view()
            return True
        return False

    def download_paper(self):
        """
        Download the online research paper.

        Returns:
            bool: True if the paper can be downloaded, False otherwise.
        """
        if self.digital_copy:
            self.digital_copy.download()
            return True
        return False
