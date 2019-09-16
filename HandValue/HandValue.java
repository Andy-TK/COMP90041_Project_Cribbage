/**
 * This main class provides a series of static methods to calculate the value of
 * a hand for Cribbage Game. The methods fifteens, pairs, runs, flushes and
 * oneForHisNob represents the five rules to calculate the score of a hand.
 * Other methods are used for converting the input from the command line into
 * appropriate types for the methods of the rules. 
 * 
 * @author Ye Yang <yey11@student.unimelb.edu.au>
 */
public class HandValue {

    /** Integer used to store the value of a hand for the game. */
    private static int score = 0;

    /** This method calculates and prints the total score of a hand for
     *  the game as an output. It receives 5 cards on the command line,
     *  while the first 4 cards for the hand and the 5th card for the start
     *  card. Cards should be entered on the command line as two-character
     *  strings, the first being an upper-case A for Ace, K for King, Q for
     *  Queen, J for Jack, T for Ten, or digit between 2 and 9 for ranks 2-9.
     *  The second character should be a C for Clubs (♣), D for Diamonds (♦),
     *  H for Hearts (♥), or a S for Spades (♠).
     *
     *  @param args an array of the double-character strings on the command line.
     */
    public static void main(String[] args) {

        /** The suit is used to store the card suits. */
        char[] suits = getSuit(args);

        /** The ranks is used to store the card ranks. */
        CribbageRank[] ranks = getRank(args);

        /** The comb is used to store the combinations of card ranks. */
        CribbageRank[][] comb = Combinations.combinations(ranks);

        /** The sortComb is used to store the combinations of sorted card ranks. */
        CribbageRank[][] sortComb = sortComb(comb);

        score = fifteens(comb) + pairs(sortComb) + runs(sortComb)
            + flushes(suits) + oneForHisNob(ranks, suits);
        System.out.println(score);
    }

    /** This method calculates the points based on the rule of fifteens.
     *  @param comb an array of the "sub-arrays" of the input card ranks.
     *  @return the integer points of fifteens.
     */
    private static int fifteens(CribbageRank[][] comb) {
        int points = 0;
        for (CribbageRank[] subComb : comb) {
            int sum = 0;
            for (CribbageRank rank : subComb) {
                sum += rank.faceValue();
            }
            if (sum == 15) {
                points += 2;
            }
        }
        return points;
    }

    /** This method calculates the points based on the rule of pairs.
     *  @param sortComb an array of the "sub-arrays" of the sorted card ranks.
     *  @return the integer points of pairs.
     */
    private static int pairs(CribbageRank[][] sortComb) {
        int points = 0;
        for (CribbageRank[] subComb : sortComb) {
            if (subComb.length == 2 && (subComb[0] == subComb[1])) {
                points += 2;
            }
        }
        return points;
    }

    /** This method calculates the points based on the rule of runs.
     *  @param sortComb an array of the "sub-arrays" of the sorted card ranks.
     *  @return the integer points of runs.
     */
    private static int runs(CribbageRank[][] sortComb) {
        int fiveRuns = 0;
        int fourRuns = 0;
        int threeRuns = 0;
        for (CribbageRank[] subComb : sortComb) {
            switch (subComb.length) {
                case 5:
                    if ((subComb[0].nextHigher() == subComb[1])
                        && (subComb[1].nextHigher() == subComb[2])
                        && (subComb[2].nextHigher() == subComb[3])
                        && (subComb[3].nextHigher() == subComb[4])) {
                        fiveRuns++;
                    }
                    break;
                case 4:
                    if ((subComb[0].nextHigher() == subComb[1])
                        && (subComb[1].nextHigher() == subComb[2])
                        && (subComb[2].nextHigher() == subComb[3])) {
                        fourRuns++;
                    }
                    break;
                case 3:
                    if ((subComb[0].nextHigher() == subComb[1])
                        && (subComb[1].nextHigher() == subComb[2])) {
                        threeRuns++;
                    }
                    break;
            }
        }
        return fiveRuns > 0? 5:(fourRuns > 0? 4 * fourRuns:(threeRuns > 0? 3 * threeRuns:0));
    }

