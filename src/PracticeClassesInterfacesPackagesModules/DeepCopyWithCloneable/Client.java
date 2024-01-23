package PracticeClassesInterfacesPackagesModules.DeepCopyWithCloneable;


import java.time.LocalDateTime;

// Класс упрощён
public class Client implements Cloneable {
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

    @Override
    public Client clone() {
        try {
            return (Client) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
