package competition;

import competition.competitors.Competitor;

public class Team {

    private Competitor[] competitors;

    private String name;

    public Team(String name) {
        this.name = name;
    }

    public Team(String name, Competitor[] competitors) {
        this.competitors = competitors;
        this.name = name;
    }

    public Competitor[] getCompetitors() {
        return this.competitors;
    }

    public void info() {
        System.out.println("----" + this.name + "---- ");
        for (Competitor participant : this.competitors) {
            System.out.println("Команда " + this.name + " содержит " + participant);
        }
    }

    public String getName() {
        return name;
    }

    public void statusInfo() {
        for (Competitor participant : this.competitors) {
            if(participant.isOnDistance()){
                System.out.println("Участник команда " + this.name + ", " +  participant + " прошел препятсвия");
            }else{
                System.out.println("Участник команда " + this.name + ", " +  participant + " не проше препятсвия");
            }

        }
    }
}
