/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment.librarymanagementsystem;

/**
 *
 * @author moonm
 */
abstract class Item 
{
    private String id;
    private int pageCount;
    private int timesBorrowed;
    private int totalCopies;
    private boolean isDamaged;
    private boolean isBorrowed;
    private DigitalCopy digitalCopy;

    public Item(String id, int pageCount, int totalCopies) 
    {
        this.id = id;
        this.pageCount = pageCount;
        this.totalCopies = totalCopies;
        this.timesBorrowed = 0;
        this.isDamaged = false;
        this.isBorrowed = false;
        this.digitalCopy = null;
    }

    public String getId() { return id; }
    public int getPageCount() { return pageCount; }
    public int getTimesBorrowed() { return timesBorrowed; }
    public int getTotalCopies() { return totalCopies; }
    public boolean getIsDamaged() { return isDamaged; }
    public boolean getIsBorrowed() { return isBorrowed; }
    public DigitalCopy getDigitalCopy() { return digitalCopy; }

    public void setPageCount(int pageCount) { this.pageCount = pageCount; }
    public void setTimesBorrowed(int timesBorrowed) { this.timesBorrowed = timesBorrowed; }
    public void setTotalCopies(int totalCopies) { this.totalCopies = totalCopies; }
    public void setIsDamaged(boolean isDamaged) { this.isDamaged = isDamaged; }
    public void setIsBorrowed(boolean isBorrowed) { this.isBorrowed = isBorrowed; }
    public void setDigitalCopy(DigitalCopy digitalCopy) { this.digitalCopy = digitalCopy; }

    /**
     * Checks if the item is damaged.
     * @return true if the item is damaged; false otherwise
     */
    public boolean ifDamaged() 
    {
        return isDamaged;
    }

    /**
     * Requests a repair for the item.
     * @return true if the item is damaged and needs repair
     */
    public boolean requestRepair() 
    {
        return isDamaged;
    }

    /**
     * Checks if the item is borrowed.
     * @return true if currently borrowed; false otherwise
     */
    public boolean ifBorrowed() 
    {
        return isBorrowed;
    }

    /**
     * Attempts to borrow the item if copies are available.
     * Updates borrowing state accordingly.
     * @return true if borrowing is successful; false if no copies are available
     */
    public boolean borrow() 
    {
        if (totalCopies > 0) 
        {
            totalCopies--;
            timesBorrowed++;
            if (totalCopies == 0) 
            {
                isBorrowed = true;
            }
            return true;
        }
        return false;
    }

    /**
     * Processes the return of an item and updates the damage and borrowing status.
     * @param damaged whether the item was returned damaged
     */
    public void returnItem(boolean damaged) 
    {
        totalCopies++;
        if (damaged) 
        {
            isDamaged = true;
        }
        if (totalCopies > 0) 
        {
            isBorrowed = false;
        }
    }

    /**
     * Creates a digital copy of this item if one doesn't already exist.
     */
    public void createDigitalCopy() 
    {
        if (digitalCopy == null) 
        {
            digitalCopy = new DigitalCopy(this.id);
        }
    }
}


class DigitalCopy 
{
    private String id;
    private int totalViews;
    private int totalDownloads;

    /**
     * Constructs a digital copy with a specific ID.
     * @param id Identifier of the original item
     */
    public DigitalCopy(String id) 
    {
        this.id = id;
        this.totalViews = 0;
        this.totalDownloads = 0;
    }

    /**
     * Increments the view counter for the digital copy.
     */
    public void view() 
    {
        totalViews++;
    }

    /**
     * Increments the download counter for the digital copy.
     */
    public void download() 
    {
        totalDownloads++;
    }

    public String getId() { return id; }
    public int getTotalViews() { return totalViews; }
    public int getTotalDownloads() { return totalDownloads; }
}