package Books;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();
        while(true){

            System.out.print("Title: ");
            String title = scn.nextLine();
            if(title.isEmpty())
                break;

            System.out.print("Pages: ");
            int pages = scn.nextInt();
            System.out.print("Publication year: ");
            int publicationYear = scn.nextInt();
            scn.nextLine();
            books.add(new Book(title,pages,publicationYear));
        }

        System.out.println("What information will be printed?");
        String whatToPrint = scn.nextLine();
        switch (whatToPrint){
            case "everything":
                for(Book book : books)
                    System.out.println(book);
                break;
            case "name":
                for(Book book : books)
                    System.out.println(book.getTitle());
                break;
            default:
                System.out.println("Incorrect input");
                break;
        }



    }


}
