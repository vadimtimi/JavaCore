package ru.iskra.peopl_cat_robot.obstacles;

import ru.iskra.peopl_cat_robot.interfaces.ActionRun;
import ru.iskra.peopl_cat_robot.interfaces.BaseSport;
import ru.iskra.peopl_cat_robot.interfaces.Competition;

public class Track implements Competition {
    int dist = 0;
    public Track(int dist_) {
        dist = dist_;
    }
    @Override
    public void start(Object obj) {
        // Реализует ли Object интерфейс плавания
        if (obj instanceof ActionRun) {
            ((ActionRun)obj).run(dist);
        }else {
            // Реализует ли Object базового спортсмена
            if(obj instanceof BaseSport){
                ((BaseSport) obj).setDisqualification();
                System.out.println(((BaseSport) obj).getName() + " Дисквалифицирован по бегу");
            }
        }
    }
}
