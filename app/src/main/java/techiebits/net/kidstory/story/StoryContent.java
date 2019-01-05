package techiebits.net.kidstory.story;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import techiebits.net.kidstory.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StoryContent extends Fragment implements ViewPager.OnPageChangeListener {

    private ViewPager              pager;
    private List<StorageReference> imagesList;
    private StorageReference       mStorageReference;
    private StorageReference       mImagesRef;
    private int                    selectedpage;
    private File                   directory;
    private DefaultTrackSelector   trackSelector;
    private DataSource.Factory dataSourceFactory;
    private MediaSource mediaSource1,mediaSource2,mediaSource3,mediaSource4,mediaSource5,mediaSource6,mediaSource7,
    mediaSource8,mediaSource9,mediaSource10,mediaSource11,mediaSource12,mediaSource13,mediaSource14;
    private SimpleExoPlayer            soundplayer1, soundplayer2, soundplayer3, soundplayer4, soundplayer5,
            soundplayer6, soundplayer7, soundplayer8, soundplayer9, soundplayer10, soundplayer11,
            soundplayer12, soundplayer13, soundplayer14;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.story_content, container, false);
        initViews(rootView);

        return rootView;
    }

    //region init views
    private void initViews(View rootView) {
        //region handle displaying and cache images
        imagesList = new ArrayList<>();
        mStorageReference = FirebaseStorage.getInstance().getReference();
        for (int i = 1; i <= 14; i++) {
            mImagesRef = mStorageReference.child("story1/images/img_y" + i + ".png");
            imagesList.add(mImagesRef);
        }
        //endregion
        createMediaPlayers();
        pager = rootView.findViewById(R.id.pager);
        ImageAdapter adapter = new ImageAdapter(getActivity(), imagesList);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(this);
    }
    //endregion

    //region initializing MediaPlayers
    private void createMediaPlayers() {
        directory = getActivity().getDir("sounds1", Context.MODE_PRIVATE);
        trackSelector = new DefaultTrackSelector();
        dataSourceFactory = new DefaultDataSourceFactory(getActivity(),
                                                         Util.getUserAgent(getActivity(), getString(R.string.app_name)));
        soundplayer1 = ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector);
        soundplayer2 = ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector);
        soundplayer3 = ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector);
        soundplayer4 = ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector);
        soundplayer5 = ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector);
        soundplayer6 = ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector);
        soundplayer7 = ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector);
        soundplayer8 = ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector);
        soundplayer9 = ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector);
        soundplayer10 = ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector);
        soundplayer11 = ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector);
        soundplayer12 = ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector);
        soundplayer13 = ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector);
        soundplayer14 = ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector);

        mediaSource1 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "sound_y_1")),
                                               dataSourceFactory,new DefaultExtractorsFactory(),
                                               null,null);
        mediaSource2 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "sound_y_2")),
                                                           dataSourceFactory,new DefaultExtractorsFactory(),
                                                           null,null);
        mediaSource3 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "sound_y_3")),
                                                           dataSourceFactory,new DefaultExtractorsFactory(),
                                                           null,null);
        mediaSource4 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "sound_y_4")),
                                                           dataSourceFactory,new DefaultExtractorsFactory(),
                                                           null,null);
        mediaSource5 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "sound_y_5")),
                                                           dataSourceFactory,new DefaultExtractorsFactory(),
                                                           null,null);
        mediaSource6 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "sound_y_6")),
                                                           dataSourceFactory,new DefaultExtractorsFactory(),
                                                           null,null);
        mediaSource7 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "sound_y_7")),
                                                           dataSourceFactory,new DefaultExtractorsFactory(),
                                                           null,null);
        mediaSource8 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "sound_y_8")),
                                                           dataSourceFactory,new DefaultExtractorsFactory(),
                                                           null,null);
        mediaSource9 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "sound_y_9")),
                                                           dataSourceFactory,new DefaultExtractorsFactory(),
                                                           null,null);
        mediaSource10 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "sound_y_10")),
                                                           dataSourceFactory,new DefaultExtractorsFactory(),
                                                           null,null);
        mediaSource11 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "sound_y_11")),
                                                           dataSourceFactory,new DefaultExtractorsFactory(),
                                                           null,null);
        mediaSource12 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "sound_y_12")),
                                                           dataSourceFactory,new DefaultExtractorsFactory(),
                                                           null,null);
        mediaSource13 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "sound_y_13")),
                                                           dataSourceFactory,new DefaultExtractorsFactory(),
                                                           null,null);
        mediaSource14 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "sound_y_14")),
                                                           dataSourceFactory,new DefaultExtractorsFactory(),
                                                           null,null);

        soundplayer1.prepare(mediaSource1);
        soundplayer2.prepare(mediaSource2);
        soundplayer3.prepare(mediaSource3);
        soundplayer4.prepare(mediaSource4);
        soundplayer5.prepare(mediaSource5);
        soundplayer6.prepare(mediaSource6);
        soundplayer7.prepare(mediaSource7);
        soundplayer8.prepare(mediaSource8);
        soundplayer9.prepare(mediaSource9);
        soundplayer10.prepare(mediaSource10);
        soundplayer11.prepare(mediaSource11);
        soundplayer12.prepare(mediaSource12);
        soundplayer13.prepare(mediaSource13);
        soundplayer14.prepare(mediaSource14);

        soundplayer1.setPlayWhenReady(true);

    }
    //endregion

    //region viewPager callback methods
    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        selectedpage = pager.getCurrentItem();
        switch (selectedpage) {
            case 0:
                if (soundplayer2.getPlayWhenReady()){
                    soundplayer2.setPlayWhenReady(false);
                }
                soundplayer1.seekTo(0);
                soundplayer1.setPlayWhenReady(true);
                break;

            case 1:
                if (soundplayer1.getPlayWhenReady()){
                    soundplayer1.setPlayWhenReady(false);
                }else if(soundplayer3.getPlayWhenReady()){
                    soundplayer3.setPlayWhenReady(false);
                }
                soundplayer2.seekTo(0);
                soundplayer2.setPlayWhenReady(true);
                break;

            case 2:
                if (soundplayer2.getPlayWhenReady()) {
                    soundplayer2.setPlayWhenReady(false);
                }
                else if (soundplayer4.getPlayWhenReady()) {
                    soundplayer4.setPlayWhenReady(false);
                }
                soundplayer3.seekTo(0);
                soundplayer3.setPlayWhenReady(true);
                break;

            case 3:
                if (soundplayer3.getPlayWhenReady()) {
                    soundplayer3.setPlayWhenReady(false);
                }
                else if (soundplayer5.getPlayWhenReady()) {
                    soundplayer5.setPlayWhenReady(false);
                }
                soundplayer4.seekTo(0);
                soundplayer4.setPlayWhenReady(true);
                break;

            case 4:
                if (soundplayer4.getPlayWhenReady()) {
                    soundplayer4.setPlayWhenReady(false);
                }
                else if (soundplayer6.getPlayWhenReady()) {
                    soundplayer6.setPlayWhenReady(false);
                }
                soundplayer5.seekTo(0);
                soundplayer5.setPlayWhenReady(true);
                break;
            case 5:
                if (soundplayer5.getPlayWhenReady()) {
                    soundplayer5.setPlayWhenReady(false);
                }
                else if (soundplayer7.getPlayWhenReady()) {
                    soundplayer7.setPlayWhenReady(false);
                }
                soundplayer6.seekTo(0);
                soundplayer6.setPlayWhenReady(true);
                break;

            case 6:
                if (soundplayer6.getPlayWhenReady()) {
                    soundplayer6.setPlayWhenReady(false);
                }
                else if (soundplayer8.getPlayWhenReady()) {
                    soundplayer8.setPlayWhenReady(false);
                }
                soundplayer7.seekTo(0);
                soundplayer7.setPlayWhenReady(true);
                break;

            case 7:
                if (soundplayer7.getPlayWhenReady()) {
                    soundplayer7.setPlayWhenReady(false);
                }
                else if (soundplayer9.getPlayWhenReady()) {
                    soundplayer9.setPlayWhenReady(false);
                }
                soundplayer8.seekTo(0);
                soundplayer8.setPlayWhenReady(true);
                break;

            case 8:
                if (soundplayer8.getPlayWhenReady()) {
                    soundplayer8.setPlayWhenReady(false);
                }
                else if (soundplayer10.getPlayWhenReady()) {
                    soundplayer10.setPlayWhenReady(false);
                }
                soundplayer9.seekTo(0);
                soundplayer9.setPlayWhenReady(true);
                break;

            case 9:
                if (soundplayer9.getPlayWhenReady()) {
                    soundplayer9.setPlayWhenReady(false);
                }
                else if (soundplayer11.getPlayWhenReady()) {
                    soundplayer11.setPlayWhenReady(false);
                }
                soundplayer10.seekTo(0);
                soundplayer10.setPlayWhenReady(true);
                break;

            case 10:
                if (soundplayer10.getPlayWhenReady()) {
                    soundplayer10.setPlayWhenReady(false);
                }
                else if (soundplayer12.getPlayWhenReady()) {
                    soundplayer12.setPlayWhenReady(false);
                }
                soundplayer11.seekTo(0);
                soundplayer11.setPlayWhenReady(true);
                break;

            case 11:
                if (soundplayer11.getPlayWhenReady()) {
                    soundplayer11.setPlayWhenReady(false);
                }
                else if (soundplayer13.getPlayWhenReady()) {
                    soundplayer13.setPlayWhenReady(false);
                }
                soundplayer12.seekTo(0);
                soundplayer12.setPlayWhenReady(true);
                break;

            case 12:
                if (soundplayer12.getPlayWhenReady()) {
                    soundplayer12.setPlayWhenReady(false);
                }
                else if (soundplayer14.getPlayWhenReady()) {
                    soundplayer14.setPlayWhenReady(false);
                }
                soundplayer13.seekTo(0);
                soundplayer13.setPlayWhenReady(true);
                break;

            case 13:
                if (soundplayer13.getPlayWhenReady()) {
                    soundplayer13.setPlayWhenReady(false);
                }
                soundplayer14.seekTo(0);
                soundplayer14.setPlayWhenReady(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
    //endregion

    //region Activity Lifecycle
    @Override
    public void onStop() {
        super.onStop();
        soundplayer1.stop();
        soundplayer2.stop();
        soundplayer3.stop();
        soundplayer4.stop();
        soundplayer5.stop();
        soundplayer6.stop();
        soundplayer7.stop();
        soundplayer8.stop();
        soundplayer9.stop();
        soundplayer10.stop();
        soundplayer11.stop();
        soundplayer12.stop();
        soundplayer13.stop();
        soundplayer14.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        soundplayer1.release();
        soundplayer2.release();
        soundplayer3.release();
        soundplayer4.release();
        soundplayer5.release();
        soundplayer6.release();
        soundplayer7.release();
        soundplayer8.release();
        soundplayer9.release();
        soundplayer10.release();
        soundplayer11.release();
        soundplayer12.release();
        soundplayer13.release();
        soundplayer14.release();
    }
    //endregion
}
