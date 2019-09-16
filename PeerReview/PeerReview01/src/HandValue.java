/**
 * Purpose: this class hold the main method of the program.
 * The class uses some other classes, includes Card.java, CardUtil.java and
 * Hand.java.
 * */
public class HandValue {
    // Define constant for the number of cards in a single hand
    public static final int CARDS_IN_HAND = 5;
    // Define constant for the number of characters in a card
    public static final int CHARS_IN_CARD = 2;    
    
    /**
     * Purpose: This is the main method. It has the following steps
     * 1 - Read in the inputs provided and validate if they are valid
     * 2 - Create instances of Cards for each card and populate Hand
     * 3 - Attempt to classify the hand based on the rules
     * 4 - Print the result of the classification or the error message
     */
    public static void main(String[] args) 
    {
        
        // get the number of arguments passed
        final int numberOfCards = args.length;

        // read each hand from the list provided at the command line
        if (numberOfCards == CARDS_IN_HAND) 
        {
            // create an array for storing the cards
            Card[] cards = new Card[CARDS_IN_HAND];
            for (int index = 0; index < numberOfCards; index++) 
            {
                // get each hand details
                String hand = args[index];
                // check how many characters are there in the hand
                if (hand.length() == CHARS_IN_CARD) 
                {
                    // convert from single characters into strings.
                    // this enables us to assign the create the Card instance
                    String rank = 
                            CardUtil.getStringForRank(hand.charAt(0));
                    String suit = 
                            CardUtil.getStringForSuit(hand.charAt(1));
                    // check if the rank & suit are valid
                    if (!(rank.equals("")) && (!suit.equals(""))) 
                    {
                        // create a new instance of Card
                        Card card = new Card(suit, rank);
                        // add the card to the array
                        cards[index] = card;
                    } 
                    else 
                    {
                        HandleInvalidCard(args, index);
                    }
                } 
                else 
                {
                    // invalid card specified. Program will exit!
                    HandleInvalidCard(args, index);
                }
            }
            // once the cards have been created, create an instance of a hand
            Hand hand = new Hand(cards);

            // Attempt to classify the hand and print the result
            System.out.println(hand.classifyHand());
        } 
        //check if input is not greater than zero or not a multiple of 5
        else if ((numberOfCards % CARDS_IN_HAND != 0) || (numberOfCards == 0))
        {
            HandleInvalidHand();
        } 
        else 
        {
            HandleUnSupportedHand();
        }
    }

    /**
     * Purpose: This method prints the error message for any input that 
     * is not supported by the program
     */    
    private static void HandleUnSupportedHand() 
    {
        System.out.println("NOT UNDERTAKEN");
        System.exit(1);        
    }

    /**
     * Purpose: This method prints the error if an invalid hand is provided
     */    
    private static void HandleInvalidHand() 
    {
        System.out.println
        ("Error: wrong number of arguments; must be a multiple of 5");
        System.exit(1);
    }

    /**
     * Purpose: This method handles if an invalid card is provided
     */    
    private static void HandleInvalidCard(String[] args, int index) {
        // invalid card specified. Program will exit!
        System.out.println("Error: invalid card name '" + args[index] + "'");
        System.exit(1);
    }
}
