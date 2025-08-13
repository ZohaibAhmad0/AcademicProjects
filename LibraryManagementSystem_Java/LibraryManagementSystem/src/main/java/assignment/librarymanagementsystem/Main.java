
package assignment.librarymanagementsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author moonm
 */
public class Main 
{
    public static void main(String[] args) 
    {
        LibraryManagementSystem library = new LibraryManagementSystem();

        // Load members from file
        try (BufferedReader br = new BufferedReader(new FileReader("./inputFiles/members.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("ID")) continue;
                String[] parts = line.split(" ");
                String id = parts[0];
                String name = parts[1];
                String email = parts[2];
                Member.Category category = Member.Category.valueOf(parts[3].toUpperCase());
                library.addMember(new Member(id, name, email, category));
            }
        } catch (IOException e) {
            System.out.println("Failed to load members: " + e.getMessage());
        }

        // Load items from file
        try (BufferedReader br = new BufferedReader(new FileReader("./inputFiles/items.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("ID")) continue;
                String[] parts = line.split(" ");
                String id = parts[0];
                String title = parts[1];
                String author = parts[2];
                int pages = Integer.parseInt(parts[3]);
                String topic = parts[4];
                String type = parts[5];
                int copies = Integer.parseInt(parts[6]);
                Item item;
                switch (type.toLowerCase()) 
                {
                    case "book":
                        item = new Book(id, title, author, pages, copies);
                        break;
                    case "thesis":
                        item = new Thesis(id, author, topic, type, pages, copies, copies);
                        break;
                    case "researchpaper":
                        item = new ResearchPaper(id, author, topic, pages, copies);
                        break;
                    default:
                        continue;
                }
                if (Integer.parseInt(id) % 2 == 0) item.createDigitalCopy();
                library.addItem(item);
            }
        } catch (IOException e) {
            System.out.println("Failed to load items: " + e.getMessage());
        }

        // Perform a few borrow operation
        Member member1 = library.findMostActiveMember();
        if (member1 != null && !library.searchByTitle("Clean_Code").isEmpty()) {
            Item book = library.searchByTitle("Clean_Code").get(0);
            boolean borrowed = library.borrowBook(member1, book);
            System.out.println("Borrowed CLEAN_CODE: " + borrowed);

            boolean returned = library.returnBook(member1, book, false);
            System.out.println("Returned CLEAN_CODE: " + returned);
        }

        // Search functionality
        System.out.println("Searching for items by author Martin_Fowler:");
        for (Item i : library.searchByAuthor("Martin_Fowler")) 
        {
            System.out.println("Book ID: " + i.getId());
        }

        // Run repair check
        library.periodicRepairCheck();

        // Simulate digital copy views and downloads
        for (Item item : library.searchByTitle("Clean_Code")) 
        {
            if (item.getDigitalCopy() != null) 
            {
                item.getDigitalCopy().view();
                item.getDigitalCopy().view();
                item.getDigitalCopy().download();
            }
        }

        // Create a digital copy for a book if not created yet
        Item itemToDigitize = library.searchByTitle("Refactoring").get(0);
        if (itemToDigitize.getDigitalCopy() == null) 
        {
            itemToDigitize.createDigitalCopy();
            itemToDigitize.getDigitalCopy().view();
        }
         // Transections Log
        for (String txn : library.getTransactions()) 
        {
            System.out.println(txn);
        }   
            
        System.out.println("Most viewed item ID: " + (library.findMostViewedItem() != null ? library.findMostViewedItem().getId() : "None"));
        System.out.println("Most downloaded item ID: " + (library.findMostDownloadedItem() != null ? library.findMostDownloadedItem().getId() : "None"));
        System.out.println("Most popular item ID: " + (library.findMostPopularItem() != null ? library.findMostPopularItem().getId() : "None"));    
    }
}
