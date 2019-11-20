package ru.iskra.peopl_cat_robot.athletes;

import ru.iskra.peopl_cat_robot.interfaces.ActionJump;
import ru.iskra.peopl_cat_robot.interfaces.ActionRun;
import ru.iskra.peopl_cat_robot.interfaces.BaseSport;

public class Cat implements BaseSport , ActionJump, ActionRun {
    private String myType= "unknown";
    private String myName = "unknown";
    private int MaxJump = 0;
    private int MaxRun = 0;
    private boolean OnDistance = false;
    public Cat() {
        myType = Thread.currentThread().getStackTrace()[1].getClassName().substring(Thread.currentThread().getStackTrace()[1].getClassName().lastIndexOf('.') + 1);
    }

    public Cat(String name_, int MaxJump_, int MaxRun_) {
        this();
        myName = name_;
        MaxJump = MaxJump_;
        MaxRun = MaxRun_;
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
    public void setDisqualification() {
        OnDistance = false;
    }

    @Override
    public void info() {
        System.out.println(myType + " " + myName + " " + (OnDistance ? " на дистанции" : " выбыл из соревнований"));
    }

    @Override
    public void jump(int distJump) {
        System.out.println(myName + " попытка прыгнуть");
        if(distJump > MaxJump) {
            OnDistance = false;
        }
        else {
            OnDistance = true;
        }
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
