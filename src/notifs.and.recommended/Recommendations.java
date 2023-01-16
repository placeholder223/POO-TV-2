package notifs.and.recommended;

import databases.MovieDatabase;
import resources.primary.Movie;
import resources.primary.User;

import java.util.ArrayList;
import java.util.HashMap;

public final class Recommendations {
   public static final String NO_RECOMMENDATION_MOVIE = "No recommendation";
   public static final String RECOMMENDATION_MESSAGE = "Recommendation";

   private Recommendations() {

   }

   /**
    *
    * @param avoidedGenres genres that were already made, it is only used by the method
    *                      that gets the recommended movie, use null otherwise
    * @return the genre that is favorite
    */
   public static String getFavoriteGenre(final User user, final ArrayList<String> avoidedGenres) {
      if (user.getLikedMovies().size() == 0) {
         return null;
      }
      HashMap<String, Integer> genresFrequency = new HashMap<>();
      for (Movie movie : user.getLikedMovies()) {
         for (String genre : movie.getGenres()) {
            if (avoidedGenres != null) { // if there are no genres to avoid
               if (!avoidedGenres.contains(genre)) { // or if there are, we don't add it
                  if (genresFrequency.containsKey(genre)) {
                     genresFrequency.put(genre, genresFrequency.get(genre) + 1);
                  } else {
                     genresFrequency.put(genre, 1);
                  }
               }
            } else {
               if (genresFrequency.containsKey(genre)) {
                  genresFrequency.put(genre, genresFrequency.get(genre) + 1);
               } else {
                  genresFrequency.put(genre, 1);
               }
            }
         }
      }
      int maxFrequency = 0;
      String mostFrequentGenre = "";
      for (String genre : genresFrequency.keySet()) {
         int genreFrequency = genresFrequency.get(genre);
         if (genreFrequency > maxFrequency) { // get the most frequent genre
            maxFrequency = genreFrequency;
            mostFrequentGenre = genre;
         } else if (genreFrequency == maxFrequency) { // if there are multiple genres with
            // maximum number of movies, get them lexicographically
            if (genre.compareTo(mostFrequentGenre) < 0) {
               mostFrequentGenre = genre;
            }
         }
      }
      return mostFrequentGenre;
   }

   /**
    * gets the recommended movie based on genre and likes
    *
    * @return the movie, or null if there is no available one
    */
   public static Movie getRecommendedMovie(final User user, final MovieDatabase movieDatabase) {
      movieDatabase.getAvailableMovies(user);
      ArrayList<Movie> copyList = new ArrayList<>(movieDatabase.getCurrentMovies());
      String favoriteGenre = getFavoriteGenre(user, null);
      copyList.removeAll(user.getWatchedMovies()); // get all the movies the user did not watch
      ArrayList<Movie> genredList = new ArrayList<>();
      for (Movie movie : copyList) {
         if (movie.getGenres().contains(favoriteGenre)) {
            genredList.add(movie); // now get all those previously mentioned movies,
            // but that have the favorite genre
         }
      }
      ArrayList<String> searchedGenres = new ArrayList<>();
      while (genredList.size() == 0) { // if there is no movie of the specified genre
         // go to the next favorite until we find a movie to recommend
         searchedGenres.add(favoriteGenre);
         movieDatabase.getAvailableMovies(user);
         copyList = new ArrayList<>(movieDatabase.getCurrentMovies());
         favoriteGenre = getFavoriteGenre(user, searchedGenres);
         copyList.removeAll(user.getWatchedMovies());
         genredList = new ArrayList<>();
         for (Movie movie : copyList) {
            if (movie.getGenres().contains(favoriteGenre)) {
               genredList.add(movie);
            }
         }
      }
      int maxLikes = 0;
      Movie recommendedMovie = null; // start as if there is no movie to recommend, in case
      // the user watched everything that could be recommended (hermit)
      for (Movie movie : genredList) {
         if (movie.getNumLikes() > maxLikes) {
            maxLikes = movie.getNumLikes();
            recommendedMovie = movie;
         } else if (recommendedMovie == null) {
            recommendedMovie = movie;
         }
      }
      return recommendedMovie;
   }

}
