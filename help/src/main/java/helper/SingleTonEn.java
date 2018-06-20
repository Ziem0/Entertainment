package helper;

import java.util.ArrayList;
import java.util.List;

public enum SingleTonEn {
    INSTANCE;

    public List<String> list = new ArrayList<>();

    public void add(String s) {
        list.add(s);
    }

    public static void main(String[] args) {
        SingleTonEn.INSTANCE.add("Ziemo");
        SingleTonEn.INSTANCE.add("Ania");
        SingleTonEn.INSTANCE.add("Nina");
        SingleTonEn.INSTANCE.list.forEach(System.out::println);
    }
}
