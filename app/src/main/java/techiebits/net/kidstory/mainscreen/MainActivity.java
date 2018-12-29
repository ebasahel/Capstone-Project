package techiebits.net.kidstory.mainscreen;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import techiebits.net.kidstory.MySharedPreferences;
import techiebits.net.kidstory.R;

import static androidx.navigation.ui.NavigationUI.onNavDestinationSelected;
import static techiebits.net.kidstory.mainscreen.AllStoriesFragment.STORY_TITLE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar             toolbar;
    private ImageView           actionShare;
    private FirebaseAuth        mAuth;
    private FirebaseUser        user;
    private NavController       navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user == null) {
            signInAnonymously();
        }
    }

    //region Check Internet Connectivity
    private boolean isNetworkConnected() {
        ConnectivityManager cm            = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo         activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
    //endregion

    //region initViews
    private void initViews() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        actionShare = findViewById(R.id.ic_share);
        actionShare.setOnClickListener(this);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (arguments != null) {
                    toolbar.setTitle(arguments.getString(STORY_TITLE));
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

    //region menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
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
        }

    }
    //endregion
}
