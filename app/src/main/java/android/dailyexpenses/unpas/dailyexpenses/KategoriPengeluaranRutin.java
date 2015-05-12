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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class KategoriPengeluaranRutin extends ActionBarActivity {
    SQLiteHelper db = new SQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_pengeluaran_rutin);

        final ArrayList<HashMap<String,String>> kategoriArr = db.getAllSpinerPengeluaranRutin();
        if (kategoriArr.size() != 0){
            ListAdapter adapter = new SimpleAdapter(KategoriPengeluaranRutin.this,
                    kategoriArr,R.layout.layout_kategori,new String[]{
                    "id_s_pengeluaran","nama_s_pengeluaran"},new int[]{
                    R.id.idKategori,R.id.kategoriTxt});
            ListView lv = (ListView) findViewById(R.id.listKategoriPengeluaranRutin);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView idDelete = (TextView) findViewById(R.id.idKategori);
                    int idBaru = Integer.parseInt(idDelete.getText().toString());
                    deleteKategoriPengeluaran(idBaru);
                }
            });
        }
    }

    public void deleteKategoriPengeluaran(final int id){
        AlertDialog.Builder builderDelete = new AlertDialog.Builder(this);
        builderDelete.setTitle("Delete Kategori");
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);
        builderDelete.setView(layoutInput);
        builderDelete.setMessage("Apakah anda yakin?");
        builderDelete.setPositiveButton("Hapus",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.hapusKategoriPengeluaranRutin(id);
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

    public void addKategoriPengeluaranRutin (View view){
        EditText nama = (EditText) findViewById(R.id.namaKategoriPengeluaranRutin);
        TextView alert = (TextView) findViewById(R.id.alertPengeluaranRutin);
        String namaS = nama.getText().toString();
        if (namaS.equalsIgnoreCase("")){
            alert.setText("harus diisi !");
        }else {
            db.tambahSpinnerPengeluaranRutin(namaS);
            closeContextMenu();
            alert.setText("");
            this.back(view);
        }
    }

    public void back(View view){
        Intent intent = new Intent(getApplicationContext(),KategoriPengeluaranRutin.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kategori_pengeluaran_rutin, menu);
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
