package boersenspiel.stock;

/**
 * User: Peach
 * Date: 09.04.13
 * Time: 17:34
 */

public class Share implements Comparable<Share> {

    private final String name;
    private long price;

    public Share clone(){
        Share clone = new Share(name, price);
        return clone;
    }

    public Share(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public String toString() {
        return ("Share mit den Namen " + name + " und den Preis " + price);
    }

    public void setPrice(long set) {
        price = set;
    }

    public void increasePrice(long set) {
        if (price + set > 0) {
            this.price += set;
        }

    }

    @Override
    public int compareTo(Share o) {
        return this.getName().compareTo(o.getName());
    }
}
