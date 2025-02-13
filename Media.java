/*
   File Name: Media.java
   Programmer: Katrina Jin
   Course: ICS4U1
   Date: 2023/6/5
   Description: An abstract class that represents a media object 
*/

package media;
import user.*;
import java.util.Date;
import java.io.*;

public abstract class Media{               // Abstract class
   // Fields // INHERITANCE : these fields are inherited by the child class
   protected String name;                  // name of media
   protected Artist artist;                // the artist that released the media
   protected Date releaseDate;             // release date of the media
   protected String length;                // time length of media
   protected int numPlays;                 // number of plays the media has
   protected int numLikes;                 // number of likes the media has
   
   /*
      Name: Media
      Parameters:
         name - name of media
         artist - artist that released media
         releaseDate - release date of media
         length - time length of media
      Return: N/A
      Description: initializes all fields of a Media object
   */
   public Media(String name, Artist artist, Date releaseDate, String length){
      this.name = name;
      this.artist = artist;
      this.releaseDate = releaseDate;
      this.length = length;
      numPlays = 0;
      numLikes = 0;
   }
   
   // Accessors
   public String getName(){         
      return name;
   }

   public Artist getArtist(){
      return artist;
   }

   public java.util.Date getReleaseDate(){
      return releaseDate;
   }
   
   public String getLength(){
      return length;
   }
   
   public int getNumPlays(){
      return numPlays;
   }
   
   public int getNumLikes(){
      return numLikes;
   }
   
   // Mutators
   public void setName(String name){         
      this.name = name;
   }

   public void setArtist(Artist artist){
      this.artist = artist;
   }

   public void setReleaseDate(java.util.Date releaseDate){
      this.releaseDate = releaseDate;
   }
   
   public void setLength(String length){
      this.length = length;
   }
   
   public void setNumPlays(int numPlays){
      this.numPlays = numPlays;
   }
   
   public void setNumLikes(int numLikes){
      this.numLikes = numLikes;
   }
   
   /*
      Name : equals 
      Parameter: 
         other - Media object to compare to if it is equal
      Return : returns a boolean value for whether it is equal or not
      Description: an equals to method to check if the name of two medias is the same and if the creators are the same
      
   */
   public boolean equals(Media other){
      if(other != null && getName().equals(other.getName()) && getArtist().getUsername().equals(other.getArtist().getUsername())){
         return true;
      }
      return false;
   }
   
   /*
      Name: toString
      Parameters:N/A
      Return: a string containing information about media
      Description: returns string with name, artist, release date, time length, number of plays, and number of likes
   */
   public String toString(){
      return "Name: " + name + "\nArtist: " + artist.getUsername() + "\nRelease Date: " + releaseDate +
             "\nTime Length: " + length + "\nNumber of Plays: " + numPlays + "\nNumber of Likes: " + numLikes;
   }
   
   /*
      Name: addPlays
      Parameters:
         played - the media being played
      Return: void
      Description: add one to the number of plays of the media
   */
   public void addPlays(){ // ADD RECORDS
      numPlays++;
   }
   
   /*
      Name: addLikes
      Parameters:
         liked - the media being liked
      Return: void
      Description: add one to the number of likes of the media
   */
   public void addLikes(){ // ADD RECORDS
      numLikes++;
   }
   
   /*
      Name: removeLikes
      Parameters:
         unliked - the media being unliked
      Return: void
      Description: remove one from the number of likes of the media
   */
   public void removeLikes(){ // REMOVE RECORDS
      numLikes--;
   }
   
   /*
      Name: compareToLikes
      Parameters:
         other - the media being compared to the implicit media
      Return: an int representing the difference between the number of likes of the two media
      Description: returns a positive integer if the implicit has more likes than the explicit media
                   returns a negative integer if the implicit has less likes then the explicit media
                   returns zero when the two have the same number of likes
   */
   public int compareToLikes(Media other){
      return numLikes - other.getNumLikes();
   }
   
   /*
      Name: compareToPlays
      Parameters:
         other - the media being compared to the implicit media
      Return: an int representing the difference between the number of plays of the two media
      Description: returns a positive integer if the implicit has more plays than the explicit media
                   returns a negative integer if the implicit has less plays then the explicit media
                   returns zero when the two have the same number of plays
   */
   public int compareToPlays(Media other){
      return numPlays - other.getNumPlays();
   }
   
   /*
      Name: download
      Paramter:
         fileName - name of the file it is being downloaded to
      Return: N/A
      Description: a public method that writes out media info to a txt file
   */ 
   public void download(String fileName) { // WRITE RECORDS TO FILE
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
         out.write("Name: " + this.getName()); 
         out.write("\nArtist: " + this.getArtist().getUsername());
         out.write("\nRelease date: " + this.getReleaseDate());
         out.write("\nTime length: " + this.getLength());
         out.write("\nNumber of plays: " + this.getNumPlays());
         out.write("\nNumber of likes: " + this.getNumLikes());
         out.close();
         System.out.println(name + " is successfully downloaded"); // message to user indicating the success of the program
      } catch (IOException iox) {
         System.out.println("File name already used."); // error message to user indicating that the file name already exists and was used
      }
   }
}