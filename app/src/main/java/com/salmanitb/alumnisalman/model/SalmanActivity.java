package com.salmanitb.alumnisalman.model;

/**
 * Created by Fadhil Imam Kurnia on 15/04/2018.
 */

public class SalmanActivity {
    private String title;
    private String startYear;
    private String endYear;

    public SalmanActivity(String title, String startYear, String endYear) {
        this.title = title;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartYear() {
        if (startYear == null)
            startYear = "";
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        if(endYear == null)
            endYear = "";
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    @Override
    public String toString() {
        return title + " (" + startYear + "-" + endYear + ")";
    }
}
