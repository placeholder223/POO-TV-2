package filters;

public class Sort {
   private String rating; // how the rating should be sorted, ascending or descending
   private String duration; // how the duration should be sorted, ascending or descending

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public String getRating() {
      return rating;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setRating(final String rating) {
      this.rating = rating;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public String getDuration() {
      return duration;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setDuration(final String duration) {
      this.duration = duration;
   }
}
