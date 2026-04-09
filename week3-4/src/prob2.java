import java.util.Scanner;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }
}

class ClientRiskRanking {

    // Bubble Sort (Ascending)
    public static void bubbleSort(Client[] arr, int n) {
        int swapCount = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {

                    // Swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapCount++;
                }
            }
        }

        System.out.println("\nBubble Sort (Ascending by Risk):");
        for (Client c : arr) {
            System.out.println(c.name + " : " + c.riskScore);
        }
        System.out.println("Total Swaps = " + swapCount);
    }

    // Insertion Sort (Descending by riskScore, then accountBalance)
    public static void insertionSort(Client[] arr, int n) {

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 &&
                    (arr[j].riskScore < key.riskScore ||
                            (arr[j].riskScore == key.riskScore &&
                                    arr[j].accountBalance < key.accountBalance))) {

                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        System.out.println("\nInsertion Sort (Descending by Risk + Balance):");
        for (Client c : arr) {
            System.out.println(c.name + " : " + c.riskScore + " (" + c.accountBalance + ")");
        }
    }

    // Top 10 highest risk clients
    public static void topClients(Client[] arr, int n) {
        System.out.println("\nTop High Risk Clients:");

        int limit = Math.min(n, 10);
        for (int i = 0; i < limit; i++) {
            System.out.println(arr[i].name + " (" + arr[i].riskScore + ")");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of clients: ");
        int n = sc.nextInt();

        Client[] clients = new Client[n];

        // Input
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter name, riskScore, accountBalance for client " + (i + 1) + ":");
            String name = sc.next();
            int risk = sc.nextInt();
            double balance = sc.nextDouble();

            clients[i] = new Client(name, risk, balance);
        }

        // Copy arrays
        Client[] bubbleArr = new Client[n];
        Client[] insertionArr = new Client[n];

        for (int i = 0; i < n; i++) {
            bubbleArr[i] = new Client(clients[i].name, clients[i].riskScore, clients[i].accountBalance);
            insertionArr[i] = new Client(clients[i].name, clients[i].riskScore, clients[i].accountBalance);
        }

        // Sorting
        bubbleSort(bubbleArr, n);
        insertionSort(insertionArr, n);

        // Top clients
        topClients(insertionArr, n);

        sc.close();
    }
}