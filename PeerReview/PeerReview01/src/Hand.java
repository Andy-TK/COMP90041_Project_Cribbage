

import java.util.ArrayList;

/**
*
* Purpose: Represent a Hand entity. The class also contains methods to 
* classify the hand
* 
*/
public class Hand {

   // enum for the different classifications 
   enum Classification {
       STRAIGHTFLUSH, FOURKIND, FULLHOUSE, FLUSH,
       STRAIGHT, THREEKIND, TWOPAIR, ONEPAIR, HIGHCARD
   };

   private int score = 0;	
   private final int PairScore = 2;
   private final int AllFlushesScore = 5;
   private final int FourFlushesScore = 4;
   private final int fifteenSScore = 2;
   private final int fifteen = 15;
   // immutable instance variable for the cards in the hand
   private final Card[] cards;
   
   // immutable instance variable for the ranks in the hand
   private final Card.CardRank[] ranks;
   
   // immutable instance variable for the suits in the hand
   private final Card.CardSuit[] suits;
   private final Card.CardRank[] threeCombinations;
   private final Card.CardRank[] fourCombinations;
   
   private int fifteenSNumber = 0;
   public ArrayList<Integer> list = new ArrayList<Integer>();

   // create a hand using the array of cards
   public Hand(Card[] cards) {
       // get the number of cards passed
       int cardCount = cards.length;
       
       // for each instance variable
       // create space for storing the cards based on the length
       this.cards = new Card[cardCount];
       this.ranks = new Card.CardRank[cardCount];
       this.suits = new Card.CardSuit[cardCount];
       //the number of combinations of three cards
       this.threeCombinations = new Card.CardRank[30];
       //the number of combinations of four cards
       this.fourCombinations = new Card.CardRank[20];
       
       // copy each card from the passed in parameter to the 
       // instance variables
       for (int i = 0; i < cardCount; i++) 
       {
           // get the card from the input array
           Card inputCard = cards[i];
           // get the rank & suit from the card
           Card.CardSuit inputSuit = inputCard.getSuit();
           Card.CardRank inputRank = inputCard.getRank();
           
           // create a new Card and store it in the array
           this.cards[i] = new Card(inputSuit, inputRank);
           this.ranks[i] = inputRank;
           this.suits[i] = inputSuit;
       }
   }

   /**
    *
    * Purpose: This method classifies a hand and returns a string
    * representation of the hand. It checks for each of the possible
    * options by calling other private methods one at a time
    * 
    */            
   public int classifyHand() {

       // method variables to help with storing the information to be 
       // returned by the method
	   
	   // calculate the number of oneNob 
       if(this.oneNob()) {
    	   this.score += 1;
       }
       
       //Calculate the type of flushes
       if(this.checkAllFlushes()) {
    	   this.score += this.AllFlushesScore;
       }
       else if(this.checkFourFlushes()) {
    	   this.score += this.FourFlushesScore;
       }
       else {
       }
       java.util.Arrays.sort(ranks);
       
       // calculate the point of pairs
       if(this.checkFourKind()) {
    	   this.score += this.PairScore * 6;
       }
       else if(this.checkThreeKind()) {
    	   if(this.checkThreeKindAndPair()) {
    		   this.score += this.PairScore * 4;
    	   }
    	   else {
    		   this.score += this.PairScore * 3;
    	   }
       }
       else if(this.checkTwoPair()) {
    	   this.score += this.PairScore * 2;
       }
       else if(this.checkOnePair()) {
    	   this.score += this.PairScore * 1;
       }
       else {}
       
       // calculate the point of runs
       countSubset();
       if(this.checkFiveRun()) {
    	   this.score += 5;
       }
       else if(this.checkFourRun() > 0 ) {
    	   this.score += 4 * this.checkFourRun();
       }
       else if(this.checkThreeRun()>0) {
    	   this.score += 3 * this.checkThreeRun();
       }
       else {
       }
       
       
       int[] cardValues = new int[ranks.length];
       for(int i=0; i<ranks.length; i++) {
    	   cardValues[i] = CardUtil.getValueForRank(ranks[i]);
       }
       
       //calculate the point of 15S
       fifteenS(cardValues,this.fifteen,0);
       this.score += this.fifteenSScore * this.fifteenSNumber;
       
       
       return this.score;
   }

