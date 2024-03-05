package fr.angelsky.angelskycoalitions.coalition;

public class Coalition {

    private final CoalitionType coalitionType;
    private int eventPoints;
    private int monthlyEventPoints;
    private int coalitionPoints;

    public Coalition(CoalitionType coalitionType, int eventPoints, int monthlyEventPoints, int coalitionPoints)
    {
        this.coalitionType = coalitionType;
    }

    public CoalitionType getCoalitionType() {
        return coalitionType;
    }

    public int getEventPoints() {
        return eventPoints;
    }

    public void setEventPoints(int eventPoints) {
        this.eventPoints = eventPoints;
    }

    public int getMonthlyEventPoints() {
        return monthlyEventPoints;
    }

    public void setMonthlyEventPoints(int monthlyEventPoints) {
        this.monthlyEventPoints = monthlyEventPoints;
    }

    public int getCoalitionPoints() {
        return coalitionPoints;
    }

    public void setCoalitionPoints(int coalitionPoints) {
        this.coalitionPoints = coalitionPoints;
    }
}
