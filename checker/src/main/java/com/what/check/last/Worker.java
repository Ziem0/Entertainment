package com.what.check.last;

public class Worker {
    private int id;
    private String name;
    private int age;

    public Worker(String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Worker(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("id:%d name:%s age:%d", this.id, this.name, this.age);
    }
}
