package lw02.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        File file = new File("orders.txt");
        if (!file.exists()) {
            file = new File("src/lw02/unguided/orders.txt");
        }

        LinkedList<String[]> orders = new LinkedList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String name = scanner.next();
                String food = scanner.next();
                String drink = scanner.next();
                String table = scanner.next();

                orders.add(new String[] { name, food, drink, table });
            }
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan");
            return;
        }

        LinkedList<String[]> foodStock = new LinkedList<>();
        foodStock.add(new String[] { "Bakso", "2" });
        foodStock.add(new String[] { "Sate", "1" });
        foodStock.add(new String[] { "Soto", "2" });

        LinkedList<String[]> drinkStock = new LinkedList<>();
        drinkStock.add(new String[] { "EsTeh", "4" });
        drinkStock.add(new String[] { "EsJeruk", "2" });

        LinkedList<String[]> successfulOrders = new LinkedList<>();

        Queue<String[]> orderQueue = new LinkedList<>();
        while (!orders.isEmpty()) {
            orderQueue.add(orders.poll());
        }

        Stack<String[]> failedOrders = new Stack<>();

        while (!orderQueue.isEmpty()) {
            String[] order = orderQueue.poll();

            String food = order[1];
            String drink = order[2];

            String[] foodRecord = null;
            if (!food.equals("-")) {
                for (String[] f : foodStock) {
                    if (f[0].equals(food)) {
                        foodRecord = f;
                        break;
                    }
                }
            }

            String[] drinkRecord = null;
            if (!drink.equals("-")) {
                for (String[] d : drinkStock) {
                    if (d[0].equals(drink)) {
                        drinkRecord = d;
                        break;
                    }
                }
            }

            boolean foodAvailable = (foodRecord == null) || (Integer.parseInt(foodRecord[1]) > 0);
            boolean drinkAvailable = (drinkRecord == null) || (Integer.parseInt(drinkRecord[1]) > 0);

            if (foodAvailable && drinkAvailable) {
                if (foodRecord != null) {
                    int stock = Integer.parseInt(foodRecord[1]);
                    foodRecord[1] = String.valueOf(stock - 1);
                }
                if (drinkRecord != null) {
                    int stock = Integer.parseInt(drinkRecord[1]);
                    drinkRecord[1] = String.valueOf(stock - 1);
                }
                successfulOrders.add(order);
            } else {
                failedOrders.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : successfulOrders) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println();
        System.out.println("=== Remaining Food Stock ===");
        for (String[] f : foodStock) {
            System.out.println(f[0] + " : " + f[1]);
        }

        System.out.println();
        System.out.println("=== Remaining Drink Stock ===");
        for (String[] d : drinkStock) {
            System.out.println(d[0] + " : " + d[1]);
        }

        System.out.println();
        System.out.println("=== Failed Orders ===");
        while (!failedOrders.isEmpty()) {
            String[] order = failedOrders.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}