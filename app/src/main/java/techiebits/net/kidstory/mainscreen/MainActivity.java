package techiebits.net.kidstory.mainscreen;

import static com.google.firebase.storage.StorageException.ERROR_BUCKET_NOT_FOUND;
import static com.google.firebase.storage.StorageException.ERROR_INVALID_CHECKSUM;
import static com.google.firebase.storage.StorageException.ERROR_NOT_AUTHENTICATED;
import static com.google.firebase.storage.StorageException.ERROR_OBJECT_NOT_FOUND;
import static com.google.firebase.storage.StorageException.ERROR_PROJECT_NOT_FOUND;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import techiebits.net.kidstory.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView     recyclerViewMain;
    private Toolbar          toolbar;
    private ImageView        actionShare;
    private StorageReference mStorageReference;
    private StorageReference mImagesRef;
    private boolean          isDownloaded;
    private String           imgSuffix = ".png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

//        initFirebase();
    }
//    private void initFirebase() {
//        mImagesRef = FirebaseStorage.getInstance().getReferenceFromUrl("/story1/images/img_y");
//    }

    //region downloadImages
    private boolean downloadImages() {
        File localFile = null;
        File mydir     = this.getDir("story1", MODE_PRIVATE); //Creating an internal dir;
        if (!mydir.exists()) {
            mydir.mkdirs();
        }
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mImagesRef = mStorageReference.child("story1/images/img_y1.png");
//        mImagesRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://firebase-kidstory.appspot.com/story1/images/img_" + i + imgSuffix);
        try {
            localFile = File.createTempFile("images", "jpg", mydir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mImagesRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        isDownloaded = true;
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                isDownloaded = false;
                int errorCode = ((StorageException) exception).getErrorCode();
                Log.e("DownloadException", exception.getMessage());
                switch (errorCode) {
                    case ERROR_OBJECT_NOT_FOUND:
                        Log.e("DownloadException", "ERROR_OBJECT_NOT_FOUND");
                        break;
                    case ERROR_BUCKET_NOT_FOUND:
                        Log.e("DownloadException", "ERROR_BUCKET_NOT_FOUND");
                        break;
                    case ERROR_PROJECT_NOT_FOUND:
                        Log.e("DownloadException", "ERROR_PROJECT_NOT_FOUND");
                        break;
                    case ERROR_NOT_AUTHENTICATED:
                        Log.e("DownloadException", "ERROR_NOT_AUTHENTICATED");
                        break;
                    case ERROR_INVALID_CHECKSUM:
                        Log.e("DownloadException", "ERROR_INVALID_CHECKSUM");
                        break;
                }

            }
        });
//        for (int i = 1; i <= 14; i++) {
//
//        }

        return isDownloaded;
    }
    //endregion
    //region initViews
    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerViewMain = findViewById(R.id.list_stories);
        actionShare = findViewById(R.id.ic_share);
        actionShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downloadImages()) {
                    Log.d("isDownloaded", "true");
                } else {
                    Log.d("isDownloaded", "false");
                }
            }
        });
        //ToDo get List
        //ToDo implement share
    }

    //endregion
    //region menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            //ToDo open about page
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion
}
