import java.util.*;

class TransactionSorter {

    // Transaction class
    static class Transaction {
        String id;
        double fee;
        String timestamp; // HH:mm

        public Transaction(String id, double fee, String timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return id + ":" + fee + "@" + timestamp;
        }
    }

    // Bubble Sort (by fee)
    public static void bubbleSortByFee(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("Bubble Sort -> Passes: " + passes + ", Swaps: " + swaps);
    }

    // Insertion Sort (by fee + timestamp)
    public static void insertionSortByFeeAndTime(List<Transaction> list) {
        int shifts = 0;

        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
                shifts++;
            }

            list.set(j + 1, key);
        }

        System.out.println("Insertion Sort -> Shifts: " + shifts);
    }

    // Comparator: fee first, then timestamp
    private static int compare(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp);
    }

    // Flag high-fee transactions
    public static void flagHighFees(List<Transaction> list) {
        System.out.println("High-fee transactions (>50):");
        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println("⚠ " + t);
            }
        }
    }

    // Main method
    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        // Sample input
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));
        transactions.add(new Transaction("id4", 60.0, "11:00")); // high-fee example

        System.out.println("Original: " + transactions);

        // Choose sorting method based on size
        if (transactions.size() <= 100) {
            bubbleSortByFee(transactions);
            System.out.println("Bubble Sorted: " + transactions);
        } else if (transactions.size() <= 1000) {
            insertionSortByFeeAndTime(transactions);
            System.out.println("Insertion Sorted: " + transactions);
        }

        // Always run insertion sort for audit (fee + timestamp)
        insertionSortByFeeAndTime(transactions);
        System.out.println("Final Sorted (fee + timestamp): " + transactions);

        // Flag outliers
        flagHighFees(transactions);
    }
}