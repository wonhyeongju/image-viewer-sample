package app.wonlab.com.imageviewersample;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.Date;
import java.util.UUID;

import app.wonlab.com.imageviewersample.client.InstagramService;
import app.wonlab.com.imageviewersample.db.Tag;
import io.realm.Realm;

/**
 * Created by wonlab on 2016/03/01.
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (TextUtils.isEmpty(InstagramService.apiToken)) {
            throw new RuntimeException("InstagramService.apiToken is empty.");
        }

        checkFirstRun();
    }

    private void checkFirstRun() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean checkedFirstRun = preferences.getBoolean("checkedFirstRun", false);
        if (!checkedFirstRun) {
            // add tag
            Realm realm = Realm.getInstance(this);
            realm.beginTransaction();
            Tag tag = realm.createObject(Tag.class);
            tag.setTitle("Pizza");
            tag.setId(UUID.randomUUID().toString());
            tag.setCreatedDate(new Date());
            realm.commitTransaction();
            // set checked
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("checkedFirstRun", true);
            editor.apply();
        }
    }
}
