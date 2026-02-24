import java.util.*;
import java.util.function.Consumer;

public class Cinema {

    static void main() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Inception", 148));
        movies.add(new Movie("The Lion King", 88));
        movies.add(new Movie("Interstellar", 169));
        movies.add(new Movie("Toy Story", 81));
        movies.add(new Movie("The Dark Knight", 152));
        movies.add(new Movie("Finding Nemo", 100));

        Queue<Person> personQueue = new LinkedList<>();
        personQueue.offer(new Person("Marek", "Kowalski", 34));
        personQueue.offer(new Person("Anna", "Wiśniewska", 16));
        personQueue.offer(new Person("Tomasz", "Zając", 45));
        personQueue.offer(new Person("Karolina", "Nowak", 17));
        personQueue.offer(new Person("Piotr", "Wójcik", 28));
        personQueue.offer(new Person("Zofia", "Kamińska", 15));

        servePerson(personQueue);
        servePerson(personQueue);
        servePerson(personQueue);
        personQueue.offer(addPerson());
        personQueue.offer(addPerson());
        personQueue.offer(addPerson());
        System.out.println("Queue that's left: ");
        displayQueue(personQueue, (p) -> System.out.println(p.toString()));
        System.out.println("Remove Minors: ");
//        displayQueue(personQueue,person -> {
//            if (person.getAge() >= 18)
//                System.out.println( person.getFirstName());
//        } );
        removeMinors(personQueue);
        displayQueue(personQueue, (p) -> System.out.println(p.toString()));
        displayQueue(movies, movie -> {
            if (movie.getTime() > 120)
                System.out.println(movie.getTitle());
        });
    }

    static Person addPerson() {
        ArrayList<String> names = new ArrayList<>(Arrays.asList("Michał", "Natalia", "Krzysztof", "Magdalena", "Bartosz"));
        ArrayList<String> sureNames = new ArrayList<>(Arrays.asList("Lewandowski", "Grabowska", "Mazur", "Woźniak", "Krawczyk"));
        ArrayList<Integer> ages = new ArrayList<>(Arrays.asList(22, 31, 19, 14, 41));
        Random rnd = new Random();


        return new Person(
                names.get(rnd.nextInt(names.size())),
                sureNames.get(rnd.nextInt(sureNames.size())),
                ages.get(rnd.nextInt(ages.size())));

    }

    static void servePerson(Queue<Person> queue) {
        //removes the first person from the queue and "sells" them a ticket, print who got served
        System.out.println("now serving: " + queue.poll());
        System.out.println("Next in the line: " + queue.peek());
        System.out.println("thank you!");
    }

    static <T> void displayQueue(Collection<T> list, Consumer<T> consumer) {
        // use a lambda with Consumer to print everyone currently waiting
        for (T t : list) {
            consumer.accept(t);
        }
    }

    static void removeMinors(Collection<Person> collection) {


        for (Iterator<Person> it = collection.iterator(); it.hasNext(); ) {
            Person p = it.next();
            if (p.getAge() < 18)
                it.remove();
        }

        //collection.removeIf(p -> p.getAge() < 18);
    }
}
