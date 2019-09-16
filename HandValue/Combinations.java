/** This class provides a single static method to compute all
 * combinations of objects in the input.  It also provides a main
 * method for experimentation.
 *
 * @author Peter Schachte <schachte@unimelb.edu.au>
 */
import java.lang.reflect.Array;

public class Combinations {

    /** This method computes all the combinations of objects from the
     * input array.  If the input array has n elements, the output array
     * has 2^n arrays, each containing from 0 to n elements taken from the
     * input array.  Elements in the output arrays are included in the order
     * they appear in the input array.  Specifically, element i of the
     * output array contains element j of the input array if i / 2^j is
     * odd, where / is integer division rounding down, so element 0 of the
     * result is empty and element 2^n - 1 of the result has all the
     * elements of the input.
     *
     * @param <T> the type of the array elements
     * @param list an array of the elements to compute the combinations of
     * @return an array of the "subarrays" of the input
     *
     * @see <a href="http://en.wikipedia.org/wiki/Combination">
     *     the wikipedia page for combinations</a>
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T[][] combinations(T[] list) {
        T[][] combos = (T[][])Array.newInstance(list.getClass(),
            (int) Math.pow(2, list.length));

        for (int i = 0 ; i < combos.length ; ++i) {
            int count = 0;
            for (int j = 0 ; j < list.length ; ++j) {
                if ((i & 1<<j) != 0) ++count;
            }
            combos[i] = (T[])Array.newInstance(list.getClass().getComponentType(), count);
            count = 0;
            for (int j = 0 ; j < list.length ; ++j) {
                if ((i & 1<<j) != 0) {
                    combos[i][count] = list[j];
                    ++count;
                }
            }
        }
        return combos;
    }

    /** A simple main method to allow experimentation with this
     *  method.  It simply prints out all the combinations of the
     *  command line arguments, one per line.
     */
    public static void main(String[] args) {
        String[][] lines = Combinations.combinations(args);
        for (String[] line : lines) {
            for (String str : line) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}