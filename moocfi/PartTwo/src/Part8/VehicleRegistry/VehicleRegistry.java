package Part8.VehicleRegistry;

import java.util.ArrayList;
import java.util.HashMap;

public class VehicleRegistry {
    private HashMap<LicensePlate, String> owners;

    public VehicleRegistry() {
        owners = new HashMap<>();
    }

    public boolean add(LicensePlate licensePlate, String owner) {
        if (!this.owners.containsKey(licensePlate) || this.owners.get(licensePlate) == null) {
            this.owners.put(licensePlate, owner);
            return true;
        }
        return false;
    }

    public String get(LicensePlate licensePlate) {
        return owners.get(licensePlate);
    }

    public boolean remove(LicensePlate licensePlate) {
        for (LicensePlate l : owners.keySet()) {
            if (l.equals(licensePlate))
                owners.remove(licensePlate);
            return true;
        }

        return false;
    }
    public void printLicensePlates(){
        ArrayList<LicensePlate> plates = new ArrayList<>();

        for(LicensePlate l : owners.keySet())
            if (!plates.contains(l)){
                plates.add(l);
            }
        for (LicensePlate l : plates)
        System.out.println(l);
    }
    public void printOwners(){
        ArrayList<String> ownerToPrint = new ArrayList<>();
        for(String s : owners.values())
            if(!ownerToPrint.contains(s))
                ownerToPrint.add(s);
        for (String l : ownerToPrint)
        System.out.println(l);
    }
}
