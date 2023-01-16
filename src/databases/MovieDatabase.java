package databases;

import filters.Contains;
import resources.primary.Movie;
import resources.primary.User;

import java.util.ArrayList;

public final class MovieDatabase {
   private static MovieDatabase instance = null;
   private ArrayList<Movie> moviesTotal;
   private ArrayList<Movie> currentMovies;

   /**
    * constructor for the database of movies
    */
   private MovieDatabase() {
      moviesTotal = new ArrayList<>();
      currentMovies = new ArrayList<>();
   }

   /**
    * the singleton getInstance
    */
   public static MovieDatabase getInstance() {
      if (instance == null) {
         instance = new MovieDatabase();
      }
      return instance;
   }

   /**
    * a get function for the arrayList, but instead of specifying the index, it specifies the name
    * of the wanted movie
    *
    * @return the specified movie if it exists, or null otherwise
    */
   public Movie get(final String name) {
      for (Movie movie : currentMovies) {
         if (movie.getName().equals(name)) {
            return movie;
         }
      }
      return null;
   }

   /**
    * searches all the current movies for the specified prefix, using the startsWith method
    *
    * @return the list of movies that all have the specified prefix
    */
   public ArrayList<Movie> search(final String prefix) {
      ArrayList<Movie> searchedMovies = new ArrayList<>();
      for (Movie movie : currentMovies) {
         if (movie.getName().startsWith(prefix)) {
            searchedMovies.add(movie);
         }
      }
      return searchedMovies;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setMovies(final ArrayList<Movie> newMovies) {
      moviesTotal = newMovies;
   }

   /**
    * gets all the available movies (currentMovies) for the given user, which is the total movies
    * minus the ones which are banned in the user's country.
    *
    * @param user the user for which the movies are calculated
    */
   public void getAvailableMovies(final User user) {
      currentMovies = new ArrayList<>();
      for (Movie movie : moviesTotal) {
         if (!movie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
            // if the banned countries list doesn't contain the user's country then it's good to go
            currentMovies.add(movie);
         }
      }
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public ArrayList<Movie> getCurrentMovies() {
      return currentMovies;
   }

   /**
    * destroys the database, in case we don't need it anymore and want to create another
    */
   public void destroyDatabase() {
      instance = null;
   }

   /**
    * searches each current movie and sees if it contains the wanted criterias, such as genre and
    * actors, and puts it in a new list if it does. Then the currentMovies list is updated to be
    * this "criteriaed" one
    *
    * @param criterias are the wanted criterias, actors and/or genres.
    */
   public void contains(final Contains criterias) {
      ArrayList<Movie> containedMovies = new ArrayList<>();
      for (Movie movie : currentMovies) {
         if (criterias.getActors() != null) {
            if (movie.getActors().containsAll(criterias.getActors())) {
               // it found the desired actors, check if there are any genre specifications
               if (criterias.getGenre() != null) {
                  if (movie.getGenres().containsAll(criterias.getGenre())) {
                     containedMovies.add(movie);
                  }
               } else {
                  containedMovies.add(movie);
               }
            }
         } else { // only genre specifications
            if (movie.getGenres().containsAll(criterias.getGenre())) {
               containedMovies.add(movie);
            }
         }
      }
      currentMovies = containedMovies;
   }

   /**
    * sorts the movies acording to their duration and rating, based on the "increasing" and
    * "decreasing" strings, the comparator chooses whether to sort in ascending or descending order
    * using a ternary operator. it also sorts only by rating or only by duration, depending on
    * which filter is empty (null)
    *
    * @param duration the string telling how to handle duration sorting
    * @param rating   the string telling how to handle rating sorting
    */
   public void filter(final String duration, final String rating) {
      if (duration == null) {
         // sort by rating only
         boolean ratingAscending = rating.equals("increasing");
         currentMovies.sort((movie1, movie2) -> {
            double rating1 = movie1.getRating();
            double rating2 = movie2.getRating();
            if (rating1 < rating2) {
               return ratingAscending ? -1 : 1;
            } else if (rating1 > rating2) {
               return ratingAscending ? 1 : -1;
            } else {
               return 0;
            }
         });
      } else if (rating == null) {
         // sort by duration only
         boolean durationAscending = duration.equals("increasing");
         currentMovies.sort((movie1, movie2) -> {
            int duration1 = movie1.getDuration();
            int duration2 = movie2.getDuration();
            if (duration1 < duration2) {
               return durationAscending ? -1 : 1;
            } else if (duration1 > duration2) {
               return durationAscending ? 1 : -1;
            } else {
               return 0;
            }
         });
      } else {
         // sort by both duration and rating
         boolean durationAscending = duration.equals("increasing");
         boolean ratingAscending = rating.equals("increasing");
         currentMovies.sort((movie1, movie2) -> {
            int duration1 = movie1.getDuration();
            double rating1 = movie1.getRating();
            int duration2 = movie2.getDuration();
            double rating2 = movie2.getRating();
            if (duration1 < duration2) {
               return durationAscending ? -1 : 1;
            } else if (duration1 > duration2) {
               return durationAscending ? 1 : -1;
            } else {
               if (rating1 < rating2) {
                  return ratingAscending ? -1 : 1;
               } else if (rating1 > rating2) {
                  return ratingAscending ? 1 : -1;
               } else {
                  return 0;
               }
            }
         });
      }
   }

}
