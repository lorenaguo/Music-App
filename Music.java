/*
   File Name: Music.java
   Programmer: Katrina Jin
   Course: ICS4U1
   Date: 2023/6/6
   Description: A subclass of Media, representing a piece of music 
*/
package media;

import java.util.Date;
import user.*;

public class Music extends Media{ // inheritance
   
   /*
      Name : Music
      Parameter : 
         name - name of media
         artist - artist that created it as an artist object
         releaseDate - date the media was released
         length - length of the media
   */
   public Music(String name, Artist artist, Date releaseDate, String length){
      super(name, artist, releaseDate, length);
      numPlays = 0;
      numLikes = 0;
   }

}