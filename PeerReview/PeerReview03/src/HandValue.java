// Game of Carriage
// Project: Creating HandValue.java which is using a Combinations.java file to execute.

public class HandValue {

	public static void main(String[] args) throws IllegalArgumentException {
		// process command line input of cards:
		if (args.length != 5) {
			System.err.println("enter cards via command line arguments input");
			return;
		}
		// hand will be represented by cards array
		Card[] cards = new Card[args.length];
		// create cards by passing the appropriate string
		for (int i = 0; i < args.length; i++)
			cards[i] = new Card(args[i]);
		// generate score
		int handScore = getScore(cards);
		// print the score with a newline after it
		System.out.println(handScore);
	}

	// class representing card
	static class Card {
		// characters representing the card's value and suit
		public char suit, value;
		// card value converted to score
		public int score;
		// card value converted to rank
		public int rank;

		// constructor to build and initialise all variables from user input string
		public Card(String input) throws IllegalArgumentException {
			// split and convert to upper-case for consistency
			value = Character.toUpperCase(input.charAt(0));
			suit = Character.toUpperCase(input.charAt(1));
			// calculate score from value
			score = cardScore(value);
			if (score == -1)
				throw new IllegalArgumentException("Value character is incorrect");
			// calculate rank from value
			rank = cardRank(value);
			if (rank == -1)
				throw new IllegalArgumentException("Value character is incorrect");
		}

		// string representation of the card
		public String toString() {
			return "" + value + suit;
		}

		// local method for value-score mapping
		private static int cardScore(char value) {
			// simple switch for value conversion
			switch (value) {
			case 'A':
				return 1;
			case '2':
				return 2;
			case '3':
				return 3;
			case '4':
				return 4;
			case '5':
				return 5;
			case '6':
				return 6;
			case '7':
				return 7;
			case '8':
				return 8;
			case '9':
				return 9;
			case 'T':
			case 'J':
			case 'K':
			case 'Q':
				return 10;
			}
			// if input is incorrect, return -1 as error code
			return -1;
		}

		private static int cardRank(char value) {
			// simple switch for value conversion to ranking
			switch (value) {
			case 'A':
				return 1;
			case '2':
				return 2;
			case '3':
				return 3;
			case '4':
				return 4;
			case '5':
				return 5;
			case '6':
				return 6;
			case '7':
				return 7;
			case '8':
				return 8;
			case '9':
				return 9;
			case 'T':
				return 10;
			case 'J':
				return 11;
			case 'K':
				return 12;
			case 'Q':
				return 13;
			}
			// if input is incorrect, return -1 as error code
			return -1;
		}
	}

	// calculates total score by calling various cards
	static int getScore(Card[] hand) {
		int totalScore = 0;
		
		Card[] temp = new Card[hand.length];
		System.arraycopy(hand, 0, temp, 0, hand.length);
		totalScore += fifteenRule(temp);
		
		System.arraycopy(hand, 0, temp, 0, hand.length);
		totalScore += pairsRule(temp);
		
		System.arraycopy(hand, 0, temp, 0, hand.length);
		totalScore += runsRule(temp);
		
		System.arraycopy(hand, 0, temp, 0, hand.length);
		totalScore += flushesRule(temp);
		
		System.arraycopy(hand, 0, temp, 0, hand.length);
		totalScore += nobRule(temp);

		return totalScore;
	}

	// 15's
	static int fifteenRule(Card[] hand) {
		/*
		 * 2 points are scored for each distinct combinations of cards that add to 15.
		 * For example, a hand with a 2, 3, 5, 10, and king scores 8 points: 2 points
		 * each for 2+3+10, 2+3+king, 5+10, and 5+king.
		 */
		int points = 0;

		// from provided class, generate combinations:
		Card[][] combinations = Combinations.combinations(hand);
		// calculate score and check for 15's in combinations
		for (int i = 0, score; i < combinations.length; i++) {
			score = 0; // reset
			// sum all face values
			for (int j = 0; j < combinations[i].length; j++) {
				score += combinations[i][j].score;
			}
			// score points if 15 is total
			if (score == 15)
				points += 2;
		}
		// return total points
		return points;
	}

