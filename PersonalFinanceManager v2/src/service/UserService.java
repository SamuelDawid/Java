package service;

import Iterators.UserListIterator;
import model.User;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class UserService {
    public User currentUser = new User();
    private final Scanner scanner = new Scanner(System.in);
    private ArrayList<User> allUsers = new ArrayList<>();
    private HashMap<String, User> userIdMap = new HashMap<>();
    public Function<User, String> userFormatCVS = User::formatUserCSV;

    public void createNewUser(String email) {
        System.out.println("First Name:");
        String firstName = scanner.nextLine();
        System.out.println("Last Name:");
        String lastName = scanner.nextLine();
        System.out.println("password:");
        String password = scanner.nextLine();
        currentUser = new User(GetRandom(), firstName, lastName, email,password);
        userIdMap.put(currentUser.getUserId(), currentUser);
        allUsers.add(currentUser);
        System.out.println(currentUser);
    }

    public Optional<User> findUser(String email, String password) {
        Stream<User> userStream = allUsers.stream();
        return userStream.filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password)).findFirst();
    }

    public boolean signUp() {
        System.out.println("Type email: ");
        String email = scanner.nextLine();
        Stream<User> userStream = allUsers.stream();
        if (userStream.anyMatch(user -> user.getEmail().equals(email))) {
            System.out.println("Email already in  use");
        } else{
            createNewUser(email);
            return true;
        }
        return false;
    }

    public boolean Login() {
        System.out.println("┌─────────────────────────────┐");
        System.out.println("│           LOG IN            │");
        System.out.println("├─────────────────────────────┤");
        System.out.print("│  Email    : ");
        String email = scanner.nextLine();
        System.out.print("│  Password : ");
        String password = scanner.nextLine();
        System.out.println("└─────────────────────────────┘");

        Optional<User> loggedUser = findUser(email, password);

        if (loggedUser.isEmpty()) {
            System.out.println("User not found");
        } else {
            currentUser = loggedUser.get();
            return true;
        }
        return false;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public String GetRandom() {
        while (true) {
            var rand = new Random().nextInt(0, 99999);
            if (!userIdMap.containsKey(String.valueOf(rand)))
                return String.valueOf(rand);
        }
    }
}
