import com.fasterxml.jackson.annotation.JsonInclude;
import resources.primary.Movie;
import resources.primary.User;
import resources.primary.Action;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Input {
   private ArrayList<User> users;
   private ArrayList<Movie> movies;
   private ArrayList<Action> actions;
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public ArrayList<User> getUsers() {
      return users;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setUsers(final ArrayList<User> users) {
      this.users = users;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public ArrayList<Movie> getMovies() {
      return movies;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setMovies(final ArrayList<Movie> movies) {
      this.movies = movies;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public ArrayList<Action> getActions() {
      return actions;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setActions(final ArrayList<Action> actions) {
      this.actions = actions;
   }
}
