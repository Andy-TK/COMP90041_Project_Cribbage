/**
* Purpose: This class defines a set of utility methods that perform 
* 1 - Accept a Char for Rank and return the corresponding string form
* 2 - Accept a Char for suit and return the corresponding string form
* 3 - Accept a Char for Rank, as enum, and return the corresponding string 
* form
 */
public class CardUtil {

   /**
    * Purpose: This method accepts a rank and returns the String form.
    * If it does not find a matching suit, it returns a blank string.
    */
   public static String getStringForRank(char rank) 
   {
       String strRank = "";
       // convert into upper case to enable case-insensitive comparison
       char internalRank = Character.toUpperCase(rank);
       switch (internalRank) 
       {
           case '2': 
           {
               strRank = "Two";
               break;
           }
           case '3': 
           {
               strRank = "Three";
               break;
           }
           case '4': 
           {
               strRank = "Four";
               break;
           }
           case '5': 
           {
               strRank = "Five";
               break;
           }
           case '6': 
           {
               strRank = "Six";
               break;
           }
           case '7': 
           {
               strRank = "Seven";
               break;
           }
           case '8': 
           {
               strRank = "Eight";
               break;
           }
           case '9': 
           {
               strRank = "Nine";
               break;
           }
           case 'T': 
           {
               strRank = "Ten";
               break;
           }
           case 'J': 
           {
               strRank = "Jack";
               break;
           }
           case 'Q': 
           {
               strRank = "Queen";
               break;
           }
           case 'K': 
           {
               strRank = "King";
               break;
           }
           case 'A': 
           {
               strRank = "Ace";
               break;
           }
       }
       return strRank;
   }

   /**
    * Purpose: This method accepts a rank, in enum, and returns the 
    * String form. If it does not find a matching suit,
    * it returns a blank string.
    */
   public static String getStringForRank(Card.CardRank rank) {
       String strRank = "";
       switch (rank) 
       {
           case Two: 
           {
               strRank = "2";
               break;
           }
           case Three: 
           {
               strRank = "3";
               break;
           }
           case Four: 
           {
               strRank = "4";
               break;
           }
           case Five: 
           {
               strRank = "5";
               break;
           }
           case Six: 
           {
               strRank = "6";
               break;
           }
           case Seven: 
           {
               strRank = "7";
               break;
           }
           case Eight: {
               strRank = "8";
               break;
           }
           case Nine: 
           {
               strRank = "9";
               break;
           }
           case Ten: 
           {
               strRank = "10";
               break;
           }
           case Jack: 
           {
               strRank = "Jack";
               break;
           }
           case Queen: 
           {
               strRank = "Queen";
               break;
           }
           case King: 
           {
               strRank = "King";
               break;
           }
           case Ace: 
           {
               strRank = "Ace";
               break;
           }
       }
       return strRank;
   }
   
   /**
    * Purpose: This method takes a card rank and return the
    * integer data of the rank. If it does not match with any
    * rank, it will return 0 which is a invalid value.
    */  
   
   public static int getValueForRank(Card.CardRank rank) {
       int intRank = 0;
       switch (rank) 
       {
           case Two: 
           {
               intRank = 2;
               break;
           }
           case Three: 
           {
        	   intRank = 3;
               break;
           }
           case Four: 
           {
        	   intRank = 4;
               break;
           }
           case Five: 
           {
        	   intRank = 5;
               break;
           }
           case Six: 
           {
        	   intRank = 6;
               break;
           }
           case Seven: 
           {
        	   intRank = 7;
               break;
           }
           case Eight: {
        	   intRank = 8;
               break;
           }
           case Nine: 
           {
        	   intRank = 9;
               break;
           }
           case Ten: 
           {
        	   intRank = 10;
               break;
           }
           case Jack: 
           {
        	   intRank = 10;
               break;
           }
           case Queen: 
           {
        	   intRank = 10;
               break;
           }
           case King: 
           {
        	   intRank = 10;
               break;
           }
           case Ace: 
           {
        	   intRank = 1;
               break;
           }
       }
       return intRank;
   }
   
   

   /**
    * Purpose: This method takes a single character and 
    * returns the matching suit. If it does not find a matching suit,
    * it returns a blank string.
    */    
   public static String getStringForSuit(char suit) 
   {
       String strSuit = "";
       char internalSuit = Character.toUpperCase(suit);
       // convert into upper case to enable case-insensitive comparison
       switch (internalSuit) 
       {
           case 'C': 
           {
               strSuit = "CLUBS";
               break;
           }
           case 'D': 
           {
               strSuit = "DIAMONDS";
               break;
           }
           case 'H': 
           {
               strSuit = "HEARTS";
               break;
           }
           case 'S': 
           {
               strSuit = "SPADES";
               break;
           }
       }
       return strSuit;
   }
}