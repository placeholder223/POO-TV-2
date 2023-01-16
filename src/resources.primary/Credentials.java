package resources.primary;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Credentials {
   private String name;
   private String password;
   private String accountType;
   private String country;
   private String balance = "0";

   /**
    * default constructor
    */
   public Credentials() {
   }

   /**
    * copy construcctor
    */
   public Credentials(final Credentials other) {
      this.name = other.name;
      this.password = other.password;
      this.accountType = other.accountType;
      this.country = other.country;
      this.balance = other.balance;
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
   public String getPassword() {
      return password;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setPassword(final String password) {
      this.password = password;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public String getAccountType() {
      return accountType;
      //return "hatz";
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setAccountType(final String accountType) {
      this.accountType = accountType;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public String getCountry() {
      return country;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setCountry(final String country) {
      this.country = country;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public String getBalance() {
      return balance;
   }
   /**
    * all hail the mighty code style checker (do I really need to explain what this does?)
    */
   public void setBalance(final String balance) {
      this.balance = balance;
   }
}
