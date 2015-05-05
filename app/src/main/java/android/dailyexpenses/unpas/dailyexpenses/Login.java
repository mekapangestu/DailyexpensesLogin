package android.dailyexpenses.unpas.dailyexpenses;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Login extends ActionBarActivity {
    SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
    EditText pin;
    Button cek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pin = (EditText) findViewById(R.id.pinInput);
        cek = (Button) findViewById(R.id.buttonLogin);

        cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pinInput = pin.getText().toString();
                cariPin(pinInput);
            }
        });

    }

    public void cariPin(String pin){
        final ArrayList<HashMap<String,String>> entryPin = sqLiteHelper.findPin(pin);
        if (entryPin.size() != 0 ){
            Toast.makeText(getApplicationContext(),"berhasil",
                    Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(),"PIN tidak sesuai",
                    Toast.LENGTH_SHORT).show();
        }
    }



//    public void openHome(View view){
//        Intent i = new Intent(getApplicationContext(),HomeActivity.class);
//        startActivity(i);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        View view = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (view instanceof EditText){
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX()+w.getLeft()-scrcoords[0];
            float y = event.getRawY()+w.getTop()-scrcoords[1];

            if (event.getAction()==MotionEvent.ACTION_UP && (x<w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom())){
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(),0);

            }
        }
        return ret;
    }

}
