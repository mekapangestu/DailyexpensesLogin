package android.dailyexpenses.unpas.dailyexpenses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.HashMap;


public class PengeluaranActivity extends ActionBarActivity{
    SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
    TableLayout tabelPengeluaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengeluaran);
        //tampil database
        ArrayList<HashMap<String, String>> arrayListPengeluaran = sqLiteHelper.tampilSemuaPengeluaran();
        if (arrayListPengeluaran.size() > 0) {
            ListAdapter adapter = new SimpleAdapter(PengeluaranActivity.this,
                    arrayListPengeluaran, R.layout.data_entry, new String[] {
                    "id_pengeluaran","nama_pengeluaran", "jumlah_pengeluaran", "deskripsi_pengeluaran","tanggal_pengeluaran","jam_pengeluaran" }, new int[] {
                    R.id.idtxt2,R.id.namaTxt, R.id.jumlahTxt, R.id.deskTxt,R.id.tglTxt,R.id.jamTxt });

            ListView lv = (ListView) findViewById(R.id.listViewPengeluaran);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView idDelete = (TextView) findViewById(R.id.idtxt2);
                    int idBaru = Integer.parseInt(idDelete.getText().toString());
                    deletePengeluaran(idBaru);

                }
            });

        }
    }

    public void deletePengeluaran(final int id){
        AlertDialog.Builder builderDelete = new AlertDialog.Builder(this);
        builderDelete.setTitle("Hapus Pengeluaran ?");
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);
        builderDelete.setView(layoutInput);
        builderDelete.setPositiveButton("Hapus",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sqLiteHelper.hapusPengeluaran(id);
                        finish();
                        startActivity(getIntent());
                    }

                });

        builderDelete.setNegativeButton("Keluar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builderDelete.show();
    }

    public void tambahPengeluaran(View view){
        Intent intent= new Intent(getApplication(),TambahPengeluaran.class);
        startActivity(intent);
    }

    public void openKategoriPengeluaran(View view){
        Intent intent= new Intent(getApplication(),KategoriPengeluaran.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pengeluaran, menu);
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
