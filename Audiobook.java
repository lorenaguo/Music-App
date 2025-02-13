/*
   File Name: Audiobook.java
   Programmer: Katrina Jin
   Course: ICS4U1
   Date: 2023/6/6
   Description: A subclass of Media, representing an audiobook
*/
package media;

import java.util.Date; 
import user.*;
import main.*;

public class Audiobook extends Media{ // inheritance
   // Fields
  private String narrator;                 // name of the narrator
  private String bookSummary;              // book summary
   
   /*
      Name: Audiobook
      Parameters:
         name - name of audiobook
         artist - book author
         releaseDate - release date of audiobook
         length - time length of audiobook
         narrator - the name of the narrator
         bookSummary - a short summary for the book being narrated
      Return: N/A
      Description: initializes all fields of an Auidobook object
   */
   public Audiobook(String name, Artist author, Date releaseDate, String length, String narrator, String bookSummary){
      super(name, author, releaseDate, length);
      this.narrator = narrator;
      this.bookSummary = bookSummary;
   }
   
   /*
      Name: toString
      Parameters:N/A
      Return: a string containing information about the audiobook
      Description: returns string with name, book author, release date, time length, number of plays, number of likes, narrator, and book summary
   */
   public String toString(){
      return super.toString() + "\nNarrator: " + narrator + "\nBook summary: " + bookSummary;
   }
   
   // Accessors
   public String getNarrator(){
      return narrator;
   }
   
   public String getBookSummary(){
      return bookSummary;
   }
   
   // Mutators
   public void setNarrator(String narrator){
      this.narrator = narrator;
   }
   
   public void setBookSummary(String bookSummary){
      this.bookSummary = bookSummary;
   }
}