package lesson;

public class Team {

    private Participant[] participants;
    private boolean[] participantStatus;

    private String name;

    public Team(String name) {
        this.name = name;
    }

    public Team(String name, Participant[] participants) {
        this.participants = participants;
        this.participantStatus = new boolean[participants.length];
        this.name = name;
    }

    public Participant[] getParticipants() {
        return this.participants;
    }

    public void setStatus(int index, boolean status) {
        this.participantStatus[index] = status;
    }

    public void info() {
        for (Participant participant : this.participants) {
            System.out.println("Команда " + this.name + " содержит " + participant);
        }
    }

    public String getName() {
        return name;
    }

    public void statusInfo() {
        for (int i = 0; i < this.participants.length; i++) {
            if(this.participantStatus[i]){
                System.out.println("Участник команда " + this.name + ", " +  this.participants[i] + " прошел препятсвия");
            }else{
                System.out.println("Участник команда " + this.name + ", " +  this.participants[i] + " не проше препятсвия");
            }

        }
    }
}
