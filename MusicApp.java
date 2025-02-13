/*
   File Name: MusicApp.java
   Programmer: Lorena Guo
   Course: ICS4U1
   Date: June 7, 2023
   Description: This program contains methods that organize and manipulate the Media, User, and Playlist classes
*/
package main;
import java.util.*;
import java.io.*;
import java.text.*;
import media.*;
import user.*;

public class MusicApp{
   private ArrayList<Media> mediaList;             //stores all media in the app
   private ArrayList<User> userList;               //stores all users that use the app
   private ArrayList<Playlist> allPlaylists;       //stores all the playlists made in the app
   
   /*
      Name: MusicApp
      Parameters: N/A
      Return: N/A
      Description: Constructor that initalizes all fields to be empty
   */
   public MusicApp(){
      mediaList = new ArrayList<Media>();
      userList = new ArrayList<User>();
      allPlaylists = new ArrayList<Playlist>();
   }
   
   //Accessor for mediaList
   public ArrayList<Media> getMediaList(){
      return mediaList;
   }
   
   //Mutator for mediaList
   public void setMediaList(ArrayList<Media> mediaList){
      this.mediaList = mediaList;
   }
   
   //Accessor for userList
   public ArrayList<User> getUserList(){
      return userList;
   }
   
   //Mutator for userList
   public void setUserList(ArrayList<User> userList){
      this.userList = userList;
   }

   //Accessor for allPlaylists
   public ArrayList<Playlist> getAllPlaylists(){
      return allPlaylists;
   }
   
   //Mutator for allPlaylists
   public void setallPlaylists(ArrayList<Playlist> allPlaylists){
      this.allPlaylists = allPlaylists;
   }
   
   /*
      Name: loadUserList
      Parameters: fileName - String with the name of the file to be read
      Return: N/A
      Description: adds User objects found in a file to userList
   */
   public void loadUserList(String fileName){ // LOAD RECORDS FROM FILE
      String userType;
      String username;
      String password;
      try { // try catch for IOException when reading infromation in for user
         BufferedReader in = new BufferedReader(new FileReader(fileName)); 
         int num = Integer.parseInt(in.readLine()); //reads number of users in file
         for (int i = 0; i < num; i++){  //loop to separate information for different user 
            userType = in.readLine();
            username = in.readLine();
            username = username.toLowerCase(); // case insensitivity
            boolean result = username.matches("[a-zA-Z]+"); // checks that the username has no non-alphabet characters
            
            // checks that username doesn't already exist
            if (searchUser(username) == null){
               password = in.readLine();
               // checks for what kind of user it will store the information as 
               if (userType.equals("normal")){ 
                  NormalUser user = new NormalUser(username, password, this);
                  if (result) { // test
                     userList.add(user);
                  }   
               }
               else if (userType.equals("premium")){
                  PremiumUser user = new PremiumUser(username, password, this);
                  if (result) { // test
                     userList.add(user);
                  }
               }
               else if (userType.equals("artist")){
                  Artist user = new Artist(username, password, this);
                  if (result) { // test
                     userList.add(user);
                  }
               }
            }
            // error message for when the username has non-alphabet characters
            if (!result) {
               System.out.println("Could not add " + username + " because it conatins non-alphabet characters.");
            }
         }
         in.close();
         sortUserList();
         System.out.println(fileName + " has been read."); // message for user to see that file has been read successfully
      } catch (IOException iox){
         System.out.println("Error reading file :("); 
      }
   }

   /*
      Name: loadMediaList
      Parameters: fileName - String with the name of the file to be read
      Return: N/A
      Description: adds Media objects found in a file to mediaList
   */
   public void loadMediaList(String fileName){ // LOAD RECORDS FROM FILE
      try{ // try catch for IOException when reading information in for media and parse exception when parsing into date format
         BufferedReader in = new BufferedReader(new FileReader(fileName));
        
         int num = Integer.parseInt(in.readLine()); //reads number of media in file
         for (int i = 0; i < num; i++){  //loop to separate information for different media 
            String mediaType = in.readLine();
            String name = in.readLine();
            String username = in.readLine();
            String release = in.readLine();
            String length = in.readLine();
            Artist artist = (Artist)searchUser(username);  //find artist object based on the username
            // checks to see that artist exists
            if (artist != null){
               SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
               Date releaseDate = format.parse(release);  
               
               // checks to see what kind of media object to store information in
               if (mediaType.equals("music")){ 
                  if (artist != null){
                     Music media = new Music(name, artist, releaseDate, length);
                     mediaList.add(media);
                  }
               }
               else if (mediaType.equals("audiobook")){
                  String narrator = in.readLine();
                  String bookSummary = in.readLine(); 
                  if (artist != null){
                     Audiobook media = new Audiobook(name, artist, releaseDate, length, narrator, bookSummary);
                     mediaList.add(media);
                  }
               }
               else if (mediaType.equals("podcast")){
                  String intro = in.readLine();
                  if (artist != null){
                     Podcast media = new Podcast(name, artist, releaseDate, length, intro);
                     mediaList.add(media);
                  }
               }
            }
         }
         System.out.println(fileName + " has been read."); // message for user to see that the action was successful
      } catch (IOException iox){
         System.out.println("Error reading file :("); 
      } catch (ParseException px){
         System.out.println("Date not formatted correctly."); 
      }
   }
   
