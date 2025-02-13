/*
   File Name: Episode.java
   Programmer: Katrina Jin
   Course: ICS4U1
   Date: 2023/6/7
   Description: represents a single episode of a podcast
*/
package media;

import java.util.Date;
import user.*;
import main.*;

public class Episode extends Media{
   // Fields
   //private String title;                 // name of the episode
   //private Date releaseDate;             // release date of the episode // CHECK DO WE NEED THIS
   //private String length;                // time length of the episode // OR THIS
   private String blurb;                 // a short intro about the episodes
   private Podcast podcast;              // the podcast the episode belongs to
   
    /*
      Name: Episode
      Parameters:
         title - title of episode
         releaseDate - release date of episode
         length - time length of episode
         blurb - a short introduction about the episode
      Return: N/A
      Description: initializes all fields of an Episode object
   */

   public Episode(String title, Date releaseDate, String length, String blurb, Podcast podcast){
      super(title, podcast.getArtist(), releaseDate, length);
      this.blurb = blurb;
      this.podcast = podcast;
      podcast.setLength(this.updatePodcastLength(length));
   }
   
   // Accessors
   public Podcast getPodcast(){
      return podcast;
   }
   
   /*
      Name: toString
      Parameters:N/A
      Return: a string containing information about the episode
      Description: returns string with title, release date, time length, and blurb
   */
   public String toString(){
      return "Title: " + name + "\nRelease date: " + releaseDate + "\nTime length: " + length + "\nEpisode blurb: " + blurb;
   }
   
   /*
      Name: updatePodcastLength
      Parameter: 
         l - the string length of the podcast
      Return: returns the string value of the new length
      Description : method that takes in the length of the podcats and the episodes and returns the sum total length
   */
   private String updatePodcastLength(String l){
      String temp = podcast.getLength(); 
      String[] podcastL = temp.split(":");
      String[] episodeL = l.split(":");
      
      int[] podcastLength = new int[podcastL.length];
      int[] episodeLength = new int[episodeL.length];
      
      for(int i = 0; i < podcastL.length; i++){
         podcastLength[i] = Integer.parseInt(podcastL[i]);
      }
      
      for(int i = 0; i < episodeL.length; i++){
         episodeLength[i] = Integer.parseInt(episodeL[i]);
      }
      
      // First adding the hours, minutes, and seconds seperately
      if(episodeLength.length == 2 && podcastLength.length > 2){
         for(int i = 0; i < episodeLength.length; i++){
            podcastLength[i+1] += episodeLength[i];
         }
      }
      else if(episodeL.length == 1){
         podcastLength[podcastLength.length - 1] += episodeLength[0];
      }
      else{
         for(int i = 0; i < episodeLength.length; i++){
         
            podcastLength[i] += episodeLength[i];
         }
      }
      
      // adjusts the time
      for(int i = podcastLength.length-1; i > 0; i--){
         if(podcastLength[i] >= 60){
            podcastLength[i] -= 60;
            podcastLength[i-1] += 1;
         }                  
      }
      
      String newPodcastLength = "";
      
      for(int i = 0; i < podcastLength.length; i++){ 
         newPodcastLength += podcastLength[i];
         if (i != podcastLength.length - 1){
            newPodcastLength += ":"; 
         }
      }  
      return newPodcastLength;
   }
   
}