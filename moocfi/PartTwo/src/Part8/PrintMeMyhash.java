package Part8;

import java.util.ArrayList;
import java.util.HashMap;

public class PrintMeMyhash {

    public static void printKeys(HashMap<String,String> hashmap){

        for (String s : hashmap.keySet())
        System.out.println(s);

    }
    public static void printKeysWhere(HashMap<String,String> hashmap, String text){
        for (String s : hashmap.keySet())
            if (s.contains(text))
            System.out.println(s);
    }
    public static void printValuesOfKeysWhere(HashMap<String,String> hashmap, String text){
        for (String s : hashmap.keySet())
            if (s.contains(text))
                System.out.println(hashmap.get(s));

    }

    static void main() {
        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put("f.e", "for example");
        hashmap.put("etc.", "and so on");
        hashmap.put("i.e", "more precisely");

        printKeys(hashmap);
        System.out.println("---");
        printKeysWhere(hashmap, "i");
        System.out.println("---");
        printValuesOfKeysWhere(hashmap, ".e");
    }
}
