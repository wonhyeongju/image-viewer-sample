package app.wonlab.com.imageviewersample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import app.wonlab.com.imageviewersample.R;
import app.wonlab.com.imageviewersample.adapter.ImageRecyclerAdapter;
import app.wonlab.com.imageviewersample.client.InstagramService;
import app.wonlab.com.imageviewersample.client.model.Media;
import app.wonlab.com.imageviewersample.client.model.RecentByTag;
import app.wonlab.com.imageviewersample.util.LogUtil;
import app.wonlab.com.imageviewersample.util.NetworkUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.RestAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wonlab on 2016/03/01.
 */
public class ImageListFragment extends Fragment {

    private static final String EXTRA_NAME = "title";

    private String title;

    private List<Media> imageDataList;

    @Bind(R.id.group_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.recycler_image_list)
    RecyclerView recyclerView;

    public static ImageListFragment newInstance(String title) {
        ImageListFragment fragment = new ImageListFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_NAME, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(EXTRA_NAME, null);
        imageDataList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_list, container, false);
        ButterKnife.bind(this, view);
        swipeRefreshLayout.setColorSchemeResources(R.color.md_yellow_100, R.color.md_yellow_700, R.color.md_red_400, R.color.md_red_800);
        swipeRefreshLayout.setOnRefreshListener(this::onRefresh);
        recyclerView.setAdapter(new ImageRecyclerAdapter(getActivity(), imageDataList, position -> {
            Fragment fragment = ImageDetailFragment.newInstance(imageDataList.get(position));
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).add(R.id.group_root, fragment).commit();
        }));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        fetchData(false);

        return view;
    }

    public void onRefresh() {
        imageDataList.clear();
        fetchData(true);
    }

    public void fetchData(boolean isRefresh) {
        if (!NetworkUtil.isConnectedNetwork(getActivity())) {
            // show message
            new MaterialDialog.Builder(getActivity())
                    .title(R.string.label_error)
                    .content(R.string.msg_network_error)
                    .negativeText(R.string.label_cancel)
                    .show();
            if (isRefresh) {
                swipeRefreshLayout.setRefreshing(false);
            }
            return;
        }
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(getString(R.string.api_endpoint)).build();
        InstagramService service = restAdapter.create(InstagramService.class);
        service.getRecentByTag(title, InstagramService.apiToken, null, null).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RecentByTag>() {
            @Override
            public void onCompleted() {
                if (recyclerView != null && recyclerView.getAdapter() != null) {
                    recyclerView.getAdapter().notifyDataSetChanged();
                    if (isRefresh) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(LogUtil.exceptionToString(e));
            }

            @Override
            public void onNext(RecentByTag recentByTag) {
                List<Media> medias = recentByTag.getData();
                imageDataList.addAll(medias);
            }
        });
    }
}
