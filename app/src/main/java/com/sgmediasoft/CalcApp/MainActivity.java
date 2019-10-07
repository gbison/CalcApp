package com.sgmediasoft.CalcApp;
/*
    Description: Designed to provide basic input/output of normal arithamtic operations.
    Date: 9/2/2019
    Class: CIT238 (SKCTCS)
    Author: George Ison
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    //Declare widget variables we might want to use in the future
    protected Button button;
    protected Button buttontwo;
    protected ProgressBar progressBar;
    protected TextView titleText;
    protected EditText editTextNum1;
    protected EditText editTextNum2;
    protected TextView textview_results;
    protected RadioGroup radioGroup;

    //Declare any other global variables you need for the class here!

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Boiler code - DONT TOUCH
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //End Boiler code

        //Initialize all the front end objects we intend to use! (remember, link the front to the back)
        button = findViewById(R.id.button);
        buttontwo = findViewById(R.id.button2);
        progressBar = findViewById(R.id.progressBar);
        titleText = findViewById(R.id.title_text);
        radioGroup = findViewById(R.id.radioGroup);
        editTextNum1 = findViewById(R.id.editText_num1);
        editTextNum2 = findViewById(R.id.editText_num2);
        textview_results = findViewById(R.id.textview_results);

        //Widget configurations and general setup around here!
        progressBar.setProgress(0);

        //Setup our activity switchers listening event. (feed it anonymous function or create a method
        //and assign in the GUI editor via onClick attribute.
        buttontwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(),Main2Activity.class);
                myIntent.putExtra("value1", "I passed a value!!");

                startActivity(myIntent);
            }
        });
    }

    public void onButtonClick(View v)
    {
        /*
            Pay attention to the order of things.

            First thing lets setup some data so our method has something to work with.
            Lets figure out which operation the user wants to perform by asking the radio group.
        */

        int id = radioGroup.getCheckedRadioButtonId();

        //Cool, we got its ID, now lets get a reference to the button object that's on our GUI.
        RadioButton button = findViewById(id);
        String text = button.getText().toString(); //Then get its name, as the name will tell us the operation.

        //Since we are using mobile, you don't want to throw memory around willy nilly, char is all we need
        //for what we are about to do.
        char op = 'x';

        /*
            Setup a switch, because its to cumbersome and actually takes longer to process several IF statements.
            Now all we have to do is determine the value we stored in text (name of radio button) then based
            on that result, will can determine what op should be and we can prepare our new object.
         */
        switch (text)
        {
            case "Add":
                op = '+';
                break;
            case "Subtract":
                op = '-';
                break;
            case "Multiply":
                op = '*';
                break;
            case "Divide":
                op = '/';
                break;

            default:
        }

        /*
            Now that we have determined what our user wants to do, the rest is up to the Calculate
            class that we have built earlier.  This constructor takes 3 arguments (int, int, char).

            If you use the constructor that takes no arguments. You must use setter methods to set
            the number values calc.setFirstNumber(value) and calc.setNextNumber(value) then you can
            call the arithmatic function directly (example: calc.Add()) and the value will be stored in
            calc class and also returned to you from the method call for use in a varible (int val = calc.Add();)
        */

        Calculate calc =  new Calculate(Integer.parseInt(editTextNum1.getText().toString()),
                                        Integer.parseInt(editTextNum2.getText().toString()),
                                        op);

        calc.MainContext = MainActivity.this; //Lets take note of our Main Context and use that variable we set in the Calc class.

        /*
            Now pay special attention above, we used the Integer classes parseInt option, to turn the text
            provided by the EditText input options into an actual number (integer in this case). Remember
            though, Java tends to set text in character sets, this might surprise you coming from C# so
            remember to call toString()!  Finally, we just pass our char value that represents our math symbol.
            We end up with two numbers the user entered and an operator sign which we passed on to the constructor.
        */

        //If you remember, during the construction phase, we determined the value and stored it within the class.
        //Now we just need to call it back, so we can populate the results field.
        textview_results.setText(Integer.toString(calc.getValue()));

        /*
        Lets give the user a little flash, let them know things are moving along. In Android slang this
        is called a Toast!!

        Now lets prepare a text statement to present to the user.
         */
        String flash = "You choose " + text + " and your result was: " + textview_results.getText().toString();

        //We send the text to the user via the Toast classes static call.
        Toast.makeText(MainActivity.this, flash, Toast.LENGTH_LONG).show();


        /*
            What in the world is happening below??

            Here we are using Javas built in timer functionality, which is super cool! In order to
            use it, we need two pieces of information.  First, a Timer and then a TimerTask.
         */

        Timer timer = new Timer(); //Create an instance of the timer class.

        //Now we create an instance of the TimerTask and feed it an anonymous function or lambda which
        //does nothing but call a method.  Bottom line, the task represents what the timer should do
        //when the times up!
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ResetProgBar(); //call a method!
            }
        };

        //Give the user some perspective, show them we have completed the request!
        progressBar.setProgress(100,true); //cannot use this command below API 24

        timer.schedule(task, 2000); //Here we Fire the timer schedule and start ticking!
    }

    //Simple progress bar reset (called from our timer above!)
    private void ResetProgBar()
    {
        progressBar.setProgress(0);
    }

    public void ClearEditBoxes(View v)
    {
        editTextNum1.setText("");
        editTextNum2.setText("");
        textview_results.setText("");
        System.gc(); //invoke cleanup
        Toast.makeText(MainActivity.this, "Calculator Reset", Toast.LENGTH_LONG).show();
    }
}
