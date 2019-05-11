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

    Money times(int multiplier) {
        return new Money(this.amount * multiplier, this.currency);
    }

    @Override
    public boolean equals(Object object) {
        Money money = (Money) object;
        return this.amount == money.amount && this.currency == money.currency;
    }


    @Override
    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(this.currency, to);
        return new Money(amount / rate, to);
    }

    @Override
    public Expression plus(Expression expression) {
        return new Sum(this, (Money) expression);
    }
}
