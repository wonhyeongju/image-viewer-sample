package app.wonlab.com.imageviewersample.client.model;

import java.io.Serializable;

public class TagInfoResponse implements Serializable {

    private TagInfo data;

    public TagInfo getData() {
        return data;
    }

    public void setData(TagInfo data) {
        this.data = data;
    }
}
