package Lesson2_1.All_Сreatures;

import Lesson2_1.Constructors.Competitor;

public class Team  {
    String name;
    Competitor[] competitors;

    public  Competitor[] getCompetitors() {return competitors;}

    public Team(String name, Competitor...competitors) {
        this.name = name;
        this.competitors = competitors;
    }
    public void showResults(){
        for(Competitor o: competitors) {
            if(o.isOnDistance()) {
                o.info();
            }
        }
    }
}
