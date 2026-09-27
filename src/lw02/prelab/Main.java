package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        try {
            File file = new File("src/lw02/prelab/transactions.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                transactions.add(parts);

                String name = parts[0];
                boolean found = false;
                for (String[] cust : customers) {
                    if (cust[0].equals(name)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    customers.add(new String[]{name, "0"});
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File transactions.txt tidak ditemukan.");
            return;
        }

        Queue<String[]> queue = new LinkedList<>();
        queue.addAll(transactions);

        Stack<String[]> failedStack = new Stack<>();

        while (!queue.isEmpty()) {
            String[] trx = queue.poll();
            String name = trx[0];
            String type = trx[1];
            int amount = Integer.parseInt(trx[2]);

            for (String[] cust : customers) {
                if (cust[0].equals(name)) {
                    int balance = Integer.parseInt(cust[1]);

                    if (type.equals("DEPOSIT")) {
                        balance += amount;
                        cust[1] = String.valueOf(balance);
                    } else if (type.equals("WITHDRAW")) {
                        if (amount > balance) {
                            failedStack.push(trx);
                        } else {
                            balance -= amount;
                            cust[1] = String.valueOf(balance);
                        }
                    }
                    break;
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] c : customers) {
            System.out.println(c[0] + " : " + c[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] failed = failedStack.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}