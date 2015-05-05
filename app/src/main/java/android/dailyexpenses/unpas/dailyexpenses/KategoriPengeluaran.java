package android.dailyexpenses.unpas.dailyexpenses;

import android.app.AlertDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class KategoriPengeluaran extends ActionBarActivity {
    SQLiteHelper db = new SQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_pengeluaran);
        final ArrayList<HashMap<String,String>> kategoriArr = db.getAllSpinerPengeluaran();
        if (kategoriArr.size() != 0){
            ListAdapter adapter = new SimpleAdapter(KategoriPengeluaran.this,
                    kategoriArr,R.layout.layout_kategori,new String[]{
                    "id_s_pengeluaran","nama_s_pengeluaran"},new int[]{
                    R.id.idKategori,R.id.kategoriTxt});
            ListView lv = (ListView) findViewById(R.id.listKategoriPengeluaran);
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
        builderDelete.setPositiveButton("Hapus",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.hapusKategoriPengeluaran(id);
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

    public void addKategoriPengeluaran (View view){
        EditText nama = (EditText) findViewById(R.id.namaKategoriPengeluaran);
        TextView alert = (TextView) findViewById(R.id.alertPengeluaran);
        String namaS = nama.getText().toString();
        if (namaS.equalsIgnoreCase("")){
            alert.setText("harus diisi !");
        }else {
            db.tambahSpinnerPengeluaran(namaS);
            closeContextMenu();
            alert.setText("");
            this.back(view);
        }
    }

    public void back(View view){
        Intent intent = new Intent(getApplicationContext(),KategoriPengeluaran.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kategori_pengeluaran, menu);
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
