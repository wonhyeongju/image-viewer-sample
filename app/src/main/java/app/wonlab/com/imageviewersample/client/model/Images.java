package app.wonlab.com.imageviewersample.client.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Images implements Serializable {

    @SerializedName("low_resolution")
    private Image lowResolution;

    private Image thumbnail;

    @SerializedName("standard_resolution")
    private Image standardResolution;

    public Image getLowResolution() {
        return lowResolution;
    }

    public void setLowResolution(Image lowResolution) {
        this.lowResolution = lowResolution;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Image getStandardResolution() {
        return standardResolution;
    }

    public void setStandardResolution(Image standardResolution) {
        this.standardResolution = standardResolution;
    }
}
