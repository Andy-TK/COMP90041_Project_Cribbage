/** A playing card rank type designed for cribbage.  It supports a single-
 *  character abbreviation for each rank, as well as providing the face
 *  value of a rank (ACE=1, KING, QUEEN, and JACK=10, other ranks are their
 *  face value), needed for counting 15s in a cribbage hand.  Also provides
 *  methods to get the next smaller and larger rank of a given rank.
 *
 * @author Peter Schachte schachte@unimelb.edu.au
 */
public enum CribbageRank {
    ACE('A'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K');

    /** Single character abbreviation used to briefly print the rank. */
    private final char abbrev;

    /** @return the single-character abbreviation for this rank. */
    public char abbrev() {
        return abbrev;
    }

    /** @return the face value of the rank for counting 15's in cribbage
     *  (ACE=1, KING, QUEEN, and JACK=10, other ranks are their face value).
     */
    public int faceValue() {
        return Math.min(this.ordinal()+1, 10);
    }

    /** @return the next higher rank */
    public CribbageRank nextHigher() {
        int value = this.ordinal() + 1;
        return value >= values().length ? null : values()[value];
    }

    /** @return the next lower rank */
    public CribbageRank nextLower() {
        int value = this.ordinal() - 1;
        return value < 0 ? null : values()[value];
    }

    /** Construct a rank.
     *  @param abbrev the single-character abbreviation for this rank
     */
    CribbageRank(char abbrev) {
        this.abbrev = abbrev;
    }

    /** @return The rank as a single-character string. */
    @Override
    public String toString() {
        return Character.toString(abbrev);
    }
}