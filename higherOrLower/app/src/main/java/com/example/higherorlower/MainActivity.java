package com.example.higherorlower;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int randomNumber;

    public void guess(View view) {
        EditText editText = (EditText) findViewById(R.id.numberInput);

        if (editText.getText().toString().isEmpty()) {
            makeToast("Please enter a number");    
	    return;
        }

        int guessValue = Integer.parseInt(editText.getText().toString());

        String message;

        if (guessValue > randomNumber) {
            message = "Lower!";
        } else if (guessValue < randomNumber) {
            message = "Higher!";
        } else {
            message = "You got it!";
            randomGenerator();
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        Log.i("Entered Value", editText.getText().toString());
        Log.i("Random Number", Integer.toString(randomNumber));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        randomGenerator();
    }
    public void randomGenerator(){
        Random rand = new Random();
        randomNumber = rand.nextInt(50) + 1;
    }

    public void makeToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
