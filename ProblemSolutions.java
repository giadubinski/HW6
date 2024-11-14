
/******************************************************************
 *
 *   Gianna Dubinski / COMP 272-001
 *
 *   This java file contains the problem solutions for the methods lastBoulder,
 *   showDuplicates, and pair methods. You should utilize the Java Collection
 *   Framework for these methods.
 *
 ********************************************************************/

import java.util.*;
import java.util.PriorityQueue;

public class ProblemSolutions {

    /**
     * Priority Queue (PQ) Game
     *
     * PQ1 Problem Statement:
     * -----------------------
     *
     * You are given an array of integers of boulders where boulders[i] is the
     * weight of the ith boulder.
     *
     * We are playing a game with the boulders. On each turn, we choose the heaviest
     * two boulders and smash them together. Suppose the heaviest two boulders have
     * weights x and y. The result of this smash is:
     *
     *    If x == y, both boulders are destroyed, and
     *    If x != y, the boulder of weight x is destroyed, and the boulder of
     *               weight y has new weight y - x.
     *
     * At the end of the game, there is at most one boulder left.
     *
     * Return the weight of the last remaining boulder. If there are no boulders
     * left, return 0.
     *
     *
     * Example 1:
     *
     * Input: boulders = [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We combine 7 and 8 to get 1 so the list converts to [2,4,1,1,1] then,
     * we combine 2 and 4 to get 2 so the list converts to [2,1,1,1] then,
     * we combine 2 and 1 to get 1 so the list converts to [1,1,1] then,
     * we combine 1 and 1 to get 0 so the list converts to [1] then that's the
     * value of the last stone.
     *
     * Example 2:
     *
     * Input: boulders = [1]
     * Output: 1
     *
     *
     *
     * RECOMMENDED APPROACH
     *
     * Initializing Priority Queue in reverse order, so that it gives
     * max element at the top. Taking top Elements and performing the
     * given operations in the question as long as 2 or more boulders;
     * returning the 0 if queue is empty else return pq.peek().
     */

  public static int lastBoulder(int[] boulders) {

      //Priority Queue initializes stores Integer
        //objects, pq with equation, ((a, b) -> b - a)
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        //For loop to initialize boulder to boulders
        for (int boulder : boulders) {

            //Adds element of boulder to pq
            pq.offer(boulder);
        }

        //While loop to loop pq size that is greater 1
        while (pq.size() > 1) {

            //Initialized y to pq poll
            int y = pq.poll();

            //Initialized x to pq poll
            int x = pq.poll();

            //If statement to check if x and y aren't equal,
            //if so, find the difference
            if (x != y) {
                pq.offer(y - x);
            }
        }

        //Return to pq if there is remaining or 0 if empty
        return pq.isEmpty() ? 0 : pq.peek();
  }


    /**
     * Method showDuplicates
     *
     * This method identifies duplicate strings in an array list. The list
     * is passed as an ArrayList<String> and the method returns an ArrayList<String>
     * containing only unique strings that appear more than once in the input list.
     * This returned array list is returned in sorted ascending order. Note that
     * this method should consider case (strings are case-sensitive).
     *
     * For example, if the input list was: "Lion", "Dog", "Cat", "Dog", "Horse", "Lion", "CAT"
     * the method would return an ArrayList<String> containing: "Dog", "Lion"
     *
     * @param  input an ArrayList<String>
     * @return       an ArrayList<String> containing only unique strings that appear
     *               more than once in the input list. They will be in ascending order.
     */

    public static ArrayList<String> showDuplicates(ArrayList<String> input) {

        //HashMap to map string and integers counts to a new HashMap
        HashMap<String, Integer> counts = new HashMap<>();

        //For loop that iterates string str to input
        for (String str : input) {

            //Updates the count using put to get the default string with an increment of 1
            counts.put(str, counts.getOrDefault(str, 0) + 1);
        }

        //An Array list of strings that will duplicate
        ArrayList<String> duplicates = new ArrayList<>();

        //For loop to check the Map entry of String and Integer to counts of entry
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {

            //If statement to check the entry of getting the value that is greater than 1
            if (entry.getValue() > 1) {

                //Adding duplicates to entry to get the key
                duplicates.add(entry.getKey());
            }
        }

        // Sort duplicates in order
        Collections.sort(duplicates);

        return duplicates;

    }


    /**
     * Finds pairs in the input array that add up to k.
     *
     * @param input   Array of integers
     * @param k       The sum to find pairs for

     * @return an ArrayList<String> containing a list of strings. The ArrayList
     *        of strings needs to be ordered both within a pair, and
     *        between pairs in ascending order. E.g.,
     *
     *         - Ordering within a pair:
     *            A string is a pair in the format "(a, b)", where a and b are
     *            ordered lowest to highest, e.g., if a pair was the numbers
     *            6 and 3, then the string would be "(3, 6)", and NOT "(6, 3)".
     *         - Ordering between pairs:
     *            The ordering of strings of pairs should be sorted in lowest to
     *            highest pairs. E.g., if the following two string pairs within
     *            the returned ArraryList, "(3, 6)" and "(2, 7), they should be
     *            ordered in the ArrayList returned as "(2, 7)" and "(3, 6 )".
     *
     *         Example output:
     *         If the input array list was {2, 3, 3, 4, 5, 6, 7}, then the
     *         returned ArrayList<String> would be {"(2, 7)", "(3, 6)", "(4, 5)"}
     *
     *  HINT: Considering using any Java Collection Framework ADT that we have used
     *  to date, though HashSet. Consider using Java's "Collections.sort()" for final
     *  sort of ArrayList before returning so consistent answer. Utilize Oracle's
     *  Java Framework documentation in its use.
     */

    public static ArrayList<String> pair(int[] input, int k) {

        //HashSet to store seen Integers
        HashSet<Integer> seen = new HashSet<>();

        //TreeSet to maintain ordered pairs of Strings
        TreeSet<String> pairs = new TreeSet<>();

        //For loop to initialize num to input
        for (int num : input) {

            //Initialize complement to find pairs that sum to k
            int complement = k - num;

            //If statement to check seen contains a complement
            if (seen.contains(complement)) {

                //Initializes smaller to make complement num of smaller number first
                int smaller = Math.min(num, complement);

                //Initializes smaller to make complement num of larger number first
                int larger = Math.max(num, complement);

                //Pairs will add to the formatted String with the smaller and larger values
                pairs.add(String.format("(%d, %d)", smaller, larger));
            }

            //adds num to seem
            seen.add(num);
        }

        //Return to the ArrayList of pairs
        return new ArrayList<>(pairs);
    }
}
