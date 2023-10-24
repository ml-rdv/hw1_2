package InternetCafe;

import java.util.Date;

public class DeliveryByPickup extends DeliveryMethod {
    public DeliveryByPickup(Date timeFrom, Date timeTo, String address) {
        super(timeFrom, timeTo, address);
    }

    @Override
    public void calculateDeliveryPrice() {
    }
}
