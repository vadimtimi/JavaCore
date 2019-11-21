package ru.iskra.enums;

public enum DayOfWeek {
    MONDAY {public int getWorkingHours(){ return 40;}} ,
    TUESDAY {public int getWorkingHours(){ return 40-8;}} ,
    WEDNESDAY {public int getWorkingHours(){ return 40-8-8;}},
    THURSDAY {public int getWorkingHours(){ return 40-8-8-8;}},
    FRIDAY {public int getWorkingHours(){ return 40-8-8-8-8;}},
    SATURDAY {public int getWorkingHours(){ return 0;}},
    SUNDAY {public int getWorkingHours(){ return 0;}};

    public abstract int getWorkingHours();
}
