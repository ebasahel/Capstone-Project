package techiebits.net.kidstory;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.firebase.auth.FirebaseUser;

public class MySharedPreferences  {
    private static MySharedPreferences mInstance = null;
    private String PREF_USER_INFO="user_uid";
    private String PREF_IMAGES_1="images_1_story";
    private String PREF_Sounds_1="sounds_1_story";
    private String sharedPreferencesName= "kidstory";

    private MySharedPreferences() {
        // Exists only to defeat instantiation.
    }
    public static MySharedPreferences getInstance() {
        if(mInstance == null) {
            mInstance = new MySharedPreferences();
        }
        return mInstance;
    }

    public SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(sharedPreferencesName, Activity.MODE_PRIVATE);
    }

    public void setUserInfo(Context context, FirebaseUser userInfo){
        if(userInfo == null)return;
        SharedPreferences.Editor _prefsEditor = getPreferences(context).edit();
        _prefsEditor.putString(PREF_USER_INFO, userInfo.getUid());
        _prefsEditor.apply();
    }
    public String getUserInfo(Context context){
        String temp = getPreferences(context).getString(PREF_USER_INFO,null);
        if(temp == null)return null;
        return temp;
    }

    public void setStory1imagesDownloaded(Context context, boolean isImagesDownloaded){
        SharedPreferences.Editor _prefsEditor = getPreferences(context).edit();
        _prefsEditor.putBoolean(PREF_IMAGES_1, isImagesDownloaded);
        _prefsEditor.apply();
    }

    public boolean isStory1imagesDownloaded (Context context){
        return getPreferences(context).getBoolean(PREF_IMAGES_1,false);
    }

    public void setStory1soundsDownloaded(Context context, boolean isSoundsDownloaded){
        SharedPreferences.Editor _prefsEditor = getPreferences(context).edit();
        _prefsEditor.putBoolean(PREF_Sounds_1, isSoundsDownloaded);
        _prefsEditor.apply();
    }

    public boolean isStory1soundsDownloaded (Context context){
        return getPreferences(context).getBoolean(PREF_Sounds_1,false);
    }
}
