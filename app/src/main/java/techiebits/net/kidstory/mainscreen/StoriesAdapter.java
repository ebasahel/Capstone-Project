package techiebits.net.kidstory.mainscreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import techiebits.net.kidstory.R;

import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(String itemResult, int storyId);
    }

    private List<String>        storiesList;
    private OnItemClickListener mListener;

    public StoriesAdapter(List<String> stringList, OnItemClickListener listener) {
        storiesList = stringList;
        mListener = listener;
    }

    @NonNull
    @Override
    public StoriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new StoriesViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stories_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesViewHolder storiesViewHolder, int i) {
        storiesViewHolder.bind(storiesList.get(i), i + 1, mListener);
    }

    @Override
    public int getItemCount() {
        return storiesList.size();
    }

    class StoriesViewHolder extends RecyclerView.ViewHolder {

        TextView    txtTitle;
        FrameLayout container;

        StoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_story_title);
            container = itemView.findViewById(R.id.container);
        }

        void bind(final String itemResult, final int storyId, final OnItemClickListener listener) {
            txtTitle.setText(itemResult);
            container.setOnClickListener((View view) -> listener.onItemClick(itemResult, storyId));
        }
    }
}
