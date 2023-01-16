import com.fasterxml.jackson.databind.node.ObjectNode;
import databases.MovieDatabase;
import databases.UserDatabase;
import error.handling.Errors;
import resources.primary.Action;
import resources.primary.Movie;
import resources.primary.User;

public final class DatabaseActions {
   private DatabaseActions() {

   }

   private static boolean needsChange;

   /**
    * adds or deletes a movie from the database, according to the feature of the action
    * use getNeedsChange to see if the output needs to be updated in case of error
    */
   public static void modifyDatabase(final ObjectNode temp, final Action action,
                                     final MovieDatabase movieDatabase,
                                     final UserDatabase userDatabase) {
      if (action.getFeature().equals(Action.DATABASE_ADD)) {
         if (!movieDatabase.addMovieToDatabase(action.getAddedMovie())) {
            needsChange = Errors.errorOutput(Errors.ERROR, null, null, temp);
         } else {
            for (User user : userDatabase.getUsers()) {
               for (String genre : action.getAddedMovie().getGenres()) {
                  if (user.getSubscriptions().contains(genre)
                        && !action.getAddedMovie().getCountriesBanned().contains(
                        user.getCredentials().getCountry())) {
                     user.addNotification(action.getAddedMovie().getName(),
                           Action.DATABASE_ADD.toUpperCase());
                     break;
                  }
               }
            }
            needsChange = false;
         }
      } else {
         Movie removedMovie = movieDatabase.removeMovieFromDatabase(
               action.getDeletedMovie());
         if (removedMovie == null) {
            needsChange = Errors.errorOutput(Errors.ERROR, null, null, temp);
         } else {
            removedMovie.setNumLikes(0);
            removedMovie.setRating((double) 0);
            removedMovie.setNumRatings(0);
            for (User user : userDatabase.getUsers()) {
               for (Movie movie : user.getPurchasedMovies()) {
                  if (movie.getName().equals(removedMovie.getName())) {
                     if (user.getCredentials().getAccountType().equals(
                           User.PREMIUM_STATUS)) {
                        user.addToNumFreeMovies(1);
                     } else {
                        user.addToTokensCount(Movie.NUM_TOKENS_FOR_BUYING);
                     }
                     user.getPurchasedMovies().removeIf(purchasedMovie
                           -> purchasedMovie.getName().equals(removedMovie.getName()));
                     user.getLikedMovies().removeIf(likedMovie
                           -> likedMovie.getName().equals(removedMovie.getName()));
                     user.getWatchedMovies().removeIf(watchedMovie
                           -> watchedMovie.getName().equals(removedMovie.getName()));
                     user.getRatedMovies().removeIf(ratedMovie
                           -> ratedMovie.getName().equals(removedMovie.getName()));
                     user.addNotification(removedMovie.getName(),
                           Action.DATABASE_DELETE.toUpperCase());
                     break;
                  }
               }
            }
            needsChange = false;
         }
      }
   }

   /**
    * @return if the output needs to be updated
    */
   public static boolean getNeedsChange() {
      return needsChange;
   }
}
