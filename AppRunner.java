/*
   File Name: AppRunner.java
   Programmer: Lorena Guo
   Course: ICS4U1
   Date: June 9, 2023
   Description: The user interacts with this class, which stores an MusicApp object
*/
package main;
import java.util.*;
import java.io.*;
import java.text.*;
import user.*;
import media.*;

public class AppRunner{
   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      MusicApp app = new MusicApp();                                 //stores the musicApp
      User thisUser;                                                 // stores the user that logs in
      final String CONFIG_FOLDER = "config";                         // folder with the text files to read in from
      
      // friendly user interface menu to introduce them to our program
      System.out.println("-------------------------------------------------------------------------");
      System.out.println("|                          Welcome to KALOTA!                           |");
      System.out.println("|        \"We thank you for being one of the first user testers          |");
      System.out.println("|             of the beta version of our software KALOTA\"               |");
      System.out.println("|                   - From your software developers,                    |");
      System.out.println("|                     Katrina J., Lorena G., Tanu S.                    |");
      System.out.println("|                                                                       |");
      System.out.println("|        There are three accounts you can make:                         |");
      System.out.println("|        Normal User: max 3 playlists, 20 songs per playlist            |");
      System.out.println("|        Premium User: umlimited songs, playlists, free download        |");
      System.out.println("|        Artist: same as premium users, can release media               |");
      System.out.println("-------------------------------------------------------------------------");
   
