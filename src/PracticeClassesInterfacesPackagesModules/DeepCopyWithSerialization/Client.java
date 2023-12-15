package PracticeClassesInterfacesPackagesModules.DeepCopyWithSerialization;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.Date;

// Класс упрощён
public class Client implements Serializable {
    protected String fullName;
    protected int phoneNumber;
    protected String email;
    protected LocalDateTime birthday;

    public Client(String fullName, int phoneNumber, String email, LocalDateTime birthday) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
    }
}
