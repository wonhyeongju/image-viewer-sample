package app.wonlab.com.imageviewersample.client.model;

import java.io.Serializable;

public class Position implements Serializable {

    private Double y;

    private Double x;

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }
}
