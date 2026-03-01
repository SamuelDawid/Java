package service;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;

public class UserService {
    public User currentUser = new User();
    private final Scanner scanner = new Scanner(System.in);
    private ArrayList<User> allUsers = new ArrayList<>();
    private HashMap<String,User> userIdMap = new HashMap<>();
    public Function<User,String> userFormatCVS = User::formatUserCSV;

   public void createNewUser() {
       System.out.println("Adding new User");
       System.out.println("First Name:");
       String firstName = scanner.nextLine();
       System.out.println("Last Name:");
       String lastName = scanner.nextLine();
       System.out.println("Email:");
       String email = scanner.nextLine();
       System.out.println("password:");
       String password = scanner.nextLine();
       currentUser = new User(GetRandom(),password,firstName,lastName,email);
       userIdMap.put(currentUser.getUserId(),currentUser);
       allUsers.add(currentUser);
   }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }
    public String GetRandom(){
        while (true){
            var rand = new Random().nextInt(0,99999);
        if(!userIdMap.containsKey(String.valueOf(rand)))
            return String.valueOf(rand) ;
        }
    }
}
