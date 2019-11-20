package ru.iskra.peopl_cat_robot.athletes;

import ru.iskra.peopl_cat_robot.interfaces.BaseSport;

public class Robot implements BaseSport {
    private String myName = "unknown";

    public Robot() {
        myName = Thread.currentThread().getStackTrace()[1].getClassName().substring(Thread.currentThread().getStackTrace()[1].getClassName().lastIndexOf('.') + 1);
    }

    public Robot(String name_) {
        this();
        myName = name_;
    }

    @Override
    public String getName() {
        return myName;
    }

    @Override
    public boolean isOnDistance() {
        return false;
    }

    @Override
    public void  setDisqualification() {
    }

    @Override
    public void info() {
    }
}