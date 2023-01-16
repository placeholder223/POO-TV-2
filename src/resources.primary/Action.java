package resources.primary;

import com.fasterxml.jackson.annotation.JsonInclude;
import filters.Filters;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Action {
   /**
    * used for seeing if the action is of type on page or change page
    * there coual also be a constant for "change page", but since it can either be on page or
    * change page, there is no need
    */
   public static final String ON_PAGE = "on page";
   public static final String CHANGE_PAGE = "change page";
   public static final String BACK = "back";
   /**
    * default constructor
    */
   public Action() {

   }

   private String type;
   private String feature;
   private String movie;
   private resources.primary.Credentials credentials;
   private String startsWith;
   private Filters filters;
   private String count;
   private String page;
   private Integer rate;

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public String getType() {
      return type;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setType(final String type) {
      this.type = type;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public String getFeature() {
      return feature;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setFeature(final String feature) {
      this.feature = feature;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public String getMovie() {
      return movie;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setMovie(final String movie) {
      this.movie = movie;
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
      this.credentials = credentials;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public String getStartsWith() {
      return startsWith;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setStartsWith(final String startsWith) {
      this.startsWith = startsWith;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public Filters getFilters() {
      return filters;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setFilters(final Filters filters) {
      this.filters = filters;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public String getCount() {
      return count;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setCount(final String count) {
      this.count = count;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public Integer getRate() {
      return rate;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setRate(final Integer rate) {
      this.rate = rate;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public String getPage() {
      return page;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setPage(final String page) {
      this.page = page;
   }
}
