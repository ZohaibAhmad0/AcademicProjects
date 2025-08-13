import tkinter as tk
from tkinter import ttk, messagebox
from models.library_system import LibrarySystem
from .tabs.members_tab import MembersTab
from .tabs.items_tab import ItemsTab
from .tabs.borrow_tab import BorrowTab
from .tabs.stats_tab import StatsTab

class MainWindow(tk.Tk):
    def __init__(self, library_system: LibrarySystem):
        super().__init__()
        self.library_system = library_system
        
        # Configure window
        self.title("Library Management System")
        self.geometry("1000x700")
        
        # Add closing protocol
        self.protocol("WM_DELETE_WINDOW", self.on_closing)
        
        self.setup_gui()
        
    def setup_gui(self):
        # Create notebook for tabs
        self.notebook = ttk.Notebook(self)
        self.notebook.pack(fill='both', expand=True, padx=10, pady=10)
        
        # Create tabs
        self.members_tab = MembersTab(self.notebook, self.library_system)
        self.items_tab = ItemsTab(self.notebook, self.library_system)
        self.borrow_tab = BorrowTab(self.notebook, self.library_system)
        self.stats_tab = StatsTab(self.notebook, self.library_system)
        
        # Add tabs to notebook
        self.notebook.add(self.members_tab, text="Members")
        self.notebook.add(self.items_tab, text="Items")
        self.notebook.add(self.borrow_tab, text="Borrow/Return")
        self.notebook.add(self.stats_tab, text="Statistics")
        
    def on_closing(self):
        try:
            self.library_system.save_data()
            self.destroy()
        except Exception as e:
            messagebox.showerror("Error", f"Failed to save data: {e}")
            if messagebox.askyesno("Error", "Continue closing?"):
                self.destroy()
