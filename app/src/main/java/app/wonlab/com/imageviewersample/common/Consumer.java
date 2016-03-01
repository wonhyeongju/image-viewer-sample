package app.wonlab.com.imageviewersample.common;

/**
 * Created by wonlab on 2016/03/01.
 */
public interface Consumer<T> {
    void apply(T t);
}
