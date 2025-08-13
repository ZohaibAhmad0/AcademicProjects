/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment.librarymanagementsystem;

/**
 *
 * @author moonm
 */
class Book extends Item 
{
    private String title;
    private String author;

    public Book(String id, String title, String author, int pageCount, int totalCopies) {
        super(id, pageCount, totalCopies);
        this.title = title;
        this.author = author;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
}
