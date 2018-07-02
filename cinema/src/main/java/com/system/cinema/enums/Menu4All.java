package com.system.cinema.enums;

public enum Menu4All {

    MANAGEMENT("1.add new worker;2.remove worker;3.view worker;4.edit worker;5.exit\n");

    private String options;

    Menu4All(String options) {
        this.options = options;
    }

    private String[] getOptions() {
        return this.options.split(";");
    }

    public void printMenu() {
        System.out.println(this.name());
        for (String s : this.getOptions()) {
            System.out.println(s);
        }
    }
}
