/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package assignment.librarymanagementsystem;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author moonm
 */
public class LibraryManagementSystemTest 
{
    LibraryManagementSystem instance;
    
    public LibraryManagementSystemTest() 
    {
    }
    
    @BeforeAll
    public static void setUpClass() 
    {
    }
    
    @AfterAll
    public static void tearDownClass() 
    {
    }
    
    @BeforeEach
    public void setUp() 
    {
        instance = new LibraryManagementSystem();
        Item item1 = new Book("abc","Hello","Martin", 200, 3 );
        Item item2 = new Book("def","Szia","Sano", 234, 5 );
        Member member1 = new Member("abc", "Ahmad", "abc@gmail.com", Member.Category.STUDENT);
        instance.addItem(item1);
        instance.addItem(item2);
        instance.addMember(member1);
    }
    
    @AfterEach
    public void tearDown() 
    {
    }
    
    
    
    /**
     * Test of getItems method, of class LibraryManagementSystem.
     */
    
    @Test
    public void testGetItems() 
    {
        System.out.println("getItems");
        Item item1 = instance.getItems().get(0);
        Item item2 = instance.getItems().get(1);
        ArrayList<Item> expResult = new ArrayList<>();
        expResult.add(item1);
        expResult.add(item2);
        ArrayList<Item> result = instance.getItems();
        assertIterableEquals(expResult, result);
    }
    
    /**
     * Test of getArchiveBooks method, of class LibraryManagementSystem.
     */
    
    @Test
    public void testGetArchiveBooks() 
    {
        System.out.println("getArchiveBooks");
        Item item1 = instance.getItems().get(0);
        Item item2 = instance.getItems().get(1);
        item1.setIsDamaged(true);
        item2.setIsDamaged(true);
        instance.periodicRepairCheck();
        ArrayList<Item> result = instance.getArchiveBooks();
        System.out.println(result);
        assertTrue(result.size() <= 2);
    }
    /**
     * Test of getTransactions method, of class LibraryManagementSystem.
     */
    @Test
    public void testGetTransactions() 
    {
        System.out.println("getTransactions");
        ArrayList<String> expResult = new ArrayList<>();
        instance.borrowBook(instance.getMembers().get(0), instance.getItems().get(0));
        instance.returnBook(instance.getMembers().get(0), instance.getItems().get(0), false);
        expResult.add("Borrowed Book ID: abc");
        expResult.add("Returned Book ID: abc");
        ArrayList<String> result = instance.getTransactions();
        assertIterableEquals(expResult, result);
    }

    /**
     * Test of addItem method, of class LibraryManagementSystem.
     */
    @Test
    public void testAddItem() 
    {
        System.out.println("addItem");
        Item item = new Book("ghi","Hola","Khalifa", 256, 5 );
        instance.addItem(item);
        assertTrue(instance.getItems().contains(item));
    }

    /**
     * Test of addMember method, of class LibraryManagementSystem.
     */
    @Test
    public void testAddMember() 
    {
        System.out.println("addMember");
        Member member = new Member("def", "Sano", "def@gmail.com", Member.Category.ALUMNI);
        instance.addMember(member);
        assertTrue(instance.getMembers().contains(member));
    }

    /**
     * Test of borrowBook method, of class LibraryManagementSystem.
     */
    @Test
    public void testBorrowBook() 
    {
        System.out.println("borrowBook");
        Member member = instance.getMembers().get(0);
        Item item = instance.getItems().get(0);
        instance.borrowBook(member, item);
        assertTrue(((User) member).borrowedItems.contains(item));
    }

    /**
     * Test of returnBook method, of class LibraryManagementSystem.
     */
    @Test
    public void testReturnBook() 
    {
        System.out.println("returnBook");
        Member member = instance.getMembers().get(0);
        Item item = instance.getItems().get(0);
        boolean damaged = false;
        instance.borrowBook(member, item);
        instance.returnBook(member, item, damaged);
        assertTrue(((User) member).totalBorrowedItems.contains(item));
    }

    /**
     * Test of periodicRepairCheck method, of class LibraryManagementSystem.
     */
    @Test
    public void testPeriodicRepairCheck() 
    {
        System.out.println("periodicRepairCheck");
        Item item1 = instance.getItems().get(0);
        item1.setIsDamaged(true);
        instance.periodicRepairCheck();
        assertTrue(instance.getArchiveBooks().size() <= 1);
    }

