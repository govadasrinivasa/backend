package com.capco.capcopay.response;

public class Duration {

	private String endDate;

    private String startDate;

    public String getEndDate ()
    {
        return endDate;
    }

    public void setEndDate (String endDate)
    {
        this.endDate = endDate;
    }

    public String getStartDate ()
    {
        return startDate;
    }

    public void setStartDate (String startDate)
    {
        this.startDate = startDate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [endDate = "+endDate+", startDate = "+startDate+"]";
    }
}
