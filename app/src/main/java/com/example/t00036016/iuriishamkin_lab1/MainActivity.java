package com.example.t00036016.iuriishamkin_lab1;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText kml,mpg;     //declaring fields to enter data
    String result;        //result of conversion
    Double conv_value;    //entered data
    Button buttonConvert; //conversion button
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonConvert = (Button)findViewById(R.id.button01); //initializing button
        kml = (EditText)findViewById(R.id.editText01); //initializing first input field
        mpg = (EditText)findViewById(R.id.editText02); //initializing second input field
        layout = (LinearLayout)findViewById(R.id.main_layout);

        kml.addTextChangedListener(new TextWatcher() {  //assigning listener to the first input so
                                                        //user will be aware of exceeded input length
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>11)
                    kml.setError("Maximum number exceeded");
            }
        });

        mpg.addTextChangedListener(new TextWatcher() {  //assigning listener to the second input so
                                                        //user will be aware of exceeded input length
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>11)
                    mpg.setError("Maximum number exceeded");
            }
        });


        buttonConvert.setOnClickListener(new View.OnClickListener() { //creating listener for the convert button
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(kml.getText().toString()) && TextUtils.isEmpty(mpg.getText().toString())) { //checking first situation
                                                                                                                  //when both inputs are empty
                    kml.setError("One of these two items must have a value!");
                    Toast.makeText(getApplicationContext(), "Please Enter a Value", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(kml.getText().toString()) && !TextUtils.isEmpty(mpg.getText().toString())) { //checking when second
                                                                                                                        //input isn't empty but first

                    try {
                        conv_value = Double.parseDouble(mpg.getText().toString());
                    } catch (NumberFormatException e) {
                        conv_value = 0.0;
                    }
                    conv_value *= 0.42;
                    result = String.format("%.2f",conv_value);
                    kml.setText(result);
                    Toast.makeText(getApplicationContext(), "Converting from MPG to KM/L", Toast.LENGTH_SHORT).show();
                } else { //third case when both have input or just first field

                    try {
                        conv_value = Double.parseDouble(kml.getText().toString());
                    } catch (NumberFormatException e) {
                        conv_value = 0.0;
                    }
                    conv_value *= 2.35;
                    result = String.format("%.2f",conv_value);
                    mpg.setText(result);
                    Toast.makeText(getApplicationContext(), "Converting from KM/L to MPG", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.theme1) {
            Toast.makeText(getApplicationContext(), "Menu selected", Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }
}
