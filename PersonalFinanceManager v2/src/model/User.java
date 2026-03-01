package model;

import java.util.Objects;

public class User {
    private String userId,firstName,lastName,email,password;
    public User()
    {}
    public User(String userId,String password, String firstName, String lastName, String email) {
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    // we are skipping the user ID in equals to avoid same users with different id

    @Override
    public String toString() {
        return String.format("┌─────────────────────────────┐%n" +
                        "│  USER PROFILE               │%n" +
                        "├─────────────────────────────┤%n" +
                        "│  ID    : %-19s│%n" +
                        "│  Name  : %-19s│%n" +
                        "│  Email : %-19s│%n" +
                        "└─────────────────────────────┘",
                userId, firstName + " " + lastName, email);
    }
    public String formatUserCSV() {
        return String.format("%s,%s,%s,%s,%s",
                userId,password,firstName,lastName,email);
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
