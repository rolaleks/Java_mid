package competition;

import competition.competitors.Competitor;
import competition.obstacles.Obstacle;

public class Course {
    private Obstacle[] obstacles;

    public Course(Obstacle[] obstacles) {
        this.obstacles = obstacles;
    }

    public void doIt(Team team) {

        System.out.println("----" + team.getName() + " начинает проходит полосу---- ");
        Competitor[] competitors = team.getCompetitors();
        boolean successPass;
        for (Competitor competitor : competitors) {

            for (Obstacle obstacle : this.obstacles) {
                obstacle.doIt(competitor);
                if (!competitor.isOnDistance()) break;
            }
            competitor.info();
        }

    }
}
