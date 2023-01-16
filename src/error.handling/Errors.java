package error.handling;


import com.fasterxml.jackson.databind.node.ObjectNode;
import resources.primary.Movie;
import resources.primary.User;

import java.util.ArrayList;

public final class Errors {

   private Errors() {

   }

   /**
    * the error message
    */
   public static final String ERROR = "Error";

   /**
    * makes a list from only one movie, for the output to handle
    *
    * @return a list with that movie
    */
   public static ArrayList<Movie> toList(final Movie movie) {
      ArrayList<Movie> auxMovieList = new ArrayList<>();
      auxMovieList.add(movie);
      return auxMovieList;
   }

   /**
    * puts the error in the objectNode to be added later in the output, if an empty list
    * is wanted, null is given as parameter.
    * If only a movie needs to be shown, the toList function is used
    *
    * @return true so the needsChange can be set to true in order to notify the program
    * that the output needs to be changed
    */
   public static boolean errorOutput(final String error, final ArrayList<Movie> movies,
                                     final User user, final ObjectNode temp) {
      temp.put("error", error);
      if (movies != null) {
         ArrayList<Movie> copyMovies = new ArrayList<>();
         for (Movie movie : movies) {
            copyMovies.add(new Movie(movie));
         }
         temp.putPOJO("currentMoviesList", new ArrayList<>(copyMovies));
      } else {
         // if the argument is null then we want an empty list
         temp.putPOJO("currentMoviesList", new ArrayList<>());
      }
      if (user != null) {
         //temp.putPOJO("currentUser", new User(user));
         temp.putPOJO("currentUser", new User(user));
      } else {
         temp.put("currentUser", (String) null);
      }
      return true;
   }

   /**
    * used for recommendations, notice how there is only null in error and movies field,
    * so no parameters for that are needed
    * @return true because it needsChange will change
    */
   public static boolean errorOutput(final User user, final ObjectNode temp) {
      temp.put("error", (String) null);
      temp.put("currentMoviesList", (String) null);
      temp.putPOJO("currentUser", new User(user));
      return true;
   }
}
