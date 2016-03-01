package app.wonlab.com.imageviewersample.client.model;

import java.io.Serializable;

public class UserInPhoto implements Serializable {

    private Position position;

    private User user;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
