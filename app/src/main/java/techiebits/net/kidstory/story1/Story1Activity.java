package techiebits.net.kidstory.story1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;
import techiebits.net.kidstory.MySharedPreferences;
import techiebits.net.kidstory.R;

import java.io.File;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

public class Story1Activity extends Fragment implements View.OnClickListener {

    private StorageReference mStorageReference;
    private StorageReference mImagesRef;
    private ImageButton      storyContent;
    private ImageButton      storyQuiz;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_story1, container, false);
        initViews(rootView);
        return rootView;
    }
    private void initViews(View view) {
        storyContent = view.findViewById(R.id.story_content);
        storyQuiz    = view.findViewById(R.id.story_quiz);
        storyContent.setOnClickListener(this);
        storyQuiz.setOnClickListener(this);
    }

    //region downloadImages
    private void downloadImages() {
        File localFile = null;
        File mydir     = getActivity().getDir("story1images", MODE_PRIVATE); //Creating an internal dir;
        if (!mydir.exists()) {
            mydir.mkdirs();
        }
        mStorageReference = FirebaseStorage.getInstance().getReference();

        for (int i = 1; i <= 14; i++) {
            mImagesRef = mStorageReference.child("story1/images/img_y" + i + ".png");
            try {
                localFile = File.createTempFile("images", "jpg", mydir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mImagesRef.getFile(localFile)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Successfully downloaded data to local file
                        MySharedPreferences.getInstance().setStory1imagesDownloaded(getActivity(), true);
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle failed download
                    MySharedPreferences.getInstance().setStory1imagesDownloaded(getActivity(), false);
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
        switch (v.getId()) {
            case R.id.story_content:
                //ToDo download sounds
                //ToDo if not downloaded show dialog to ask download story content
                if (MySharedPreferences.getInstance().isStory1imagesDownloaded(getContext()))
                    startActivity(new Intent(getContext(), Story1Content.class));
                else downloadImages();
                break;
            case R.id.story_quiz:
                //ToDo open Story Quiz Screen
                break;
        }
    }
    //endregion
}
