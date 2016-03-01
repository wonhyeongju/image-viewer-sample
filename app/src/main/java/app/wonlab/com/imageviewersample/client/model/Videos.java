package app.wonlab.com.imageviewersample.client.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Videos implements Serializable {

    @SerializedName("low_resolution")
    private Video lowResolution;

    @SerializedName("standard_resolution")
    private Video standardResolution;

    public Video getLowResolution() {
        return lowResolution;
    }

    public void setLowResolution(Video lowResolution) {
        this.lowResolution = lowResolution;
    }

    public Video getStandardResolution() {
        return standardResolution;
    }

    public void setStandardResolution(Video standardResolution) {
        this.standardResolution = standardResolution;
    }
}
