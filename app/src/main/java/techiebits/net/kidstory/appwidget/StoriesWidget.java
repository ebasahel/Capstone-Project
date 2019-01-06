package techiebits.net.kidstory.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.firebase.database.*;
import techiebits.net.kidstory.R;
import techiebits.net.kidstory.mainscreen.MainActivity;

import java.util.ArrayList;

import static techiebits.net.kidstory.appwidget.WidgetFactory.WIDGET_BUNDLE_STRING;
import static techiebits.net.kidstory.appwidget.WidgetFactory.WIDGET_LIST;

/**
 * Implementation of App Widget functionality.
 */
public class StoriesWidget extends AppWidgetProvider {

    private Intent intent,openAppIntent;
    private ArrayList<String> mSupplicationsList = new ArrayList<>();
    private String TAG = StoriesWidget.class.getSimpleName();

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        FirebaseDatabase  database = FirebaseDatabase.getInstance();
        DatabaseReference myRef    = database.getReference("supplications");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    mSupplicationsList.add(child.getValue().toString());
                }
                // There may be multiple widgets active, so update all of them
                for (int appWidgetId : appWidgetIds) {
                    updateRemoteViews(context,appWidgetManager,appWidgetId,mSupplicationsList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    //region update widget data
    private void updateRemoteViews(Context context, AppWidgetManager appWidgetManager,
                                   int appWidgetId, ArrayList<String> supplicationList)
    {
        intent = new Intent(context, WidgetService.class);
        openAppIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, openAppIntent, 0);
        Bundle extrasBundle = new Bundle();
        extrasBundle.putStringArrayList(WIDGET_LIST,supplicationList);
        extrasBundle.putInt(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.putExtra(WIDGET_BUNDLE_STRING, extrasBundle);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.stories_widget);
        rv.setRemoteAdapter(R.id.list, intent);
        rv.setOnClickPendingIntent(R.id.widget_container, pendingIntent);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.list);
        appWidgetManager.updateAppWidget(appWidgetId, rv);

    }
    //endregion

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

