/*	
	File Name: PremiumUser.java
	Programmer:	T.	Shreya
	Class: ICS4U1
   School: A.Y. Jackson
	Date:	June 9, 2023
	Description: PremiumUser subclass for the musicApp program
                superclass for artist class
 */

package user;

import java.io.*;
import java.util.*;
import media.*;
import main.*;
 
public class PremiumUser extends User {
   
   /* Name: PremiumUser
      Paramter:
         user - name of the username 
         pass - password of the user
      Return: N/A
      Description: a constructor method for PremiumUser
   */
   public PremiumUser(String user, String pass, MusicApp app) { 
      super(user, pass, app);
   }
   
   /* Name: PremiumUser
      Paramter:
         user - name of the username 
         pass - password of the user
         followers - arraylist of followers
         following - arraylist of those user follows
         friends - arraylist of the users friends
         playlist - arraylist of playlists
         favourites - arraylist of liked media
         this - the MusicApp object that this user is in
      Return: N/A
      Description: a constructor method for PremiumUser when you switch from normal user to premium user and saves all parameters
   */  
   public PremiumUser(String user, String pass, ArrayList<User> followers, ArrayList<User> following, ArrayList<User> friends, ArrayList<Playlist> playlists, Playlist favourites, MusicApp app){
      super(user, pass, followers, following, friends, playlists, favourites, app);
      changePlaylistCreator();
   }
   
   /*
      Name:createPlayist
      Paramter: 
         name - a string name of the new playlist name
      Return: returns a boolean confirming whether playlist was made
      Description: a public method that creates a playlist
   */ 
   public boolean createPlaylist(String name) { // ADD RECORDS
      Playlist created = new Playlist(name, this);
      this.playlists.add(created);
      app.getAllPlaylists().add(created);
      return true;
   }
   
   /*
      Name: addMedia
      Paramter: 
         madia - a Media that will be added to the playlist
         selected - the Playlist that the media will be added to
         dateAdded - the date of when media was added to the playlist
      Return: N/A
      Description: a public method that adds media to a playlist
   */ 
   public void addMedia (Media media, Playlist selected, Date dateAdded) { // ADD RECORDS
      boolean match = false;
      if(this != selected.getCreator()){
         System.out.println("Couldn't add the media to playlist " + selected.getName() + " as it is not owned by " + this.username); // error message for when user tries adding media to another users playlist
      } else {
         // checks if the media is music
         if (media instanceof Music) { 
            for(int i = 0; i < selected.getMediaList().size(); i++){
               // checks if media is already in playlist
               if(media.equals(selected.getMediaList().get(i))){
                  match = true; 
               }
            }
            // proceeds with functions based on whether media is already in the playlist or not
            if (!match){
               PlaylistMusic pm = new PlaylistMusic(media.getName(), media.getArtist(), media.getReleaseDate(), media.getLength(), dateAdded);
               selected.getMediaList().add(pm);
               System.out.println("Added " + media.getName() + " to playlist " + selected.getName()); // message for user to see the success of the function
            }
            else{
               System.out.println(media.getName() + " already in playlist " + selected.getName()); // message for user to see where thinsg went wrong in the process
            }
         } else {
            System.out.println(media.getName() + " couldn't be added to " + selected.getName() + " because it is not a song."); // message for user to see where things went wrong in the process
         }
      
      }
   }
}