   /*
      Name: logIn
      Parameters: 
         username - String with the username of the User selected
         password - String with the password inputted
      Return: User - object that matches the username and password
      Description: Allows users to access a specific User object if the username and password matches. Returns null if User doesn't exist or password is incorrect.
   */
   public User logIn(String username, String password){
      username = username.toLowerCase(); // case insensitive turns username to all lower case
      User chosen = searchUser(username);
      //checks if searchUser method returns null (user not found)
      if (chosen != null && chosen.getPassword().equals(password)){ 
         return chosen;
      }
      else{
         return null;
      }
   }
   
   /*
      Name: signUp
      Parameters: N/A
      Return: N/A
      Description: Creates new User object based on user input of username and password
   */
   public void signUp(){   
      Scanner sc = new Scanner(System.in);
      System.out.println("Types of users: Normal, Premium, Artist\nSelect the type (N/P/A):");
      char type = sc.nextLine().charAt(0); 
                        
      System.out.println("Enter username: ");
      String username = sc.nextLine();
      boolean result = username.matches("[a-zA-Z]+"); // check for no non-alphabet characters
      if (!result) { 
         System.out.println("Username must contain only the alphabet and have no spaces."); // error message for invalid username 
      } else {
         username = username.toLowerCase(); // case insensitive so turn username to lowercase
         if (!userList.contains(searchUser(username))){ //checks if userList contains a User with the same username
            int limit = User.getPasswordLimit();
         
            System.out.println("Enter password (must be " +  limit + " characters or less): "); // message for password requirements
            String password = sc.nextLine();
            
            // check for correct password length
            if (password.length() <= limit){
               // check for the type of user they want to sign up as
               if (type == 'N' || type == 'n'){
                  NormalUser user = new NormalUser(username, password, this);
                  userList.add(user);
                  sortUserList();
               }
               else if(type == 'P' || type == 'p'){
                  PremiumUser user = new PremiumUser(username, password, this);
                  userList.add(user);
                  sortUserList();
               }
               else if(type == 'A' || type == 'a'){
                  Artist user = new Artist(username, password, this);
                  userList.add(user);
                  sortUserList();
               }
               else{
                  System.out.println("Invalid user type selected.");
               }
            }
            else{
               System.out.println("Password must be " +  limit + " characters or less");
            }
         }
         else{
            System.out.println("Username is not avaliable");
         }
      
      }
   }
   
   /*
      Name: findMostLikedMedia
      Parameters: N/A
      Return: Media - object with the most likes
      Description: finds Media object with the most likes
      *uses sequential search
   */
   public Media findMostLikedMedia(){ 
      if(!mediaList.isEmpty()){
         int mostLiked = 0;
         for (int i = 1; i < mediaList.size(); i++){
            if (mediaList.get(i).getNumLikes() > mediaList.get(mostLiked).getNumLikes()){  //checks if numLikes of object i is greater than current greatest numLikes
               mostLiked = i;
            }
         }
         return mediaList.get(mostLiked);
      }
      return null;
   }
   
   /*
      Name: findMostPopularArtist
      Parameters: N/A
      Return: Artist - Artist object with the most followers
      Description: finds Artist with the most followers
      *uses sequential search
   */
   public Artist findMostPopularArtist(){
      int mostPopular = -1;
      for (int i = 1; i < userList.size(); i++){
         if (userList.get(i) instanceof Artist) {  
            if (mostPopular == -1){ //save first artist index that is found
               mostPopular = i;
            }
            else if (userList.get(i).getFollowers().size() > userList.get(mostPopular).getFollowers().size()){ //checks if followers of object i is greater than current greatest number of followers
               mostPopular = i;
            }   
         }
      }
      
      if (mostPopular != -1){ //no artists exist in user list
         return (Artist)userList.get(mostPopular); // POLYMORPHISM : a user is found and then cast as an artist as it was checked to see if it was an instance of artist earlier
      }  
      return null;  
   }
   
   /*
      Name: findMostPlayedMedia
      Parameters: N/A
      Return: Media - Media object with the most plays
      Description: finds Media with the most plays
      *uses sequential search
   */
   public Media findMostPlayedMedia(){
      if(!mediaList.isEmpty()){
         int mostPlayed = 0;
         for (int i = 1; i < mediaList.size(); i++){
            if (mediaList.get(i).getNumPlays() > mediaList.get(mostPlayed).getNumPlays()){  //checks if numPlays of object i is greater than current greatest numPlays
               mostPlayed = i;
            }
         }
         return mediaList.get(mostPlayed);
      }
      return null;
   }
   
