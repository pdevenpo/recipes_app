package edu.osu.recipe_app.ui.Timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import edu.osu.recipe_app.R;

public class TimerFragment extends Fragment {
    private String mTimerLength;
    private TextView mCountdownText;
    private Button mStartPauseTimerButton;
    private Button mPauseTimerButton;
    private Button mCancelTimerButton;
    private LinearLayout bottomTimerButtonBar;

    private ProgressBar mProgressBar;

    private CountDownTimer mCountDownTimer;
    private CountDownTimer mProgressBarTimer;

    private float timeLeftInMilliseconds = 60100; // 10 minutes, hardcoded for now
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
        mStartPauseTimerButton = (Button) v.findViewById(R.id.StartTimerButton);
        mPauseTimerButton = (Button) v.findViewById(R.id.PauseTimerButton);
        mCancelTimerButton = (Button) v.findViewById(R.id.CancelTimerButton);

        bottomTimerButtonBar = (LinearLayout) v.findViewById(R.id.TimerButtonBar);
        bottomTimerButtonBar.setVisibility(View.INVISIBLE);

        mProgressBar = (ProgressBar) v.findViewById(R.id.TimerProgressBar);

        mProgressBar.setMax((int) startingTimeInMilliseconds);
        mProgressBar.setProgress((int) startingTimeInMilliseconds);

        mStartPauseTimerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                StartTimer();
            }
        });

        mPauseTimerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ToggleTimer();
            }
        });

        mCancelTimerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CancelTimer();
            }
        });

        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null){
            mTimerLength =  extras.getString("TimerLength");
        }

        return v;
    }

    public void ToggleTimer(){
        if(timerRunning){
            mPauseTimerButton.setText("Resume");
            PauseTimer();
        } else {
            mPauseTimerButton.setText("Pause");
            StartTimer();
        }
    }

    public void CancelTimer(){
        mCancelTimerButton.setVisibility(View.INVISIBLE);
        bottomTimerButtonBar.setVisibility(View.INVISIBLE);
        mStartPauseTimerButton.setVisibility(View.VISIBLE);

        mProgressBarTimer.cancel();
        mCountDownTimer.cancel();

        timerRunning = false;

        timeLeftInMilliseconds = startingTimeInMilliseconds;

        UpdateTimer();
        UpdateProgressBar();
    }

    public void PauseTimer(){
        mCancelTimerButton.setVisibility(View.VISIBLE);

        mProgressBarTimer.cancel();
        mCountDownTimer.cancel();

        timerRunning = false;
    }

    public void StartTimer(){
        bottomTimerButtonBar.setVisibility(View.VISIBLE);
        mStartPauseTimerButton.setVisibility(View.INVISIBLE);
        mCancelTimerButton.setVisibility(View.INVISIBLE);

        // Countdown timer for smooth progress bar visuals
        mProgressBarTimer = new CountDownTimer((long) timeLeftInMilliseconds, 1) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                // Update Timer Progress bar visually
                UpdateProgressBar();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        // Countdown timer for text on screen
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
        timerRunning = true;
    }

    private void UpdateProgressBar(){
        int progress = (int) ((timeLeftInMilliseconds / startingTimeInMilliseconds) * 100);
        mProgressBar.setProgress((int) timeLeftInMilliseconds);
    }

    public void UpdateTimer(){
        int hours = (int) timeLeftInMilliseconds / 3600000;
        int minutes = (int) timeLeftInMilliseconds % 3600000 / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText = "";

        if(hours < 10){
            timeLeftText += "0";
        }

        timeLeftText += hours + ":";

        if(minutes < 10){
            timeLeftText += "0";
        }
        timeLeftText += minutes + ":";

        if(seconds < 10){
            timeLeftText += "0";
        }
        timeLeftText += seconds;

        mCountdownText.setText(timeLeftText);
    }
}
