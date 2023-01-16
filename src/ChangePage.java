import com.fasterxml.jackson.databind.node.ObjectNode;
import databases.MovieDatabase;
import error.handling.Errors;
import resources.primary.Action;
import resources.primary.Movie;
import resources.primary.Pages;
import resources.primary.SubPages;
import resources.primary.User;

import java.util.Stack;

public final class ChangePage {
   //these 3 fields act as return values basically
   private static SubPages actualPage;
   private static boolean needsChange;
   private static User currUser;

   /**
    * initializer
    */
   private ChangePage() {

   }

   /**
    * if the page is one other than movies, see details or logout, it just changes the page,
    * through "actualPage"
    * needsChange changes depending on whether or not there needs to be any change in the json
    * currUser is changed only on logout
    */
   public static void changePageActions(final ObjectNode temp, final Action action,
                                        final User currentUser, final SubPages currentPage,
                                        final Pages pages, final MovieDatabase movieDatabase,
                                        final SubPages tempPage, final Stack<SubPages> backStack) {
      needsChange = false; // we assume no change is needed, this changes however for the
      // movies and see details page
      currUser = currentUser; // the current user only channges on the logout pag
      if (!action.getPage().equals("see details")) {
         // this for the purchase, watch, like and rate on_page functions
         // since after entering the "see details" page of a movie, they can do those actions
         // without explicitly saying the movie's name
         OnPageActions.setDetailedMovie(null);
      }
      switch (action.getPage()) {
         case Pages.MOVIES -> {
            backStack.push(currentPage);
            actualPage = tempPage;
            movieDatabase.getAvailableMovies(currentUser);
            needsChange = Errors.errorOutput(null, movieDatabase.getCurrentMovies(),
                  currentUser, temp);
         }
         case Pages.SEE_DETAILS -> {
            backStack.push(currentPage);
            for (Movie movie : movieDatabase.getCurrentMovies()) {
               if (movie.getName().equals(action.getMovie())) {
                  actualPage = tempPage;
                  OnPageActions.setDetailedMovie(action.getMovie());
                  needsChange = Errors.errorOutput(null, Errors.toList(movie), currentUser, temp);
                  break;
               }
            }
            if (needsChange) { // there was an error so no more continuation of the function
               break;
            }
            actualPage = currentPage;
            needsChange = Errors.errorOutput(Errors.ERROR, null, null, temp);
         }
         case Pages.LOGOUT -> {
            actualPage = pages.getHomepage(false);
            currUser = null;
            backStack.clear();
         }
         default -> {
            if (currUser != null) {
               backStack.push(currentPage);
            }
            actualPage = tempPage;
         }
      }
   }

   /**
    * @return the page after the change page command was used (even if invalid for see_details )
    */
   public static SubPages getActualPage() {
      return actualPage;
   }

   /**
    * @return if the output needs to be updated
    */
   public static boolean getNeedsChange() {
      return needsChange;
   }

   /**
    * @return the user after the command was used (it is only changed for logout)
    */
   public static User getCurrUser() {
      return currUser;
   }
}
