package service;

import model.User;

import java.util.Scanner;

public class UserService extends User{
    public User currentUser = new User();
    int currentUsersNumber = 0;
    private final Scanner scanner = new Scanner(System.in);

   public User createNewUser(){
       System.out.println("Adding new User");
       System.out.println("First Name:");
       String firstName = scanner.nextLine();
       System.out.println("Last Name:");
       String lastName = scanner.nextLine();
       System.out.println("Email:");
       String email = scanner.nextLine();
       currentUser = new User(String.valueOf(currentUsersNumber), firstName, lastName, email);
       currentUsersNumber++;
      return currentUser;
   }
}
