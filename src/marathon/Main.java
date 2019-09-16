package marathon;

import competition.Course;
import competition.Team;
import competition.competitors.Cat;
import competition.competitors.Competitor;
import competition.competitors.Dog;
import competition.competitors.Human;
import competition.obstacles.Cross;
import competition.obstacles.Obstacle;
import competition.obstacles.Wall;
import competition.obstacles.Water;

public class Main {
    public static void main(String[] args) {
        //Команда с инициализированными способностями
        Competitor[] t1_competitors = {
                new Dog("Franky", 150, 200, 50),
                new Human("Ivan", 200, 100, 200),
                new Cat("Mauch", 100, 300, 0),
                new Dog("Bamboo", 300, 50, 100),

        };
        //Команда с рендомными показателями способностей {@link competition.competitors.Animal#getRandomAttributeValue()}
        Competitor[] t2_competitors = {
                new Human("Peter"),
                new Cat("Mursik"),
                new Human("Sergey"),
                new Dog("Boby")
        };
        Team firstTeam = new Team("First Team", t1_competitors);
        Team secondTeam = new Team("second Team", t2_competitors);

        //Первая полоса препятсвий
        Obstacle[] obstacles_1 = {
                new Cross(100),
                 new Wall(100),
                new Water(50)
        };

        //Вторая полоса препятсвий
        Obstacle[] obstacles_2 = {
                new Water(100),
                new Cross(50),
                new Wall(100)
        };

        Course firstCourse = new Course(obstacles_1);
        Course secondCourse = new Course(obstacles_2);

        //Проход всех полос первой командой
        firstTeam.info();
        firstCourse.doIt(firstTeam);
        secondCourse.doIt(firstTeam);

        //Проход всех полос 2й командой
        secondTeam.info();
        firstCourse.doIt(secondTeam);
        secondCourse.doIt(secondTeam);

        System.out.println("----ИТОГИ-----");
        //Подводим итоги
        firstTeam.statusInfo();
        secondTeam.statusInfo();


    }
}
