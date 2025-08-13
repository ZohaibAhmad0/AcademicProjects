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
public class DigitalCopyTest 
{
    static DigitalCopy instance;
    
    public DigitalCopyTest() 
    {
    }
    
    @BeforeAll
    public static void setUpClass() 
    {
        instance = new DigitalCopy("abc");
        instance.view();
        instance.download();
        instance.download();
    }
    
    @AfterAll
    public static void tearDownClass() 
    {
    }
    
    @BeforeEach
    public void setUp() 
    {
    }
    
    @AfterEach
    public void tearDown() 
    {
    }

    /**
     * Test of view method, of class DigitalCopy.
     */
    @Test
    public void testView() 
    {
        System.out.println("view");
        assertEquals(1,instance.getTotalViews());
    }

    /**
     * Test of download method, of class DigitalCopy.
     */
   @Test
   public void testDownloads()
   {
       System.out.println("download");
       assertEquals(2,instance.getTotalDownloads());
   }

    /**
     * Test of getId method, of class DigitalCopy.
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
     * Test of getTotalViews method, of class DigitalCopy.
     */
    @Test
    public void testGetTotalViews() 
    {
        System.out.println("getTotalViews");
        int expResult = 1;
        int result = instance.getTotalViews();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalDownloads method, of class DigitalCopy.
     */
    @Test
    public void testGetTotalDownloads() 
    {
        System.out.println("getTotalDownloads");
        int expResult = 2;
        int result = instance.getTotalDownloads();
        assertEquals(expResult, result);
    }
    
}
