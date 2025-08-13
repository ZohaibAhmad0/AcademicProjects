import tkinter as tk
from tkinter import ttk

class StatsTab(ttk.Frame):
    def __init__(self, parent, library_system):
        super().__init__(parent)
        self.library_system = library_system
        
        # Create stats display
        self.create_stats_display()
        
        # Refresh button
        ttk.Button(self, text="Refresh Stats", 
                  command=self.refresh_stats).pack(pady=10)
                  
        # Initial refresh
        self.refresh_stats()
        
    def create_stats_display(self):
        # General statistics
        stats_frame = ttk.LabelFrame(self, text="Library Statistics")
        stats_frame.pack(fill='both', expand=True, padx=10, pady=5)
        
        # Create labels for statistics
        self.total_members = self.create_stat_label(stats_frame, "Total Members:")
        self.total_items = self.create_stat_label(stats_frame, "Total Items:")
        self.items_borrowed = self.create_stat_label(stats_frame, "Items Currently Borrowed:")
        
        # Popular items section
        popular_frame = ttk.LabelFrame(self, text="Most Popular")
        popular_frame.pack(fill='both', expand=True, padx=10, pady=5)
        
        self.popular_item = self.create_stat_label(popular_frame, "Most Popular Item:")
        self.active_member = self.create_stat_label(popular_frame, "Most Active Member:")
        
    def create_stat_label(self, parent, text):
        frame = ttk.Frame(parent)
        frame.pack(fill='x', padx=5, pady=2)
        ttk.Label(frame, text=text).pack(side='left')
        label = ttk.Label(frame, text="")
        label.pack(side='right')
        return label
        
    def refresh_stats(self):
        try:
            # Update general statistics
            self.total_members.config(
                text=str(len(self.library_system.get_all_members())))
            self.total_items.config(
                text=str(len(self.library_system.get_all_items())))
            
            # Count borrowed items
            borrowed_count = sum(1 for item in self.library_system.get_all_items() 
                               if getattr(item, 'is_borrowed', False))
            self.items_borrowed.config(text=str(borrowed_count))
            
            # Update popular items
            popular_item = self.library_system.find_most_popular_item()
            if popular_item:
                self.popular_item.config(
                    text=f"{popular_item.title} ({popular_item.times_borrowed} borrows)")
            
            # Update active member
            active_member = self.library_system.find_most_active_member()
            if active_member:
                self.active_member.config(
                    text=f"{active_member.name} ({len(active_member.total_borrowed_items)} items)")
                    
        except Exception as e:
            print(f"Error refreshing stats: {e}")
