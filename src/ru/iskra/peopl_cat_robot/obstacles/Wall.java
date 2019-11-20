package ru.iskra.peopl_cat_robot.obstacles;

import ru.iskra.peopl_cat_robot.interfaces.ActionJump;
import ru.iskra.peopl_cat_robot.interfaces.BaseSport;
import ru.iskra.peopl_cat_robot.interfaces.Competition;

public class Wall implements Competition {
    int dist = 0;
    public Wall(int dist_) {
        dist = dist_;
    }
    @Override
    public void start(Object obj) {
        // Реализует ли Object интерфейс плавания
        if (obj instanceof ActionJump) {
            ((ActionJump)obj).jump(dist);
        }else {
            // Реализует ли Object базового спортсмена
            if(obj instanceof BaseSport){
                ((BaseSport) obj).setDisqualification();
                System.out.println(((BaseSport) obj).getName() + " Дисквалифицирован");
            }
        }
    }
}
