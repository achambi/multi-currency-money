public class Money implements Expression {
    public final int amount;
    private final String currency;

    static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }


    public Expression times(int multiplier) {
        return new Money(this.amount * multiplier, this.currency);
    }


    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(this.currency, to);
        return new Money(amount / rate, to);
    }

    @Override
    public boolean equals(Object object) {
        Money money = (Money) object;
        return this.amount == money.amount && this.currency == money.currency;
    }
}
