package RecipeSearch;

import java.util.ArrayList;

public class Recipe {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    private String name;
    private  int time;

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description.add(description);
    }

    private ArrayList<String> description;

    public Recipe(String name, int time) {
        this.name = name;
        this.time = time;
        this.description = new ArrayList<>();
    }
    public Recipe(){
    this.name ="";
    this.time = 0;
    this.description = new ArrayList<>();
    }

    @Override
    public String toString(){
        return "Name: " + name + "\n" + "Time: " + time + "\n" + "Description: " + description;

    }
}
