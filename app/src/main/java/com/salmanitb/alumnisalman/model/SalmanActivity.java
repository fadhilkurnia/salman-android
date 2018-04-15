package com.salmanitb.alumnisalman.model;

/**
 * Created by Fadhil Imam Kurnia on 15/04/2018.
 */

public class SalmanActivity {
    String title;
    String startYear;
    String endYear;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
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
