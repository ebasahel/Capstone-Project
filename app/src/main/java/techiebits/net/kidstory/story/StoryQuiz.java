package techiebits.net.kidstory.story;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.navigation.Navigation;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.Gson;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import techiebits.net.kidstory.R;
import techiebits.net.kidstory.data.Quiz1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoryQuiz extends Fragment {

    private String      TAG       = StoryQuiz.class.getSimpleName();
    private int         randomInteger;
    private TextView    txtQn;
    private SimpleExoPlayer QuestionPlayer, correctPlayer, failPlayer;
    private ImageView imgAnswer1, imgAnswer2;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.story_quiz, container, false);
        // getting Random Question with its Answers
        Random random = new Random();
        randomInteger = random.nextInt(6) + 1;
        initViews(rootView);
        createMediaPlayers();
        //region read Json File data
        Gson gson = new Gson();
        @SuppressWarnings("serial")
        Type collectionType = new TypeToken<List<Quiz1>>() {}.getType();
        List<Quiz1> quiz1List = gson.fromJson(loadJSONFromAsset(getActivity()), collectionType);
        //endregion

        txtQn.setText(quiz1List.get(randomInteger - 1).getQuestion());
        setAnswers(randomInteger - 1);
        return rootView;
    }

    //region set Answers for questions and actions on it
    private void setAnswers(int i) {
        switch (i) {
            case 0:
                imgAnswer1.setImageResource(R.drawable.ans1a);
                imgAnswer1.setOnClickListener(view -> {
                    imgAnswer1.startAnimation(setScale());
                    correctPlayer.setPlayWhenReady(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Navigation.findNavController(view).navigateUp();
                        }
                    }, 3000);
                });

                imgAnswer2.setImageResource(R.drawable.ans1b);
                imgAnswer2.setOnClickListener(view -> {
                    imgAnswer2.startAnimation(setTranslate());
                    failPlayer.setPlayWhenReady(true);
                });
                break;
            case 1:
                imgAnswer1.setImageResource(R.drawable.shark);
                imgAnswer1.setOnClickListener(view -> {
                    imgAnswer1.startAnimation(setTranslate());
                    failPlayer.setPlayWhenReady(true);
                });

                imgAnswer2.setImageResource(R.drawable.whale);
                imgAnswer2.setOnClickListener(view -> {
                    imgAnswer2.startAnimation(setScale());
                    correctPlayer.setPlayWhenReady(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Navigation.findNavController(view).navigateUp();
                        }
                    }, 3000);
                });
                break;
            case 2:
                imgAnswer1.setImageResource(R.drawable.three);
                imgAnswer1.setOnClickListener(view -> {
                    imgAnswer1.startAnimation(setScale());
                    correctPlayer.setPlayWhenReady(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Navigation.findNavController(view).navigateUp();
                        }
                    }, 3000);
                });

                imgAnswer2.setImageResource(R.drawable.one);
                imgAnswer2.setOnClickListener(view -> {
                    imgAnswer2.startAnimation(setTranslate());
                    failPlayer.setPlayWhenReady(true);
                });
                break;
            case 3:
                imgAnswer1.setImageResource(R.drawable.cross);
                imgAnswer1.setOnClickListener(view -> {
                    imgAnswer1.startAnimation(setScale());
                    correctPlayer.setPlayWhenReady(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Navigation.findNavController(view).navigateUp();
                        }
                    }, 3000);
                });

                imgAnswer2.setImageResource(R.drawable.check);
                imgAnswer2.setOnClickListener(view -> {
                    imgAnswer2.startAnimation(setTranslate());
                    failPlayer.setPlayWhenReady(true);
                });
                break;
            case 4:
                imgAnswer1.setImageResource(R.drawable.plane);
                imgAnswer1.setOnClickListener(view -> {
                    imgAnswer1.startAnimation(setTranslate());
                    failPlayer.setPlayWhenReady(true);
                });

                imgAnswer2.setImageResource(R.drawable.ship);
                imgAnswer2.setOnClickListener(view -> {
                    imgAnswer2.startAnimation(setScale());
                    correctPlayer.setPlayWhenReady(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Navigation.findNavController(view).navigateUp();
                        }
                    }, 3000);
                });
                break;
            case 5:
                imgAnswer1.setImageResource(R.drawable.cross);
                imgAnswer1.setOnClickListener(view -> {
                    imgAnswer1.startAnimation(setScale());
                    correctPlayer.setPlayWhenReady(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Navigation.findNavController(view).navigateUp();
                        }
                    }, 3000);
                });

                imgAnswer2.setImageResource(R.drawable.check);
                imgAnswer2.setOnClickListener(view -> {
                    imgAnswer2.startAnimation(setTranslate());
                    failPlayer.setPlayWhenReady(true);
                });
                break;
        }
    }
    //endregion

    //region read json file from assets
    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is     = context.getAssets().open("quiz.json");
            int         size   = is.available();
            byte[]      buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    //endregion

    //region initViews
    private void initViews(View rootView) {
        txtQn = rootView.findViewById(R.id.txt_question);
        imgAnswer1 = rootView.findViewById(R.id.img_answer1);
        imgAnswer2 = rootView.findViewById(R.id.img_answer2);
    }
    //endregion

    //region initializing MediaPlayers
    private void createMediaPlayers() {
        File directory = getActivity().getDir("sounds1", Context.MODE_PRIVATE);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(),
                                                                            Util.getUserAgent(getActivity(), getString(R.string.app_name)));
        QuestionPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
        correctPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
        failPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);

        MediaSource mediaSource1 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "y_quiz_" + randomInteger)),
                                                            dataSourceFactory, new DefaultExtractorsFactory(),
                                                            null, null);
        MediaSource mediaSource2 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "correct")),
                                                            dataSourceFactory, new DefaultExtractorsFactory(),
                                                            null, null);
        MediaSource mediaSource3 = new ExtractorMediaSource(Uri.fromFile(new File(directory, "fail")),
                                                            dataSourceFactory, new DefaultExtractorsFactory(),
                                                            null, null);


        QuestionPlayer.prepare(mediaSource1);
        correctPlayer.prepare(mediaSource2);
        failPlayer.prepare(mediaSource3);


        QuestionPlayer.setPlayWhenReady(true);

    }
    //endregion

    //region create Animations
    private  ScaleAnimation setScale(){
        // *** set scale ***
        // ScaleAnimation(float fromX, float toX, float fromY, float toY, float
        // pivotX, float pivotY)
        ScaleAnimation scale = new ScaleAnimation(1, 2, 1, 2, 150, 150);
        scale.setDuration(3000);
        scale.setInterpolator(new CycleInterpolator(3));

        return scale;
    }

    private  TranslateAnimation setTranslate(){
        // *** set translate ***
        // TranslateAnimation(float fromX, float toX, float fromY, float toY)
        TranslateAnimation translate = new TranslateAnimation(0, 30, 0, 30);
        // set duration to 3000ms
        translate.setDuration(3000);
        // repeat 2 times
        translate.setInterpolator(new CycleInterpolator(2));

        return translate;
    }
    //endregion

    //region Activity Lifecycle
    @Override
    public void onStop() {
        super.onStop();
        QuestionPlayer.stop();
        correctPlayer.stop();
        failPlayer.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        QuestionPlayer.release();
        correctPlayer.release();
        failPlayer.release();
    }
    //endregion
}
