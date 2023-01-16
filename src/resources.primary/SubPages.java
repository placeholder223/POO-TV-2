package resources.primary;
import java.util.ArrayList;

public class SubPages {
   private String name;
   private ArrayList<String> onPage;
   private ArrayList<SubPages> changePage;

   /**
    * default constructor
    */
   SubPages() {
      this.onPage = new ArrayList<>();
      this.changePage = new ArrayList<>();
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
   public ArrayList<String> getOnPage() {
      return onPage;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setOnPage(final ArrayList<String> onPage) {
      this.onPage = onPage;
   }

   /**
    * adds the given command to the list of commands
    */
   public void addOnPage(final String onPageAction) {
      this.onPage.add(onPageAction);
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public ArrayList<SubPages> getChangePage() {
      return changePage;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setChangePage(final ArrayList<SubPages> changePage) {
      this.changePage = changePage;
   }

   /**
    * changes on the subPage specified by the name
    * @return the wanted page, or null if not found
    */
   public SubPages changeOnSubPage(final String subPageName) {
      for (SubPages subPage : this.changePage) {
         if (subPage.getName().compareTo(subPageName) == 0) {
            return subPage;
         }
      }
      return null;
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   @Override
   public String toString() {
      String allOnPage = new String();
      for (String s : onPage) {
         allOnPage += s + " ";
      }
      String allChangePage = new String();
      for (SubPages s : changePage) {
         allChangePage += s.getName() + " ";
      }
      return "SubPages{"
            + "name='" + name + '\n'
            + ", onPage=" + allOnPage + "\n"
            + ", changePage=" + allChangePage
            + '}';
   }
}
