package edu.osu.recipe_app.ui.Timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import edu.osu.recipe_app.R;

public class TimerFragment extends Fragment {
    private TextView mCountdownText;
    private Button mCountdownButton;
    private ProgressBar mProgressBar;

    private CountDownTimer mCountDownTimer;
    private CountDownTimer mProgressBarTimer;

    private float timeLeftInMilliseconds = 60000; // 10 minutes, hardcoded for now
    private float startingTimeInMilliseconds;

    private boolean timerRunning;

    public TimerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        super.onCreateView(inflater, parent, savedInstanceState);
        View v = inflater.inflate(R.layout.timer_fragment, parent, false);

        startingTimeInMilliseconds = timeLeftInMilliseconds;

        mCountdownText = (TextView) v.findViewById(R.id.CountdownText);
        mCountdownButton = (Button) v.findViewById(R.id.StartTimerButton);
        mProgressBar = (ProgressBar) v.findViewById(R.id.TimerProgressBar);

        mProgressBar.setMax((int) startingTimeInMilliseconds);
        mProgressBar.setProgress((int) startingTimeInMilliseconds);

        mCountdownButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ToggleTimer();
            }
        });

        return v;
    }

    public void ToggleTimer(){
        if(timerRunning){
            StopTimer();
        } else {
            StartTimer();
        }
    }

    public void StopTimer(){
        mCountDownTimer.cancel();
        mCountdownButton.setText("Start");

        mProgressBarTimer.cancel();
        mCountDownTimer.cancel();

        timerRunning = false;
    }

    public void StartTimer(){
        mProgressBarTimer = new CountDownTimer((long) timeLeftInMilliseconds, 1) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;

                // Update Timer Progress bar visually
                int progress = (int) ((timeLeftInMilliseconds / startingTimeInMilliseconds) * 100);
                mProgressBar.setProgress((int) timeLeftInMilliseconds);
            }

            @Override
            public void onFinish() {

            }
        }.start();

        mCountDownTimer = new CountDownTimer((long) timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                // Update
                UpdateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        mCountdownButton.setText("Pause");
        timerRunning = true;
    }

    public void UpdateTimer(){
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText;

        timeLeftText = Integer.toString(minutes) + ":";
        if(seconds < 10){
            timeLeftText += "0";
        }
        timeLeftText += seconds;

        mCountdownText.setText(timeLeftText);
    }
}
