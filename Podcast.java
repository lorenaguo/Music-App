/*
   File Name: Podcast.java
   Programmer: Katrina Jin
   Course: ICS4U1
   Date: 2023/6/6
   Description: A subclass of Media, representing a podcast
*/
package media; // tanu was here fixing errors

import java.util.Date; 
import java.util.*;
import media.*;
import user.*;

public class Podcast extends Media{             // Inheritance
   // Fields         
   private String blurb;                        // a short intro about the podcast
   private ArrayList<Episode> episodes;         // an arraylist storing all the episodes the podcast has released     
   
   /*
      Name: Podcast
      Parameters:
         name - name of media
         artist - artist that released podcast
         releaseDate - release date of podcast
         length - average time length of the episodes of the podcast
         numEpisode - the number of episodes of the podcast
         blurb - a short introduction about the podcast
      Return: N/A
      Description: initializes all fields of a Podcast object
   */
   public Podcast(String name, Artist artist, Date releaseDate, String length, String blurb){
      super(name, artist, releaseDate, length);
      this.blurb = blurb;
      episodes = new ArrayList<Episode>();
   }
   
   /*
      Name: toString
      Parameters:N/A
      Return: a string containing information about the podcast
      Description: returns string with name, artist, release date, time length, number of plays, number of likes, number of episodes, and blurb
   */
   public String toString(){
      String info = "";
      info += super.toString() + "\nIntroduction: " + blurb;
      if (episodes.isEmpty()){ 
         info += "\n\nNo episodes released yet :((";
      }
      else {
         info += "\n\nAll Episodes:"; 
         for(int i = 0; i < episodes.size(); i++){
            info += "\n\nEpisode " + (i+1) + ":\n" + episodes.get(i).toString();
         }
      }
      return info;
   }
   
   // Accessors

   public String getBlurb(){
      return blurb;
   }
   
   public ArrayList<Episode> getEpisodes() {
      return episodes;
   }
   
   // Mutators 
   
   public void setBlurb(String blurb){
      this.blurb = blurb;
   }
   
   public void setEpisodes(ArrayList<Episode> e) {
      this.episodes = e;
   }
}