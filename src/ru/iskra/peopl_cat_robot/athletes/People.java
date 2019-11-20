package ru.iskra.peopl_cat_robot;

import ru.iskra.peopl_cat_robot.interfaces.ActionJump;
import ru.iskra.peopl_cat_robot.interfaces.ActionRun;
import ru.iskra.peopl_cat_robot.interfaces.ActionSwim;
import ru.iskra.peopl_cat_robot.interfaces.BaseSport;

public class People implements BaseSport, ActionJump, ActionSwim, ActionRun {

    private String myType= "unknown";
    private String myName = "unknown";
    private int MaxJump = 0;
    private int MaxRun = 0;
    private int MaxSwim = 0;
    private boolean OnDistance = false;

    @Override
    public String getName() {
       return myName;
    }

    @Override
    public boolean isOnDistance() {
        return OnDistance;
    }

    @Override
    public void info() {
        System.out.println(myType + " " + myName + " " + (OnDistance ? " на дистанции" : " выбыл из соревнований"));
    }

    public People() {
        myType = Thread.currentThread().getStackTrace()[1].getClassName().substring(Thread.currentThread().getStackTrace()[1].getClassName().lastIndexOf('.') + 1);
    }

    public People(String name_, int MaxJump_, int MaxRun_, int MaxSwim_) {
        this();
        myName = name_;
        MaxJump = MaxJump_;
        MaxRun = MaxRun_;
        MaxSwim  = MaxSwim_;
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

    @Override
    public void swim(int distSwim) {
        System.out.println(myName + " попытка проплыть");
        if(distSwim > MaxSwim) {
            OnDistance = false;
        }
        else {
            OnDistance = true;
        }
    }
}
