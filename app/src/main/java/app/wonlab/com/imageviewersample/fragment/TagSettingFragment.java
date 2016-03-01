package app.wonlab.com.imageviewersample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Date;
import java.util.UUID;

import app.wonlab.com.imageviewersample.R;
import app.wonlab.com.imageviewersample.activity.MainActivity;
import app.wonlab.com.imageviewersample.db.Tag;
import app.wonlab.com.imageviewersample.util.LogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by wonlab on 2016/03/01.
 */
public class TagSettingFragment extends Fragment {

    public static final String TAG = "TAG_SETTING";

    @Bind(R.id.group_tag)
    ViewGroup tagGroup;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public static TagSettingFragment newInstance() {
        return new TagSettingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tag_setting, container, false);
        ButterKnife.bind(this, view);

        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(v -> {
            ((MainActivity) getActivity()).getDrawerLayout().openDrawer(GravityCompat.START);
        });

        updateTagList();
        return view;
    }

    private void updateTagList() {
        if (tagGroup.getChildCount() > 0) {
            tagGroup.removeAllViews();
        }
        Realm realm = Realm.getInstance(getActivity().getApplicationContext());
        realm.refresh();
        RealmResults<Tag> tags = realm.where(Tag.class).findAllSorted("createdDate", true);
        for (Tag tag : tags) {
            View tagView = View.inflate(getActivity(), R.layout.item_tag, null);
            ((TextView)tagView.findViewById(R.id.label_title)).setText(tag.getTitle());
            View deleteButton = tagView.findViewById(R.id.btn_delete);
            deleteButton.setOnClickListener(this::onClick);
            deleteButton.setTag(tag.getId());
            tagGroup.addView(tagView);
        }
        realm.close();
    }

    public void onClick(View view) {
        LogUtil.d("onclick delete : " + view.getTag());
        new MaterialDialog.Builder(getActivity())
                .content(getString(R.string.msg_confirm_delete))
                .negativeText(getString(R.string.label_cancel))
                .negativeColorRes(R.color.md_white_1000)
                .positiveText(getString(R.string.label_delete))
                .positiveColorRes(R.color.md_red_600)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        // db
                        Realm realm = Realm.getInstance(getActivity().getApplicationContext());
                        realm.refresh();
                        realm.beginTransaction();
                        Tag tag = realm.where(Tag.class).equalTo("id", view.getTag().toString()).findFirst();
                        tag.removeFromRealm();
                        realm.commitTransaction();
                        realm.close();
                        // refresh
                        updateTagList();
                        dialog.dismiss();
                    }
                }).show();
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
                    }
                    // refresh
                    updateTagList();
                })
                .positiveText(R.string.label_add)
                .positiveColorRes(R.color.md_orange_900)
                .negativeText(R.string.label_cancel)
                .negativeColorRes(R.color.md_grey_500)
                .show();
    }
}
