package techiebits.net.kidstory.story;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.navigation.Navigation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;
import techiebits.net.kidstory.util.MySharedPreferences;
import techiebits.net.kidstory.R;

import java.io.File;

import static techiebits.net.kidstory.mainscreen.AllStoriesFragment.STORY_TITLE;

public class StoryFragment extends Fragment implements View.OnClickListener {

    private StorageReference mStorageReference;
    private StorageReference mSoundRef;
    private ImageView        storyContent, storyQuiz;
    private String         storyTitle;
    private File           fileSound;
    private ProgressBar    progressBar;
    private RelativeLayout progressbarContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_story1, container, false);
        initViews(rootView);
        storyTitle = getArguments().getString(STORY_TITLE, getString(R.string.the_story));
        downloadIfConnected();
        return rootView;
    }

    //region download content for the first use if connected
    private void downloadIfConnected(){
        if (!MySharedPreferences.getInstance().isStory1soundsDownloaded(getActivity())) {
            if (!isNetworkConnected()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.dialog_message)
                        .setTitle(R.string.dialog_title);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        startActivity(new Intent(
                                Settings.ACTION_WIFI_SETTINGS));
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        getActivity().finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.setCancelable(false);
                dialog.show();

            }
            else downloadSounds();
        }
    }
    //endregion


    @Override
    public void onResume() {
        super.onResume();
        downloadIfConnected();
    }

    //region init views
    private void initViews(View view) {
        storyContent = view.findViewById(R.id.story_content);
        storyQuiz = view.findViewById(R.id.story_quiz);
        progressBar = view.findViewById(R.id.progressbar);
        progressbarContainer = view.findViewById(R.id.progress_container);
        storyContent.setOnClickListener(this);
        storyQuiz.setOnClickListener(this);
    }
    //endregion

    //region download Sounds
    private void downloadSounds() {
        progressbarContainer.setVisibility(View.VISIBLE);
        mStorageReference = FirebaseStorage.getInstance().getReference();

        for (int i = 1; i <= 14; i++) {
            mSoundRef = mStorageReference.child("story1/sounds/y" + i + ".ogg");
            fileSound = new File(getActivity().getDir("sounds1", Context.MODE_PRIVATE), "sound_y_" + i);
            mSoundRef.getFile(fileSound)
                    .addOnSuccessListener(taskSnapshot -> {
                        progressbarContainer.setVisibility(View.GONE);
                        // Successfully downloaded data to local file
                        MySharedPreferences.getInstance().setStory1soundsDownloaded(getActivity(), true);
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle failed download
                    progressbarContainer.setVisibility(View.GONE);
                    MySharedPreferences.getInstance().setStory1soundsDownloaded(getActivity(), false);
                    int errorCode = ((StorageException) exception).getErrorCode();
                    Log.e("DownloadException", exception.getMessage());
                }
            });
        }
    }
    //endregion

    //region click listener
    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString(STORY_TITLE, storyTitle);
        switch (v.getId()) {
            case R.id.story_content:
                Navigation.findNavController(v).navigate(R.id.storyContent, bundle);
                break;
            case R.id.story_quiz:
                Navigation.findNavController(v).navigate(R.id.storyQuiz, bundle);
                break;
        }
    }
    //endregion

    //region Check Internet Connectivity
    private boolean isNetworkConnected() {
        ConnectivityManager cm            = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo         activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
    //endregion

}
