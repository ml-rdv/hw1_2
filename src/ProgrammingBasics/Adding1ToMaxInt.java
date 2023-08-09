package ProgrammingBasics;// Определить максимально и минимальное значение для Integer. Прибавить 1 от максимального
// и отнять 1 от минимального значения, затем вывести результаты. Проделать те же самые операции
// но через Math.addExact() и посмотреть что будет. (Прочитать про переполнение чисел)

public class Adding1ToMaxInt {
    public static void main(String[] args) {
        int minInt = Integer.MIN_VALUE;
        int maxInt = Integer.MAX_VALUE;
        minInt -= 1;
        maxInt += 1;
        System.out.println(minInt + " " + maxInt);

        minInt = Integer.MIN_VALUE;
        maxInt = Integer.MAX_VALUE;
        // integer overflow
//        minInt = Math.addExact(minInt, -1);
//        maxInt = Math.addExact(maxInt, 1);
//        System.out.println(minInt + " " + maxInt);
    }
}
