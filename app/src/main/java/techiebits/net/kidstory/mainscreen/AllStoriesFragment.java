package techiebits.net.kidstory.mainscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import androidx.navigation.Navigation;
import techiebits.net.kidstory.R;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllStoriesFragment extends Fragment {


    private RecyclerView storiesRecyclerView;
    private StoriesAdapter storiesAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> storiesList;
    public static String STORY_ID = "storyid";
    public static String STORY_TITLE = "storytitle";
    public AllStoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_all_stories, container, false);
        storiesList = Arrays.asList(getResources().getStringArray(R.array.stories_title));
        storiesRecyclerView = view.findViewById(R.id.list_stories);
        mLayoutManager=new LinearLayoutManager(getActivity());
        storiesRecyclerView.setLayoutManager(mLayoutManager);
        storiesAdapter = new StoriesAdapter(storiesList,
                new StoriesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String itemResult, int storyId) {
                        //ToDo send The name of the story to the next page to set te title
                        //ToDo download images and sounds, if downloaded navigate to the page

                        //This condition because there's only one story
                        if (storyId!=1){
                            Toast.makeText(getContext(),getString(R.string.coming_soon),Toast.LENGTH_LONG).show();
                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putInt(STORY_ID, storyId);
                            bundle.putString(STORY_TITLE,itemResult);
                            Navigation.findNavController(view).navigate(R.id.StoryFragment,bundle);
                        }
                    }
                }

        );
        storiesRecyclerView.setAdapter(storiesAdapter);
        return view;
    }

}
