package android.dailyexpenses.unpas.dailyexpenses;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class SplashScreen extends Activity {
    private static int timer = 3000;
    SQLiteHelper sqLiteHelper = new SQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //tampil database
        final ArrayList<HashMap<String,String>> arrayListPemasukan = sqLiteHelper.getAllPin();
        if (arrayListPemasukan.size() != 0){
            new Handler ().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this,Login.class);
                    startActivity(i);

                    finish();
                }
            },timer);


        }else{
            new Handler ().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this,HomeActivity.class);
                    startActivity(i);

                    finish();
                }
            },timer);
        }


    }
}
