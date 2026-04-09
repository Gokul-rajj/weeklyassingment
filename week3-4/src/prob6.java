import java.util.*;

class RiskThresholdLookup {

    // -------- LINEAR SEARCH (UNSORTED) --------
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                found = true;
                System.out.println("Found at index: " + i);
                break;
            }
        }

        System.out.println("\nLinear Search:");
        if (!found) {
            System.out.println("Threshold not found");
        }
        System.out.println("Comparisons = " + comparisons);
        System.out.println("Time Complexity = O(n)");
    }

    // -------- BINARY SEARCH + FLOOR + CEILING --------
    public static void binarySearchFloorCeil(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int floor = -1, ceil = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] == target) {
                floor = ceil = arr[mid];
                break;
            } else if (arr[mid] < target) {
                floor = arr[mid]; // possible floor
                low = mid + 1;
            } else {
                ceil = arr[mid]; // possible ceiling
                high = mid - 1;
            }
        }

        // Insertion point = low
        System.out.println("\nBinary Search:");
        System.out.println("Insertion Index = " + low);

        System.out.println("Floor = " + (floor != -1 ? floor : "None"));
        System.out.println("Ceiling = " + (ceil != -1 ? ceil : "None"));

        System.out.println("Comparisons = " + comparisons);
        System.out.println("Time Complexity = O(log n)");
    }

    public static void main(String[] args) {

        // 🔹 UNSORTED array (for Linear Search)
        int[] unsorted = {50, 10, 100, 25};

        // 🔹 SORTED array (for Binary Search)
        int[] sorted = {10, 25, 50, 100};

        int target = 30;

        System.out.println("Unsorted Risk Bands: " + Arrays.toString(unsorted));
        System.out.println("Sorted Risk Bands: " + Arrays.toString(sorted));
        System.out.println("Target Threshold: " + target);

        // Linear Search
        linearSearch(unsorted, target);

        // Binary Search + Floor/Ceiling
        binarySearchFloorCeil(sorted, target);
    }
}