import java.util.*;

class AccountLookup {

    // -------- LINEAR SEARCH --------
    public static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                if (first == -1)
                    first = i;
                last = i;
            }
        }

        System.out.println("\nLinear Search:");
        if (first != -1) {
            System.out.println("First occurrence at index: " + first);
            System.out.println("Last occurrence at index: " + last);
        } else {
            System.out.println("Account not found");
        }
        System.out.println("Comparisons = " + comparisons);
        System.out.println("Time Complexity = O(n)");
    }

    // -------- BINARY SEARCH --------
    public static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid].equals(target)) {
                System.out.println("\nBinary Search:");
                System.out.println("Found at index: " + mid);
                System.out.println("Comparisons = " + comparisons);
                return mid;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("\nBinary Search:");
        System.out.println("Account not found");
        System.out.println("Comparisons = " + comparisons);
        return -1;
    }

    // -------- COUNT OCCURRENCES --------
    public static int countOccurrences(String[] arr, String target) {
        int count = 0;
        for (String s : arr) {
            if (s.equals(target))
                count++;
        }
        return count;
    }

    public static void main(String[] args) {

        // 🔹 PREDEFINED INPUT (sorted logs)
        String[] logs = {"accA", "accB", "accB", "accC"};

        String target = "accB";

        System.out.println("Transaction Logs:");
        System.out.println(Arrays.toString(logs));
        System.out.println("Target Account: " + target);

        // Linear Search
        linearSearch(logs, target);

        // Binary Search
        int index = binarySearch(logs, target);

        // Count duplicates
        if (index != -1) {
            int count = countOccurrences(logs, target);
            System.out.println("Total Occurrences = " + count);
            System.out.println("Time Complexity = O(log n) for search + O(n) for count");
        }
    }
}