    /**
     * Test of findMostViewedItem method, of class LibraryManagementSystem.
     */
    @Test
    public void testFindMostViewedItem() 
    {
        System.out.println("findMostViewedItem");
        Item item1 = instance.getItems().get(0);
        Item item2 = instance.getItems().get(1);
        item1.createDigitalCopy();
        item2.createDigitalCopy();
        item2.getDigitalCopy().view();
        item2.getDigitalCopy().view();
        item1.getDigitalCopy().view();
        Item expResult = item2;
        Item result = instance.findMostViewedItem();
        assertEquals(expResult, result);
    }

    /**
     * Test of findMostDownloadedItem method, of class LibraryManagementSystem.
     */
    @Test
    public void testFindMostDownloadedItem() 
    {
        System.out.println("findMostDownloadedItem");
        Item item1 = instance.getItems().get(0);
        Item item2 = instance.getItems().get(1);
        item1.createDigitalCopy();
        item2.createDigitalCopy();
        item2.getDigitalCopy().download();
        item1.getDigitalCopy().download();
        item1.getDigitalCopy().download();
        Item expResult = item1;
        Item result = instance.findMostDownloadedItem();
        assertEquals(expResult, result);
    }

    /**
     * Test of findMostPopularItemBasesOnTimesBorrowed method, of class LibraryManagementSystem.
     */
    @Test
    public void testFindMostPopularItemBasesOnTimesBorrowed() 
    {
        System.out.println("findMostPopularItemBasesOnTimesBorrowed");
        Item item1 = instance.getItems().get(0);
        Item item2 = instance.getItems().get(1);
        item1.borrow();
        item1.borrow();
        item2.borrow();
        Item expResult = item1;
        Item result = instance.findMostPopularItemBasesOnTimesBorrowed();
        assertEquals(expResult, result);
    }

    /**
     * Test of findMostPopularItem method, of class LibraryManagementSystem.
     */
    @Test
    public void testFindMostPopularItem() 
    {
        System.out.println("findMostPopularItem");
        Item item1 = instance.getItems().get(0);
        Item item2 = instance.getItems().get(1);
        item1.createDigitalCopy();
        item2.createDigitalCopy();
        item1.borrow();
        item1.getDigitalCopy().download();
        item1.getDigitalCopy().download();
        item2.borrow();
        item2.getDigitalCopy().download();
        Item expResult = item1;
        Item result = instance.findMostPopularItem();
        assertEquals(expResult, result);
    }

    /**
     * Test of findMostActiveMember method, of class LibraryManagementSystem.
     */
    @Test
    public void testFindMostActiveMember() 
    {
        System.out.println("findMostActiveMember");
        Member member1 = instance.getMembers().get(0);
        Member member2 = new Member("def", "Sano", "def@gmail.com", Member.Category.ALUMNI);
        instance.addMember(member2);
        Item item1 = instance.getItems().get(0);
        Item item2 = instance.getItems().get(1);
        instance.borrowBook(member1, item1);
        instance.borrowBook(member1, item2);
        instance.borrowBook(member2, item1);
        instance.borrowBook(member2, item2);
        instance.returnBook(member2, item2, false);
        Member expResult = member2;
        Member result = instance.findMostActiveMember();
        assertEquals(expResult, result);
    }

    /**
     * Test of searchByAuthor method, of class LibraryManagementSystem.
     */
    @Test
    public void testSearchByAuthor() 
    {
        System.out.println("searchByAuthor");
        String author = "Martin";
        Item item1 = instance.getItems().get(0);
        Item item2 = instance.getItems().get(1);
        ArrayList<Item> expResult = new ArrayList<>();
        expResult.add(item1);
        ArrayList<Item> result = instance.searchByAuthor(author);
        assertIterableEquals(expResult, result);
    }

    /**
     * Test of searchByTitle method, of class LibraryManagementSystem.
     */
    @Test
    public void testSearchByTitle() 
    {
        System.out.println("searchByTitle");
        String title = "Szia";
        Item item1 = instance.getItems().get(0);
        Item item2 = instance.getItems().get(1);
        ArrayList<Item> expResult = new ArrayList<>();
        expResult.add(item2);
        ArrayList<Item> result = instance.searchByTitle(title);
        assertIterableEquals(expResult, result);
    }
    
}
