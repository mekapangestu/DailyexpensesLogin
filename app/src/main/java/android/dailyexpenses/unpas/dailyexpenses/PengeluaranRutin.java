package android.dailyexpenses.unpas.dailyexpenses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PengeluaranRutin extends ActionBarActivity {
    SQLiteHelper sqLiteHelper = new SQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengeluaran_rutin);
        ArrayList<HashMap<String, String>> arrayListPengeluaran = sqLiteHelper.tampilSemuaPengeluaranRutin();
        if (arrayListPengeluaran.size() > 0) {
            ListAdapter adapter = new SimpleAdapter(PengeluaranRutin.this,
                    arrayListPengeluaran, R.layout.data_entry, new String[] {
                    "id_pengeluaran","nama_pengeluaran", "jumlah_pengeluaran", "deskripsi_pengeluaran","tanggal_pengeluaran","jam_pengeluaran" }, new int[] {
                    R.id.idtxt2,R.id.namaTxt, R.id.jumlahTxt, R.id.deskTxt,R.id.tglTxt,R.id.jamTxt });

            ListView lv = (ListView) findViewById(R.id.listViewPengeluaranRutin);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView idDelete = (TextView) findViewById(R.id.idtxt2);
                    int idBaru = Integer.parseInt(idDelete.getText().toString());
                    deletePengeluaranRutin(idBaru);

                }
            });

        }
    }

    public void deletePengeluaranRutin(final int id){
        AlertDialog.Builder builderDelete = new AlertDialog.Builder(this);
        builderDelete.setTitle("Hapus Pengeluaran ?");
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);
        builderDelete.setView(layoutInput);
        builderDelete.setMessage("Apakah anda yakin?");
        builderDelete.setPositiveButton("Hapus",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sqLiteHelper.hapusPengeluaranRutin(id);
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


    public void tambahPengeluaranRutin(View view){
        List<String> pemesanan_spinner = sqLiteHelper.getAllSpinnerPengeluaranRutin();
        if (pemesanan_spinner.size() == 0){
            Toast.makeText(getApplicationContext(), "Tambah kategori terlebih dahulu",
                    Toast.LENGTH_SHORT).show();
            return;
        }else{
            Intent intent= new Intent(getApplication(),TambahPengeluaranRutin.class);
            startActivity(intent);
        }

    }

    public void openKategoriPengeluaranRutin(View view){
        Intent intent= new Intent(getApplication(),KategoriPengeluaranRutin.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pengeluaran_rutin, menu);
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
