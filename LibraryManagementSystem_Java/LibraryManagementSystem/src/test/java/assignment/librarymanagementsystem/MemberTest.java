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
public class MemberTest 
{
    
    public MemberTest() 
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
    }
    
    @AfterEach
    public void tearDown() 
    {
    }

    /**
     * Test of getCategory method, of class Member.
     */
    @Test
    public void testGetCategory() 
    {
        System.out.println("getCategory");
        Member instance = new Member("abc", "Zohaib", "abc@gmail.com", Member.Category.STUDENT);
        Member.Category expResult = Member.Category.valueOf("STUDENT");
        Member.Category result = instance.getCategory();
        assertEquals(expResult, result);
    }
    
}
