package com.example.myapp1;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Main4Activity extends Activity {
    private final int REQUEST_SPEECH_RECOGNIZER = 3000;
    private TextView mTextView;
    private final String mQuestion = "What day of week it is?";
    private String mAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        mTextView = (TextView) findViewById(R.id.simpleText);
        startSpeechRecognizer();
    }

    private void startSpeechRecognizer() {
        Intent intent = new Intent
                (RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, mQuestion);

        startActivityForResult(intent, REQUEST_SPEECH_RECOGNIZER);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SPEECH_RECOGNIZER) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                mAnswer = (String) ((ArrayList) results).get(0);


                if (mAnswer.toUpperCase().equals("MONDAY")) {
                    mTextView.setText("\n\nQuestion: " + mQuestion + "\n\nYour answer is '" + mAnswer + "' and it is Correct!\n you are verified");


                } else {
                    mTextView.setText("\n\nQuestion: " + mQuestion + "\n\nYour answer is '" + mAnswer + "' and it is incorrect!\n Please try again");

                }
            }
        }
    }
}