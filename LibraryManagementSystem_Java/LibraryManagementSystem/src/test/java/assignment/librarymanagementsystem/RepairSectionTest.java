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
public class RepairSectionTest 
{
    Item item;
    RepairSection instance;
    
    public RepairSectionTest() 
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
        item = new Book("abc", "Szia","Sano",200, 4);
        instance = new RepairSection();
    }
    
    @AfterEach
    public void tearDown() 
    {
    }

    /**
     * Test of repair method, of class RepairSection.
     */
    @Test
    public void testRepair() 
    {
        System.out.println("repair");
        ((Item) item).setIsDamaged(true);
        assertFalse(instance.repair(item).getIsDamaged());
    }

    /**
     * Test of getTotalBooksCount method, of class RepairSection.
     */
    @Test
    public void testGetTotalBooksCount() 
    {
        System.out.println("getTotalBooksCount");
        ((Item) item).setIsDamaged(true);
        instance.repair(item);
        int expResult = 1;
        int result = instance.getTotalBooksCount();
        assertEquals(expResult, result);
        
    }
    
}
