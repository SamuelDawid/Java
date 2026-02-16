package ui;

public class InputValidator {
    //Static methods:
    static boolean isValidEmail(String email){
        String[] split = email.split(email);
        for (String c : split){
            if(c.equals("@")){
                return true;
            }
        }
return false;
    }
    //- boolean isValidDate(String date)
    //- boolean isValidAmount(double amount)
    //- Category selectCategory(Scanner scanner)
}