    /** This method calculates the points based on the rule of flushes.
     *  @param suits a char array of the input card suits.
     *  @return the integer points of flushes.
     */
    private static int flushes(char[] suits) {
        int points = 0;
        boolean sameHandSuits = true;
        for (int i = 1; i < suits.length - 1; i++) {
            if (suits[i - 1] != suits[i]) {
                sameHandSuits = false;
            }
        }
        if (sameHandSuits) {
            points = 4;
            if (suits[0] == suits[suits.length - 1]) {
                points++;
            }
        }
        return points;
    }

    /** This method calculates the points based on the rule of one for his nob.
     *  @param ranks an CribbageRank array of the input card ranks.
     *  @param suits a char array of the input card suits.
     *  @return the integer points of on for his nob.
     */
    private static int oneForHisNob(CribbageRank[] ranks, char[] suits) {
        int points = 0;
        for (int i = 0; i < ranks.length - 1; i++) {
            if (ranks[i].abbrev() == 'J' && suits[i] == suits[suits.length - 1]) {
                points = 1;
            }
        }
        return points;
    }

    /** This method constructs an CribbageRank array of input card ranks.
     *  @param args a double-character string array of input card ranks and suits.
     *  @return an CribbageRank array of input card ranks.
     */
    private static CribbageRank[] getRank(String[] args) {
        CribbageRank[] ranks = new CribbageRank[args.length];
        for (int i = 0; i < ranks.length; i++) {
            switch (args[i].charAt(0)) {
                case 'A':
                    ranks[i] = CribbageRank.ACE;
                    break;
                case '2':
                    ranks[i] = CribbageRank.TWO;
                    break;
                case '3':
                    ranks[i] = CribbageRank.THREE;
                    break;
                case '4':
                    ranks[i] = CribbageRank.FOUR;
                    break;
                case '5':
                    ranks[i] = CribbageRank.FIVE;
                    break;
                case '6':
                    ranks[i] = CribbageRank.SIX;
                    break;
                case '7':
                    ranks[i] = CribbageRank.SEVEN;
                    break;
                case '8':
                    ranks[i] = CribbageRank.EIGHT;
                    break;
                case '9':
                    ranks[i] = CribbageRank.NINE;
                    break;
                case 'T':
                    ranks[i] = CribbageRank.TEN;
                    break;
                case 'J':
                    ranks[i] = CribbageRank.JACK;
                    break;
                case 'Q':
                    ranks[i] = CribbageRank.QUEEN;
                    break;
                case 'K':
                    ranks[i] = CribbageRank.KING;
                    break;
                default:
                    System.out.println("Input wrong!");
                    break;
            }
        }
        return ranks;
    }

    /** This method constructs a char array of input card suits.
     *  @param args a double-character string array of input card ranks and suits.
     *  @return an char array of input card suits.
     */
    private static char[] getSuit(String[] args) {
        char[] suits = new char[args.length];
        for (int i = 0; i < suits.length; i++) {
            suits[i] = args[i].charAt(1);
        }
        return suits;
    }

    /** This method sorts an array of input card ranks from low to high.
     *  @param comb an array of the "sub-arrays" of the input card ranks.
     *  @return an array of the "sub-arrays" of the sorted card ranks.
     */
    private static CribbageRank[][] sortComb(CribbageRank[][] comb) {
        for (int i = 0; i < comb.length; i++) {
            for (int j = 0; j < comb[i].length - 1; j++) {
                for (int k = 0; k < comb[i].length - 1 - j; k++) {
                    if (comb[i][k].ordinal() > comb[i][k + 1].ordinal()) {
                        CribbageRank temp = comb[i][k];
                        comb[i][k] = comb[i][k + 1];
                        comb[i][k + 1] = temp;
                    }
                }
            }
        }
        return comb;
    }
}
