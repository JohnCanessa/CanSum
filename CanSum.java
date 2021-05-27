import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * 
 */
public class CanSum {


    /**
     * Write a function `boolean canSum(int targetSum, int[] numbers)` that takes in a
     * targetSum and an array of numbers as arguments.
     * 
     * The function should return a boolean indicating weather or not it
     * is possible to generate the targetSum using numbers from the array.
     * 
     * You may use an element of the array as many times as needed.
     * 
     * You may assume that all input numbers are non-negative.
     * 
     * Entry point call.
     * 
     * m = target sum
     * n = array length
     * 
     * Without memoization:  Time: O(n ^ m) and Space: O(m)
     * With memoization:     Time: O(m * n) and Space: O(m)
     */
    static boolean canSum(int targetSum, int[] numbers) {

        // **** initialization ****
        boolean ans                     = false;
        int[] callCounter               = new int[1];
        TreeMap<Integer, Boolean> memo  = new TreeMap<>();

        // **** start recursion ****
        ans = canSum(targetSum, numbers, memo, callCounter);

        // ???? ????
        System.out.println("canSum <<< callCounter: " + callCounter[0]);

        // **** return result ****
        return ans;
    }


    /**
     * Write a function `boolean canSum(int targetSum, int[] numbers)` that takes in a
     * targetSum and an array of numbers as arguments.
     * 
     * The function should return a boolean indicating weather or not it
     * is possible to generate the targetSum using numbers from the array.
     * 
     * You may use an element of the array as many times as needed.
     * 
     * You may assume that all input numbers are non-negative.
     * 
     * Recursive call.
     */
    static boolean canSum(  int targetSum,
                            int[] numbers,
                            TreeMap<Integer, Boolean> memo, 
                            int[] callCounter) {

        // ???? increment call counter ????
        callCounter[0]++;

        // **** base cases(s) ****
        if (targetSum == 0) return true;
        if (targetSum <= 0) return false;

        // **** initialization ****
        boolean found = false;

        // **** loop making recursive call (or getting value from memo) ****
        for (int i = 0; i < numbers.length && !found; i++) {

            // **** for ease of use ****
            int rem = targetSum - numbers[i];

            // **** make recursive call (or get it from memo) ****
            if (rem >= 0) {

                // **** if not in memo; recurse and update memo ****
                if (!memo.containsKey(rem))     // comment this line to disable memoization !!!
                    memo.put(rem, canSum(rem, numbers, memo, callCounter));

                // **** get value from memo ****
                found = memo.get(rem);
            }
        }

        // **** return answer ****
        return found;
    }


    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read and assign the target value ****
        int targetSum = Integer.parseInt(br.readLine().trim());

        // **** read and create int[] array of numbers ****
        int[] numbers = Arrays.stream(br.readLine().trim().split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< targetSum: " + targetSum);
        System.out.println("main <<< numbers: " + Arrays.toString(numbers));

        // **** call the function and display the result ****
        System.out.println("main <<< ans: " + canSum(targetSum, numbers));
    }

}