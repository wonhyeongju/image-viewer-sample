package app.wonlab.com.imageviewersample.db;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by wonlab on 2016/03/01.
 */
public class Tag extends RealmObject {

    private String id;

    private String title;

    private Date createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
