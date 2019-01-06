package techiebits.net.kidstory.appwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import techiebits.net.kidstory.R;

import java.util.ArrayList;
import java.util.List;

public class WidgetFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context      mContext =null;
    private int          appWidgetId;
    private List<String> supplications;
    public static String WIDGET_BUNDLE_STRING="widgetbundle";
    public static String WIDGET_LIST="widgetlist";
    public WidgetFactory(Context context, Intent intent)
    {
        mContext=context;
        Bundle bundle = intent.getBundleExtra(WIDGET_BUNDLE_STRING);
        supplications = new ArrayList<>();
        supplications= bundle.getStringArrayList(WIDGET_LIST);
        appWidgetId=bundle.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                                  AppWidgetManager.INVALID_APPWIDGET_ID);
    }
    @Override
    public void onCreate() {

    }
    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return supplications.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv=new RemoteViews(mContext.getPackageName(),
                                       R.layout.widget_story_item);
        rv.setTextViewText(R.id.widget_text,supplications.get(position) );
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
