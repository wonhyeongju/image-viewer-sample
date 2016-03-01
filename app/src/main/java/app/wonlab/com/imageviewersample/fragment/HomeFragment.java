package app.wonlab.com.imageviewersample.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Date;
import java.util.UUID;

import app.wonlab.com.imageviewersample.R;
import app.wonlab.com.imageviewersample.activity.MainActivity;
import app.wonlab.com.imageviewersample.adapter.ListFragmentPagerAdapter;
import app.wonlab.com.imageviewersample.db.Tag;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by wonlab on 2016/03/01.
 */
public class HomeFragment extends Fragment {

    public static final String TAG = "HOME";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.pager)
    ViewPager viewPager;

    @Bind(R.id.tabs)
    TabLayout tabLayout;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(v -> {
            ((MainActivity) getActivity()).getDrawerLayout().openDrawer(GravityCompat.START);
        });

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        Realm realm = Realm.getInstance(getActivity().getApplicationContext());
        realm.refresh();
        RealmResults<Tag> tags = realm.where(Tag.class).findAllSorted("createdDate", true);
        // refresh
        ListFragmentPagerAdapter adapter = new ListFragmentPagerAdapter(getChildFragmentManager());
        for (Tag tag : tags) {
            adapter.addFragment(ImageListFragment.newInstance(tag.getTitle()), tag.getTitle());
        }
        realm.close();
        viewPager.setAdapter(adapter);
    }

    @OnClick(R.id.btn_add)
    void addTag() {
        new MaterialDialog.Builder(getActivity())
                .content(R.string.msg_input_tag_title)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("", "", (dialog, input) -> {
                    if (!"".equals(input.toString().trim())) {
                        Realm realm = Realm.getInstance(getActivity().getApplicationContext());
                        realm.refresh();
                        realm.beginTransaction();
                        Tag tag = realm.createObject(Tag.class);
                        tag.setTitle(input.toString());
                        tag.setId(UUID.randomUUID().toString());
                        tag.setCreatedDate(new Date());
                        realm.commitTransaction();
                        realm.close();
                        // add
                        ((ListFragmentPagerAdapter)viewPager.getAdapter()).addFragment(ImageListFragment.newInstance(input.toString()), input.toString());
                        viewPager.getAdapter().notifyDataSetChanged();
                        tabLayout.setupWithViewPager(viewPager);
                        //viewPager.setCurrentItem(tabLayout.getTabCount() - 1, false);
                        tabLayout.getTabAt(tabLayout.getTabCount() - 1).select();
                    }

                })
                .positiveText(R.string.label_add)
                .positiveColorRes(R.color.md_orange_900)
                .negativeText(R.string.label_cancel)
                .negativeColorRes(R.color.md_grey_500)
                .show();
    }

}
