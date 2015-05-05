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

import java.util.ArrayList;
import java.util.HashMap;


public class PemasukanRutin extends ActionBarActivity {
    SQLiteHelper db = new SQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukan_rutin);

        final ArrayList<HashMap<String,String>> arrayListPemasukan = db.tampilSemuaPemasukanRutin();
        if (arrayListPemasukan.size() != 0){
            ListAdapter adapter = new SimpleAdapter(PemasukanRutin.this,
                    arrayListPemasukan, R.layout.data_entry, new String[] {
                    "id_pemasukan","nama_pemasukan", "jumlah_pemasukan", "deskripsi_pemasukan","tanggal_pemasukan","jam_pemasukan" }, new int[] {
                    R.id.idtxt2,R.id.namaTxt, R.id.jumlahTxt, R.id.deskTxt,R.id.tglTxt,R.id.jamTxt });

            ListView lv = (ListView) findViewById(R.id.listViewPemasukanRutin);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView idDelete = (TextView) findViewById(R.id.idtxt2);
                    int idBaru = Integer.parseInt(idDelete.getText().toString());
                    deletePemasukan(idBaru);
                }
            });

        }
    }

    public void deletePemasukan(final int id){

        AlertDialog.Builder builderDelete = new AlertDialog.Builder(this);
        builderDelete.setTitle("Delete Pemasukan");
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);
        builderDelete.setView(layoutInput);
        builderDelete.setPositiveButton("Hapus",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.hapusPemasukanRutin(id);
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

    public void tambahPemasukanRutin(View view){
        Intent intent= new Intent(getApplication(),TambahPemasukanRutin.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pemasukan_rutin, menu);
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
