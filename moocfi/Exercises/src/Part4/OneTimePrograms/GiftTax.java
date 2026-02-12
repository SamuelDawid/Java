package OneTimePrograms;

import java.util.Scanner;

public class GiftTax {
    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        System.out.println("Value of the gift?");
        int giftAmmount = scan.nextInt();
        double tax = 0;
        if (giftAmmount < 5000) {
            System.out.println("No Tax!");
        }else if(giftAmmount >= 5000 && giftAmmount <25000)
        {
             tax = (100 +(giftAmmount - 5000) * 0.08);
            System.out.printf("Tax: "+ tax );
        } else if (giftAmmount >= 25000 && giftAmmount < 55000) {
            tax = (1700 +(giftAmmount - 25000) * 0.10);
            System.out.printf("Tax: "+ tax );
        }else if (giftAmmount >= 55000 && giftAmmount < 200000) {
            tax = (4700 +(giftAmmount - 55000) * 0.12);
            System.out.printf("Tax: "+ tax );
        }else if (giftAmmount >= 200000 && giftAmmount < 1000000) {
            tax = (22100 +(giftAmmount - 200000) * 0.15);
            System.out.printf("Tax: "+ tax );
        }else if (giftAmmount >= 1000000) {
            tax = (142100 +(giftAmmount - 1000000) * 0.17);
            System.out.printf("Tax: "+ tax );
        }
    }
}
