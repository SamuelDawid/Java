package Part8.DictionaryOfManyTranslations;

import java.util.ArrayList;
import java.util.HashMap;

public class DictionaryOfManyTranslations {

   private HashMap<String, ArrayList<String>> dictionary;

    public DictionaryOfManyTranslations(){
        this.dictionary = new HashMap<>();
    }

    public void add(String word, String translation){
        this.dictionary.putIfAbsent(word,new ArrayList<>());

        this.dictionary.get(word).add(translation);

    }
    public ArrayList<String> translate(String word){

        for(String w: dictionary.keySet()){
            if(w.equals(word))
                return dictionary.get(w);
        }

        return null;
    }
    public void remove(String word){
        this.dictionary.remove(word);

    }
}
