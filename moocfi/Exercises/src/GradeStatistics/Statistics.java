package GradeStatistics;

import java.util.ArrayList;

public class Statistics {
    private ArrayList<Integer> stats;

    public Statistics(){
        this.stats = new ArrayList<>();

    }
    public void add(int _value){
        stats.add(_value);
    }
    public void printStar(int number){
        for(int i =0; i < number; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
    public int returnSize(){
        return stats.size();
    }
    public double CalculateAvrage(){
    int total = 0;
    for (int i :this.stats)
        total+=i;
    return (double) total / this.stats.size();
    }
}
