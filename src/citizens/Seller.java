package citizens;

public class Seller extends Citizen{
    private final int products;

    public Seller(String name, int age)
    {
        super(name,age);
        products=10;
    }

    public int getProducts() {
        return products;
    }
}
