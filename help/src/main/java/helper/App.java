package helper;


import java.util.*;
import java.util.function.Predicate;

public abstract class App<T extends String, U extends Integer>{
    private T name;
    private U n;
    private static final Comparator<App> comp = (a, b) -> a.n < b.n ? 1 : 0;
    private static Predicate<App> checker = a -> a.name.startsWith("a");

    public App(T name, U n) {
        this.name = name;
        this.n = n;
    }

    @Override
    public String toString() {
        return this.name;
    }

    void print() {
        System.out.println("print");
    }

    public static void main(String[] args) {
    }
}
