
public class LessonExtra {

    private static int workingDayHours = 8;

    private static DayOfWeek[] weekends = {DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};

    public static void main(String[] args) {
        System.out.println("--5 часовя рабочая неделя--");
        System.out.println(DayOfWeek.FRIDAY + " осталось часов: " + getWorkingHours(DayOfWeek.FRIDAY));
        System.out.println(DayOfWeek.MONDAY + " осталось часов: " + getWorkingHours(DayOfWeek.MONDAY));
        System.out.println(DayOfWeek.SUNDAY + " осталось часов: " + getWorkingHours(DayOfWeek.SUNDAY));

        System.out.println("--выходной в среду--");
        weekends = new DayOfWeek[]{DayOfWeek.WEDNESDAY};
        System.out.println(DayOfWeek.THURSDAY + " осталось часов: " + getWorkingHours(DayOfWeek.THURSDAY));
        System.out.println(DayOfWeek.TUESDAY + " осталось часов: " + getWorkingHours(DayOfWeek.TUESDAY));

        System.out.println("--безвыходных--");
        weekends = new DayOfWeek[]{};
        System.out.println(DayOfWeek.THURSDAY + " осталось часов: " + getWorkingHours(DayOfWeek.THURSDAY));
    }

    private static int getWorkingHours(DayOfWeek dayOfWeek) {

        //Проверяем, dayOfWeek не выходной
        if (isWeekend(dayOfWeek)) {
            return 0;
        }

        //Ищем ближайщий выходной, тк выходные могут быть не суббота и воскресенье
        int nearestWeekendIndex = nearestWeekendIndex(dayOfWeek);

        if (nearestWeekendIndex > dayOfWeek.getIndex()) {
            //если выходной будет на этой недели
            return (nearestWeekendIndex - dayOfWeek.getIndex()) * workingDayHours;
        }
        //если выходной будет только на след недели
        return (7 - dayOfWeek.getIndex() + nearestWeekendIndex) * workingDayHours;
    }

    private static boolean isWeekend(DayOfWeek dayOfWeek) {
        for (DayOfWeek weekendDay : weekends) {
            if (dayOfWeek == weekendDay) {
                return true;
            }
        }
        return false;
    }

    private static int nearestWeekendIndex(DayOfWeek dayOfWeek) {
        //0 - выходной не найден
        int index = 0;
        for (DayOfWeek weekendDay : weekends) {
            if (index == 0) {
                //если ближайщий выходной еще не найдет ставим первы выходной
                index = weekendDay.getIndex();
                continue;
            }
            if (weekendDay.getIndex() > dayOfWeek.getIndex()) {
                //если выходной еще будет на этой недели
                //проверяем что бы порядок дня был меньше уже найденого, но не меньше текущего дня
                index = weekendDay.getIndex() < index || index < dayOfWeek.getIndex() ? weekendDay.getIndex() : index;
            } else if (index <= weekendDay.getIndex()) {
                //если выходной уже прошел, и най этой недели уже не будет выходных
                index = weekendDay.getIndex() < index ? weekendDay.getIndex() : index;
            }
        }
        if (index > 0) {
            return index;
        }

        throw new ArrayIndexOutOfBoundsException("Без выходных работать нельзя!");
    }
}