   /*
      Name: searchMedia
      Parameters: name - inputted name of the Media 
      Return: Media - Media object with the same name as the input
      Description: Finds Media object based on its name. Returns null if Media doesn't exist.
      *uses sequential search
   */
   public Media searchMedia(String name){ // SEARCH RECORDS BY MEDIA NAME (ONE CRITERION)
      for (int i = 0; i < mediaList.size(); i++){
         if (mediaList.get(i).getName().equalsIgnoreCase(name)){  //checks if Media name matches
            return mediaList.get(i);
         }
      }
      return null;
   }
   
   /*
      Name: searchMedia
      Parameters: 
         name - inputted name of the Media
         artistName - inputted name of the Artist of the Media 
      Return: Media - Media object with the same name and artist as the input
      Description: Finds Media object based on its Artist and name. Returns null if Media doesn't exist.
      *uses sequential search
   */
   public Media searchMedia(String name, String artistName){ // SEARCH RECORDS BY MEDIA NAME AND ARTIST NAME (TWO CRITERIA)
      artistName = artistName.toLowerCase(); // case insensitivity
      for (int i = 0; i < mediaList.size(); i++){
         if (mediaList.get(i).getName().equalsIgnoreCase(name) && (mediaList.get(i).getArtist().getUsername()).equals(artistName)){ 
            return mediaList.get(i);
         }
      }
      return null;
   }
   
   /*
      Name: searchUser
      Parameters: 
         username - inputted username 
      Return: User - object with the same username as the input
      Description: Finds User object based on its username. Returns null if User doesn't exist.
      *uses binary search
   */
   public User searchUser(String username){ // SEARCH RECORDS BY USERNAME (ONE CRITERION)
      username = username.toLowerCase(); // case insensitivity
      int bottom = 0;
      int top = userList.size() - 1;
      while (bottom <= top){
         int middle = (bottom + top)/2;
         String middleName = userList.get(middle).getUsername();
         
         if (middleName.equals(username)){  //checks if username of object i matches
            User middleUser = userList.get(middle);
            return middleUser; 
         }
         else if (middleName.compareToIgnoreCase(username) > 0){
            top = middle - 1;
         }
         else{
            bottom = middle + 1;
         }
      }
      return null;
   }
   
   /*
      Name: sortUserList
      Parameters: N/A
      Return: N/A
      Description: sorts userList in alphabetical order      
      *uses selection sort
   */ 
   private void sortUserList(){ // SORT RECORDS
   
      for (int upperbound = userList.size() - 1; upperbound >= 0; upperbound--){
         User u = userList.get(upperbound);
         int maxUserIndex = upperbound;
         for (int i = 0; i < upperbound; i++){
            
            User u1 = userList.get(i);
            User u2 = userList.get(maxUserIndex);
            if (u1.getUsername().compareTo(u2.getUsername()) > 0){ //check if user at i is lexographically greater
               maxUserIndex = i;            }
         }
         //swap items at maxIndex and upperbound
         User temp = userList.get(maxUserIndex);
         userList.set(maxUserIndex, u);
         userList.set(upperbound, temp);

      }
   }
   
   /*
      Name: searchPlaylist 
      Parameters: name - String with the inputted name of the Playlist to be found
      Return: Playlist - object with matching name
      Description: Finds the Playlist object with the same name as the input. Returns null if not found.     
      *uses sequential search
   */ 
   public Playlist searchPlaylist(String name){  // SEARCH RECORDS
      for (int i = 0; i < allPlaylists.size(); i++){
         if (allPlaylists.get(i).getName().equals(name)){
            return allPlaylists.get(i);
         }
      }
      return null;
   }
   
   /*
      Name: becomeArtist
      Paramter: 
         thisUser - user object that will be changed
      Return: returns the new user object it has been changed to
      Description: a public method that changes the object type of user by constructing a different user with all the same fields
                   , removing the old user from the list and adding the new user to the list
   */ 
   public User becomeArtist(User thisUser) { // MODIFY, ADD, REMOVE RECORDS
      Artist newA = new Artist(thisUser.getUsername(), thisUser.getPassword(), thisUser.getFollowers(), thisUser.getFollowing(), thisUser.getFriends(),thisUser.getPlaylists(), thisUser.getFavourites(), this);
      userList.remove(thisUser);
      userList.add(newA);
      sortUserList();
      System.out.println("Account " + newA.getUsername() + " successfully changed to Artist."); // message for user to see the success of the function
      return newA;  
   } 
   
   /*
      Name: goPremium
      Paramter: 
         thisUser - user object that will be changed
      Return: returns the new user object it has been changed to
      Description: a public method that changes the object type of user by constructing a different user with all the same fields
                   , removing the old user from the list and adding the new user to the list
   */
   public User goPremium(User thisUser) { // MODIFY, ADD, REMOVE RECORDS
      PremiumUser newP = new PremiumUser(thisUser.getUsername(), thisUser.getPassword(), thisUser.getFollowers(), thisUser.getFollowing(), thisUser.getFriends(),thisUser.getPlaylists(), thisUser.getFavourites(), this);
      userList.remove(thisUser);
      userList.add(newP);
      sortUserList();
      System.out.println("Account " + newP.getUsername() + " successfully changed to PremiumUser."); // message for the user to see the success of the program
      return newP;  
   }  
}