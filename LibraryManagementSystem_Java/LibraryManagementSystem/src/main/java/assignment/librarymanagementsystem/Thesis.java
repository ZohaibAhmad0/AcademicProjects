/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment.librarymanagementsystem;

/**
 *
 * @author moonm
 */
class Thesis extends Item 
{
    private String author;
    private String adviser;
    private int defenseYear;
    private String topic;

    public Thesis(String id, String author, String adviser, String topic, int pageCount, int totalCopies, int defenseYear) 
    {
        super(id, pageCount, totalCopies);
        this.author = author;
        this.topic = topic;
        this.defenseYear = defenseYear;
        this.adviser = adviser;
    }

    public String getAuthor() { return author; }
    public String getTopic() { return topic; }
    public void setAuthor(String author) { this.author = author; }
    public void setTopic(String topic) { this.topic = topic; }
    public String getAdviser() { return adviser; }
    public int getDefenseYear() { return defenseYear; }
    public void setAdviser(String adviser) { this.adviser = adviser; }
    public void setDefenseYear(int defenseYear) { this.defenseYear = defenseYear; }
    @Override
    public void createDigitalCopy() 
    {
        System.out.println("No digital copy can be created for this item.");
    }
}
