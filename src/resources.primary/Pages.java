package resources.primary;

import java.util.ArrayList;
public class Pages {
   public static final String AUTHENTICATED_NOT = "not authenticated";
   public static final String AUTHENTICATED = "authenticated";
   public static final String LOGIN = "login";
   public static final String REGISTER = "register";
   public static final String SEARCH = "search";
   public static final String FILTER = "filter";
   public static final String PURCHASE = "purchase";
   public static final String WATCH = "watch";
   public static final String LIKE = "like";
   public static final String RATE = "rate";
   public static final String BUY_PREMIUM_ACCOUNT = "buy premium account";
   public static final String BUY_TOKENS = "buy tokens";
   public static final String UPGRADES = "upgrades";
   public static final String MOVIES = "movies";
   public static final String SEE_DETAILS = "see details";
   public static final String LOGOUT = "logout";
   private ArrayList<SubPages> changePage;

   /**
    * the constructor of pages, which initializes the hierarchy of pages, with the available pages
    * and actions for each page
    */
   public Pages() {
      this.changePage = new ArrayList<>();
      SubPages child = new SubPages();
      child.setName(AUTHENTICATED_NOT);
      this.changePage.add(child);
      child = new SubPages();
      child.setName(LOGIN);
      child.addOnPage(LOGIN);
      getHomepage(false).getChangePage().add(child);
      child = new SubPages();
      child.setName(REGISTER);
      child.addOnPage(REGISTER);
      getHomepage(false).getChangePage().add(child);
      getHomepage(false).getChangePage().add(getHomepage(false));
      SubPages curr = new SubPages();
      curr.setName(AUTHENTICATED);
      this.changePage.add(curr);
      child = new SubPages();
      child.setName(LOGOUT);
      SubPages logout = child;
      getHomepage(true).getChangePage().add(logout);
      child = new SubPages();
      child.setName(UPGRADES);
      SubPages upgrades = child; // saving it
      getHomepage(true).getChangePage().add(0, upgrades);
      upgrades.getChangePage().add(upgrades);
      upgrades.getChangePage().add(getHomepage(true));
      upgrades.getChangePage().add(logout);
      upgrades.addOnPage(BUY_PREMIUM_ACCOUNT);
      upgrades.addOnPage(BUY_TOKENS);
      upgrades.addOnPage(PURCHASE);
      child = new SubPages();
      child.setName(MOVIES);
      getHomepage(true).getChangePage().add(0, child);
      getHomepage(true).getChangePage().add(upgrades);
      SubPages movies = child;
      movies.getChangePage().add(getHomepage(true));
      movies.getChangePage().add(movies);
      movies.addOnPage(SEARCH);
      movies.addOnPage(FILTER);
      movies.getChangePage().add(logout);
      child = new SubPages();
      child.setName(SEE_DETAILS);
      movies.getChangePage().add(child);
      SubPages seeDetails = child;
      seeDetails.getChangePage().add(seeDetails);
      seeDetails.getChangePage().add(getHomepage(true));
      seeDetails.getChangePage().add(movies);
      seeDetails.getChangePage().add(upgrades);
      seeDetails.getChangePage().add(logout);
      seeDetails.getOnPage().add(PURCHASE);
      seeDetails.getOnPage().add(WATCH);
      seeDetails.getOnPage().add(LIKE);
      seeDetails.getOnPage().add(RATE);
      upgrades.getChangePage().add(movies);
   }

   /**
    * @return the homepage, depending on whether the program wants the Authenticated homepage
    * or the non-Authenticated
    */
   public SubPages getHomepage(final boolean isAuthenticated) {
      if (isAuthenticated) {
         return this.changePage.get(1);
      }
      return this.changePage.get(0);
   }
}
