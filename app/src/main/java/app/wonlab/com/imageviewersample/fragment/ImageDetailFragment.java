package app.wonlab.com.imageviewersample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.wonlab.com.imageviewersample.R;
import app.wonlab.com.imageviewersample.client.model.Comment;
import app.wonlab.com.imageviewersample.client.model.Media;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wonlab on 2016/03/01.
 */
public class ImageDetailFragment extends Fragment {

    private static final String EXTRA_NAME = "media";

    private Media media;

    @Bind(R.id.image_backdrop)
    ImageView backdropImageView;

    @Bind(R.id.label_caption)
    TextView captionTextView;

    @Bind(R.id.group_image_label)
    ViewGroup labelGroup;

    public static ImageDetailFragment newInstance(Media media) {
        ImageDetailFragment fragment = new ImageDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_NAME, media);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        media = (Media)getArguments().getSerializable(EXTRA_NAME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(getActivity()).load(media.getImages().getStandardResolution().getUrl()).error(R.drawable.noimage).into(backdropImageView);
        captionTextView.setText(media.getCaption().getText());

        for (Comment comment : media.getComments().getData()) {
            View commentView = View.inflate(getActivity(), R.layout.item_comment, null);
            ((TextView)commentView.findViewById(R.id.label_comment)).setText(comment.getText());
            labelGroup.addView(commentView);
        }

        return view;
    }

    @OnClick(R.id.btn_back)
    void back() {
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }

}
