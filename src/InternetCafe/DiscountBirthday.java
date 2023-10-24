package InternetCafe;

import java.util.Date;

public class DiscountBirthday implements Discount {
    private final Date birthday; // protected ???

    public DiscountBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public int calculatePercent() {
        return 0;
    }
}
