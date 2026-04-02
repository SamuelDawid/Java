package Part8.StorageFacility;

public class Program {
    static void main() {
        StorageFacility facility = new StorageFacility();
        facility.add("a14", "ice skates");
        facility.add("a14", "ice hockey stick");
        facility.add("a14", "ice skates");

        facility.add("f156", "rollerblades");
        facility.add("f156", "rollerblades");

        facility.add("g63", "six");
        facility.add("g63", "pi");

        facility.remove("f156", "rollerblades");

        System.out.println(facility.contents("f156"));
        System.out.println(facility.storageUnits());
        facility.remove("f156", "rollerblades");

        System.out.println(facility.storageUnits());
    }
}
