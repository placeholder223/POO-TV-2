package notifs.and.recommended;

public class Notific {
   private String movieName;
   private String message;

   public Notific() {

   }

   public Notific(String movieName, String message) {
      this.movieName = movieName;
      this.message = message;
   }

   public Notific(Notific other) {
      this.movieName = other.movieName;
      this.message = other.message;
   }

   public String getMovieName() {
      return movieName;
   }

   public void setMovieName(String movieName) {
      this.movieName = movieName;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }
}
