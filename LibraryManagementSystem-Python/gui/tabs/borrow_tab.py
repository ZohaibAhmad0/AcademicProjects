import tkinter as tk
from tkinter import ttk, messagebox

class BorrowTab(ttk.Frame):
    def __init__(self, parent, library_system):
        super().__init__(parent)
        self.library_system = library_system
        
        # Create left and right frames
        left_frame = ttk.Frame(self)
        right_frame = ttk.Frame(self)
        left_frame.pack(side='left', fill='both', expand=True, padx=5, pady=5)
        right_frame.pack(side='right', fill='both', expand=True, padx=5, pady=5)
        
        # Borrow section
        self.create_borrow_section(left_frame)
        
        # Return section
        self.create_return_section(right_frame)
        
    def create_borrow_section(self, parent):
        borrow_frame = ttk.LabelFrame(parent, text="Borrow Item")
        borrow_frame.pack(fill='both', expand=True, padx=5, pady=5)
        
        # Member selection
        ttk.Label(borrow_frame, text="Member ID:").pack(pady=5)
        self.member_id_entry = ttk.Entry(borrow_frame)
        self.member_id_entry.pack(pady=5)
        
        # Item selection
        ttk.Label(borrow_frame, text="Item ID:").pack(pady=5)
        self.item_id_entry = ttk.Entry(borrow_frame)
        self.item_id_entry.pack(pady=5)
        
        # Borrow button
        ttk.Button(borrow_frame, text="Borrow", 
                  command=self.borrow_item).pack(pady=10)
                  
    def create_return_section(self, parent):
        return_frame = ttk.LabelFrame(parent, text="Return Item")
        return_frame.pack(fill='both', expand=True, padx=5, pady=5)
        
        # Member selection for return
        ttk.Label(return_frame, text="Member ID:").pack(pady=5)
        self.return_member_id = ttk.Entry(return_frame)
        self.return_member_id.pack(pady=5)
        
        # Item selection for return
        ttk.Label(return_frame, text="Item ID:").pack(pady=5)
        self.return_item_id = ttk.Entry(return_frame)
        self.return_item_id.pack(pady=5)
        
        # Damage report
        ttk.Label(return_frame, text="Damaged:").pack(pady=5)
        self.damage_var = tk.BooleanVar()
        ttk.Checkbutton(return_frame, variable=self.damage_var).pack()
        
        # Return button
        ttk.Button(return_frame, text="Return", 
                  command=self.return_item).pack(pady=10)
                  
    def borrow_item(self):
        try:
            member = self.library_system.find_member(self.member_id_entry.get())
            item = self.library_system.find_item(self.item_id_entry.get())
            
            if member and item:
                if self.library_system.borrow_book(member, item):
                    messagebox.showinfo("Success", "Item borrowed successfully!")
                else:
                    messagebox.showerror("Error", "Could not borrow item")
            else:
                messagebox.showerror("Error", "Member or item not found")
        except Exception as e:
            messagebox.showerror("Error", str(e))
            
    def return_item(self):
        try:
            member = self.library_system.find_member(self.return_member_id.get())
            item = self.library_system.find_item(self.return_item_id.get())
            
            if member and item:
                if self.library_system.return_book(member, item, 
                                                self.damage_var.get()):
                    messagebox.showinfo("Success", "Item returned successfully!")
                else:
                    messagebox.showerror("Error", "Could not return item")
            else:
                messagebox.showerror("Error", "Member or item not found")
        except Exception as e:
            messagebox.showerror("Error", str(e))
