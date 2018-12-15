package techiebits.net.kidstory.mainscreen;

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
import androidx.navigation.Navigation;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import techiebits.net.kidstory.MySharedPreferences;
import techiebits.net.kidstory.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerViewMain;
    private Toolbar      toolbar;
    private ImageView    actionShare;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        if (user == null) {
            signInAnonymously();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }
    //region initViews
    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerViewMain = findViewById(R.id.list_stories);
        actionShare = findViewById(R.id.ic_share);
        actionShare.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        //ToDo get List
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
            //ToDo call about page
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region click listener
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_share:
                //ToDo Impl share intent
//                startActivity(new Intent(this,Story1Activity.class));
                break;
        }

    }
    //endregion
}
