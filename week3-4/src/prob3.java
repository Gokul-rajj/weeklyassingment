import java.util.*;

class Trade {
    String name;
    int volume;

    Trade(String name, int volume) {
        this.name = name;
        this.volume = volume;
    }
}

class TradeVolumeAnalysis {

    // ----------- MERGE SORT (ASCENDING - STABLE) -----------
    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(Trade[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) { // stable
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // ----------- QUICK SORT (DESCENDING) -----------
    public static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume; // Lomuto pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) { // DESCENDING
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // ----------- MERGE TWO SORTED ARRAYS -----------
    public static Trade[] mergeTwoSorted(Trade[] a, Trade[] b) {
        int i = 0, j = 0, k = 0;
        Trade[] result = new Trade[a.length + b.length];

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    // ----------- TOTAL VOLUME -----------
    public static int totalVolume(Trade[] arr) {
        int sum = 0;
        for (Trade t : arr) {
            sum += t.volume;
        }
        return sum;
    }

    // ----------- PRINT FUNCTION -----------
    public static void printTrades(String msg, Trade[] arr) {
        System.out.println("\n" + msg);
        for (Trade t : arr) {
            System.out.println(t.name + " : " + t.volume);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of trades: ");
        int n = sc.nextInt();

        Trade[] trades = new Trade[n];

        // Input
        for (int i = 0; i < n; i++) {
            System.out.println("Enter trade name and volume:");
            String name = sc.next();
            int vol = sc.nextInt();
            trades[i] = new Trade(name, vol);
        }

        // Copy arrays
        Trade[] mergeArr = Arrays.copyOf(trades, n);
        Trade[] quickArr = Arrays.copyOf(trades, n);

        // Merge Sort (Ascending)
        mergeSort(mergeArr, 0, n - 1);
        printTrades("Merge Sort (Ascending - Stable):", mergeArr);

        // Quick Sort (Descending)
        quickSort(quickArr, 0, n - 1);
        printTrades("Quick Sort (Descending):", quickArr);

        // Example: split into two halves (morning & afternoon)
        int mid = n / 2;
        Trade[] morning = Arrays.copyOfRange(mergeArr, 0, mid);
        Trade[] afternoon = Arrays.copyOfRange(mergeArr, mid, n);

        // Merge both sorted lists
        Trade[] merged = mergeTwoSorted(morning, afternoon);
        printTrades("Merged Morning + Afternoon:", merged);

        // Total volume
        int total = totalVolume(merged);
        System.out.println("\nTotal Volume = " + total);

        sc.close();
    }
}