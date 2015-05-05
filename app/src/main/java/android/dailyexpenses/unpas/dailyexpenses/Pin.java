package android.dailyexpenses.unpas.dailyexpenses;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Pin extends ActionBarActivity {
    EditText pin,pin2;
    SQLiteHelper db = new SQLiteHelper(this);
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        spinner = (Spinner) findViewById(R.id.spinnerPin);
        loadSpinnerData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pin, menu);
        return true;
    }

    private void loadSpinnerData() {


        // Spinner Drop down elements
        List<String> pemasukan_spinner = db.spinnerPin();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, pemasukan_spinner);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    public void addPin(View view){
        final ArrayList<HashMap<String,String>> arrayListPin = db.getAllPin();
        if (arrayListPin.size() == 0){
            pin = (EditText) findViewById(R.id.pinTxt);
            String pinText = pin.getText().toString();

            pin2 = (EditText) findViewById(R.id.pin2Txt);
            String pin2Text = pin2.getText().toString();

            if (pinText != null && pin2Text != null && pinText.contains(pin2Text)){
                db.tambahPin(pinText);
                this.callHomePage(view);
            }else{
                Toast.makeText(getApplicationContext(),"PIN tidak sesuai",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"anda Sudah Memiliki Pin",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void callHomePage(View view){
        Intent intent = new Intent(getApplicationContext(),Pin.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