   /**
    * Purpose: list all the combinations with three/four cards
    * */
   
   private void countSubset() {
	   this.threeCombinations[0] = this.ranks[0];
	   this.threeCombinations[1] = this.ranks[1];
	   this.threeCombinations[2] = this.ranks[2];
	   this.threeCombinations[3] = this.ranks[0];
	   this.threeCombinations[4] = this.ranks[1];
	   this.threeCombinations[5] = this.ranks[3];
	   this.threeCombinations[6] = this.ranks[0];
	   this.threeCombinations[7] = this.ranks[1];
	   this.threeCombinations[8] = this.ranks[4];
	   this.threeCombinations[9] = this.ranks[0];
	   this.threeCombinations[10] = this.ranks[2];
	   this.threeCombinations[11] = this.ranks[3];
	   this.threeCombinations[12] = this.ranks[0];
	   this.threeCombinations[13] = this.ranks[2];
	   this.threeCombinations[14] = this.ranks[4];
	   this.threeCombinations[15] = this.ranks[0];
	   this.threeCombinations[16] = this.ranks[3];
	   this.threeCombinations[17] = this.ranks[4];
	   this.threeCombinations[18] = this.ranks[1];
	   this.threeCombinations[19] = this.ranks[2];
	   this.threeCombinations[20] = this.ranks[3];
	   this.threeCombinations[21] = this.ranks[1];
	   this.threeCombinations[22] = this.ranks[2];
	   this.threeCombinations[23] = this.ranks[4];
	   this.threeCombinations[24] = this.ranks[1];
	   this.threeCombinations[25] = this.ranks[3];
	   this.threeCombinations[26] = this.ranks[4];
	   this.threeCombinations[27] = this.ranks[2];
	   this.threeCombinations[28] = this.ranks[3];
	   this.threeCombinations[29] = this.ranks[4];
	   
	   
	   this.fourCombinations[0] = this.ranks[0];
	   this.fourCombinations[1] = this.ranks[1];
	   this.fourCombinations[2] = this.ranks[2];
	   this.fourCombinations[3] = this.ranks[3];
	   this.fourCombinations[4] = this.ranks[0];
	   this.fourCombinations[5] = this.ranks[1];
	   this.fourCombinations[6] = this.ranks[2];
	   this.fourCombinations[7] = this.ranks[4];
	   this.fourCombinations[8] = this.ranks[0];
	   this.fourCombinations[9] = this.ranks[2];
	   this.fourCombinations[10] = this.ranks[3];
	   this.fourCombinations[11] = this.ranks[4];
	   this.fourCombinations[12] = this.ranks[1];
	   this.fourCombinations[13] = this.ranks[2];
	   this.fourCombinations[14] = this.ranks[3];
	   this.fourCombinations[15] = this.ranks[4];
	   this.fourCombinations[16] = this.ranks[0];
	   this.fourCombinations[17] = this.ranks[1];
	   this.fourCombinations[18] = this.ranks[3];
	   this.fourCombinations[19] = this.ranks[4];
	   
   }

   /**
    * Purpose: calculate all the subset satisfied 15S
    * using recursive method here.
    * */
   
   private void fifteenS(int[] A, int m, int step){
	   while(step < A.length) {
           list.add(A[step]); 
           if(getSum(list) == m) {
        	   this.fifteenSNumber += 1;
           }
           step++;
           fifteenS(A, m, step);
           list.remove(list.size() - 1);
       }
   }
   

