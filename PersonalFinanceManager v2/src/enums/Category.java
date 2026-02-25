package enums;

public enum Category {

    SALARY("Salary",1),
    BONUS("Bonus",2),
    GIFT("Gift",3),
    FOOD("Food",4),
    TRANSPORT("Transport",5),
    ENTERTAINMENT("Entertainment",6),
    BILLS("Bills",7),
    SHOPPING("Shopping",8),
    HEALTH("Health",9),
    OTHER("Other",10);

    private final String displayName;
    final int order;

    Category(String _displayName,int number){
        this.order = number;
        this.displayName = _displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


    public Integer getType() {
        return order;
    }

    @Override
    public String toString() {
        return "Category{" +
                "displayName='" + displayName + '\'' +
                ", type=" + order +
                '}';
    }

}
