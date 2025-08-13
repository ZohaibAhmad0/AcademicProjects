/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package assignment.librarymanagementsystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author moonm
 */
class LibraryManagementSystem 
{
    private ArrayList<Item> items;
    private ArrayList<Member> members;
    private ArrayList<Item> archiveBooks;
    private ArrayList<String> transactions;
    private RepairSection repairSection;
    private Random random;

    public ArrayList<Item> getItems() { return items; }
    public ArrayList<Member> getMembers() { return members; }
    public ArrayList<Item> getArchiveBooks() { return archiveBooks; }
    public ArrayList<String> getTransactions() { return transactions; }
    
    public LibraryManagementSystem() 
    {
        items = new ArrayList<>();
        members = new ArrayList<>();
        archiveBooks = new ArrayList<>();
        transactions = new ArrayList<>();
        repairSection = new RepairSection();
        random = new Random();
    }

    /**
     * Adds an item to the library.
     * @param item The item to add
     */
    public void addItem(Item item) 
    {
        items.add(item);
    }

    /**
     * Adds a member to the library.
     * @param member The member to add
     */
    public void addMember(Member member)
    {
        members.add(member);
    }

    /**
     * Attempts to borrow a book for a member.
     * Records the transaction if successful.
     * @param member The member borrowing the book
     * @param item The item being borrowed
     * @return true if the borrow was successful; false otherwise
     */
    public boolean borrowBook(Member member, Item item) 
    {
        if (members.contains(member))
        {
            boolean success = member.borrowBook(item);
            if (success) 
            {
                transactions.add("Borrowed Book ID: " + item.getId());
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a book for a member and records the transaction.
     * @param member The member returning the book
     * @param item The item being returned
     * @param damaged true if the item was returned damaged
     * @return true if the return was successful
     */
    public boolean returnBook(Member member, Item item, boolean damaged) 
    {
        boolean success = member.returnBook(item, damaged);
        if (success) 
        {
            transactions.add("Returned Book ID: " + item.getId());
        }
        return success;
    }

    /**
     * Checks all items for damage and processes them accordingly.
     * Items are either archived or sent to repair based on damage severity.
     */
    public void periodicRepairCheck() 
    {
        ArrayList<Item> damagedItems = new ArrayList<>();
        for (Item item : items) 
        {
            if (item.ifDamaged()) 
            {
                damagedItems.add(item);
            }
        }
        for (Item item : damagedItems) 
        {
            int severity = random.nextInt(10) + 1;
            items.remove(item);
            if (severity < 5) {
                archiveBooks.add(item);
            } else {
                items.add(repairSection.repair(item));
            }
        }
    }

    /**
     * Finds the item with the highest number of digital views.
     * @return The most viewed item, or null if none
     */
    public Item findMostViewedItem() 
    {
        return items.stream()
            .filter(item -> item.getDigitalCopy() != null)
            .max(Comparator.comparingInt(i -> i.getDigitalCopy().getTotalViews()))
            .orElse(null);
    }

    /**
     * Finds the item with the highest number of digital downloads.
     * @return The most downloaded item, or null if none
     */
    public Item findMostDownloadedItem() 
    {
        return items.stream()
            .filter(item -> item.getDigitalCopy() != null)
            .max(Comparator.comparingInt(i -> i.getDigitalCopy().getTotalDownloads()))
            .orElse(null);
    }

    /**
     * Finds the item that has been borrowed the most times.
     * @return The most borrowed item, or null if none
     */
    public Item findMostPopularItemBasesOnTimesBorrowed() 
    {
        return items.stream()
            .max(Comparator.comparingInt(i -> i.getTimesBorrowed()))
            .orElse(null);
    }

    /**
     * Finds the most popular item based on both borrow count and digital downloads.
     * @return The most popular item overall
     */
    public Item findMostPopularItem() 
    {
        return items.stream()
            .max(Comparator.comparingInt(i -> i.getTimesBorrowed() + (i.getDigitalCopy() != null ? i.getDigitalCopy().getTotalDownloads() : 0)))
            .orElse(null);
    }

    /**
     * Identifies the most active member based on total borrowed items.
     * @return The most active member, or null if none
     */
    public Member findMostActiveMember() 
    {
        return members.stream()
            .max(Comparator.comparingInt(m -> m.totalBorrowedItems.size()))
            .orElse(null);
    }

    /**
     * Searches the item list by author.
     * @param author The author name to search for
     * @return List of items matching the author's name
     */
    public ArrayList<Item> searchByAuthor(String author) 
    {
        ArrayList<Item> result = new ArrayList<>();
        for (Item item : items) 
        {
            if (item instanceof Book && ((Book) item).getAuthor().equalsIgnoreCase(author)) 
            {
                result.add(item);
            } 
            else if (item instanceof Thesis && ((Thesis) item).getAuthor().equalsIgnoreCase(author)) 
            {
                result.add(item);
            } 
            else if (item instanceof ResearchPaper && ((ResearchPaper) item).getAuthor().equalsIgnoreCase(author)) 
            {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Searches the item list by title.
     * @param title The title of the item to search for
     * @return List of items matching the title
     */
    public ArrayList<Item> searchByTitle(String title) 
    {
        ArrayList<Item> result = new ArrayList<>();
        for (Item item : items) 
        {
            if (item instanceof Book && ((Book) item).getTitle().equalsIgnoreCase(title)) 
            {
                result.add(item);
            }
        }
        return result;
    }
}


class RepairSection 
{
    private int totalBooksCount;
    public RepairSection() 
    {
        this.totalBooksCount = 0;
    }

    /**
     * Repairs a damaged item and marks it as not damaged.
     * @param item The item to repair
     * @return The repaired item
     */
    public Item repair(Item item) 
    {
        if(item.getIsDamaged())
        {
            totalBooksCount++;
            item.setIsDamaged(false);
        }
        return item;
    }
    
    public int getTotalBooksCount() { return totalBooksCount; }
}