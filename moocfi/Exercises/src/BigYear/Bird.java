package BigYear;

public class Bird {
    private String name,nameInLatin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameInLatin() {
        return nameInLatin;
    }

    public void setNameInLatin(String nameInLatin) {
        this.nameInLatin = nameInLatin;
    }

    public int getObservation() {
        return observation;
    }

    public void setObservation(int observation) {
        this.observation = observation;
    }
    public void addOneObservation(){
        this.observation++;
    }
    private int observation;

    public Bird(String name, String nameInLatin) {
        this.name = name;
        this.nameInLatin = nameInLatin;
        this.observation = 0;
    }
    @Override
    public String toString(){
        return name+" ("+nameInLatin+"): " + observation+"observations";
    }
}
