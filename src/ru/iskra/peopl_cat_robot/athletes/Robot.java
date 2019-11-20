package ru.iskra.peopl_cat_robot.athletes;

import ru.iskra.peopl_cat_robot.interfaces.ActionRun;
import ru.iskra.peopl_cat_robot.interfaces.BaseSport;

public class Robot implements BaseSport, ActionRun {
    private String myType= "unknown";
    private String myName = "unknown";
    private int MaxRun = 0;
    private boolean OnDistance = false;

    public Robot() {
        myType = Thread.currentThread().getStackTrace()[1].getClassName().substring(Thread.currentThread().getStackTrace()[1].getClassName().lastIndexOf('.') + 1);
    }

    public Robot(String name_, int MaxRun_ ) {
        this();
        myName = name_;
        MaxRun = MaxRun;
    }

    @Override
    public String getName() {
        return myName;
    }

    @Override
    public boolean isOnDistance() {
        return OnDistance;
    }

    @Override
    public void  setDisqualification() {
        OnDistance = false;
    }

    @Override
    public void info() {
        System.out.println(myType + " " + myName + " " + (OnDistance ? " на дистанции" : " выбыл из соревнований"));
    }

    @Override
    public void run(int distRun) {
        System.out.println(myName + " попытка пробежать");
        if(distRun > MaxRun) {
            OnDistance = false;
        }
        else {
            OnDistance = true;
        }
    }
}