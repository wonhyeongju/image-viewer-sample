package app.wonlab.com.imageviewersample.client.model;

import java.io.Serializable;
import java.util.List;

public class RecentByTag implements Serializable {

    private Pagination pagination;

    private List<Media> data;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<Media> getData() {
        return data;
    }

    public void setData(List<Media> data) {
        this.data = data;
    }
}
