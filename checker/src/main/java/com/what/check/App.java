package com.what.check;

public class App {

    public static void main(String[] args) {
        Person p3 = new Person("gabi", 29);
        Person p4 = new Person("ewa", 50);

        ConnectionDao.migrate();
        MentorDao md = MentorDao.getDao();
        md.save(p3);
        md.save(p4);
        ConnectionDao.close();
    }
}
