package android.dailyexpenses.unpas.dailyexpenses;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;


public class TambahPemasukanRutin extends ActionBarActivity implements View.OnClickListener {
    SQLiteHelper db = new SQLiteHelper(this);
    EditText namaEdit,jumlahEdit,deksEdit,tanggalEdit,jamEdit;
    // Spinner element
    Spinner spinner;
    //date time
    Button btnCalendar,btnTimePicker;
    EditText txtDate,txtTime;

    // variable for stooring current time date
    private int mYear,mMonth,mDay,mHour,mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pemasukan_rutin);

        spinner = (Spinner) findViewById(R.id.spinner);

        // Loading spinner data from database
        loadSpinnerData();

        //datepicker
        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        btnTimePicker = (Button) findViewById(R.id.btnTimePicker);
        txtDate = (EditText) findViewById(R.id.txtDate);
        txtTime = (EditText) findViewById(R.id.txtTime);
        btnCalendar.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
    }

    private void loadSpinnerData() {
        // database handler
        SQLiteHelper db = new SQLiteHelper(getApplicationContext());

        // Spinner Drop down elements
        List<String> pemasukan_spinner = db.getAllSpinnerPemasukanRutin();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, pemasukan_spinner);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    public void edit(String id,String nama,String jumlah,String desk){
        namaEdit.setText(nama);
        jumlahEdit.setText(jumlah);
        deksEdit.setText(desk);

    }

    public void openKategoriPemasukanRutin(View view){
        Intent intent= new Intent(getApplication(),KategoriPemasukanRutin.class);
        startActivity(intent);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tambah_pemasukan_rutin, menu);
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

    public void addNewPemasukanRutin(View view){

//        namaEdit = (EditText) findViewById(R.id.namaInput);
//        String nama = namaEdit.getText().toString();

        spinner = (Spinner) findViewById(R.id.spinner);
        String nama = spinner.getSelectedItem().toString();

        jumlahEdit = (EditText) findViewById(R.id.jumlahInput);
        int jumlah = Integer.parseInt(jumlahEdit.getText().toString());

        deksEdit = (EditText) findViewById(R.id.deskInput);
        String desk = deksEdit.getText().toString();

        tanggalEdit = (EditText) findViewById(R.id.txtDate);
        String tgl = tanggalEdit.getText().toString();

        jamEdit = (EditText) findViewById(R.id.txtTime);
        String jam = jamEdit.getText().toString();

        if(desk.matches("") || tgl.matches("") || jam.matches("") || jumlah == 0) {
            Toast.makeText(getApplicationContext(), "Input tidak boleh kosong",
                    Toast.LENGTH_SHORT).show();
            return;
        }else{
            db.tambahPemasukanRutin(nama, jumlah, desk, tgl, jam);
            this.callHomePage(view);
        }



    }

    public void closeNewPemasukanRutin(View view){
        this.callHomePage(view);
    }

    public void callHomePage(View view){
        Intent intent = new Intent(getApplicationContext(),PemasukanRutin.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == btnCalendar){
            // current date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            // dialog
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener(){

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" +year);

                        }
                    },mYear,mMonth,mDay);
            dpd.show();
        }
        if (v == btnTimePicker){
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener(){

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    },mHour,mMinute,false);
            tpd.show();
        }
    }
}
