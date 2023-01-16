package resources.primary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Hashtable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie {
   public static final int NUM_TOKENS_FOR_BUYING = 2;
   private String name;
   private String year;
   private Integer duration;
   private ArrayList<String> genres;
   private ArrayList<String> actors;
   private ArrayList<String> countriesBanned;
   private Integer numLikes;
   private double rating;
   private Integer numRatings;
   /**
    * in case the user wants to change the rating
    */
   private final Hashtable<User, Integer> ratings;

   /**
    * adds the rating to the database which means:
    * -> increasing the number of ratings by one
    * -> recalculating the rating
    * -> I also used a hashtable in case the user would like to
    * -> change their rating
    */
   public void addRating(final User user, final Integer givenRating) {
      //System.out.println("prob addRating not finished");
      this.ratings.put(user, givenRating);
      this.numRatings++;
      this.rating
            = ((double) ratings.values().stream().mapToInt(Integer::intValue).sum())
            / (double) this.numRatings;
   }

   /**
    * changes the rating from the database, used for when user re-rates a movie
    */
   public void changeRating(final User user, final Integer givenRating) {
      this.ratings.put(user, givenRating);
      this.rating
            = ((double) ratings.values().stream().mapToInt(Integer::intValue).sum())
            / (double) this.numRatings;
   }

   /**
    * default constructor
    */
   public Movie() {
      this.numLikes = 0;
      this.rating = 0;
      this.numRatings = 0;
      this.ratings = new Hashtable<User, Integer>();
   }

   /**
    * copy constructor
    */
   public Movie(final Movie other) {
      this.name = other.name;
      this.year = other.year;
      this.duration = other.duration;
      this.genres = other.genres;
      this.actors = other.actors;
      this.countriesBanned = other.countriesBanned;
      this.numLikes = other.numLikes;
      this.rating = other.rating;
      this.numRatings = other.numRatings;
      this.ratings = other.ratings;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public String getName() {
      return name;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setName(final String name) {
      this.name = name;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public String getYear() {
      return year;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setYear(final String year) {
      this.year = year;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public Integer getDuration() {
      return duration;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setDuration(final Integer duration) {
      this.duration = duration;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public ArrayList<String> getGenres() {
      return genres;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setGenres(final ArrayList<String> genres) {
      this.genres = genres;
   }

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
   public ArrayList<String> getCountriesBanned() {
      return countriesBanned;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setCountriesBanned(final ArrayList<String> countriesBanned) {
      this.countriesBanned = countriesBanned;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public Integer getNumLikes() {
      return numLikes;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setNumLikes(final Integer numLikes) {
      this.numLikes = numLikes;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public double getRating() {
      return rating;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setRating(final Double rating) {
      this.rating = rating;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public int getNumRatings() {
      return numRatings;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setNumRatings(final Integer numRatings) {
      this.numRatings = numRatings;
   }

   /**
    * used to change the numLikes counter directly, without getters and setters, by specifying the
    * value by which it needs to be changed
    */
   public void addToNumLikes(final int number) {
      this.numLikes += number;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   @Override
   @JsonIgnore
   public String toString() {
      return "Movie{"
            + "name='" + name + '\''
            + ", year=" + year
            + ", duration=" + duration
            + ", genres=" + genres
            + ", actors=" + actors
            + ", countriesBanned=" + countriesBanned
            + ", numLikes=" + numLikes
            + ", rating=" + rating
            + ", numRatings=" + numRatings
            + '}';
   }
}
