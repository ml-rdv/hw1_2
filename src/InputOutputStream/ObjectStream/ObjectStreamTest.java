package InputOutputStream.ObjectStream;

import java.io.*;

/**
 * Реализовать 2 метода:
 * Первый сохраняет объект класса в файл в виде байтов
 * Второй из файла загружает байты и десериализует их в объект
 */
public class ObjectStreamTest {
    public static void main(String[] args) {
        Worker worker = new Worker("John", 45);

        writeObject(worker);
        Worker worker2 = readObject();

        System.out.println(worker2);
    }

    public static void writeObject(Worker worker) {
        String filename = "SerializedObject";
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(worker);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Worker readObject() {
        Worker worker = null;
        String filename = "SerializedObject";
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fis)) {
            worker = (Worker) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return worker;
    }
}
