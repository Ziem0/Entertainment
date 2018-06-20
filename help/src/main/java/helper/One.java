package helper;

public class One<T> extends App {
    T name1;

    public One(String name, Integer n, T name1) {
        super(name, n);
        this.name1 = name1;
    }

    void print() {
        System.out.println("print 2");
    }

    public static void main(String[] args) {
        App a = new One<String>("z", 1, "ol");

    }
}
