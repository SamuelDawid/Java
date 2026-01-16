package SimpleDictionary;
import java.util.Scanner;

public class TextUI {
    private Scanner scanner;
    private SimpleDictionary dictionary;

    public TextUI(Scanner _scanner,SimpleDictionary _dictionary){
        this.dictionary = _dictionary;
        this.scanner = _scanner;

    }
    public void start(){

        while(true){
            System.out.print("Command:");
            String input = scanner.nextLine();
            switch(input){
                case "end":
                    System.out.print("Bye bye!");
                    return;
                case "add":
                    System.out.print("Word:");
                    String  word = scanner.nextLine();
                    System.out.print("Translation:");
                    String  translation = scanner.nextLine();
                    dictionary.add(word, translation);
                    break;
                case "search":
                    System.out.print("To be translated:");
                    String translate =scanner.nextLine();
                    if(dictionary.translate(translate) == null){
                        System.out.println("Word "+translate+" was not found");
                    }else
                        System.out.println("Translation: "+dictionary.translate(translate));
                    break;
                default:
                    System.out.println("Unknown command");
                    break;

            }
        }
    }
}

