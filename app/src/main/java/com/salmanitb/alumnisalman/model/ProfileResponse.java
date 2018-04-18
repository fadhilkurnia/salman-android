package com.salmanitb.alumnisalman.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Fadhil Imam Kurnia on 18/04/2018.
 */

public class ProfileResponse extends User{

    @SerializedName("aktifitas")
    private ArrayList<String> activityPlain;
    @SerializedName("tahun_aktif")
    private ArrayList<String> activityYearData;

    public ArrayList<String> getActivityPlain() {
        return activityPlain;
    }

    public void setActivityPlain(ArrayList<String> activityPlain) {
        this.activityPlain = activityPlain;
    }

    public ArrayList<String> getActivityYearData() {
        return activityYearData;
    }

    public void setActivityYearData(ArrayList<String> activityYearData) {
        this.activityYearData = activityYearData;
    }

    public void decodeActivityData() {
        activities = new ArrayList<>();
        for (int i = 0; i< activityPlain.size(); i++) {
            SalmanActivity act = new SalmanActivity(activityPlain.get(i), null, null);
            String yearPlain = activityYearData.get(i).replaceAll("\\s+", "");
            String yearData[] = yearPlain.split("-");
            if (yearData.length > 0)
                act.setStartYear(yearData[0]);
            if (yearData.length > 1)
                act.setEndYear(yearData[1]);
            activities.add(act);
        }
    }

}
