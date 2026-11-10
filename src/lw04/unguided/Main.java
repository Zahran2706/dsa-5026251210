package lw04.unguided;

import java.util.*;

public class Main {

    static void bubbleSort(int[] a, int startPass, int endPass) {
        int n = a.length;
        for (int i = startPass; i < endPass; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] < a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    static void binarySearch(int[] a, int target) {
        int low = 0;
        int high = a.length - 1;
        int steps = 0;
        int foundIndex = -1;

        while (low <= high && foundIndex == -1) {
            int mid = (low + high) / 2;
            steps++;

            if (a[mid] == target) {
                foundIndex = mid;
            } else if (a[mid] > target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (foundIndex != -1) {
            System.out.println(target + ": FOUND at index " + foundIndex + " (steps: " + steps + ")");
        } else {
            System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
        }
    }

    static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i != a.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int n = sc.nextInt();
        int[] parcels = new int[n];
        for (int i = 0; i < n; i++) {
            parcels[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();

        bubbleSort(parcels, 0, 2);
        int[] afterTwoPasses = new int[n];
        for (int i = 0; i < n; i++) {
            afterTwoPasses[i] = parcels[i];
        }

        bubbleSort(parcels, 2, n - 1);

        System.out.println("==== After 2 Passes ====");
        printArray(afterTwoPasses);
        System.out.println("==== Fully Sorted ====");
        printArray(parcels);
        System.out.println("==== Search Results ====");
        for (int i = 0; i < m; i++) {
            binarySearch(parcels, targets[i]);
        }
    }
}