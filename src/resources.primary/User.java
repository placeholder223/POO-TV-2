package resources.primary;
import com.fasterxml.jackson.annotation.JsonIgnore;
import notifs.and.recommended.Notific;

import java.util.ArrayList;


public class User {
   public static final int NUMBER_TOKENS_FOR_PREMIUM = 10;
   public static final int STARTING_FREE_MOVIES = 15;
   public static final String PREMIUM_STATUS = "premium";
   private Credentials credentials;
   private Integer tokensCount = 0;
   private Integer numFreePremiumMovies = STARTING_FREE_MOVIES;
   private ArrayList<Movie> purchasedMovies;
   private ArrayList<Movie> watchedMovies;
   private ArrayList<Movie> likedMovies;
   private ArrayList<Movie> ratedMovies;
   private ArrayList<Notific> notifications;
   @JsonIgnore
   private ArrayList<String> subscriptions;

   public User() {
      this.credentials = new Credentials();
      this.purchasedMovies = new ArrayList<>();
      this.watchedMovies = new ArrayList<>();
      this.likedMovies = new ArrayList<>();
      this.ratedMovies = new ArrayList<>();
      this.notifications = new ArrayList<>();
      this.subscriptions = new ArrayList<>();
   }

   public User(final User other) {
      if (other == null) {
         return;
      }
      this.credentials = new Credentials(other.credentials);
      this.tokensCount = other.tokensCount;
      this.numFreePremiumMovies = other.numFreePremiumMovies;
      this.purchasedMovies = new ArrayList<>();
      if (other.purchasedMovies != null) {
         for (Movie movie : other.purchasedMovies) {
            this.purchasedMovies.add(new Movie(movie));
         }
      }
      this.watchedMovies = new ArrayList<>();
      if (other.watchedMovies != null) {
         for (Movie movie : other.watchedMovies) {
            this.watchedMovies.add(new Movie(movie));
         }
      }
      this.likedMovies = new ArrayList<>();
      if (other.likedMovies != null) {
         for (Movie movie : other.likedMovies) {
            this.likedMovies.add(new Movie(movie));
         }
      }
      this.ratedMovies = new ArrayList<>();
      if (other.ratedMovies != null) {
         for (Movie movie : other.ratedMovies) {
            this.ratedMovies.add(new Movie(movie));
         }
      }
      this.notifications = new ArrayList<>();
      if (other.notifications != null) {
         for (Notific notific : other.notifications) {
            this.notifications.add(new Notific(notific));
         }
      }
      this.subscriptions = other.getSubscriptions();
   }

   /**
    * copy constructor
    */
   public User(final Credentials other) {
      if (other == null) {
         return;
      }
      this.credentials = new Credentials(other);
      if (credentials.getAccountType().compareTo(PREMIUM_STATUS) == 0) {
         this.numFreePremiumMovies = STARTING_FREE_MOVIES;
      }
      this.purchasedMovies = new ArrayList<>();
      this.watchedMovies = new ArrayList<>();
      this.likedMovies = new ArrayList<>();
      this.ratedMovies = new ArrayList<>();
      this.subscriptions = new ArrayList<>();
      this.notifications = new ArrayList<>();
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public Credentials getCredentials() {
      return credentials;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setCredentials(final Credentials credentials) {
      this.credentials = new Credentials(credentials);
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public Integer getTokensCount() {
      return tokensCount;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setTokensCount(final Integer tokens) {
      this.tokensCount = tokens;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public Integer getNumFreePremiumMovies() {
      return numFreePremiumMovies;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setNumFreePremiumMovies(final Integer numFreePremiumMovies) {
      this.numFreePremiumMovies = numFreePremiumMovies;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public ArrayList<Movie> getPurchasedMovies() {
      return purchasedMovies;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
      this.purchasedMovies = purchasedMovies;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public ArrayList<Movie> getWatchedMovies() {
      return watchedMovies;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
      this.watchedMovies = watchedMovies;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public ArrayList<Movie> getLikedMovies() {
      return likedMovies;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setLikedMovies(final ArrayList<Movie> likedMovies) {
      this.likedMovies = likedMovies;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public ArrayList<Movie> getRatedMovies() {
      return ratedMovies;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
      this.ratedMovies = ratedMovies;
   }

   /**
    * used to change the numFreeMovies counter directly, without getters and setters, by specifying
    * the value by which it needs to be changed
    */
   public void addToNumFreeMovies(final int number) {
      this.numFreePremiumMovies += number;
   }

   /**
    * used to change the numTokensCount counter directly, without getters and setters, by specifying
    * the value by which it needs to be changed
    */
   public void addToTokensCount(final int number) {
      this.tokensCount += number;
   }

   /**
    * checks if the user wants to like/rate a movie not watched
    * currently not used
    */
   public Movie getFromWatchedMovies(final String name) {
      for (Movie movie : watchedMovies) {
         if (movie.getName().equals(name)) {
            return movie;
         }
      }
      return null;
   }

   /**
    * the method adds the desired movie to the user's liked movie list, and also increases the
    * number of likes for that movie by one
    */
   public void addToLikedMovies(final Movie movie) {
      this.getLikedMovies().add(movie);
      movie.addToNumLikes(1);
   }

   /**
    * the method adds the desired movie to the user's rated movie list, and also adds another
    * rating to that movie
    */
   public void addToRatedMovies(final Movie movie, final Integer rating) {
      this.getRatedMovies().add(movie);
      movie.addRating(this, rating);
   }

   public ArrayList<Notific> getNotifications() {
      return notifications;
   }

   public void setNotifications(ArrayList<Notific> notifications) {
      this.notifications = notifications;
   }
   public void addNotification(String movieName, String message){
      Notific notific = new Notific(movieName,message);
      this.notifications.add(notific);
   }
   @JsonIgnore
   public ArrayList<String> getSubscriptions() {
      return subscriptions;
   }
   @JsonIgnore
   public void setSubscriptions(ArrayList<String> subscriptions) {
      this.subscriptions = subscriptions;
   }
   public void addSubscription(String subscription){
      this.subscriptions.add(subscription);
   }
}
