package android.dailyexpenses.unpas.dailyexpenses;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Setting extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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

    public void openKategoriPemasukan(View view){
        Intent intent = new Intent(getApplication(),KategoriPemasukan.class);
        startActivity(intent);
    }

    public void openKategoriPengeluaran(View view){
        Intent intent = new Intent(getApplication(),KategoriPengeluaran.class);
        startActivity(intent);
    }

    public void openKategoriPemasukanRutin(View view){
        Intent intent = new Intent(getApplication(),KategoriPemasukanRutin.class);
        startActivity(intent);
    }

    public void openKategoriPengeluaranRutin(View view){
        Intent intent = new Intent(getApplication(),KategoriPengeluaranRutin.class);
        startActivity(intent);
    }

    public void openPin(View view){
        Intent intent = new Intent(getApplication(),Pin.class);
        startActivity(intent);
    }


}
