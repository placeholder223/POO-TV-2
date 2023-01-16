package notifs.and.recommended;

public class Notific {
   private String movieName;
   private String message;

   /**
    * default constructor
    */
   public Notific() {

   }
   /**
    * basic constructor
    */
   public Notific(final String movieName, final String message) {
      this.movieName = movieName;
      this.message = message;
   }
   /**
    * copy constructor
    */
   public Notific(final Notific other) {
      this.movieName = other.movieName;
      this.message = other.message;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public String getMovieName() {
      return movieName;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setMovieName(final String movieName) {
      this.movieName = movieName;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public String getMessage() {
      return message;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setMessage(final String message) {
      this.message = message;
   }
}
