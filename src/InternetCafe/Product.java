package InternetCafe;

import java.util.Date;

public class Product {
    private int id;
    private String name;
    private int amount;
    private Date manufacturingDate;
    private Date shelfLife;

    public Product(String name, int amount, Date manufacturingDate, Date shelfLife) {
        this.name = name;
        this.amount = amount;
        this.manufacturingDate = manufacturingDate;
        this.shelfLife = shelfLife;
    }

    public void changeAmount(String name, int amount) {
    }

}
