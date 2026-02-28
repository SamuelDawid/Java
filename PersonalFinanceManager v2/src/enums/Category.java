package enums;

public enum Category {

    SALARY("Salary",1,TransactionType.INCOME),
    BONUS("Bonus",2,TransactionType.INCOME),
    GIFT("Gift",3,TransactionType.INCOME),
    FOOD("Food",4,TransactionType.EXPENSE),
    TRANSPORT("Transport",5,TransactionType.EXPENSE),
    ENTERTAINMENT("Entertainment",6,TransactionType.EXPENSE),
    BILLS("Bills",7,TransactionType.EXPENSE),
    SHOPPING("Shopping",8,TransactionType.EXPENSE),
    HEALTH("Health",9,TransactionType.EXPENSE),
    OTHER("Other",10,TransactionType.EXPENSE);

    private final String displayName;
    final int order;
    final TransactionType type;

    Category(String _displayName,int number, TransactionType type){
        this.order = number;
        this.displayName = _displayName;
        this.type = type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getOrder() {
        return order;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return order +" "+ displayName;
    }

}
