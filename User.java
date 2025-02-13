/*	
	File Name: User.java
	Programmer:	T.	Shreya
	Class: ICS4U1
   School: A.Y. Jackson
	Date:	June 5, 2023
	Description: User superclass for the musicApp program
 */

package user;
import main.*;
import media.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
 
public abstract class User { // This is an abstract class.
   //fields // INHERITANCE : these fields are inherited by the child classes
   protected String username;                      // the username of the user
   protected String password;                      // the password of the users account
   protected final static int PASSWORD_LIMIT = 8;  // the max limit of characters in a password
   protected ArrayList<User> followers;            // arraylist of the followers the user has
   protected ArrayList<User> following;            // arraylist of the people the user follows
   protected ArrayList<User> friends;              // arraylist of friends (people the user follows that follows the user back
   protected ArrayList<Playlist> playlists;        // arraylist of the Playlists that the user has
   protected Playlist favourites;                  // arraylist of the favourites playlist specifically in a Playlist object // is empty I thin
   protected MusicApp app;                         // the MusicApp object app that the user methods can access 
   
   /*
      Name: User
      Parameter: 
         user - represents the username of the user
         pass - represents the password of the user
         app  - representst the app that user is a part of
      Return: N/A
      Description: constructor that initializes values for the username,
                   password, and the number of playlists
   */
   public User(String user, String pass, MusicApp app) {
      this.username = user;
      this.password = pass;
      followers = new ArrayList<User>();          
      following = new ArrayList<User>();           
      friends = new ArrayList<User>();           
      playlists = new ArrayList<Playlist>();    
      favourites = new Playlist("favourites", this);                                        
      this.app = app;
   }
   
   /*
      Name: User
      Parameter: 
         user - represents the username of the user
         pass - represents the password of the user
         followers - represents the followers of the user
         following - represents the users this user follows 
         friends - represents the user this user is friends with
         playlists - representst the playlists this user has created
         favourites - representst the playlist of users liked media
         app  - representst the app that user is a part of
      Return: N/A
      Description: constructor that initializes all fields of the user for when they switch account types 
   */
   public User(String user, String pass, ArrayList<User> followers, ArrayList<User> following, ArrayList<User> friends, ArrayList<Playlist> playlists, Playlist favourites, MusicApp app){
      this.username = user;
      this.password = pass;
      this.followers = followers;          
      this.following = following;           
      this.friends = friends;           
      this.playlists = playlists;    
      this.favourites = favourites;                                        
      this.app = app;
   }
   
   /*
      Description: Accessor of the variable username
   */
   public String getUsername () {
      return username;
   } 

   /*
      Description: Mutator of the variable username
   */  
   public void setUsername (String u) {
      username = u;
   }
   
   /*
      Description: Accessor of the variable password
   */
   public String getPassword () {
      return password;
   } 

   /*
      Description: Mutator of the variable password
   */  
   public void setPassword (String p) {
      password = p;
   } 
   
   /*
      Description: Accessor of the constant PASSWORD_LIMIT
   */
   public static int getPasswordLimit() {
      return PASSWORD_LIMIT;
   } 
 
   /*
      Description: Accessor of the variable followers
   */  
   public ArrayList<User> getFollowers() {
      return followers;
   }
   
   /*
      Description: Mutator of the variable followers
   */ 
   public void setFollowers(ArrayList<User> fs){
      this.followers = fs;
   }
    
   /*
      Description: Accessor of the variable following
   */  
   public ArrayList<User> getFollowing() {
      return following;
   }
   
   /*
      Description: Mutator of the variable following
   */ 
   public void setFollowing(ArrayList<User> fg){
      this.following = fg;
   }  
   
   /*
      Description: Accessor of the variable friends
   */  
   public ArrayList<User> getFriends() {
      return friends;
   }
   
   /*
      Description: Mutator of the variable followers
   */ 
   public void setFriends(ArrayList<User> fns){
      this.friends = fns;
   } 
    
   /*
      Description: Accessor of the variable playlists
   */  
   public ArrayList<Playlist> getPlaylists() {
      return playlists;
   }
   
   /*
      Description: Mutator of the variable playlists
   */ 
   public void setPLaylists(ArrayList<Playlist> ps){
      this.playlists = ps;
   }  
   
   /*
      Description: Accessor of the variable favourites
   */  
   public Playlist getFavourites() {
      return favourites;
   }
   
   /*
      Description: Mutator of the variable favourites
   */ 
   public void setFavourites(Playlist fvs){
      this.favourites = fvs;
   }  
   
   /*
      Description: Accessor of the variable app
   */  
   public MusicApp getApp() {
      return app;
   }
   
   /*
      Description: Mutator of the variable followers
   */ 
   public void setApp(MusicApp a){
      this.app = a;
   }  
   
   /*
      Name: toString
      Parameter: N/A
      Return: a string value of all the details of user
      Description: public method that returns the username,
                   password, and number of playlists of a user
   */
   public String toString() {
      return "Username: " + username + 
         "\n# of Playlists: " + playlists.size() + "\nFollowers: " + followers.size() +  "\nFollowing: " + following.size() + "\nFriends: " + friends.size();
   }
  
   /*
      Name: compareToFollowers
      Parameter: 
         other - represents the second user that the values
      Return: an int value of the difference in number of followers
      Description: public method that returns the difference between this
                   user and other user
   */ 
   public int compareToFollowers(User other) {
      int difference = this.getFollowers().size() - other.getFollowers().size();
      return difference;
   }
   
