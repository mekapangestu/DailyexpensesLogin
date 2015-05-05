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
import android.widget.EditText;


public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
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

    public void openPemasukan(View view){
        Intent objIntent = new Intent(getApplication(),PemasukanActivity.class);
        startActivity(objIntent);
    }

    public void openPengeluaran(View view){
        Intent objIntent = new Intent(getApplication(),PengeluaranActivity.class);
        startActivity(objIntent);
    }

    public void openPemasukanRutin(View view){
        Intent objIntent = new Intent(getApplication(),PemasukanRutin.class);
        startActivity(objIntent);
    }

    public void openPengeluaranRutin(View view){
        Intent objIntent = new Intent(getApplication(),PengeluaranRutin.class);
        startActivity(objIntent);
    }

    public void openSetting(View view){
        Intent intent = new Intent(getApplication(),Setting.class);
        startActivity(intent);
    }

    public void openCashFlow(View view){
        Intent intent = new Intent(getApplication(),CashFlow.class);
        startActivity(intent);
    }

    public void openLaporan(View view){
        Intent intent = new Intent(getApplication(),LaporanTanggal.class);
        startActivity(intent);
    }

}
