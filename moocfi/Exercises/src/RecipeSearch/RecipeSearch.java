package RecipeSearch;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> list = new ArrayList<>();
        System.out.println("File to read:");
        String fileName = scanner.nextLine();
        // read line by line until line is empty.
        try (Scanner scr = new Scanner(Paths.get(fileName))) {
            // here we add all to the list
            while (scr.hasNextLine()) {
                list.add(scr.nextLine());
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ArrayList<Recipe> allRecipes = RecipeSearch.seperateRecipes(list);
        System.out.println("Commands:");
        System.out.println("list - lists the recipes");
        System.out.println("stop - stops the program");
        System.out.println("find name - searches recipes by name");
        System.out.println("find cooking time - searches recipes by cooking time");
        System.out.println("find ingredient - searches recipes by ingredient");

        while (true) {
            String option = scanner.nextLine();
            switch (option) {
                case "stop":
                    return;
                case "list":
                    System.out.println("Recipes:");
                    for (Recipe recipe : allRecipes) {
                        System.out.println(recipe.getName() + ",  cooking time: " + recipe.getTime());
                    }
                    break;
                case "find name":
                    System.out.print("Searched word:");
                    String nameToFind = scanner.nextLine();

                    for (Recipe recipe : allRecipes) {
                        if (recipe.getName().contains(nameToFind))
                            System.out.println(recipe.getName() + ",  cooking time: " + recipe.getTime());
                    }
                    break;
                case "find cooking time":
                    System.out.print("Max cooking time:");
                    int timeToFind = scanner.nextInt();

                    for (Recipe recipe : allRecipes) {
                        if (recipe.getTime() <= timeToFind)
                            System.out.println(recipe.getName() + ",  cooking time: " + recipe.getTime());
                    }
                    break;
                case "find ingredient":
                    System.out.print("Ingredient:");
                    String ingToFind = scanner.nextLine();

                    for (Recipe recipe : allRecipes) {
                        ArrayList<String> listOfIng = recipe.getDescription();
                        if(listOfIng.contains(ingToFind)){
                            System.out.println(recipe.getName() + ",  cooking time: " + recipe.getTime());

                        }
                    }
                    break;
            }
        }

    }

    public static ArrayList<Recipe> seperateRecipes(ArrayList<String> list) {
        ArrayList<Recipe> seperatedRecips = new ArrayList<>();
        int startingIndex = 0;
//        starting index = 0
//        new Recipe
        Recipe newRecipe = new Recipe();
        for (int i = startingIndex; i < list.size(); i++) {


            if (i == startingIndex)
                newRecipe.setName(list.get(i));

            if (i == startingIndex + 1)
                newRecipe.setTime(Integer.parseInt(list.get(i)));
            if (i >= startingIndex + 2)
                newRecipe.setDescription(list.get(i));

            if (list.get(i).isEmpty()) {
                startingIndex = i + 1;
                seperatedRecips.add(newRecipe);
                newRecipe = new Recipe();
            }

        }
        if (!newRecipe.getName().isEmpty()) {
            seperatedRecips.add(newRecipe);
        }
        return seperatedRecips;

    }
}