   /*
      Name: likeMedia
      Parameter: 
         mediaName - represents the name of the media that the user wants to like
      Return: N/A
      Description: public method that finds the media, adds it to favourites arraylist
                   and increases addLikes by 1
   */ 
   public void likeMedia (Media liked) { // ADD RECORDS 
      if (!(favourites.getMediaList().contains(liked))) {
            favourites.getMediaList().add(liked);
         } 
      liked.addLikes();
      System.out.println("Chosen media has been liked."); // message for user to see the success of the function
   }
   
   /*
      Name: unlikeMedia
      Parameter: 
         mediaName - represents the name of the media that the user wants to unlike
      Return: N/A
      Description: public method that finds the media, removes it to favourites arraylist
                   and decreases addLikes by 1
   */ 
   public void unlikeMedia (Media unliked) { // DELETE RECORDS
      favourites.getMediaList().remove(unliked); 
      unliked.removeLikes();
      System.out.println("Chosen media has been unliked."); // message for user to see the success of the function
   }
   
   /*
      Name: followUser
      Parameter: 
         name - represents the name of the other user the user wants to follow
      Return: N/A
      Description: public method that finds the user, add the other user to this users following
                   , adds this user to the other persons followers list, and if they both follow each other
                   call friends method.
                   prints out followed.
   */ 
   public void followUser (User other) { // ADD RECORDS
      this.following.add(other);
      other.followers.add(this);
      System.out.println("Followed"); // message for user to see the success of the function
      if (other.following.contains(this)) {
         this.friendUser(other);
      }
   } 
   
   /*
      Name: unfollowUser
      Parameter: 
         name - represents the name of the other user the user wants to unfollow
      Return: N/A
      Description: public method that finds the user, removes the other user from this users following
                   , removes this user from the other persons followers list, and if they 
                   are friends, remove them from friends list
                   prints out followed.
   */ 
   public void unfollowUser (User other) { // DELETE RECORDS
      this.following.remove(other);
      other.followers.remove(this);
      
      if (other.friends.contains(this)) {
         this.friends.remove(other);
         other.friends.remove(this);
      }
      
      System.out.println("Unfollowed"); // message for the user to see the success of the function
   }  
   
   /*
      Name: unfriendUser
      Parameter: 
         name - represents the name of the other user the user has become friends with
      Return: N/A
      Description: private method that adds both user to each others friends list
   */ 
   private void friendUser (User person) { // ADD RECORDS
      this.friends.add(person);
      person.friends.add(this);
      System.out.println("You are friends with " + person.getUsername()); // message for the user to see the success of the function
   }        
   
   /*
      Name: changeUsername
      Parameter: 
         newUsername - represents the new string username they want
      Return: returns a boolean value true or false of whether it was changed
      Description: public method that returns a boolean value of whether the
                   username was changed
   */ 
   public boolean changeUsername (String newUsername) { // MODIFY RECORDS
      newUsername = newUsername.toLowerCase(); // case insensitive, changes username to all lowercase
      boolean found = false;
      List users = app.getUserList();
      for (int i = 0; i < users.size(); i++) {
         User u = (User) users.get(i);
         // checks if new username is equal to old username
         if (u.getUsername().equals(newUsername)) {
            found = true;
         } 
         
      }
      if (!found) {
         this.username = newUsername;
         return true;
      } else { 
         return false;
      }
   }

   /*
      Name: changePassword
      Parameter: 
         newPassword - represents the new string password they want
      Return: returns a boolean value true or false if the password was changed
      Description: public method that returns a boolean value of whether it was changed
   */ 
   public boolean changePassword (String newPassword) { // MODIFY RECORDS
      boolean found;
      // checks if you entered the same password
      if (this.password.equals(newPassword)) {
            found = true;
      } else {
         found = false;
      }
      if ((newPassword.length() <= PASSWORD_LIMIT) && !found) {
         this.password = newPassword;
         return true;
      } else {
         return false;
      }
   }
   
   /*
      Name: createPlaylist
      Parameter: 
         name - represents the string of the playlist name
      Return: returns a boolean if the playlist was created 
      Description: an abstract method to create a playlist
   */ 
   public abstract boolean createPlaylist(String name);   
   
   /*
      Name: addMedia
      Parameter: 
         media - represents the media that is going to be added to the playlist
         selected - represents the playlist that the media will be added to
         dateAdded - represents the date that the media was added to the playlist
      Return: returns a boolean to confirm if the media was added
      Description: an abstract method to add media to a playlist
   */ 
   public abstract void addMedia(Media media, Playlist selected, Date dateAdded); // ADD RECORDS
 
 /*
      Name: removeMedia
      Parameter: 
         chosenMedia - represents the media that is to be removed
         chosenPlaylist - represents the playlist that the media is removed from
      Return:N/A 
      Description: an public method to remove media from a playlist
   */ 
   public void removeMedia(Media chosenMedia, Playlist chosenPlaylist) { // REMOVE RECORDS
      PlaylistMusic pm = chosenPlaylist.searchInPlaylist(chosenMedia);
      chosenPlaylist.getMediaList().remove(pm); 
      System.out.println("Removed " + chosenMedia.getName() + " from playlist " + chosenPlaylist.getName()); // message for user to show the success of the function
   }
   
   /*
      Name: playMedia
      Parameter: 
         mediaName - name of the media that was played
      Return: N/A
      Description: a public method that plays the media (adds number of plays
                   and states that it has been played)
   */ 
   public void playMedia(Media mediaName) { // ADD RECORDS
      mediaName.addPlays();
      System.out.println(mediaName.getName() + " has been played."); // message for user to show the success of the function
      
   }

   /*
      Name: changePlaylistCreator
      Parameter: N/A
      Return: N/A 
      Description: Changes creator of playlists to this user, used when normal users become premium/artist
   */ 
   public void changePlaylistCreator(){
      for (int i = 0; i < playlists.size(); i++){
         playlists.get(i).setCreator(this);
      }
      favourites.setCreator(this);
   }
   
}