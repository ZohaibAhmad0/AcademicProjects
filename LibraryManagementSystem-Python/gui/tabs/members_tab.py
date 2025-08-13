import tkinter as tk
from tkinter import ttk, messagebox
from models.member import Member, MemberCategory

class MembersTab(ttk.Frame):
    def __init__(self, parent, library_system):
        super().__init__(parent)
        self.library_system = library_system
        
        # Create member form
        self.create_member_form()
        
        # Create member list
        self.create_member_list()
        
    def create_member_form(self):
        form_frame = ttk.LabelFrame(self, text="Add Member")
        form_frame.pack(fill='x', padx=5, pady=5)
        
        # Member ID
        ttk.Label(form_frame, text="ID:").grid(row=0, column=0, padx=5, pady=5)
        self.id_entry = ttk.Entry(form_frame)
        self.id_entry.grid(row=0, column=1, padx=5, pady=5)
        
        # Name
        ttk.Label(form_frame, text="Name:").grid(row=1, column=0, padx=5, pady=5)
        self.name_entry = ttk.Entry(form_frame)
        self.name_entry.grid(row=1, column=1, padx=5, pady=5)
        
        # Email
        ttk.Label(form_frame, text="Email:").grid(row=2, column=0, padx=5, pady=5)
        self.email_entry = ttk.Entry(form_frame)
        self.email_entry.grid(row=2, column=1, padx=5, pady=5)
        
        # Category
        ttk.Label(form_frame, text="Category:").grid(row=3, column=0, padx=5, pady=5)
        self.category_var = tk.StringVar(value="Student")
        self.category_combo = ttk.Combobox(form_frame, textvariable=self.category_var,
                                           values=["Student", "Faculty", "Staff"])
        self.category_combo.grid(row=3, column=1, padx=5, pady=5)
        
        # Add button
        ttk.Button(form_frame, text="Add Member", 
                  command=self.add_member).grid(row=4, column=0, columnspan=2, pady=10)
                  
    def create_member_list(self):
        list_frame = ttk.LabelFrame(self, text="Members")
        list_frame.pack(fill='both', expand=True, padx=5, pady=5)
        
        # Create treeview
        self.tree = ttk.Treeview(list_frame, 
                                 columns=('ID', 'Name', 'Email', 'Category', 'Books'), 
                                 show='headings')
        self.tree.heading('ID', text='ID')
        self.tree.heading('Name', text='Name')
        self.tree.heading('Email', text='Email')
        self.tree.heading('Category', text='Category')
        self.tree.heading('Books', text='Borrowed Books')
        self.tree.pack(fill='both', expand=True)
        
        # Add refresh button
        ttk.Button(list_frame, text="Refresh", 
                  command=self.refresh_list).pack(pady=5)
                  
        self.refresh_list()
        
    def add_member(self):
        try:
            category = MemberCategory(self.category_var.get())
            member = Member(
                self.id_entry.get(),
                self.name_entry.get(),
                self.email_entry.get(),
                category
            )
            self.library_system.add_member(member)
            self.refresh_list()
            # Clear entries
            self.id_entry.delete(0, 'end')
            self.name_entry.delete(0, 'end')
            self.email_entry.delete(0, 'end')
            messagebox.showinfo("Success", "Member added successfully!")
        except Exception as e:
            messagebox.showerror("Error", str(e))
            
    def refresh_list(self):
        for item in self.tree.get_children():
            self.tree.delete(item)
        
        for member in self.library_system.get_all_members():
            self.tree.insert('', 'end', values=(
                member.id,
                member.name,
                member.email,
                member.category.value,  # Use .value to get string representation
                len(member.borrowed_items)
            ))
