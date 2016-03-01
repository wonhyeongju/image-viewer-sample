package app.wonlab.com.imageviewersample.client.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TagInfo implements Serializable {

    @SerializedName("media_count")
    private Integer mediaCount;

    private String name;

    public Integer getMediaCount() {
        return mediaCount;
    }

    public void setMediaCount(Integer mediaCount) {
        this.mediaCount = mediaCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
