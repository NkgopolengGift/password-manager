package passwordmanager.payment.entity;

public enum PaymentMethod {
    CREDIT_CARD,
    EFT,
    DEBIT_CARD;

    @Override
    public String toString() {
        switch(this) {
            case CREDIT_CARD: return "Credit Card";
            case EFT: return "Electronic Funds Transfer";
            case DEBIT_CARD: return "Debit Card";
            default: throw new IllegalArgumentException();
        }
    }
}
