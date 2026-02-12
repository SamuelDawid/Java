package OneTimePrograms;

import java.util.Scanner;

public class Login {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] username = {"alex" ,"emma"};
        String[] password = {"sunshine", "haskell"};

        System.out.print("Enter username:");
        String userNameInput = scanner.nextLine();
        System.out.print("Enter password:");
        String userPasswordInput = scanner.nextLine();

        for(int i =0; i < username.length;i++){
            if(userNameInput.equals(username[i])){
                // check password
                if(userPasswordInput.equals(password[i])){
                    System.out.println("You have successfully logged in!");
                    break;
                }else {

                    System.out.println("Incorrect username or password!");
                }
            }

        }

    }
}
