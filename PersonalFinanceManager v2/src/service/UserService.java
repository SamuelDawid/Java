package service;

import model.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

public class UserService {
    public User currentUser = new User();
    int currentUsersNumber = 0;
    private final Scanner scanner = new Scanner(System.in);
    private ArrayList<User> allUsers = new ArrayList<>();

    public Function<User,String> userFormatCVS = User::formatUserCSV;


   public void createNewUser() {
       System.out.println("Adding new User");
       System.out.println("First Name:");
       String firstName = scanner.nextLine();
       System.out.println("Last Name:");
       String lastName = scanner.nextLine();
       System.out.println("Email:");
       String email = scanner.nextLine();
       currentUser = new User(String.valueOf(currentUsersNumber), firstName, lastName, email);
       currentUsersNumber++;
       allUsers.add(currentUser);
   }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }
}
