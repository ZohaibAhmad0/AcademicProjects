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
public class ItemTest 
{
   Item instance;
    
    public ItemTest() 
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
        instance = new Book("abc","Hello","Martin", 200, 3 );
        instance.borrow();
        instance.returnItem(false);
        instance.borrow();
        instance.borrow();
        instance.borrow();
    }
    
    @AfterEach
    public void tearDown() 
    {
    }

    /**
     * Test of getId method, of class Item.
     */
    @Test
    public void testGetId() 
    {
        System.out.println("getId");
        String expResult = "abc";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPageCount method, of class Item.
     */
    @Test
    public void testGetPageCount() 
    {
        System.out.println("getPageCount");
        int expResult = 200;
        int result = instance.getPageCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTimesBorrowed method, of class Item.
     */
    @Test
    public void testGetTimesBorrowed() 
    {
        System.out.println("getTimesBorrowed");
        int expResult = 4;
        int result = instance.getTimesBorrowed();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalCopies method, of class Item.
     */
    @Test
    public void testGetTotalCopies() 
    {
        System.out.println("getTotalCopies");
        int expResult = 0;
        int result = instance.getTotalCopies();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIsDamaged method, of class Item.
     */
    @Test
    public void testGetIsDamaged() 
    {
        System.out.println("getIsDamaged");
        assertFalse(instance.getIsDamaged());
    }

    /**
     * Test of getIsBorrowed method, of class Item.
     */
    @Test
    public void testGetIsBorrowed() 
    {
        System.out.println("getIsBorrowed");
        assertTrue(instance.getIsBorrowed());
    }

    /**
     * Test of getDigitalCopy method, of class Item.
     */
    @Test
    public void testGetDigitalCopy() 
    {
        System.out.println("getDigitalCopy");
        instance.createDigitalCopy();
        DigitalCopy expResult = instance.getDigitalCopy();
        DigitalCopy result = instance.getDigitalCopy();
        assertSame(expResult, result);
    }

    /**
     * Test of setPageCount method, of class Item.
     */
    @Test
    public void testSetPageCount() 
    {
        System.out.println("setPageCount");
        int pageCount = 201;
        instance.setPageCount(pageCount);
        assertEquals(201,instance.getPageCount());
    }

    /**
     * Test of ifDamaged method, of class Item.
     */
    @Test
    public void testIfDamaged() 
    {
        System.out.println("ifDamaged");
        assertFalse(instance.ifDamaged());
    }

    /**
     * Test of requestRepair method, of class Item.
     */
    @Test
    public void testRequestRepair() 
    {
        System.out.println("requestRepair");
        boolean expResult = instance.getIsDamaged();
        boolean result = instance.requestRepair();
        assertEquals(expResult, result);
    }

    /**
     * Test of ifBorrowed method, of class Item.
     */
    @Test
    public void testIfBorrowed() 
    {
        System.out.println("ifBorrowed");
        boolean expResult = instance.getIsBorrowed();
        boolean result = instance.ifBorrowed();
        assertEquals(expResult, result);
    }

    /**
     * Test of borrow method, of class Item.
     */
    @Test
    public void testBorrow() 
    {
        System.out.println("borrow");
        int expResult = 4;
        int result = instance.getTimesBorrowed();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of returnItem method, of class Item.
     */
    @Test
    public void testReturnItem() 
    {
        System.out.println("returnItem");
        instance.returnItem(false);
        int expResult = 1;
        int result = instance.getTotalCopies();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of createDigitalCopy method, of class Item.
     */
    @Test
    public void testCreateDigitalCopy() 
    {
        System.out.println("createDigitalCopy");
        instance.createDigitalCopy();
        assertSame(instance.getDigitalCopy(), instance.getDigitalCopy());
    }

    public class ItemImpl extends Item 
    {

        public ItemImpl() 
        {
            super("", 0, 0);
        }
    }
    
}
