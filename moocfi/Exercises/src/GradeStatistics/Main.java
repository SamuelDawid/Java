package GradeStatistics;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Statistics stats = new Statistics();
        Statistics passing = new Statistics();
        int passingInt = 0, fiveStars = 0, fourStars = 0, threeStars = 0, twoStars = 0, oneStars = 0, failed = 0;
        System.out.println("Enter point totals, -1 stops:");
        while (true) {

            int value = scanner.nextInt();
            if (value == -1) {
                System.out.println("Point average (all): " + stats.CalculateAvrage());
                if (passing.returnSize() == 0) {
                    System.out.println("Point average (passing): -");
                } else
                    System.out.println("Point average (passing): " + passing.CalculateAvrage());
                System.out.println("Pass percentage: " + (double) (100 * passingInt) / stats.returnSize());
                System.out.print("5:");
                stats.printStar(fiveStars);
                System.out.print("4:");
                stats.printStar(fourStars);
                System.out.print("3:");
                stats.printStar(threeStars);
                System.out.print("2:");
                stats.printStar(twoStars);
                System.out.print("1:");
                stats.printStar(oneStars);
                System.out.print("failed:");
                stats.printStar(failed);
                break;
            }

            if (value >= 0 && value <= 100) {
                stats.add(value);
            }

            if (value >= 50 && value <= 100) {
                passing.add(value);
                passingInt++;
            }

            if (value >= 90) {
                fiveStars++;
            } else if (value >= 80) {
                fourStars++;
            } else if (value >= 70) {
                threeStars++;
            } else if (value >= 60) {
                twoStars++;
            } else if (value >= 50)
                oneStars++;
            else failed++;


        }
    }
}
