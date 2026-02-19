package Part8;

import java.util.HashMap;
import java.util.Map;

public class IOU {
    Map<String, Double> iou;

    public IOU(){
        this.iou = new HashMap<>();
    }

    public void setSum(String toWhom, double amount){
        iou.put(toWhom,amount);
    }
    public double howMuchDoIOweTo(String toWhom){
        double sum = 0;
        for(String s : this.iou.keySet()){
            if (s.equals(toWhom))
                    sum+= iou.get(s);
        }

        return  sum;
    }

    static void main() {
        IOU mattsIOU = new IOU();
        mattsIOU.setSum("Arthur", 51.5);
        mattsIOU.setSum("Michael", 30);

        System.out.println(mattsIOU.howMuchDoIOweTo("Arthur"));
        System.out.println(mattsIOU.howMuchDoIOweTo("Michael"));
    }
}
