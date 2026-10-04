package lw03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    // problem 1
    public static void problem1() {
        List<String> playlist = new ArrayList<String>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("playlist.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.startsWith("ADD ")) {
                String song = line.substring(4);
                playlist.add(song);
            } else if (line.startsWith("INSERT ")) {
                String rest = line.substring(7);
                int spaceIndex = rest.indexOf(" ");
                int index = Integer.parseInt(rest.substring(0, spaceIndex));
                String song = rest.substring(spaceIndex + 1);
                playlist.add(index, song);
            } else if (line.startsWith("REMOVE ")) {
                String song = line.substring(7);
                playlist.remove(song);
            }
        }
        scanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    // problem 2
    public static void problem2() {
        Set<String> participants = new LinkedHashSet<String>();
        int duplicateCount = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("participants.txt"));

        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();

            if (participants.contains(name)) {
                duplicateCount = duplicateCount + 1;
            } else {
                participants.add(name);
            }
        }
        scanner.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int number = 1;
        for (String name : participants) {
            System.out.println(number + ". " + name);
            number = number + 1;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);
    }

    // problem 3
    public static void problem3() {
        Map<String, Integer> stock = new LinkedHashMap<String, Integer>();
        int failedSales = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(" ");
            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (stock.containsKey(product)) {
                    int currentStock = stock.get(product);
                    stock.put(product, currentStock + quantity);
                } else {
                    stock.put(product, quantity);
                }
            } else {
                if (stock.containsKey(product) && stock.get(product) >= quantity) {
                    int currentStock = stock.get(product);
                    stock.put(product, currentStock - quantity);
                } else {
                    failedSales = failedSales + 1;
                }
            }
        }
        scanner.close();

        System.out.println("===== Problem 3 =====");
        for (String product : stock.keySet()) {
            System.out.println(product + ": " + stock.get(product));
        }
        System.out.println("Failed sales: " + failedSales);
    }
}