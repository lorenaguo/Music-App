/*
   File Name: Playlist.java
   Programmer: Lorena Guo
   Course: ICS4U1
   Date: June 5, 2023
   Description: This class contains methods and fields for Playlists
*/
package main;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Date;
import user.*;
import media.*;

public class Playlist{
   private String name;                   //name of playlist
   private User creator;                  //user that created the playlist
   private ArrayList<Media> mediaList;    //the list of media that the playlist holds
   
   /*
      Name: Playlist
      Parameters:
         name - chosen name of playlist
         creator - object of the user that created this playlist 
      Return: N/A
      Description: Constructor 
   */
   public Playlist(String name, User creator){
      this.name = name;
      this.creator = creator;
      mediaList = new ArrayList<Media>();  //automatically creates empty arrayList
   }
   
   //Accessor for name
   public String getName(){
      return name;
   }
   
   //Mutator for name
   public void setName(String name){
      this.name = name;
   }
   
   //Accessor for creator
   public User getCreator(){
      return creator;
   }
   
   //Mutator for creator
   public void setCreator(User creator){
      this.creator = creator;
   }
   
   //Accessor for mediaList
   public ArrayList<Media> getMediaList(){
      return mediaList;
   }
   
   //Mutator for mediaList
   public void setMediaList(ArrayList<Media> mediaList){
      this.mediaList = mediaList;
   }
   
   /* 
      Name: toString
      Parameter: N/A
      Return: String with all information about the playlist
      Description: returns string with name, creator, and all media in the playlist
   */
   public String toString(){
      String s = "Playlist Name: " + name + "\nCreator: " + creator.getUsername();
      
      if (mediaList.isEmpty()){ // output message for user if playlist is empty
         s += "\n\nPlaylist is empty";
      }
      else{
         for (int i = 1; i <= mediaList.size(); i++){  //adds toString of each object in mediaList
            s += "\n\nTrack " + i + ":\n" + mediaList.get(i-1);
         }
      }
      
      return s;
   }
   
   /*
      Name: add
      Parameters: media - Media object that is added to the playlist
      Return: N/A
      Description: adds a media to the playlist
   */
   public void add(Media media, Date dateAdded){                             
      PlaylistMusic copy = new PlaylistMusic(media.getName(), media.getArtist(), media.getReleaseDate(), media.getLength(), dateAdded);  
      mediaList.add(copy);
   }
   
   /*
      Name: remove
      Parameters: media - Media object that is removed from the playlist
      Return: N/A
      Description: removes a media from the playlist if it exists
   */
   public void remove(Media media){
      mediaList.remove(media);
   }
   
   /*
      Name: shuffle
      Parameters: N/A
      Return: N/A 
      Description : prints out the number of ways a playlist can be shuffled and then shuffles
                    the playlist randomly
   */
   public void shuffle(){ // wrapper method for shuffle
      //calls factorial method to find number of ways playlist can be shuffled
      int num = factorial(mediaList.size());
      System.out.println("This playlist can shuffled in " + num + " ways.\n"); 
      
      //create a copy of the current media list
      ArrayList<Media> copy = new ArrayList<Media>();
      copy.addAll(mediaList);

      // empties media list
      mediaList.clear();
      
      //calls shuffle method
      shuffle(copy);
   }
   
   /*
      Name: factorial
      Paramters : 
         n - an int value used in determining the factorial
      Return : returns the int value of the factorial of the number received
      Description: a private method that figures out the number of ways you can order a list of n items
   */
   private int factorial(int n){ // RECURSION
      // base case 
      if (n == 0){
         return 1;
      }
      else{
         return n * factorial(n - 1);
      }
   }
   
   /*
      Name: shuffle
      Parameter: 
         copy - arraylist copy of the media list before shuffling
      Return: N/A
      Description: Uses recursion to randomize the order of a playlist
   */
   private void shuffle(ArrayList<Media> copy){ // RECURSION
      // base case is when copy is empty
      if (!copy.isEmpty()){
         // find random number within 0 and the length of the media list
         int ran = (int)(Math.random() * copy.size());  
         //add a random song from copy to media list
         mediaList.add(copy.get(ran));
         //remove the song that was added from copy so it won't be added twice
         copy.remove(ran);
         
         shuffle(copy);
      }
   }

   /*
      Name: sortByArtist
      Parameters: N/A
      Return: N/A
      Description: sorts Playlist by artist name in alphabetical order      
      *uses selection sort 
   */
   public void sortByArtist(){ // SORT RECORDS BY ARTIST (ONE LEVEL)
      int size = mediaList.size();
      for (int lowerbound = 0; lowerbound < size - 1; lowerbound++){
         int minIndex = size - 1;
         for (int i = lowerbound; i < size; i++){
            if (mediaList.get(i).getArtist().getUsername().compareTo(mediaList.get(minIndex).getArtist().getUsername()) < 0){
               minIndex = i;
            }
         }
         Media temp = mediaList.get(lowerbound);
         mediaList.set(lowerbound, mediaList.get(minIndex));
         mediaList.set(minIndex, temp);
      }
   }
   
   /*
      Name: sortByArtistAndDate
      Parameters: N/A
      Return: N/A
      Description: sorts Playlist by artist name in alphabetical order and most recent date added      
      *uses bubble sort 
   */ 
   public void sortByArtistAndDate(){ // SORT RECORDS BY ARTIST AND DATE ADDED (TWO LEVELS)
      boolean sorted = false;
   	for (int upperbound = mediaList.size() - 1; upperbound > 0 && !sorted; upperbound--){
   		sorted = true;
   		for (int i = 0; i < upperbound; i++){
            int comparison = mediaList.get(i).getArtist().getUsername().compareTo(mediaList.get(i + 1).getArtist().getUsername());
   			if (comparison > 0 || (comparison == 0 && (((PlaylistMusic)(mediaList.get(i))).getDateAdded().compareTo(((PlaylistMusic)(mediaList.get(i + 1))).getDateAdded())) > 0)){
               Media temp = mediaList.get(i);
               mediaList.set(i, mediaList.get(i + 1));
               mediaList.set(i + 1, temp);
               sorted = false;
            }
         }
      }
   }
   
   /*
      Name: PlaylistMusic
      Parameters: 
         toSearch - the media object to be searched in playlist
      Return: the playlistmusic found
      Description: searches for the playlistmusic that matches the given media 
   */
   public PlaylistMusic searchInPlaylist(Media toSearch){ //  SEARCH RECORDS 
      for(int i = 0; i < mediaList.size(); i++){
         if (toSearch.getName().equals(mediaList.get(i).getName()) && toSearch.getArtist().getUsername().equals(mediaList.get(i).getArtist().getUsername())){
            
            return (PlaylistMusic)mediaList.get(i); 
         }
      }
      return null;
   }
}