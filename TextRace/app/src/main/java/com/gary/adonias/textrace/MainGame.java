package com.gary.adonias.textrace;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.EditText;
import android.widget.TextView;

public class MainGame extends AppCompatActivity {
    private EditText playerInput;
    private TextView typingScript;
    private String[] scriptWords;
    private SpannableString spannable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        typingScript = (TextView)findViewById(R.id.typingScript);
        spannable = new SpannableString(typingScript.getText().toString());
        scriptWords = typingScript.getText().toString().split(" ");
        playerInput = (EditText) findViewById(R.id.playerInput);
        playerInput.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        playerInput.addTextChangedListener(spaceCharWatcher);
    }

    private final TextWatcher spaceCharWatcher = new TextWatcher(){
        private int count = 0;
        private int index = 0;
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String text = charSequence.toString();
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String text = charSequence.toString();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            if(!text.equals("")) {
                if (text.charAt(text.length() - 1) == ' ') {
                    if(scriptWords[count].equals(text.trim())){
                        boolean correct = true;
                        final ForegroundColorSpan GREEN = new ForegroundColorSpan(Color.GREEN);
                        //fix for repeated words
                        if(count != 0) {
                            index = (typingScript.getText().toString()).indexOf(scriptWords[count], index + scriptWords[count - 1].length());
                        }
                        spannable.setSpan(GREEN,
                                index, index + scriptWords[count].length(), 0);
                        typingScript.setText(spannable);
                    }
                    else{
                        final ForegroundColorSpan RED = new ForegroundColorSpan(Color.RED);
                        //fix for repeated words
                        if(count != 0){
                            index = (typingScript.getText().toString()).indexOf(scriptWords[count], index + scriptWords[count - 1].length());
                        }

                        spannable.setSpan(RED,
                                index, index + scriptWords[count].length(), 0);
                        typingScript.setText(spannable);
                    }
                    count++;
                    editable.clear();
                }
                if(scriptWords.length == count){
                    //reset
                    final ForegroundColorSpan DKGRAY = new ForegroundColorSpan(Color.DKGRAY);
                    spannable.setSpan(DKGRAY,
                            0, typingScript.getText().toString().length(), 0);
                    typingScript.setText(spannable);
                    count = 0;
                    index = 0;
                }
            }
        }
    };
}
