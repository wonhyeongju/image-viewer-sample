package app.wonlab.com.imageviewersample.client.model;

import java.io.Serializable;

public class MediaResponse implements Serializable {

    private Media data;

    public Media getData() {
        return data;
    }

    public void setData(Media data) {
        this.data = data;
    }
}
