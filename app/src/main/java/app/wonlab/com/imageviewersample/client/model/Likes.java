package app.wonlab.com.imageviewersample.client.model;

import java.io.Serializable;
import java.util.List;

public class Likes implements Serializable {

    private Integer count;

    private List<User> data;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
