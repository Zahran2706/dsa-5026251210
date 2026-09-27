import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<PrintJob> jobs = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File("jobs.txt"))) {
            while (fileScanner.hasNext()) {
                String type = fileScanner.next();
                String id = fileScanner.next();
                int pages = fileScanner.nextInt();

                PrintJob job;
                if (type.equalsIgnoreCase("MONO")) {
                    job = new MonoPrint(id, pages);
                } else if (type.equalsIgnoreCase("COLOUR")) {
                    job = new ColourPrint(id, pages);
                } else {
                    throw new IllegalArgumentException("Unknown job type: " + type);
                }
                jobs.add(job);
            }
        } catch (FileNotFoundException e) {
            System.out.println("jobs.txt not found: " + e.getMessage());
            return;
        }

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}