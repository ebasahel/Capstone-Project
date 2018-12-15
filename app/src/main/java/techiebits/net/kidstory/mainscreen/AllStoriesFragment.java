package techiebits.net.kidstory.mainscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techiebits.net.kidstory.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllStoriesFragment extends Fragment {


    public AllStoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_stories, container, false);
    }

}
