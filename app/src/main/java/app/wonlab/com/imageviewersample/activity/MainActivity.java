package app.wonlab.com.imageviewersample.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import app.wonlab.com.imageviewersample.R;
import app.wonlab.com.imageviewersample.fragment.HomeFragment;
import app.wonlab.com.imageviewersample.fragment.TagSettingFragment;
import app.wonlab.com.imageviewersample.util.LogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wonlab on 2016/03/01.
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.navigation_main)
    NavigationView navigationView;

    @Bind(R.id.drawer)
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupDrawerContent(navigationView);
        setContent(R.id.nav_home);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            setContent(menuItem.getItemId());
            drawerLayout.closeDrawers();
            return true;
        });
    }

    private void setContent(int resId) {
        switch (resId) {
            case R.id.nav_home: {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.group_content, HomeFragment.newInstance(), HomeFragment.TAG).commit();
                navigationView.getMenu().getItem(0).setChecked(true);
                break;
            }
            case R.id.nav_tag: {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(TagSettingFragment.TAG);
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.group_content, TagSettingFragment.newInstance(), TagSettingFragment.TAG).commit();
                navigationView.getMenu().getItem(1).setChecked(true);
                break;
            }
            default:
                LogUtil.e("index is not available.");
                break;
        }

    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

}
