enum DayOfWeek {
    MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

    private int index;


    DayOfWeek(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
