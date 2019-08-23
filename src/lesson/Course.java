package lesson;

public class Course {

    private Action[] actions;

    public Course(Action[] actions) {
        this.actions = actions;
    }

    public void doIt(Team team) {

        Participant[] participants = team.getParticipants();
        boolean successPass;
        for (int i = 0; i < participants.length; i++) {
            successPass = true;
            for (int j = 0; j < this.actions.length; j++) {
                successPass &= this.actions[j].pass(participants[i]);
                if (!successPass) {
                    break;
                }
            }
            team.setStatus(i, successPass);
        }

    }
}
