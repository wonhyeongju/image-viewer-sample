package app.wonlab.com.imageviewersample.client.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pagination implements Serializable {

    @SerializedName("next_url")
    private String nextUrl;

    @SerializedName("next_min_id")
    private String nextMinId;

    @SerializedName("next_max_id")
    private String nextMaxId;

    @SerializedName("next_min_tag_id")
    private String nextMinTagId;

    @SerializedName("next_max_tag_id")
    private String nextMaxTagId;

    @SerializedName("next_cursor")
    private String nextCursor;

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public String getNextMinId() {
        return nextMinId;
    }

    public void setNextMinId(String nextMinId) {
        this.nextMinId = nextMinId;
    }

    public String getNextMaxId() {
        return nextMaxId;
    }

    public void setNextMaxId(String nextMaxId) {
        this.nextMaxId = nextMaxId;
    }

    public String getNextMinTagId() {
        return nextMinTagId;
    }

    public void setNextMinTagId(String nextMinTagId) {
        this.nextMinTagId = nextMinTagId;
    }

    public String getNextMaxTagId() {
        return nextMaxTagId;
    }

    public void setNextMaxTagId(String nextMaxTagId) {
        this.nextMaxTagId = nextMaxTagId;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }
}
