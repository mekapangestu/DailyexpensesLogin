package android.dailyexpenses.unpas.dailyexpenses;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;


public class TambahPengeluaran extends ActionBarActivity implements OnClickListener{
    SQLiteHelper db = new SQLiteHelper(this);
    EditText namaEdit,jumlahEdit,deksEdit,tanggalEdit,jamEdit;
    //spinner
    Spinner spinner2;
    //date
    Button btnCalendar2, btnTimePicker2;
    EditText txtDate,txtTime;

    private int mYear,mMonth,mDay,mHour,mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pengeluaran);

        spinner2 = (Spinner) findViewById(R.id.spinner2);

        loadSpinnerDate();

        // date picker
        btnCalendar2 = (Button) findViewById(R.id.btnCalendar2);
        btnTimePicker2 = (Button) findViewById(R.id.btnTimePicker2);
        txtDate = (EditText) findViewById(R.id.txtDate2);
        txtTime = (EditText) findViewById(R.id.txtTime2);
        btnCalendar2.setOnClickListener(this);
        btnTimePicker2.setOnClickListener(this);

    }

    private void loadSpinnerDate() {
        // db handler
        SQLiteHelper db = new SQLiteHelper(getApplicationContext());

        //spinner drop down
        List<String> pengeluaran_spinner = db.getAllSpinnerPengeluaran();

        //create adapter
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,pengeluaran_spinner);

        spinner2.setAdapter(dataAdapter);
    }

    public void openKategoriPengeluaran(View view){
        Intent intent= new Intent(getApplication(),KategoriPengeluaran.class);
        startActivity(intent);
    }

    public void addNewPengeluaran(View view){

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        String nama = spinner2.getSelectedItem().toString();

        jumlahEdit = (EditText) findViewById(R.id.jumlahInput);
        int jumlah = Integer.parseInt(jumlahEdit.getText().toString());

        deksEdit = (EditText) findViewById(R.id.deskInput);
        String desk = deksEdit.getText().toString();

        tanggalEdit = (EditText) findViewById(R.id.txtDate2);
        String tgl = tanggalEdit.getText().toString();

        jamEdit = (EditText) findViewById(R.id.txtTime2);
        String jam = jamEdit.getText().toString();
        if(desk.matches("") || tgl.matches("") || jam.matches("") || jumlah == 0) {
            Toast.makeText(getApplicationContext(), "Input tidak boleh kosong",
                    Toast.LENGTH_SHORT).show();
            return;
        }else{
            db.tambahPengeluaran(nama,jumlah,desk,tgl,jam);
            this.callHomePage(view);
        }

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

    public void closeNewPengeluaran(View view){
        this.callHomePage(view);
    }

    public void callHomePage(View view){
        Intent intent = new Intent(getApplicationContext(),PengeluaranActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tambah_pengeluaran, menu);
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
    public void onClick(View v) {
        if (v == btnCalendar2){
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
        if (v == btnTimePicker2){
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
