package InternetCafe;

import java.util.Date;
import java.util.List;

public class Client {
    private int id;
    private String fullName;
    private int phoneNumber;
    private String email;
    private Date birthday;
    private List<Order> orders;

    public Client(String fullName, int phoneNumber, String email, Date birthday) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
        id = 0;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public boolean changePhoneNumber(int phoneNumber) {
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void createOrder() {
    }

    public boolean changeEmail(String email) {
        return true;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", orders=" + orders +
                '}';
    }
}
