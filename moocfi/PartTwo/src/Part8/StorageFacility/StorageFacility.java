package Part8.StorageFacility;

import java.util.ArrayList;
import java.util.HashMap;

public class StorageFacility {

    private HashMap<String, ArrayList<String>> facility;

    public StorageFacility() {
        this.facility = new HashMap<>();
    }

    public void add(String unit, String item){
    this.facility.putIfAbsent(unit, new ArrayList<>());

    this.facility.get(unit).add(item);

    }
    public ArrayList<String> contents(String storageUnit){
        for(String s : this.facility.keySet()){
            if(s.equals(storageUnit))
                return this.facility.get(s);
        }

        return new ArrayList<>();
    }

    public void remove(String storageUnit, String item){
        this.facility.get(storageUnit).remove(item);
        //removes the given item from the given storage unit. NB! Only removes one item —
        // if there are several items with the same name, the rest still remain.
        if(facility.get(storageUnit).isEmpty())
            facility.remove(storageUnit);
        // If the storage unit would be empty after the removal, the method also removes the unit.
    }
    public ArrayList<String> storageUnits(){
        ArrayList<String> notEmptyStr = new ArrayList<>();
        for (String s: facility.keySet())
            if(!s.isEmpty())
                notEmptyStr.add(s);

        return notEmptyStr;
    }
}
