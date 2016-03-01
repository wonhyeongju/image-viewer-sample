package app.wonlab.com.imageviewersample.client;

import app.wonlab.com.imageviewersample.client.model.RecentByTag;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by wonlab on 2016/03/01.
 */
public interface InstagramService {

    String apiToken = "";

    @GET("/tags/{tag_name}/media/recent")
    Observable<RecentByTag> getRecentByTag(
            @Path("tag_name") String tagName,
            @Query("access_token") String accessToken,
            @Query("min_id") String minId,
            @Query("max_id") String maxId
    );
}
