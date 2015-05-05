package android.dailyexpenses.unpas.dailyexpenses;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LaporanTanggal extends ActionBarActivity {
    Spinner spinner;
    SQLiteHelper db = new SQLiteHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_tanggal);

        spinner = (Spinner) findViewById(R.id.spinnerLaporanTanggal);
        loadspinner();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tanggal = spinner.getSelectedItem().toString();
                tampilList(tanggal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void tampilList(String tanggal){
        ArrayList<HashMap<String, String>> kampusList = db
                .tampilUnionTanggal(tanggal);
		/* jikta tidak kosong, tampilkan data kampus ke ListView
		 *
		 */
        if (kampusList.size() != 0) {

            ListAdapter adapter = new SimpleAdapter(LaporanTanggal.this,
                    kampusList, R.layout.layout_cashflow, new String[] {
                    "nama_pemasukan", "jumlah_pemasukan", "deskripsi_pemasukan","tanggal_pemasukan","jam_pemasukan" }, new int[] {
                    R.id.namaTxt, R.id.jumlahTxt, R.id.deskTxt,R.id.tglTxt,R.id.jamTxt });

            ListView lv = (ListView) findViewById(R.id.listLaporanTanggal);
            lv.setAdapter(adapter);
        }
    }

    private void loadspinner(){
        SQLiteHelper db = new SQLiteHelper(getApplicationContext());
        List<String> list =db.getAllSpinnerTanggal();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,list);

        spinner.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_laporan_tanggal, menu);
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
}
