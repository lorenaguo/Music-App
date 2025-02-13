/*
   File Name: PlaylistMusic.java
   Programmer: Katrina Jin
   Course: ICS4U1
   Date: 2023/6/6
   Description: A subclass of Music, representing a piece of music being added to a playlist by user  
*/

package media;

import java.util.Date; 
import user.*;
import media.*;
    
public class PlaylistMusic extends Music{  // Inheritance
   // Fields
     private Date dateAdded;               // the date the song was added to a playlist
     
   /*
      Name: PlaylistMusic
      Parameters:
         name - name of music
         creator - user that released music
         releaseDate - release date of music
         length - time length of music
      Return: N/A
      Description: initializes all fields of a PlaylistMusic object
   */
   public PlaylistMusic(String name, Artist artist, Date releaseDate, String length, Date dateAdded){
      super(name, artist, releaseDate, length);
      this.dateAdded = dateAdded;
   }
   
   // Accessors
   public Date getDateAdded(){
      return dateAdded;
   }
   
   /*
      Name: toString
      Parameters:N/A
      Return: a string containing information about the song being added to playlsit
      Description: returns string with name, artist, release date, time length, number of plays, number of likes, and the date it was added to the playlist
   */
   public String toString(){
      return "Name: " + name + "\nArtist: " + artist.getUsername() + "\nRelease Date: " + releaseDate + "\nTime Length: " + length + "\nDate Added: " + dateAdded;
   }
   
   
   
}