   /**
    * Purpose: tools function, calculate the sum of ranks
    * of a set of cards
    * */
   public int getSum(ArrayList<Integer> list) {
       int sum = 0;
       for(int i = 0;i < list.size();i++)
           sum += list.get(i);
       return sum;
   }
   
   /**
    * 
    * Purpose:find out whether there is a oneNob
    * */
   private boolean oneNob() {
	   boolean jackCheck = false;
	   for (int i = 0; i < ranks.length-1; i++) {
		   if((ranks[i].compareTo(Card.CardRank.Jack)==0) 
				   && (suits[i].compareTo(suits[suits.length-1])==0)){
			   jackCheck = true;
		   }
	   }
	   return jackCheck;
   }
   
   /**
    * Purpose: find out whether there is a five run
    * */
   private boolean checkFiveRun() {
	   boolean straightCheck = false;
       // implement logic to check if its a straight 
       for (int i = 1; i < ranks.length; i++) {
           if (ranks[i].compareTo(ranks[i - 1]) == 1) 
           {
               straightCheck = true;
           } else 
           {
               straightCheck = false;
               return straightCheck;
           }
       }
       return straightCheck;
   }
   
   /**
    * Purpose: find out whether there is a four run
    * */  
   private int checkFourRun() {
       boolean straightCheck = false;
       int fourRunsCount = 0;
       // implement logic to check if its a straight 
       for(int j = 0; j<5; j++) {
    	   for (int i = j*4 + 1; i < j*4 + 4; i++) {
    		   if (this.fourCombinations[i].compareTo(this.fourCombinations[i - 1]) == 1) 
    		   {
    			   straightCheck = true;
    		   } else 
    		   {
    			   straightCheck = false;
    			   break;
    		   }
    	   }
    	   if(straightCheck) {
    		   fourRunsCount += 1;
    	   }
       }
       return fourRunsCount;
   }
   
   /**
    *
    * Purpose: This method checks if the hand is a straight.
    * It does this by checking if the ranks are all in the right sequence
    * 
    */      
   private int checkThreeRun() {
       boolean straightCheck = false;
       int threeRunsCount = 0;
       // implement logic to check if its a straight 
       for(int j = 0; j< 10;j++) {
    	   for (int i = j*3 + 1; i < j*3 + 3; i++) {
    		   if (this.threeCombinations[i].compareTo(this.threeCombinations[i - 1]) == 1) 
    		   {
    			   straightCheck = true;
    		   } else 
    		   {
    			   straightCheck = false;
    			   break;
    		   }
    	   }
    	   if(straightCheck) {
    		   threeRunsCount += 1;
    	   }
       }
       return threeRunsCount;
   }
   
   /**
    *
    * Purpose: This method checks if the hand is a flush.
    * It does this by checking if the suits are the same
    * 
    */      
   private boolean checkAllFlushes() {
       boolean flushCheck = false;
       // implement logic to check if its a  flush
       for (int i = 1; i < suits.length; i++) {
           if (suits[i].compareTo(suits[i - 1]) == 0) 
           {
               flushCheck = true;
           } 
           else 
           {
               flushCheck = false;
               return flushCheck;
           }
       }
       return flushCheck;
   }
   
   private boolean checkFourFlushes() {
       boolean flushCheck = false;
       // implement logic to check if its a  flush
       for (int i = 1; i < suits.length-1; i++) {
           if (suits[i].compareTo(suits[i - 1]) == 0) 
           {
               flushCheck = true;
           } 
           else 
           {
               flushCheck = false;
               return flushCheck;
           }
       }
       return flushCheck;
   }

