/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package assignment.librarymanagementsystem;

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
public class UserTest 
{
    Item item;
    User member;
    
    public UserTest() 
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
        item = new Book("def","Szia","Sano", 234, 5 );
        member = new Member("abc", "Ahmad", "abc@gmail.com", Member.Category.STUDENT);
    }
    
    @AfterEach
    public void tearDown() 
    {
    }

    /**
     * Test of borrowBook method, of class User.
     */
    @Test
    public void testBorrowBook() 
    {
        System.out.println("borrowBook");
        boolean expResult = true;
        member.borrowBook(item);
        boolean result = member.borrowedItems.contains(item);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of returnBook method, of class User.
     */
    @Test
    public void testReturnBook() 
    {
        System.out.println("returnBook");
        boolean damaged = false;
        member.borrowBook(item);
        member.returnBook(item, damaged);
        assertTrue(member.totalBorrowedItems.contains(item));
    }
    
    public class UserImpl extends User 
    {

        public UserImpl() {
            super("", "", "");
        }
    }
    
}
