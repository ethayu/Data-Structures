import java.io.*;
import java.util.Scanner;
import java.util.zip.InflaterInputStream;

public class AdvAsg1 {

    public static void main(String[] args) throws FileNotFoundException {
        // R11.1 FileNotFoundException
        // R11.2 If file doesn't exist, it is created with nothing inside. If it is read-only, an FileNotFoundException is thrown.
        // R11.8 IndexOutOfBoundsException is unchecked
        // R11.11 It can handle the error by printing out the error, call stack trace and ask the user to try again.
        // R11.13 It can simplify code and remove wordy double "try" - example:
        try (Scanner sc = new Scanner(new File("noexist.txt"))) {
            sc.nextInt();
        } catch (IndexOutOfBoundsException e){
            System.out.println("file doesn't exist");
        }

        //P11.13
        Scanner reader = new Scanner(new File("params.txt"));
        PrintWriter writer = new PrintWriter(new File("rc.txt"));
        double B = reader.nextDouble();
        double R = reader.nextDouble();
        double C = reader.nextDouble();
        int s = reader.nextInt();
        int f = reader.nextInt();
        for (int i = 0; i < 100; i++) {
            int t = i * (f - s);
            writer.println(t+ " " + B * (1 - Math.exp(-1 * t / R / C)));
        }


    }

}
