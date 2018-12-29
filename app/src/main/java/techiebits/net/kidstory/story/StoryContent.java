package techiebits.net.kidstory.story;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import techiebits.net.kidstory.R;

public class StoryContent extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.story_content, container, false);
        //ToDo deal with locally downloaded images and sounds to show them
        return rootView;
    }
}
