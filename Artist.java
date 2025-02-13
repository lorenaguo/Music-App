/*	
	File Name: Artist.java
	Name:	T.	Shreya
	Class: ICS4U1
   School: A.Y. Jackson
	Date:	June 9, 2023
	Description: PremiumUser subclass for the musicApp program
 */

package user; 
import java.util.*;
import media.*;
import main.*;

public class Artist extends PremiumUser {
   protected ArrayList<Media> mediaReleased; // an arraylist of the media that is released by each artist
   
   /*
      Name: Artist
      Paramter: 
         name - represents the username of the user
         password - represents the password of the user
      Return: N/A
      Description: constructor that initializes values for the username,
                   password, and the mediaReleased
   */
   public Artist(String name, String password, MusicApp app) {
      super(name, password, app);
      mediaReleased = new ArrayList<Media>();
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
   public Artist(String user, String pass, ArrayList<User> followers, ArrayList<User> following, ArrayList<User> friends, ArrayList<Playlist> playlists, Playlist favourites, MusicApp app) {
      super(user, pass, followers, following, friends, playlists, favourites, app);
      mediaReleased = new ArrayList<Media>();
      changePlaylistCreator();
   }
   
   /*
      Description: Accessor of the variable playlists
   */  
   public ArrayList<Media> getmediaReleased() {
      return mediaReleased;
   }
   
   /*
      Description: Mutator of the variable playlists
   */ 
   public void setMediaReleased(ArrayList<Media> mr){
      this.mediaReleased = mr;
   }  

   /*
      Name: releaseMusic
      Paramter: 
         name - represents the name of the music
         creator - artist that created the music
         releaseDate - Date object of date released 
         length - string value of the length of the song
      Return: N/A
      Description: public method that releases new music
   */
   public void releaseMusic(String name, Artist creator, Date releaseDate, String length) { // ADD RECORDS
      Music newMusic = new Music(name, creator, releaseDate, length);
      this.mediaReleased.add(newMusic);
      app.getMediaList().add(newMusic); 
   }

   /*
      Name: releaseAudiobook
      Paramter: 
         name - represents the name of the audiobook
         creator - artist that created the audiobook
         releaseDate - Date object of date released 
         length - string value of the length of the audiobook
         narrator - string name of who narrates the book
         bookSummary - string summary of the audiobook
      Return: N/A
      Description: public method that releases new audiobooks
   */
   public void releaseAudiobook(String name, Artist creator, Date releaseDate, String length, String narrator, String bookSummary) { // ADD RECORDS
      Audiobook newAudiobook = new Audiobook(name, creator, releaseDate, length, narrator, bookSummary);
      this.mediaReleased.add(newAudiobook);
      app.getMediaList().add(newAudiobook);
   } 

   /*
      Name: releasePodcast
      Paramter: 
         name - represents the name of the podcast
         creator - artist that created the podcast
         releaseDate - Date object of date released 
         length - string value of the length of the podcast
         blurb - string blurb of the podcast
      Return: N/A
      Description: public method that releases new podcasts
   */
   public void releasePodcast(String name, Artist creator, Date releaseDate, String length, String blurb) { // ADD RECORDS
      Podcast newPodcast = new Podcast(name, creator, releaseDate, length, blurb);
      this.mediaReleased.add(newPodcast);
      app.getMediaList().add(newPodcast);
   }  

   /*
      Name: releaseEpisode
      Paramter: 
         title - represents the name of the episode
         releaseDate - Date object of date released 
         length - string value of the length of the episode
         blurb - string blurb of the episode
         specified - podcast to add episode too
      Return: N/A
      Description: public method that releases new episodes
   */
   public void releaseEpisode(String name, Date releaseDate, String length, String blurb, Podcast specified) {  // ADD RECORDS
      Episode newEpisode = new Episode(name, releaseDate, length, blurb, specified);
      specified.getEpisodes().add(newEpisode);
   }
   

}