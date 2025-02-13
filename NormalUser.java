/*
   File Name: NormalUser.java
   Programmer: Katrina Jin
   Course: ICS4U1
   Date: 2023/6/9
   Description: A subclass of user, representing a normal user 
*/

package user;
import user.*;
import media.*;
import main.*;
import java.util.Date;

public class NormalUser extends User{ // Inheritance
   // Fields
   private static int MAX_PLAYLISTS = 3;                // every normal user can have maximum 3 playlists
   private static int MAX_SONGS = 20;                    // every normal user can have maximum 20 songs in each playlist
   
   /*
      Name:NormalUser
      Parameters:
         user - username of the user
         pass - password of the user
         app - the MusicApp that the user is a part of 
      Return: N/A
      Description: Constructor of the NormalUser just calls super
   */
   public NormalUser(String user, String pass, MusicApp app) {
      super(user, pass, app);
   }
   
   /*
      Name: createPlaylist
      Parameters:
         name - name of playlist the user wants to use
      Return: true or false the playlist was created successfully
      Description: creates a new playlist for user
   */
   public boolean createPlaylist(String name){
      if(playlists.size() < MAX_PLAYLISTS){
         Playlist created = new Playlist(name, this);
         playlists.add(created); 
         app.getAllPlaylists().add(created); 
         return true;
      }
      return false;
   }
   
    /*
      Name: addMedia
      Parameters:
         media - the media object user wants to add to playlist
         selected - the playlist user chooses to add the media to
      Return: true or false the media was added successfully
      Description: adds a media to selected playlist for user
   */
   public void addMedia(Media media, Playlist selected, Date dateAdded){  // ADD RECORDS
      boolean found = false;
      if(this != selected.getCreator()){
         System.out.println("Couldn't add the media to playlist " + selected.getName() + " as it is not owned by " + this.username); // error message when user tries to add media to someone elses playlist
      } 
      else {
         // checks if number of songs in playlist is within limit and if the media to add is music
         if (selected.getMediaList().size() < MAX_SONGS && media instanceof Music) { 
            for(int i = 0; i < selected.getMediaList().size(); i++){
               if(media.equals(selected.getMediaList().get(i))){
                  found = true; 
               }
            }
            // proceeds with functions based on whether media is already in the playlist
            if (!found){
               PlaylistMusic pm = new PlaylistMusic(media.getName(), media.getArtist(), media.getReleaseDate(), media.getLength(), dateAdded);
               selected.getMediaList().add(pm);
               System.out.println("Added " + media.getName() + " to playlist " + selected.getName());
            }
            else{
               System.out.println(media.getName() + " already in playlist " + selected.getName());
            }
         } 
         else {
            System.out.println(media.getName() + " couldn't be added to " + selected.getName() + " because MAX songs already reached.");    
         }  
      }
   } 
}