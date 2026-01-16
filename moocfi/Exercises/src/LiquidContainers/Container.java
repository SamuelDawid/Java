package LiquidContainers;

public class Container {
    private int amountIn; final int maxAmount;

    public Container() {
        this.amountIn = 0;
        this.maxAmount = 100;
    }

    public int contains() {
        return amountIn;
    }

    public void add(int amount) {
        if (this.amountIn >= 0 && amount > 0) {
            amountIn += amount;
        }
        if(amountIn > maxAmount)
            amountIn = maxAmount;
    }
    public void remove(int amount){
        if(this.amountIn <= 0 || amount < 0)
            return;
        else
        {
            this.amountIn -= amount;
            if(amountIn < 0)
                amountIn = 0;
        }
    }

    @Override
    public String toString(){
        return amountIn +"/"+maxAmount;
    }
}

