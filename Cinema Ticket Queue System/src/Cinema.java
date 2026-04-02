import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Cinema {

    static void main() {

        List<List<Integer>> numbers = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(4,5),
                Arrays.asList(6,7,8,9)
        );
        int[][] numbers1 = {{1,2,3},{4,5},{6,7,8,9}};
       List<Integer> EvenNumbers = numbers.stream().flatMap(Collection::stream).filter(integer -> integer % 2 ==0).toList();
        System.out.println(EvenNumbers);
        List<Integer> evens = Arrays.stream(numbers1).flatMapToInt(Arrays::stream).filter( value -> value % 2 == 0).boxed().toList();
        System.out.println(evens);
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
