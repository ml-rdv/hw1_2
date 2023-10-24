package InternetCafe;

import java.util.Date;

public class DeliveryByCourier extends DeliveryMethod {
    public DeliveryByCourier(Date timeFrom, Date timeTo, String address) {
        super(timeFrom, timeTo, address);
    }

    @Override
    public void calculateDeliveryPrice() {
    }
}
