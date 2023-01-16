package notifs.and.recommended;

import databases.MovieDatabase;
import resources.primary.Movie;
import resources.primary.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Recommendations {
   public static final String NO_RECOMMENDATION_MOVIE = "No recommendation";
   public static final String RECOMMENDATION_MESSAGE = "Recommendation";

   public static String getFavoriteGenre(User user, ArrayList<String> avoidedGenres) {
      if (user.getLikedMovies().size() == 0) {
         return null;
      }
      HashMap<String, Integer> genresFrequency = new HashMap<>();
      for (Movie movie : user.getLikedMovies()) {
         for (String genre : movie.getGenres()) {
            if(avoidedGenres != null){
               if (!avoidedGenres.contains(genre)) {
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
         if (genreFrequency > maxFrequency) {
            maxFrequency = genreFrequency;
            mostFrequentGenre = genre;
         } else if (genreFrequency == maxFrequency) {
            if (genre.compareTo(mostFrequentGenre) < 0) {
               mostFrequentGenre = genre;
            }
         }
      }
      return mostFrequentGenre;
   }

   public static Movie getRecommendedMovie(User user, MovieDatabase movieDatabase) {
      movieDatabase.getAvailableMovies(user);
      ArrayList<Movie> copyList = new ArrayList<>(movieDatabase.getCurrentMovies());
      String favoriteGenre = getFavoriteGenre(user, null);
      copyList.removeAll(user.getWatchedMovies());
      ArrayList<Movie> genredList = new ArrayList<>();
      for (Movie movie : copyList) {
         if (movie.getGenres().contains(favoriteGenre)) {
            genredList.add(movie);
         }
      }
      ArrayList<String> searchedGenres = new ArrayList<>();
      while(genredList.size()==0){
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
      Movie recommendedMovie = null;
      for(Movie movie : genredList){
         if(movie.getNumLikes() > maxLikes){
            maxLikes = movie.getNumLikes();
            recommendedMovie = movie;
         } else if (recommendedMovie == null) {
            recommendedMovie = movie;
         }
      }
      return recommendedMovie;
   }

}