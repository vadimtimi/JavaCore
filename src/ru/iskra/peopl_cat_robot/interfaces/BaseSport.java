package ru.iskra.peopl_cat_robot.interfaces;

//
public interface BaseSport {
    /**
     * Получите Имя
     * @return Имя спортсмена
     */
    public String getName();

    /**
     * Узнать находится ли спортсмен на дистанции
     * @return
     */
    boolean isOnDistance();

    /**
     * Дисквалификация спортсмена
     */
    void setDisqualification();

    /**
     * Вывести в консоль информацию о спортсмене
     */
    void info();
}
