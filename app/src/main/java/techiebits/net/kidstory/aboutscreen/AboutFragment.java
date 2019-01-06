package techiebits.net.kidstory.aboutscreen;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import techiebits.net.kidstory.R;

public class AboutFragment extends Fragment {
    TextView devtitle, devname, graphtitle, graphname, twitteruserdev, twitterusergraph, emaildev, emailgraph;
    ImageButton devteitter, devemail, graphtwitter, graphiconemail;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        devtitle         = rootView.findViewById(R.id.devtitle);
        devname          = rootView.findViewById(R.id.devname);
        graphtitle       = rootView.findViewById(R.id.graphictitle);
        graphname        = rootView.findViewById(R.id.graphicname);
        twitteruserdev   = rootView.findViewById(R.id.twitteruserdev);
        twitterusergraph = rootView.findViewById(R.id.twitterusergraphic);
        emaildev         = rootView.findViewById(R.id.emaildev);
        emailgraph       = rootView.findViewById(R.id.emailgraphic);
        devteitter       = rootView.findViewById(R.id.twittericondev);
        graphtwitter     = rootView.findViewById(R.id.twittericongraphic);
        devemail         = rootView.findViewById(R.id.emailicondev);
        graphiconemail   = rootView.findViewById(R.id.emailicongraphic);

        devtitle.setText(getString(R.string.txt_dev_title));
        devname.setText(getString(R.string.txt_dev_name));
        graphtitle.setText(getString(R.string.txt_graphic_title));
        graphname.setText(getString(R.string.txt_graphic_name));
        twitteruserdev.setMovementMethod(LinkMovementMethod.getInstance());
        twitterusergraph.setMovementMethod(LinkMovementMethod.getInstance());
        emaildev.setText(getString(R.string.txt_dev_email));
        emailgraph.setText(getString(R.string.txt_graphic_email));
        return rootView;
    }
}
