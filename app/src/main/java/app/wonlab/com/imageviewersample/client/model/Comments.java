package app.wonlab.com.imageviewersample.client.model;

import java.io.Serializable;
import java.util.List;

public class Comments implements Serializable {

    private Integer count;

    private List<Comment> data;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Comment> getData() {
        return data;
    }

    public void setData(List<Comment> data) {
        this.data = data;
    }
}
