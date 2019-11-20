package ru.iskra.peopl_cat_robot;

import ru.iskra.peopl_cat_robot.interfaces.BaseSport;

public class Cat implements BaseSport {
    private String myName = "unknown";

    public Cat() {
        myName = Thread.currentThread().getStackTrace()[1].getClassName().substring(Thread.currentThread().getStackTrace()[1].getClassName().lastIndexOf('.') + 1);
    }

    public Cat(String name_) {
        this();
        myName = name_;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isOnDistance() {
        return false;
    }

    @Override
    public void info() {

    }
}
