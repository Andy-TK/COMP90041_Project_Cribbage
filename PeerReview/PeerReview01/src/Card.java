
/**
* Purpose: Represent a Card entity. The class also contains enums
* defined to represent a rank and suit
*/
public class Card {

   // enum for the suits
   public enum CardSuit 
   {
       CLUBS, DIAMONDS, HEARTS, SPADES
   };

   // enum for the ranks
   public enum CardRank 
   {
       Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack,
       Queen, King
   };

   // instance variable for the suit of this card. 
   // Immutable since once its created it cannot be changed
   private final CardSuit suit;
   // instance variable for the rank of this card
   private final CardRank rank;

   // Constructor that accepts string values of suit & rank
   public Card(String suit, String rank) {
       this.suit = CardSuit.valueOf(suit);
       this.rank = CardRank.valueOf(rank);
   }

   // Constructor that accepts enum values of suit & rank    
   public Card(CardSuit suit, CardRank rank) {
       this.suit = suit;
       this.rank = rank;
   }

   // Accessor for suit
   public CardSuit getSuit() {
       return suit;
   }

   // Accessor for rank    
   public CardRank getRank() {
       return rank;
   }

}