   /**
    *
    * Purpose: This method checks if the hand is a Four of a kind.
    * It does this by 
    * 1 - checking if there are any cards with the same rank
    * 2 - storing the common rank if a match is found
    * 3 - keeping a count of the matching ranks
    * 4 - at the end, seeing if the count of matching ranks is 3
    */      
   private boolean checkFourKind() {
       boolean checkFour = false;
       // variable to hold the count of matching ranks
       int run = 0;
       Card.CardRank innerRank = null;
       for (int i = 0; i < ranks.length - 1; i++) 
       {
           if (ranks[i + 1].compareTo(ranks[i]) == 0) 
           {
               if (innerRank == null) 
               {
                   innerRank = ranks[i];
               }
               if (innerRank.compareTo(ranks[i]) == 0) 
               {
                   run++;
               }
           }
       }
       if (run == 3) 
       {
           checkFour = true;
       }
       return checkFour;
   }

   /**
    *
    * Purpose: This method checks if the hand is a Three of a kind.
    * It does this by 
    * 1 - checking if there are any cards with the same rank
    * 2 - storing the common rank if a match is found
    * 3 - keeping a count of the matching ranks
    * 4 - at the end, seeing if the count of matching ranks is 2
    */    
   private boolean checkThreeKind() {
       boolean checkThree = false;
       // implement logic to check if its three of a kind
       int run = 0;
       for (int i = 0; i < ranks.length - 1; i++) 
       {
           if (ranks[i + 1].compareTo(ranks[i]) == 0) 
           {
               run++;
           }
           else {
        	   if(run == 2) {
        		   checkThree = true;
        	   }
        	   else {
        		   run = 0;
        	   }
           }
       }
       if (run == 2) 
       {
           checkThree = true;
       }
       return checkThree;
   }

   /**
    *
    * Purpose: This method checks if the hand has a Pair.
    * It does this by 
    * 1 - checking if there are any cards with the same rank
    * 2 - if yes, it returns the rank 
    */ 
   private boolean checkOnePair() {
       boolean checkOnePair = false;
       for (int i = 0; i < ranks.length - 1; i++) 
       {
           // found a pair, so return
           if (ranks[i + 1].compareTo(ranks[i]) == 0) 
           {
               checkOnePair = true;
               return checkOnePair;
           }
       }
       return checkOnePair;
   }

   /**
    *
    * Purpose: This method checks if the hand has a Two Pair.
    * It does this by 
    * 1 - checking if there are any cards with the same rank
    * 2 - if yes, it stores the rank as the first of a pair
    * 3 - if another pair is found, it compares with the stored rank
    * 4 - based on the result of comparing the new with the existing rank
    *     it updates the instance variables
    * 5-  in case it encounters 3 cards of the same rank, it assumes that
    *     this is not a Two Pair so it returns.
    */     
   private boolean checkTwoPair() 
   {
       boolean checkTwoPair = false;
       Card.CardRank firstPairRank = null;
       for (int i = 0; i < ranks.length - 1; i++) 
       {
           // found a match, so keep it handy
           if (ranks[i + 1].compareTo(ranks[i]) == 0) 
           {
               if (firstPairRank == null) 
               {
                   firstPairRank = ranks[i];
               } 
               else if (firstPairRank.compareTo(ranks[i]) == 0) 
               {
                   checkTwoPair = false;
                   return checkTwoPair;                    
               } 
               else 
               {
                   checkTwoPair = true;
                   // check which is the lower rank
                   if (ranks[i].compareTo(firstPairRank) < 0) 
                   {
                   } 
                   else 
                   {
                   }
               }
           }
       }
       return checkTwoPair;
   }

   
 /**
  * 
  * purpose: Check if the card contains a three kind and a pair
  * For example 3C 3D 3S 7S 7D
  * */
   
   private boolean checkThreeKindAndPair() 
   {
	   int checkThreePair = 0;
       for (int i = 0; i < ranks.length - 1; i++) 
       {
           // found a pair, so return
           if (ranks[i + 1].compareTo(ranks[i]) == 0) 
           {
               checkThreePair += 1;
           }
       }
       if(checkThreePair == 3) {
    	   return true;
       }
       else {
    	   return false;
       } 
   }
}
