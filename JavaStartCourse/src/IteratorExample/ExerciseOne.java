package IteratorExample;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ExerciseOne {
    static void main() {

//        Map<String, Person> listaOsob = new TreeMap<>();
//        listaOsob.put("Krzyszpien", new Person("Samuel", "50"));
//        listaOsob.put("Kowalski", new Person("Marek", "34"));
//        listaOsob.put("Wiśniewski", new Person("Tomasz", "27"));
//        listaOsob.put("Zając", new Person("Piotr", "41"));
//
//        Iterator<String> iterator = listaOsob.keySet().iterator();
//        while (iterator.hasNext()){
//            String key = iterator.next();
//            System.out.println(key);
//        }
        Map<String, Person> people = new TreeMap<>();

        people.put("Kowalski", new Person("Jan", "Kowalski", 35));
        people.put("Adamiak", new Person("Ania", "Adamiak", 21));
        people.put("Zaręba", new Person("Adam", "Zaręba", 17));
        people.put("Piotrowski", new Person("Karol", "Piotrowski", 42));
        people.put("Bobrowska", new Person("Michalina", "Bobrowska", 15));

        //Collection<Person> values = people.values();
        Iterator<Person> iterator = people.values().iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }

}
