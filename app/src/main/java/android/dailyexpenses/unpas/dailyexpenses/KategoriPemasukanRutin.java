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


public class KategoriPemasukanRutin extends ActionBarActivity {
    SQLiteHelper db = new SQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_pemasukan_rutin);

        final ArrayList<HashMap<String,String>> kategoriArr = db.getAllSpinerPemasukanRutin();
        if (kategoriArr.size() != 0){
            ListAdapter adapter = new SimpleAdapter(KategoriPemasukanRutin.this,
                    kategoriArr,R.layout.layout_kategori,new String[]{
                    "id","nama"},new int[]{
                    R.id.idKategori,R.id.kategoriTxt});
            ListView lv = (ListView) findViewById(R.id.listKategoriPemasukanRutin);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView idDelete = (TextView) findViewById(R.id.idKategori);
                    int idBaru = Integer.parseInt(idDelete.getText().toString());
                    deleteKategoriPemasukan(idBaru);
                }
            });
        }
    }

    public void deleteKategoriPemasukan(final int id){
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
                        db.hapusKategoriPemasukanRutin(id);
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

    public void addKategoriRutin(View view){
        EditText nama = (EditText) findViewById(R.id.namaKategoriPemasukanRutin);
        TextView alert = (TextView) findViewById(R.id.alertPemasukanRutin);
        String namaS = nama.getText().toString();
        if (namaS.equalsIgnoreCase("")){
            alert.setText("harus diisi !");
        }else {
            db.tambahSpinnerPemasukanRutin(namaS);
            closeContextMenu();
            alert.setText("");
            this.back(view);
            System.out.println("tes");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kategori_pemasukan_rutin, menu);
        return true;
    }

    public void back(View view){
        Intent intent = new Intent(getApplicationContext(),KategoriPemasukanRutin.class);
        startActivity(intent);
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