	// pairs
	static int pairsRule(Card[] hand) {
		/*
		 * 2 points are scored for each pair. With 3 of a kind, there are 3 different
		 * ways to make a pair, so 3 of a kind scores 6 points. Similarly, 4 of a kind
		 * scores 12 points for the 6 possible pairs.
		 */
		int points = 0;

		// apply simple sort by rank, all pairs will be aligned
		Card swap;
		for (int i = 0, smallest; i < hand.length - 1; i++) {
			smallest = i;
			for (int j = i + 1; j < hand.length; j++) {
				if (hand[j].rank < hand[smallest].rank)
					smallest = j;
			}
			if (smallest != i) {
				swap = hand[i];
				hand[i] = hand[smallest];
				hand[smallest] = swap;
			}
		}
		// find pairs and calculate points
		for (int i = 0, similars; i < hand.length - 1; i++) {
			similars = 0; // to keep track how many cards are similar
			if (hand[i].rank == hand[i + 1].rank) {
				// by this check, we are sure there are 2 cards that are of same rank
				similars = 2;
				i += 1;
				// after pair has been detected, check for more cards
				// calculate all points:
				while (i < hand.length - 1) {
					if (hand[i].rank == hand[i + 1].rank) {
						similars += 1;
						i += 1;
					} else {
						break;
					}
				}
				points += choose(similars, 2) * 2;
			}
		}

		return points;
	}

	// runs
	static int runsRule(Card[] hand) {
		/*
		 * 1 point is scored for each card in a run of 3 or more consecutive cards (the
		 * suits of these cards need not be the same). Aces are low in cribbage, so Ace,
		 * 2, 3 is a run, but Queen, King, Ace is not. For example, a 2, 6, 8, and 9,
		 * with a 7 as the start card scores 4 points for a 4 card run. It also scores a
		 * further 6 points for 15s (6+9, 7+8, and 2+6+7).
		 */

		// apply simple sort by rank, all pairs will be aligned
		Card swap;
		for (int i = 0, smallest; i < hand.length - 1; i++) {
			smallest = i;
			for (int j = i + 1; j < hand.length; j++) {
				if (hand[j].rank < hand[smallest].rank)
					smallest = j;
			}
			if (smallest != i) {
				swap = hand[i];
				hand[i] = hand[smallest];
				hand[smallest] = swap;
			}
		}

		// generate all combinations
		Card[][] combinations = Combinations.combinations(hand);

		// filter combinations that have length of 3 or more and form a run:
		boolean isRun; // flag for checking run
		int longestRun = 0; // default value represents there is no longest run recorded yet
		// loop over all combinations and only keep valid ones:
		for (int i = 0; i < combinations.length; i++) {
			// a run must be 3 or more cards
			if (combinations[i].length < 3) {
				combinations[i] = null;
				continue;
			}
			// a run must have cards in consecutive order
			isRun = true;
			for (int j = 0; j < combinations[i].length - 1; j++) {
				if (combinations[i][j].rank != combinations[i][j+1].rank - 1) {
					isRun = false;
					break;
				}
			}
			// if it is a run, record its length as maximum length
			if (!isRun) {
				combinations[i] = null;
			} else {
				if (combinations[i].length > longestRun)
					longestRun = combinations[i].length;
			}
		}
		// now only remaining combinations remaining are runs of 3 or more lengths
		// calculate how many longest runs are there:
		int longestRunCount = 0;
		for (int i = 0; i < combinations.length; i++) {
			if (combinations[i] != null) {
				if (combinations[i].length == longestRun)
					longestRunCount++;
			}
		}
		// calculate total:
		return longestRunCount * longestRun;
	}
	
	// flushes
	static int flushesRule(Card[] hand) {
		/*
		 * 4 points is scored if all the cards in the hand are of the same suit. 1
		 * further point is scored if the start card is also the same suit. Note that no
		 * points are scored if 3 of the hand cards, plus the start card, are the same
		 * suit.
		 */
		int points = 0;
		// 4 points if all cards in hand are same suit:
		boolean sameSuit = true;
		for (int i = 0; i < hand.length - 3; i++)
			if (hand[i].suit != hand[i + 1].suit)
				sameSuit = false;
		if (sameSuit)
			points += 4;

		// only if hand cards all have same suit, we can apply this check on first card:
		if (sameSuit) {
			if (hand[hand.length - 1].suit == hand[0].suit)
				points += 1;
		}

		return points;
	}

	// one for his nob
	static int nobRule(Card[] hand) {
		/*
		 * 1 point is scored if the hand contains the jack of the same suit as the start
		 * card.
		 */
		// hand: 0 to n-1 cards
		// start card: last card
		char startCardSuit = hand[hand.length - 1].suit;
		for (int i = 0; i < hand.length - 1; i++)
			if (hand[i].suit == startCardSuit && hand[i].value == 'J')
				return 1;
		// if no card in hand is jack & same suit as first card then return 0
		return 0;
	}

	// helper method, for calculating discrete math combinations
	static int combination(int n, int k) {
		return permutation(n) / (permutation(k) * permutation(n - k));
	}

	static int permutation(int i) {
		if (i == 1) {
			return 1;
		}
		return i * permutation(i - 1);
	}

	static long choose(long total, long choose) {
		if (total < choose)
			return 0;
		if (choose == 0 || choose == total)
			return 1;
		return choose(total - 1, choose - 1) + choose(total - 1, choose);
	}
}

