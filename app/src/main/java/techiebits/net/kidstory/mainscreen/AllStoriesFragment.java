package techiebits.net.kidstory.mainscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    public AllStoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all_stories, container, false);
        storiesList = Arrays.asList(getResources().getStringArray(R.array.stories_title));
        storiesRecyclerView = view.findViewById(R.id.list_stories);
        mLayoutManager=new LinearLayoutManager(getActivity());
        storiesRecyclerView.setLayoutManager(mLayoutManager);
        storiesAdapter = new StoriesAdapter(storiesList,
                new StoriesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String itemResult) {
                        //ToDo call story page
                        Navigation.findNavController(view).navigate(R.id.Story1Fragment);
                    }
                }

        );
        storiesRecyclerView.setAdapter(storiesAdapter);
        return view;
    }

}