      String function = "";
      //menu continues prompting user until they exit the app
      while (!function.equals("0")){
         //main menu with functions to choose
         System.out.println("\nMenu:");
         System.out.println("0. Exit");
         System.out.println("1. Load Userlist");
         System.out.println("2. Load Medialist");
         System.out.println("3. Log-in");
         System.out.println("4. Sign-up");
                  
         System.out.println("\nEnter: ");
         function = sc.nextLine(); 
         
         //load userlist
         if (function.equals("1")){
            System.out.println("Enter name of file: ");
            String fileName = sc.nextLine();
            //System.out.println("XXX");
            app.loadUserList(CONFIG_FOLDER + "/" + fileName); // we have txt files in config folder so they will be read from them
         }
         //load medialist
         else if (function.equals("2")){
            System.out.println("Enter name of file: ");
            String fileName = sc.nextLine();
         
            app.loadMediaList(CONFIG_FOLDER + "/" + fileName); // we have txt files in config folder so they will be read from them
         }
         //log in
         else if (function.equals("3")){
            System.out.println("Username:"); 
            String username = sc.nextLine(); 
            System.out.println("Password:");
            String password = sc.nextLine();
            
            thisUser = app.logIn(username, password);  //saves user object in a variable 
            if (thisUser == null){  //logIn method returns null if user can not be found 
               System.out.println("Username or password incorrect");
            }
            else{
               String function2 = ""; 
               //user menu prompts user until they choose to sign out
               while (!function2.equals("0")){
                  //user menu with functions to choose
                  System.out.println("\nUser Menu:");
                  System.out.println("0. Sign-out");
                  System.out.println("1. Change username");
                  System.out.println("2. Change password");
                  System.out.println("3. Create playlist");
                  System.out.println("4. Search for media");
                  System.out.println("5. Search for user");
                  System.out.println("6. Search for playlist");
                  System.out.println("7. View favourites");
                  System.out.println("8. Kalota Stats");
                  if (thisUser instanceof Artist){  //only print out release media option if user is an artist
                     System.out.println("9. Release media");
                  }   
                  else{  //print out become an artist option if the user isn't one
                     System.out.println("9. Become an artist");
                  }
                  if (thisUser instanceof NormalUser){  //print out become premium option if the user is normal  
                     System.out.println("10. Become premium");
                  }
                  
                  System.out.println("\nEnter: ");
                  function2 = sc.nextLine();
                  
                  //change username
                  if (function2.equals("1")){  
                     System.out.println("Enter new username: ");
                     String newUsername = sc.nextLine();
                     boolean result = newUsername.matches("[a-zA-Z]+"); 
                     // validating username to make sure it has no non-alphabet characters
                     if (!result) { 
                        System.out.println("Username must contain only the alphabet and have no spaces."); 
                     } else {
                        String oldUsername = thisUser.getUsername();
                        boolean success = thisUser.changeUsername(newUsername);
                        // checking if username already exists and lets the user know whether their username changed or now
                        if (!success){
                           System.out.println(newUsername + " is already taken."); 
                        } else {
                           System.out.println("Old username " + oldUsername + " is successfully changed to: " + newUsername + ".");
                        }
                     }
                  }
                  
                  //change password
                  else if (function2.equals("2")){
                     int limit = User.getPasswordLimit();
                     System.out.println("Enter new password (must be " +  limit + " characters or less): "); // lets user know the requirements for the new password
                     String newPassword = sc.nextLine();
                     
                     boolean success = thisUser.changePassword(newPassword);
                     // checks the success of the change and lets the user know
                     if (!success){
                        System.out.println("Password must be " +  limit + " characters or less or you entered the same password."); 
                     } else {
                        System.out.println("Password changed successfully.");
                     } 
                  }
                  
                  //create playlist
                  else if (function2.equals("3")){
                     System.out.println("Enter name of playlist: ");
                     String playlistName = sc.nextLine();
                     
                     boolean success = thisUser.createPlaylist(playlistName);
                     // checks the success of creating a playlist and lets user know what the outcome is 
                     if (!success){
                        System.out.println("MAX # of playlists already reached on your account");
                     }
                     else{
                        System.out.println(playlistName + " has been created");
                     }
                  }
                  
                  //search for media
                  else if (function2.equals("4")){
                     System.out.println("Name of media:"); 
                     String mediaName = sc.nextLine(); 
                     System.out.println("Artist of media (or N/A):");
                     String artistName = sc.nextLine();
                     
                     Media thisMedia = null;
                     // checks user input and proceeds to the correct searching method
                     if (artistName.equals("N/A")){
                        thisMedia = app.searchMedia(mediaName);
                     }
                     else{
                        thisMedia = app.searchMedia(mediaName, artistName);
                     }
                     // checks for whether media was and prints out results based on that
                     if (thisMedia == null){
                        System.out.println(mediaName + " could not be found");
                     }
                     else{
                        System.out.println("\n" + thisMedia);
                        
                        String function3 = "";
                        // sub menu for user to make further decisions
                        while (!function3.equals("0")){
                           System.out.println("\n0. Back to user menu");
                           System.out.println("1. Play");
                           Playlist tempP = thisUser.getFavourites();
                           ArrayList<Media> tempM;
                           if (tempP != null) {
                              tempM = tempP.getMediaList();   
                              if (tempM.contains(thisMedia)) {  //provide option to unlike if user liked it already
                                 System.out.println("2. Unlike");
                              }
                              else{ 
                                 System.out.println("2. Like");
                              }
                           } else {
                              System.out.println("2. Like");
                           }
                           if (thisMedia instanceof Music){  //only songs can be added/removed from a playlist
                              System.out.println("3. Add to playlist");
                              System.out.println("4. Remove from playlist");
                           }
                           if (thisUser instanceof PremiumUser){
                              System.out.println("*. Download");
                           }
                                                      
                           System.out.println("\nEnter:");
                           function3 = sc.nextLine();
                           
                           //play media
                           if (function3.equals("1")){
                              //user needs to specify which episode they are playing if media is a podcast
                              if (thisMedia instanceof Podcast){
                                 // checks if the podcast has episodes yet
                                 if (!((Podcast)thisMedia).getEpisodes().isEmpty()) {
                                    System.out.println("Enter episode number: ");
                                    // handling an input mismatch exception when we ask for an episode number and they provide another kind of input
                                    try {
                                       int index = sc.nextInt();  
                                       Podcast thisPodcast = (Podcast)thisMedia;                                    
                                       thisUser.playMedia(thisPodcast.getEpisodes().get(index - 1));  //plays episode specified by user //
                                       thisUser.playMedia(thisPodcast);  //adds numPlays to podcast as well
                                    } catch (InputMismatchException imx) {
                                       System.out.println("Please input an integer.");
                                    }
                                 
                                 }
                                 else{
                                    System.out.println("Can't be played because there are no episodes released yet.");
                                 }
                              }
                              else{
                                 thisUser.playMedia(thisMedia);
                              }
                           }
                           
                           //like/unlike media
                           else if (function3.equals("2")){
                              tempP = thisUser.getFavourites(); 
                              tempM = tempP.getMediaList();    
                              if (tempM.contains(thisMedia)){  //unlikes media if media is already liked by user 
                                 thisUser.unlikeMedia(thisMedia);
                              }
                              else{  //likes media is media wasn't liked yet
                                 thisUser.likeMedia(thisMedia); 
                              } 
                           }
                           
                           //add media to playlist
                           else if (function3.equals("3")){
                              System.out.println("Enter name of playlist to add to: ");
                              String playlistName = sc.nextLine();
                              Playlist thisPlaylist = app.searchPlaylist(playlistName);  //finds playlist object
                              
                              if (thisPlaylist != null){
                                 try{  //try catch for parsing string into date
                                    System.out.println("Enter date added (yyyy-mm-dd): ");
                                    Date dateAdded = format.parse(sc.nextLine());   
                                    
                                    if (thisPlaylist == null){  //search method returns null if playlist doesn't exist
                                       System.out.println(playlistName + " could not be found");
                                    }
                                    else{
                                       thisUser.addMedia(thisMedia, thisPlaylist, dateAdded);  //adds media to playlist, returns false if it could not be added
                                         //System.out.println("MAX # of media reached or already added");
                                       
                                    }
                                 } catch(ParseException px){
                                    System.out.println("Enter date with yyyy-mm-dd format");
                                 }
                              }
                              else{
                                 System.out.println("Playlist can not be found");
                              }
                           }
                           
                           //remove media from playlist
                           else if (function3.equals("4")){
                              System.out.println("Enter name of playlist to remove from: ");
                              String playlistName = sc.nextLine();
                              Playlist thisPlaylist = app.searchPlaylist(playlistName);  //finds playlist object 
                              if (thisPlaylist != null){
                                 thisUser.removeMedia(thisMedia, thisPlaylist);
                              }
                              else{
                                 System.out.println("Playlist can not be found");
                              }
                           }
                           
                           //download media through file output
                           else if (function3.equals("*")){
                              System.out.println("Enter file name of download: ");
                              String fileName = sc.nextLine();
                              thisMedia.download(fileName);
                           }
                           
                           else if(!function3.equals("0")){
                              System.out.println("Please enter a valid function");
                           }
                        }
                     }
                  }
                  
                  //search for username
                  else if (function2.equals("5")){
                     System.out.println("Username:"); 
                     String newUsername = sc.nextLine(); 
                     User otherUser = app.searchUser(newUsername);  //stores user object with the username
                     
                     if (otherUser == null){  //search method returns null if user doesn't exist
                        System.out.println(newUsername + " could not be found");
                     }
                     else{
                        System.out.println(otherUser);
                        
                        // sub menu to direct the users next steps
                        System.out.println("\n0. Back to user menu");
                        if (thisUser.getFollowing().contains(otherUser)){
                           System.out.println("1. Unfollow");
                        }
                        else{
                           System.out.println("1. Follow");
                        }
                           
                        System.out.println("\nEnter:");
                        String function3 = sc.nextLine();
                        
                        // checks for user input and either follows or unfollows the other user   
                        if (function3.equals("1")){
                           if (thisUser.getFollowing().contains(otherUser)){
                              thisUser.unfollowUser(otherUser);
                           }
                           else{
                              thisUser.followUser(otherUser);
                           }
                        }
                        else if (!function3.equals("0")){ // tells user what went wrong when they enter something not out of the applicable options
                           System.out.println("Please enter a valid function");
                        }
                     }
                  }
                  
                  //search for playlist
                  else if (function2.equals("6")){
                     System.out.println("Playlist Name:"); 
                     String playlistName = sc.nextLine(); 
                     
                     Playlist thisPlaylist = app.searchPlaylist(playlistName);
                     // checks whether playlist could be found
                     if (thisPlaylist == null){
                        System.out.println(playlistName + " could not be found");
                     }
                     else{
                        System.out.println(thisPlaylist);
                        
                        // prints out a sub menu if the playlist belongs to the user
                        if(thisPlaylist.getCreator() == thisUser){
                           String function3 = "";
                           
                           // sub menu for the user to use on their playlist
                           while(!function3.equals("0")){
                              System.out.println("\n0. Back to user menu");
                              System.out.println("1. Shuffle");
                              System.out.println("2. Sort by artist");
                              if (!thisPlaylist.getName().equals("favourites")){  //favourites playlist can't be sorted by date added since it doesn't contain playlist music
                                 System.out.println("3. Sort by artist and date added");
                              }
                              
                              System.out.println("\nEnter:");
                              function3 = sc.nextLine();
                             
                              if(function3.equals("1")){
                                 thisPlaylist.shuffle();
                                 System.out.println(thisPlaylist);
                              }
                              else if(function3.equals("2")){
                                 thisPlaylist.sortByArtist();
                                 System.out.println(thisPlaylist);
                              }
                              else if(function3.equals("3")){
                                 thisPlaylist.sortByArtistAndDate();
                                 System.out.println(thisPlaylist);
                              }
                              else if(!function3.equals("0")){
                                 System.out.println("Please enter a valid function");
                              }
                           }
                        }
                     }
                  }
                  
                  //view favourites playlist
                  else if (function2.equals("7")){
                     System.out.println(thisUser.getFavourites());
                  }
                  
                  //kalota stats 
                  else if (function2.equals("8")){
                     //print out menu with functions that provide statistics on program
                     System.out.println("1. Find most liked media");
                     System.out.println("2. Find most played media");
                     System.out.println("3. Find most popular artist");
                     
                     System.out.println("\nEnter:");
                     String function3 = sc.nextLine();
                     
                     if (function3.equals("1")){
                        Media mostLiked = app.findMostLikedMedia(); // POLYMORPHISM : most liked media could be any kind of media and is stored in a media object
                        // checks if there is any value in most liked media and outputs a message if there isn't anything
                        if (mostLiked == null){
                           System.out.println("No media has been released in this app");
                        }
                        else{
                           System.out.println(mostLiked);
                        }
                     }
                     else if (function3.equals("2")){
                        Media mostPlayed = app.findMostPlayedMedia();
                        // checks if there is any value in most played media and outputs a message if there isn't anything
                        if (mostPlayed == null){
                           System.out.println("No media has been released in this app");
                        }
                        else{
                           System.out.println(mostPlayed);
                        }
                     }
                     else if (function3.equals("3")){
                        Artist mostPopular = app.findMostPopularArtist();
                        // checks if there is any value in most played media and outputs a message if there isn't anything
                        if (mostPopular == null){
                           System.out.println("No artists exist in this app");
                        }
                        else{
                           System.out.println(mostPopular);
                        }
                     }
                     else{
                        System.out.println("Please enter a valid function");
                     }
                  }
                  
                  //release media or become artist
                  else if (function2.equals("9")){
                     //release media if the user is an artist
                     if (thisUser instanceof Artist){
                        //type cast the user to artist
                        Artist thisArtist = (Artist)thisUser;
                        //menu with options to release media
                        System.out.println("1. Release music");
                        System.out.println("2. Release audiobook");
                        System.out.println("3. Release podcast");
                        System.out.println("4. Release podcast episode");
                        
                        System.out.println("\nEnter:");
                        String function3 = sc.nextLine();
                        
                        //release music
                        if (function3.equals("1")){
                           System.out.println("Enter name: ");
                           String name = sc.nextLine();
                           try{  //try catch for parsing string into date
                              System.out.println("Enter release date (yyyy-mm-dd): ");
                              Date releaseDate = format.parse(sc.nextLine()); 
                              System.out.println("Enter length: ");
                              String length = sc.nextLine();
                              
                              thisArtist.releaseMusic(name, thisArtist, releaseDate, length);
                           } catch(ParseException px){
                              System.out.println("Enter date with yyyy-mm-dd format");
                           }
                        }
                        
                        //release audiobook
                        else if (function3.equals("2")){
                           System.out.println("Enter name: ");
                           String name = sc.nextLine();
                           try{  //try catch for parsing string to date
                              System.out.println("Enter releaseDate (yyyy-mm-dd): ");                        
                              Date releaseDate = format.parse(sc.nextLine()); 
                              System.out.println("Enter length: ");
                              String length = sc.nextLine();
                              System.out.println("Enter narrator name: ");
                              String narrator = sc.nextLine();
                              System.out.println("Enter book summary: ");
                              String bookSummary = sc.nextLine();
                              
                              thisArtist.releaseAudiobook(name, thisArtist, releaseDate, length, narrator, bookSummary);
                           } catch(ParseException px){
                              System.out.println("Enter date with yyyy-mm-dd format");
                           }
                        }
                        
                        //release podcast
                        else if (function3.equals("3")){
                           System.out.println("Enter name: ");
                           String name = sc.nextLine();
                           try{  //try catch for parsing string into date
                              System.out.println("Enter releaseDate (yyyy-mm-dd): ");                        
                              Date releaseDate = format.parse(sc.nextLine()); 
                              System.out.println("Enter blurb: ");
                              String blurb = sc.nextLine();
                              
                              thisArtist.releasePodcast(name, thisArtist, releaseDate, "00:00", blurb); 
                           } catch(ParseException px){
                              System.out.println("Enter date with yyyy-mm-dd format");
                           }
                        }
                        
                        //release episode in a podcast
                        else if (function3.equals("4")){
                           System.out.println("Enter name of podcast: ");
                           String podcastName = sc.nextLine();
                           
                           Podcast podcast = (Podcast)app.searchMedia(podcastName, thisArtist.getUsername());  //check if podcast exists and belongs to this artist
                           // checks if the podcast exists for the episode to be placed in it
                           if (podcast == null){
                              System.out.println("Podcast can not be found or not owned by this user");
                           }
                           else{
                              //allow episodes to be released if the podcast is found
                              System.out.println("Enter episode title ");
                              String title = sc.nextLine();
                           
                              try{  //try catch for parsing string into date
                                 System.out.println("Enter releaseDate (yyyy-mm-dd): ");                        
                                 String date = sc.nextLine();
                              
                                 Date releaseDate = format.parse(date); 
                                 
                                 System.out.println("Enter length: ");
                                 String length = sc.nextLine(); 
                              
                                 System.out.println("Enter blurb: ");
                                 String blurb = sc.nextLine();
                              
                                 thisArtist.releaseEpisode(title, releaseDate, length, blurb, podcast);
                                 
                              }catch(ParseException px){
                                 System.out.println("Enter date with yyyy-mm-dd format");
                              }
                           }
                        }
                        else if(!function3.equals("0")){
                           System.out.println("Please enter a valid function");
                        }
                     }
                     //other option for function 9 is becoming an artist
                     else{
                        User becameArtist = app.becomeArtist(thisUser);       
                        thisUser = becameArtist; 
                     }
                  }
                  else if (function2.equals("10")){
                     User gonePremium = app.goPremium(thisUser);
                     thisUser = gonePremium;
                  }
                  else if (!function2.equals("0")){
                     System.out.println("Please enter a valid function");
                  }
               }
            }
         }
         //sign up
         else if (function.equals("4")){
            app.signUp();
         }    
         else if (!function.equals("0")){
            System.out.println("Please enter a valid function");
         }
      }
      // exit message for the user
      System.out.println("Goodbye!");
   }
}