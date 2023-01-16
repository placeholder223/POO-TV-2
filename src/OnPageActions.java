import com.fasterxml.jackson.databind.node.ObjectNode;
import databases.MovieDatabase;
import databases.UserDatabase;
import error.handling.Errors;
import resources.primary.Action;
import resources.primary.Movie;
import resources.primary.Pages;
import resources.primary.SubPages;
import resources.primary.User;

import java.util.ArrayList;
import java.util.Stack;

public final class OnPageActions {
   public static final int MAXIMUM_RATE = 5;
   private static boolean needsChange;
   private static SubPages currPage;
   private static User currUser;
   private static String detailedMovie;

   /**
    * it sets the movie for which "see details" was accessed
    * this is needed because while on that page, the use can purchase/watch/like/rate without
    * specifying the movie, so the program needs to know what to byt
    */
   public static void setDetailedMovie(final String detailedMovie) {
      OnPageActions.detailedMovie = detailedMovie;
   }

   /**
    * default constructor
    */
   private OnPageActions() {

   }

   /**
    * does the appropriate on_page action based on the action name
    */
   public static void onPageActions(final ObjectNode temp, final Action action,
                                    final User currentUser, final SubPages currentPage,
                                    final Pages pages, final UserDatabase userDatabase,
                                    final MovieDatabase movieDatabase,
                                    final Stack<SubPages> backStack) {
      currUser = currentUser;
      currPage = currentPage;
      needsChange = false;
      switch (action.getFeature()) {
         case Pages.LOGIN -> {
            String name = action.getCredentials().getName();
            String password = action.getCredentials().getPassword();
            if (userDatabase.isLoginValid(name, password)) {
               currUser = userDatabase.retrieveUser(name);
               currPage = pages.getHomepage(true);
               needsChange = Errors.errorOutput(null, null, currUser, temp);
               movieDatabase.getAvailableMovies(currUser);

               break;
            }
            currPage = pages.getHomepage(false);
            needsChange = Errors.errorOutput(Errors.ERROR, null, null, temp);
         }
         case Pages.REGISTER -> {
            currUser = new User(action.getCredentials());
            userDatabase.addUserToHash(currUser);
            needsChange = Errors.errorOutput(null, null, currUser, temp);
            currPage = pages.getHomepage(true);
            movieDatabase.getAvailableMovies(currUser);
         }
         case Pages.BUY_PREMIUM_ACCOUNT -> {
            if (currentUser.getTokensCount() < User.NUMBER_TOKENS_FOR_PREMIUM
                  || currentUser.getCredentials().getAccountType().equals(User.PREMIUM_STATUS)) {
               needsChange = Errors.errorOutput(Errors.ERROR, null,
                     currentUser, temp);
               break;
            }
            currentUser.addToTokensCount(-User.NUMBER_TOKENS_FOR_PREMIUM);
            currentUser.getCredentials().setAccountType(User.PREMIUM_STATUS);
         }
         case Pages.BUY_TOKENS -> {
            int balance = Integer.parseInt(currentUser.getCredentials().getBalance());
            int boughtCount = Integer.parseInt(action.getCount());
            if (balance < boughtCount) {
               needsChange = Errors.errorOutput(Errors.ERROR, null,
                     currentUser, temp);
               break;
            }
            balance -= boughtCount;
            currentUser.getCredentials().setBalance(Integer.toString(balance));
            currentUser.addToTokensCount(Integer.parseInt(action.getCount()));
         }
         case Pages.SEARCH -> {
            ArrayList<Movie> searchedMovies = movieDatabase.search(action.getStartsWith());
            needsChange = Errors.errorOutput(null, searchedMovies,
                  currentUser, temp);
         }

         case Pages.FILTER -> {
            movieDatabase.getAvailableMovies(currentUser);
            if (action.getFilters().getContains() != null) {
               movieDatabase.contains(action.getFilters().getContains());
            }
            if (action.getFilters().getSort() != null) {
               movieDatabase.filter(action.getFilters().getSort().getDuration(),
                     action.getFilters().getSort().getRating());
            }
            needsChange = Errors.errorOutput(null, movieDatabase.getCurrentMovies(),
                  currentUser, temp);
         }
         case Pages.PURCHASE -> {
            Movie wantedMovie;
            if (action.getMovie() != null) { // this if is because you can buy a movie on the
               // upgrades tab, by specfying the name
               wantedMovie = movieDatabase.get(action.getMovie());
            } else { // but you don't need to specify the name if on the page see_details
               wantedMovie = movieDatabase.get(detailedMovie);
            }
            if (wantedMovie == null || !movieDatabase.getCurrentMovies().contains(wantedMovie)) {
               // if the wanted movie doesn't exist
               needsChange = Errors.errorOutput(Errors.ERROR, null, currentUser, temp);
               break;
            }
            if (!currentUser.getCredentials().getAccountType().equals(User.PREMIUM_STATUS)) {
               // very dumb thing, all users start with 15 free movies, but for the standard
               // account they don't count. Why is this?
               currentUser.addToNumFreeMovies(-User.STARTING_FREE_MOVIES);
            }
            boolean hasUsedFreeMovie = false;
            if (currentUser.getNumFreePremiumMovies() > 0) {
               currentUser.addToNumFreeMovies(-1);
               currentUser.getPurchasedMovies().add(wantedMovie);
               hasUsedFreeMovie = true;
            }
            if (!currentUser.getCredentials().getAccountType().equals(User.PREMIUM_STATUS)) {
               // put the back the movies so the output is right
               currentUser.addToNumFreeMovies(User.STARTING_FREE_MOVIES);
            }
            if (hasUsedFreeMovie) {
               needsChange = Errors.errorOutput(null, Errors.toList(wantedMovie), currentUser,
                     temp);
               break;
            }
            if (currentUser.getTokensCount() >= Movie.NUM_TOKENS_FOR_BUYING) {
               // check if the user has enough tokens
               currentUser.addToTokensCount(-Movie.NUM_TOKENS_FOR_BUYING);
               currentUser.getPurchasedMovies().add(wantedMovie);
               needsChange = Errors.errorOutput(null, Errors.toList(wantedMovie), currentUser,
                     temp);
               break;
            }
            needsChange = Errors.errorOutput(Errors.ERROR, movieDatabase.getCurrentMovies(),
                  currentUser, temp);
         }
         case Pages.WATCH -> {
            Movie wantedMovie = movieDatabase.get(detailedMovie);
            if (wantedMovie == null || !currentUser.getPurchasedMovies().contains(wantedMovie)) {
               needsChange = Errors.errorOutput(Errors.ERROR, null, null, temp);
               break;
            }
            currentUser.getWatchedMovies().add(wantedMovie);
            needsChange = Errors.errorOutput(null, Errors.toList(wantedMovie), currentUser,
                  temp);
         }
         case Pages.LIKE -> {
            Movie wantedMovie = movieDatabase.get(detailedMovie);
            if (wantedMovie == null || !currentUser.getWatchedMovies().contains(wantedMovie)) {
               needsChange = Errors.errorOutput(Errors.ERROR, null, null, temp);
               break;
            }
            currentUser.addToLikedMovies(wantedMovie);
            needsChange = Errors.errorOutput(null, Errors.toList(wantedMovie), currentUser,
                  temp);
         }
         case Pages.RATE -> {
            Movie wantedMovie;
            if (action.getMovie() != null) {
               wantedMovie = movieDatabase.get(action.getMovie());
            } else {
               wantedMovie = movieDatabase.get(detailedMovie);
            }
            if (wantedMovie == null || !currentUser.getWatchedMovies().contains(wantedMovie)
                  || action.getRate() > MAXIMUM_RATE || action.getRate() < 0) {
               needsChange = Errors.errorOutput(Errors.ERROR, null, null, temp);
               break;
            }
            currentUser.addToRatedMovies(wantedMovie, action.getRate());
            needsChange = Errors.errorOutput(null, Errors.toList(wantedMovie), currentUser,
                  temp);
         }
         case Pages.SUBSCRIBE -> {
            if(!movieDatabase.get(detailedMovie).getGenres().contains(
                  action.getSubscribedGenre())){
               needsChange = Errors.errorOutput(Errors.ERROR, null, null, temp);
               break;
            }
            if(currentUser.getSubscriptions().contains(action.getSubscribedGenre())){
               needsChange = Errors.errorOutput(Errors.ERROR, null, null, temp);
               break;
            }
            currentUser.addSubscription(action.getSubscribedGenre());
         }
         default -> {
         }
      }
   }

   /**
    * returns the needsChange value, which tells whether or not the output needs to be updated
    */
   public static boolean getNeedsChange() {
      return needsChange;
   }

   /**
    * returns the current page if it is changed (only for login and register)
    */
   public static SubPages getCurrPage() {
      return currPage;
   }

   /**
    * returns the current user if it is changed (again, only for login and register)
    */
   public static User getCurrUser() {
      return currUser;
   }
}
