package lesson;

public class Lesson1 {
    public static void main(String[] args) {

        //Часть 1, из методички
        System.out.println("Часть 1 (методичка)");
        Participant participants[] = new Participant[5];
        participants[0] = new Cat(20, 10);
        participants[1] = new Robot(1, 15);
        participants[2] = new Human(20, 24);
        participants[3] = new Human(24, 2);
        participants[4] = new Robot(10, 18);

        Action actions[] = new Action[3];
        actions[0] = new Treadmill(10);
        actions[1] = new Wall(15);
        actions[2] = new Treadmill(18);
        for(int i = 0; i<5; i++) {
            System.out.println("--------------");
            System.out.println("Участник номер:" + (i+1));
            System.out.println(participants[i] + " начал прохождение препятсвий");
            int j;
            for(j = 0; j<3; j++){
                if(!actions[j].pass(participants[i])){
                    System.out.println(participants[i] + " не справился");
                    break;
                }
            }
            if(j == actions.length)
                System.out.println(participants[i] + " прошел все препятсвия");
        }

        //Часть 2, с сайта
        System.out.println("--------Часть 2 (сайт)-------");


        Participant bestTeamParticipants[] = new Participant[4];
        bestTeamParticipants[0] = new Cat(15, 18);
        bestTeamParticipants[1] = new Robot(5, 17);
        bestTeamParticipants[2] = new Human(4, 15);
        bestTeamParticipants[3] = new Human(20, 30);

        Team bestTeam = new Team("Best team", bestTeamParticipants);
        Team firstPartTeam = new Team("First part team", participants);
        Course course = new Course(actions);

        firstPartTeam.info();
        bestTeam.info();
        System.out.println("--------Прохождение препятсвий-------");
        System.out.println("--Команда " + firstPartTeam.getName());
        course.doIt(firstPartTeam);
        System.out.println("--Команда " + bestTeam.getName());
        course.doIt(bestTeam);
        System.out.println("--------Результаты-------");
        firstPartTeam.statusInfo();
        bestTeam.statusInfo();


    }
}
