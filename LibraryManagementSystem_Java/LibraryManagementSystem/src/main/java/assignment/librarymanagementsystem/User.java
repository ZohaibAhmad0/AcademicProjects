/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment.librarymanagementsystem;
import java.util.ArrayList;
/**
 *
 * @author moonm
 */

class Member extends User 
{
    public enum Category { STUDENT, FACULTY, ALUMNI }

    private Category category;

    public Member(String id, String name, String email, Category category) 
    {
        super(id, name, email);
        this.category = category;
    }

    public Category getCategory() { return category; }
}


abstract class User 
{
    protected String id;
    protected String name;
    protected String email;
    protected ArrayList<Item> borrowedItems;
    protected ArrayList<Item> totalBorrowedItems;
    protected int allowedItems;

    
    public User(String id, String name, String email) 
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.borrowedItems = new ArrayList<>();
        this.totalBorrowedItems = new ArrayList<>();
        this.allowedItems = 3;
    }

    /**
     * Attempts to borrow an item if within allowed borrowing limit.
     * @param item The item to borrow
     * @return true if borrowing was successful; false otherwise
     */
    public boolean borrowBook(Item item) 
    {
        if (borrowedItems.size() < allowedItems) 
        {
            if (item.borrow())  // Call borrow() here and check its result
            {
                borrowedItems.add(item);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a borrowed item and updates borrowing history.
     * @param item The item being returned
     * @param damaged true if the item was returned damaged
     * @return true if the item was successfully returned; false otherwise
     */
    public boolean returnBook(Item item, boolean damaged) 
    {
        boolean success;
        if (success = borrowedItems.remove(item)) 
        {
            item.returnItem(damaged);
            if (!damaged) 
            {
                totalBorrowedItems.add(item);
                if (totalBorrowedItems.size() % 3 == 0) 
                {
                    allowedItems++;
                }
            }
        }
        return success;
    }
}