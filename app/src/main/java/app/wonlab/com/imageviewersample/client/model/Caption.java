package app.wonlab.com.imageviewersample.client.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Caption implements Serializable {

    @SerializedName("created_time")
    private Long createdTime;

    private String text;

    private User from;

    private Long id;

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
