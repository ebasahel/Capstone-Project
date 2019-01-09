package techiebits.net.kidstory.mainscreen;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import techiebits.net.kidstory.util.MySharedPreferences;
import techiebits.net.kidstory.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton actionShare,actionAbout;
    private FirebaseAuth         mAuth;
    private FirebaseUser         user;
    private NavController        navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        initViews();
        checkVolume();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user == null) {
            signInAnonymously();
        }
    }

    private void checkVolume(){
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int musicVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (musicVolume == 0) {
            Toast.makeText(this, getString(R.string.volume_is_muted), Toast.LENGTH_LONG).show();
        }

    }
    //region initViews
    private void initViews() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        actionShare = findViewById(R.id.ic_share);
        actionAbout = findViewById(R.id.ic_about);
        actionAbout.setOnClickListener(this);
        actionShare.setOnClickListener(this);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId()!= R.id.allStoriesFragment){
                    actionAbout.hide();
                    actionShare.hide();
                }else {
                    actionAbout.show();
                    actionShare.show();
                }
            }
        });

    }
    //endregion

    //region Sign in Anonymously
    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener((AuthResult authResult) -> {
            MySharedPreferences.getInstance().setUserInfo(this, mAuth.getCurrentUser());
        }).addOnFailureListener((@NonNull Exception exception) -> {
            Log.e("TAG", "signInAnonymously:FAILURE", exception);
        });
    }
    //endregion

    //region click listener
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ic_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_msg));
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.ic_about:
                navController.navigate(R.id.AboutFragment);
                break;
        }

    }
    //endregion
}
