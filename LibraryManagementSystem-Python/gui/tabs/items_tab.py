import tkinter as tk
from tkinter import ttk, messagebox
from models.book import Book
from models.thesis import Thesis
from models.online_research_paper import OnlineResearchPaper

class ItemsTab(ttk.Frame):
    def __init__(self, parent, library_system):
        super().__init__(parent)
        self.library_system = library_system
        
        # Create item form
        self.create_item_form()
        
        # Create item list
        self.create_item_list()
        
        self.type_var.trace('w', self.on_type_changed)  # Add type change handler
        
    def create_item_form(self):
        form_frame = ttk.LabelFrame(self, text="Add Item")
        form_frame.pack(fill='x', padx=5, pady=5)
        
        # Item type selector
        ttk.Label(form_frame, text="Type:").grid(row=0, column=0, padx=5, pady=5)
        self.type_var = tk.StringVar(value="Book")
        type_combo = ttk.Combobox(form_frame, textvariable=self.type_var,
                                values=["Book", "Thesis", "Online Research Paper"])
        type_combo.grid(row=0, column=1, padx=5, pady=5)
        
        # Common fields
        self.id_entry = self.add_form_field(form_frame, "ID:", 1)
        self.title_entry = self.add_form_field(form_frame, "Title:", 2)
        self.author_entry = self.add_form_field(form_frame, "Author:", 3)
        
        # URL field (initially hidden)
        ttk.Label(form_frame, text="URL:").grid(row=4, column=0, padx=5, pady=5)
        self.url_entry = ttk.Entry(form_frame)
        self.url_entry.grid(row=4, column=1, padx=5, pady=5)
        self.url_entry.grid_remove()  # Hide initially
        
        # Add button
        ttk.Button(form_frame, text="Add Item", 
                  command=self.add_item).grid(row=5, column=0, columnspan=2, pady=10)
                  
    def add_form_field(self, parent, label, row):
        ttk.Label(parent, text=label).grid(row=row, column=0, padx=5, pady=5)
        entry = ttk.Entry(parent)
        entry.grid(row=row, column=1, padx=5, pady=5)
        return entry
        
    def create_item_list(self):
        list_frame = ttk.LabelFrame(self, text="Items")
        list_frame.pack(fill='both', expand=True, padx=5, pady=5)
        
        # Create treeview
        self.tree = ttk.Treeview(list_frame, 
                                columns=('ID', 'Type', 'Title', 'Author', 'Status'),
                                show='headings')
        self.tree.heading('ID', text='ID')
        self.tree.heading('Type', text='Type')
        self.tree.heading('Title', text='Title')
        self.tree.heading('Author', text='Author')
        self.tree.heading('Status', text='Status')
        self.tree.pack(fill='both', expand=True)
        
        ttk.Button(list_frame, text="Refresh", 
                  command=self.refresh_list).pack(pady=5)
        
        self.refresh_list()
        
    def on_type_changed(self, *args):
        if self.type_var.get() == "Online Research Paper":
            self.url_entry.grid()  # Show URL field
        else:
            self.url_entry.grid_remove()  # Hide URL field
            
    def add_item(self):
        try:
            item_type = self.type_var.get()
            if item_type == "Online Research Paper":
                item = OnlineResearchPaper(
                    self.id_entry.get(),
                    self.title_entry.get(),
                    self.author_entry.get(),
                    self.url_entry.get()
                )
            elif item_type == "Book":
                item = Book(self.id_entry.get(), self.title_entry.get(), 
                          self.author_entry.get())
            else:
                item = Thesis(self.id_entry.get(), self.title_entry.get(),
                            self.author_entry.get())
            
            self.library_system.add_item(item)
            self.refresh_list()
            messagebox.showinfo("Success", "Item added successfully!")
        except Exception as e:
            messagebox.showerror("Error", str(e))
            
    def refresh_list(self):
        for item in self.tree.get_children():
            self.tree.delete(item)
            
        for item in self.library_system.get_all_items():
            status = "Available" if not item.is_borrowed else "Borrowed"
            self.tree.insert('', 'end', values=(
                item.id,
                item.__class__.__name__,
                item.title,
                item.author,
                status
            ))
