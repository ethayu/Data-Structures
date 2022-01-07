// SearchTester
// @author Yu
// @author Charagulla
import java.util.Scanner;

/**
 *
 */

/**
 * @author Ethan Yu
 * @author Nihaar Charagulla
 *
 */
public class SearchTesterEYNC {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Ask the user for search string
        Scanner myScan = new Scanner(System.in);
        System.out.println("Enter name: ");
        String input = myScan.nextLine();
        int index = binSearch(getList(), input);
        System.out.println(input + " found in list at index: " + index + " via Binary Search.");
        index = binSearchWreck( getList(), input, 0, getList().length);
        System.out.println(input + " found in list at index: " + index + " via recursive Binary Search");
    }

    /**
     * recursively searches for String in String array through binary  search algorithm
     * @param names given String array to be searched
     * @param input String value to be looked for
     * @param left left bound of search range
     * @param right right bound of search range
     * @return index at which String input appears; otherwise, return -1
     * @throws IllegalArgumentException if names isn't sorted
     */
    static int binSearchWreck(String[] names, String input, int left, int right) {
        if (left >= right) return -1;
        int middle  = (left + right) / 2;
        if (names[middle].equals(input)) return middle;
        else if (names[middle].compareTo(input) > 0) return binSearchWreck(names, input, left, middle - 1);
        else return binSearchWreck(names, input, middle + 1, right);
    }

    /**
     binSearch method to search for a string in given array using binary search
     algorithm
     @param sArr given String array to be searched
     @param input searchString to look for
     @return -1 if searchString is not found
     @return mid index at which searchString is found


     */
    public static int binSearch(String[] sArr, String input) {
        int first = 0;
        int last = sArr.length-1;
        while (first <= last) {
            int mid = first + (last-first)/2;

            if (sArr[mid].compareTo(input) < 0) {
                first = mid + 1;

            }
            else if (sArr[mid].compareTo(input) > 0) {
                last = mid -1;
            }

            else {
                return mid;
            }
        }

        return -1;
    }

    public static String[] getList() {
        String[] names = { "Adams", "Amarillas", "Baxter", "Eder", "Giradaux",
                "Gonzalez", "Hansbrough", "Janda", "Kniffen",
                "Lambert", "Mathurin", "McCrystal", "Molina",
                "Preciado", "Reyerson", "Tam", "Ward", "Wolf",
                "Wong", "Zabinski" };
        return names;

    }
}