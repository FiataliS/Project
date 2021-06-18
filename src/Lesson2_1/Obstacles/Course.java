package Lesson2_1.Obstacles;

import Lesson2_1.All_Сreatures.Team;
import Lesson2_1.Constructors.Competitor;

public class Course  {
    private Obstacle[] course;

    public Course(Obstacle... course) {this.course = course;}

    public void doIt(Team teams){
        for (Competitor c : teams.getCompetitors()) { //неполучалось, из за ошибки типа я не использовал ..getCompetitors()
            for (Obstacle o : course) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }
   }
}
