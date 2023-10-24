package InternetCafe;

import java.util.Date;

public abstract class DeliveryMethod {
    protected Date timeFrom;
    protected Date timeTo;
    protected String address;

    public DeliveryMethod(Date timeFrom, Date timeTo, String address) {
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.address = address;
    }

    public abstract void calculateDeliveryPrice();
}
