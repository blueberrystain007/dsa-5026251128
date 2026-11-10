package lw04.unguided;

import java.util.*;

public class Main {
    static void bubbleSortTwoStages(int[] a, int[] snapshot) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] < a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
            if (i == 1) {
                for (int k = 0; k < n; k++) {
                    snapshot[k] = a[k];
                }
            }
        }
    }
 
    static int binarySearchDesc(int[] a, int target, int[] steps) {
        int low = 0;
        int high = a.length - 1;
        int count = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            count++;
            if (a[mid] == target) {
                steps[0] = count;
                return mid;
            } else if (a[mid] < target) {
                high = mid - 1; 
            } else {
                low = mid + 1;  
            }
        }
        steps[0] = count;
        return -1;
    }
 
    static void printArray(int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(a[i]);
        }
        System.out.println(sb);
    }
 
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new java.io.File("parcels.txt"));
 
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
 
        int[] afterTwo = new int[n];
        bubbleSortTwoStages(parcels, afterTwo);
 
        System.out.println("==== After 2 Passes ====");
        printArray(afterTwo);
        System.out.println("==== Fully Sorted ====");
        printArray(parcels);
        System.out.println("==== Search Results ====");
 
        int[] steps = new int[1];
        for (int i = 0; i < m; i++) {
            int idx = binarySearchDesc(parcels, targets[i], steps);
            if (idx >= 0) {
                System.out.println(targets[i] + ": FOUND at index " + idx + " (steps: " + steps[0] + ")");
            } else {
                System.out.println(targets[i] + ": NOT FOUND (steps: " + steps[0] + ")");
            }
        }
    }
}