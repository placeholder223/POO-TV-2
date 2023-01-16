package databases;

import com.fasterxml.jackson.annotation.JsonIgnore;
import resources.primary.User;

import java.util.ArrayList;
import java.util.Hashtable;

public final class UserDatabase {
   @JsonIgnore
   private static UserDatabase instance = null;
   /**
    * we also keep an ArrayList of the total users in case we need it. The program could do
    * without this by using the values of the HashTable, but it would be more efficient to do it
    * like this if we need to get the list multiple times.
    */
   private static ArrayList<User> users;
   @JsonIgnore
   private static Hashtable<String, String> checkingPassword; // hashtable for passwords
   @JsonIgnore
   private static Hashtable<String, User> retrievingAccount; // hashtable for the whole user

   // both Hashtables are detailed a little bit below, in the addToUserHash java doc

   /**
    * the constructor for the  of users
    */
   private UserDatabase() {
      users = new ArrayList<>();
      checkingPassword = new Hashtable<>();
      retrievingAccount = new Hashtable<>();
   }

   /**
    * the singleton getInstance
    */
   public static UserDatabase getInstance() {
      if (instance == null) {
         instance = new UserDatabase();
      }
      return instance;
   }

   /**
    * adds the user to the 2 HashTables of users, hashtable is preferred over an ArrayList
    * because it's much faster.
    * The checkingPassword HashTable uses the name of the user as the key,
    * and the password as it's value, meaning only one user with a said name can be used, but
    * it's instantaneous
    * The retrievingAccount HashTable uses the name of the desired user as the key, and the user
    * itself as the value. This means there can't be multiple users with the same name
    */
   @JsonIgnore
   public void addUserToHash(final User user) {
      checkingPassword.put(user.getCredentials().getName(), user.getCredentials().getPassword());
      retrievingAccount.put(user.getCredentials().getName(), user);
      if (!users.contains(user)) {
         addUser(user);
      }
   }

   /**
    * retrieves the user from the database, more accurately from the HashTable
    * @return the desired user
    */
   @JsonIgnore
   public User retrieveUser(final String name) {
      return retrievingAccount.get(name);
   }

   /**
    * checks if the user and password combo are valid
    * @return true if valid, otherwise false
    */
   @JsonIgnore
   public boolean isLoginValid(final String name, final String password) {
      if (!checkingPassword.containsKey(name)) {
         return false;
      }
      return password.compareTo(checkingPassword.get(name)) == 0;
   }

   /**
    * adds the users to the ArrayList and also to the HashTable
    */
   public void setUsers(final ArrayList<User> newUsers) {
      users = newUsers;
      for (User user : newUsers) {
         addUserToHash(user);
      }
   }

   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public ArrayList<User> getUsers() {
      return users;
   }

   /**
    * adds an user to the database
    */
   public void addUser(final User user) {
      users.add(user);
   }
   /**
    * destroys the database, in case we don't need it anymore and want to create another
    */
   public void destroyDatabase() {
      instance = new UserDatabase();
   }
}
