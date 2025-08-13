/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment.librarymanagementsystem;

/**
 *
 * @author moonm
 */
class ResearchPaper extends Item 
{
    private String author;
    private String topic;
    public ResearchPaper(String id, String author, String topic, int pageCount, int totalCopies) 
    {
        super(id, pageCount, totalCopies);
        this.author = author;
        this.topic = topic;
    }

    public String getAuthor() { return author; }
    public String getTopic() { return topic; }
    public void setAuthor(String author) { this.author = author; }
    public void setTopic(String topic) { this.topic = topic; }
    @Override
    public boolean borrow() 
    {
        this.createDigitalCopy();
        this.getDigitalCopy().view();
        return true;

    }
}
