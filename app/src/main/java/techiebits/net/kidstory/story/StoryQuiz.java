package techiebits.net.kidstory.story;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import techiebits.net.kidstory.R;

public class StoryQuiz extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.story_quiz, container, false);
        //ToDo deal with questions and answers stored in real database (to be cached)
        return rootView;
    }
}
