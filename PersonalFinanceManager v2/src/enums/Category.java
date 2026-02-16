package enums;

public enum Category {

    SALARY("Salary",TransactionType.INCOME),
    BONUS("Bonus",TransactionType.INCOME),
    GIFT("Gift",TransactionType.INCOME),
    FOOD("Food",TransactionType.EXPENSE),
    TRANSPORT("Transport",TransactionType.EXPENSE),
    ENTERTAINMENT("Entertainment",TransactionType.EXPENSE),
    BILLS("Bills",TransactionType.EXPENSE),
    SHOPPING("Shopping",TransactionType.EXPENSE),
    HEALTH("Health",TransactionType.EXPENSE),
    OTHER("Other",TransactionType.EXPENSE);

    private String displayName;
    TransactionType type;

    Category(String _displayName,TransactionType _type){
        this.type = _type;
        this.displayName = _displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
