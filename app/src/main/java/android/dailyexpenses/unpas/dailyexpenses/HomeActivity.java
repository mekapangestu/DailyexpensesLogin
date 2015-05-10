package android.dailyexpenses.unpas.dailyexpenses;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class HomeActivity extends ActionBarActivity {
    SQLiteHelper db = new SQLiteHelper(this);
    private int tahun, bulan, hari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       //ini
        final Calendar c = Calendar.getInstance();
        tahun = c.get(Calendar.YEAR);
        bulan = c.get(Calendar.MONTH)+1;
        hari = c.get(Calendar.DAY_OF_MONTH);

        final ArrayList<HashMap<String,String>> arrayListReminder = db.getAllReminder();
        for (int i=0;i<arrayListReminder.size();i++){
            String tanggal = arrayListReminder.get(i).get("tanggal_reminder");
            String pesan = arrayListReminder.get(i).get("pesan_reminder");

            if (tanggal.equals(hari +"-"+ bulan +"-"+ tahun)){
                Intent intent = new Intent();
                PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
                Notification n = new Notification.Builder(this)
                        .setTicker("Daily Expenses")
                        .setContentTitle("Pengingat Hutang")
                        .setContentText(pesan)
                        .setSmallIcon(R.mipmap.ic)
                        .setContentIntent(pIntent).getNotification();
                n.flags=Notification.FLAG_AUTO_CANCEL;
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, n);
            }
        }
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

    public void openReminder(View view){
        Intent intent = new Intent(getApplication(),Reminder.class);
        startActivity(intent);
    }

    public void openInfo(View view){
        Intent intent = new Intent(getApplication(),Info.class);
        startActivity(intent);
    }

}
