package simulation.producer.models;
import java.awt.Color;
import java.util.Objects;
import java.util.Random;

public class Product {
    private static int count = 0;
    private Color color;
    private int id;

    public Product() {
        this.id = count++;
        Random rand = new Random();
        this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    public Product(Color color) {
        this.id = count++;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return this.id;
    }

    public Product color(Color color) {
        setColor(color);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(color, product.color) && id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, id);
    }

    @Override
    public String toString() {
        return "{" +
            " color='" + getColor() + "'" +
            ", id='" + getId() + "'" +
            "}";
    }
    
}
