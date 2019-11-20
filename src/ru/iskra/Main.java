/**
 * Класс выполнения домашнего задания
 * @autor Vadim Timofeev
 * @version 0.1
 */

package ru.iskra;
import ru.iskra.*;
import ru.iskra.peopl_cat_robot.athletes.Calc;
import ru.iskra.peopl_cat_robot.athletes.Cat;
import ru.iskra.peopl_cat_robot.athletes.People;
import ru.iskra.peopl_cat_robot.athletes.Robot;
import ru.iskra.peopl_cat_robot.interfaces.BaseSport;
import ru.iskra.peopl_cat_robot.interfaces.Competition;
import ru.iskra.peopl_cat_robot.obstacles.Pool;
import ru.iskra.peopl_cat_robot.obstacles.Track;
import ru.iskra.peopl_cat_robot.obstacles.Wall;

public class Main {

    private static void  run_test_people_cat_robot() {
        People people1 = new People("Иван", 1, 3500, 1200);
        People people2 = new People("Жора", 2, 2900, 1000);
        People people3 = new People("Ганя", 1, 4000, 400);
        Cat cat = new Cat("Муся", 3, 1000);
        Robot robot = new Robot("Железяка", 99999999);
        Calc calc = new Calc();

        Object[] obj = {people1, people2, people3, cat, robot,  calc};

        Pool pool = new Pool(500);
        Track track =  new Track(500);

        Competition[] comp = {track, new Wall(2), pool};

        for (Object o :obj) {
            if(!(o instanceof BaseSport)) {
                System.out.println(o.getClass().getName() + " - Это не спортсмен");
            }else {
                for(Competition c: comp) {
                    c.start(o);
                }
                ((BaseSport) o).info();
                System.out.println("----------------");
            }
        }
    }

    public static void enums_test() {

    }

    public static void main(String[] args) {
	    // Начинаем проверять домашнее задание
        run_test_people_cat_robot();
        enums_test();
        // Домашнее задание закончено
        return;
    }
}
