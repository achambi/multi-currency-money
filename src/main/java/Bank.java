import java.util.Hashtable;

public class Bank {

    private Hashtable rates;

    Bank() {
        this.rates = new Hashtable();
    }

    public Money reduce(Expression expression, String to) {
        return expression.reduce(this, to);
    }

    public void addRate(String from, String to, int rate) {
        rates.put(new Pair(from, to), rate);
    }

    public int rate(String from, String to) {
        if (from == to) {
            return 1;
        }
        return (int) rates.get(new Pair(from, to));
    }
}
