package filters;

import java.util.ArrayList;

public class Contains {
   private ArrayList<String> actors; // the wanted actors
   private ArrayList<String> genre; // the wanted genres

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public ArrayList<String> getActors() {
      return actors;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setActors(final ArrayList<String> actors) {
      this.actors = actors;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public ArrayList<String> getGenre() {
      return genre;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setGenre(final ArrayList<String> genre) {
      this.genre = genre;
   }
}
