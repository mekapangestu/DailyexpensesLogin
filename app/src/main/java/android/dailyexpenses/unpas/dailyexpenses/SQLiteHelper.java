package android.dailyexpenses.unpas.dailyexpenses;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by upi on 4/16/2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String NAMA_DATABASE = "ujicoba";
    private static final int VERSI_DB = 1;
    private static final String QUERY_BUAT_TABEL_PEMASUKAN = "CREATE TABLE IF NOT EXISTS tabel_pemasukan(id_pemasukan INTEGER PRIMARY KEY AUTOINCREMENT, nama_pemasukan TEXT,jumlah_pemasukan INTEGER,deskripsi_pemasukan TEXT,tanggal_pemasukan TEXT,jam_pemasukan TEXT)";
    private static final String QUERY_BUAT_TABEL_PENGELUARAN = "CREATE TABLE IF NOT EXISTS tabel_pengeluaran(id_pengeluaran INTEGER PRIMARY KEY AUTOINCREMENT, nama_pengeluaran TEXT,jumlah_pengeluaran INTEGER,deskripsi_pengeluaran TEXT,tanggal_pengeluaran TEXT,jam_pengeluaran TEXT)";
    private static final String QUERY_BUAT_TABEL_PEMASUKAN_RUTIN = "CREATE TABLE IF NOT EXISTS tabel_pemasukan_rutin(id_pemasukan INTEGER PRIMARY KEY AUTOINCREMENT, nama_pemasukan TEXT,jumlah_pemasukan INTEGER,deskripsi_pemasukan TEXT,tanggal_pemasukan TEXT,jam_pemasukan TEXT)";
    private static final String QUERY_BUAT_TABEL_PENGELUARAN_RUTIN = "CREATE TABLE IF NOT EXISTS tabel_pengeluaran_rutin(id_pengeluaran INTEGER PRIMARY KEY AUTOINCREMENT, nama_pengeluaran TEXT,jumlah_pengeluaran INTEGER,deskripsi_pengeluaran TEXT,tanggal_pengeluaran TEXT,jam_pengeluaran TEXT)";
    private static final String QUERY_HAPUS_SPINNER_PEMASUKAN = "DROP TABLE IF EXISTS pemasukan_spinner";
    private static final String QUERY_HAPUS_SPINNER_PENGELUARAN = "DROP TABLE IF EXISTS pengeluaran_spinner";
    private static final String QUERY_HAPUS_SPINNER_PEMASUKAN_RUTIN = "DROP TABLE IF EXISTS pemasukan_rutin_spinner";
    private static final String QUERY_HAPUS_SPINNER_PENGELUARAN_RUTIN = "DROP TABLE IF EXISTS pengeluaran_rutin_spinner";
    private static final String QUERY_SPINNER_PEMASUKAN = "CREATE TABLE IF NOT EXISTS pemasukan_spinner(id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT)";
    private static final String QUERY_SPINNER_PENGELUARAN = "CREATE TABLE IF NOT EXISTS pengeluaran_spinner(id_s_pengeluaran INTEGER PRIMARY KEY AUTOINCREMENT, nama_s_pengeluaran TEXT)";
    private static final String QUERY_SPINNER_PEMASUKAN_RUTIN = "CREATE TABLE IF NOT EXISTS pemasukan_rutin_spinner(id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT)";
    private static final String QUERY_SPINNER_PENGELUARAN_RUTIN = "CREATE TABLE IF NOT EXISTS pengeluaran_rutin_spinner(id_s_pengeluaran INTEGER PRIMARY KEY AUTOINCREMENT, nama_s_pengeluaran TEXT)";
    private static final String QUERY_HAPUS_TABEL_PEMASUKAN = "DROP TABLE IF EXISTS tabel_pemasukan";
    private static final String QUERY_HAPUS_TABEL_PENGELUARAN = "DROP TABLE IF EXISTS tabel_pengeluaran";
    private static final String QUERY_HAPUS_TABEL_PEMASUKAN_RUTIN = "DROP TABLE IF EXISTS tabel_pemasukan_rutin";
    private static final String QUERY_HAPUS_TABEL_PENGELUARAN_RUTIN = "DROP TABLE IF EXISTS tabel_pengeluaran_rutin";
    private static final String QUERY_HAPUS_TABEL_PIN = "DROP TABLE IF EXISTS tabel_pin";
    private static final String QUERY_BUAT_TABEL_PIN = "CREATE TABLE IF NOT EXISTS tabel_pin(id INTEGER PRIMARY KEY AUTOINCREMENT, pin TEXT)";

    public SQLiteHelper(Context context) {
        super(context, NAMA_DATABASE, null, VERSI_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_BUAT_TABEL_PEMASUKAN);
        db.execSQL(QUERY_BUAT_TABEL_PENGELUARAN);
        db.execSQL(QUERY_BUAT_TABEL_PEMASUKAN_RUTIN);
        db.execSQL(QUERY_BUAT_TABEL_PENGELUARAN_RUTIN);
        db.execSQL(QUERY_SPINNER_PEMASUKAN);
        db.execSQL(QUERY_SPINNER_PENGELUARAN);
        db.execSQL(QUERY_SPINNER_PEMASUKAN_RUTIN);
        db.execSQL(QUERY_SPINNER_PENGELUARAN_RUTIN);
        db.execSQL(QUERY_BUAT_TABEL_PIN);
        System.out.println("tabel berhasil di buat");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QUERY_HAPUS_TABEL_PEMASUKAN);
        db.execSQL(QUERY_HAPUS_TABEL_PENGELUARAN);
        db.execSQL(QUERY_HAPUS_TABEL_PEMASUKAN_RUTIN);
        db.execSQL(QUERY_HAPUS_TABEL_PENGELUARAN_RUTIN);
        db.execSQL(QUERY_HAPUS_SPINNER_PEMASUKAN_RUTIN);
        db.execSQL(QUERY_HAPUS_SPINNER_PENGELUARAN_RUTIN);
        db.execSQL(QUERY_HAPUS_SPINNER_PEMASUKAN);
        db.execSQL(QUERY_HAPUS_SPINNER_PENGELUARAN);
        db.execSQL(QUERY_HAPUS_TABEL_PIN);
        onCreate(db);
        System.out.println("tabel berhasil di hapus");
    }


//  ========================== TAMBAH ========================================
    public void tambahPemasukan(String nama,int jumlah,String deskripsi,String tanggal,String jam){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama_pemasukan",nama);
        values.put("jumlah_pemasukan",jumlah);
        values.put("deskripsi_pemasukan",deskripsi);
        values.put("tanggal_pemasukan",tanggal);
        values.put("jam_pemasukan",jam);
        database.insert("tabel_pemasukan",null,values);
        database.close();
    }

    public void tambahPin(String pin){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pin",pin);
        database.insert("tabel_pin",null,values);
        database.close();
    }

    public void tambahPengeluaran(String nama,int jumlah,String deskripsi,String tanggal,String jam){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama_pengeluaran",nama);
        values.put("jumlah_pengeluaran",jumlah);
        values.put("deskripsi_pengeluaran",deskripsi);
        values.put("tanggal_pengeluaran",tanggal);
        values.put("jam_pengeluaran",jam);
        database.insert("tabel_pengeluaran",null,values);
        database.close();
    }

    public void tambahSpinnerPemasukan(String nama){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama",nama);
        database.insert("pemasukan_spinner",null,values);
        database.close();
    }

    public void tambahPemasukanRutin(String nama,int jumlah,String deskripsi,String tanggal,String jam){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama_pemasukan",nama);
        values.put("jumlah_pemasukan",jumlah);
        values.put("deskripsi_pemasukan",deskripsi);
        values.put("tanggal_pemasukan",tanggal);
        values.put("jam_pemasukan",jam);
        database.insert("tabel_pemasukan_rutin",null,values);
        database.close();
    }
    public void tambahPengeluaranRutin(String nama,int jumlah,String deskripsi,String tanggal,String jam){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama_pengeluaran",nama);
        values.put("jumlah_pengeluaran",jumlah);
        values.put("deskripsi_pengeluaran",deskripsi);
        values.put("tanggal_pengeluaran",tanggal);
        values.put("jam_pengeluaran",jam);
        database.insert("tabel_pengeluaran_rutin",null,values);
        database.close();
    }

    public void tambahSpinnerPengeluaran(String nama){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama_s_pengeluaran",nama);
        database.insert("Pengeluaran_spinner",null,values);
        database.close();
    }

    public void tambahSpinnerPemasukanRutin(String nama){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama",nama);
        database.insert("pemasukan_rutin_spinner",null,values);
        database.close();
    }

    public void tambahSpinnerPengeluaranRutin(String nama){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama_s_pengeluaran",nama);
        database.insert("Pengeluaran_rutin_spinner",null,values);
        database.close();
    }
//    =========================================================================

//    ========================== TAMPIL =======================================
    public ArrayList<HashMap<String,String>> tampilSemuaPemasukan(){
        ArrayList<HashMap<String,String>> arrayListPemasukan = new ArrayList<>();
//        String query = "SELECT SUM(jumlah_pemasukan) as total from tabel_pemasukan";
        SQLiteDatabase database = this.getWritableDatabase();
//        database.execSQL(query);


        Cursor cursor = database.rawQuery("SELECT * FROM tabel_pemasukan",null);

        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> hashMapPemasukan = new HashMap<>();

                hashMapPemasukan.put("id_pemasukan",cursor.getString(0));
                hashMapPemasukan.put("nama_pemasukan",cursor.getString(1));
                hashMapPemasukan.put("jumlah_pemasukan",cursor.getString(2));
                hashMapPemasukan.put("deskripsi_pemasukan",cursor.getString(3));
                hashMapPemasukan.put("tanggal_pemasukan",cursor.getString(4));
                hashMapPemasukan.put("jam_pemasukan",cursor.getString(5));

                arrayListPemasukan.add(hashMapPemasukan);
            } while (cursor.moveToNext());
        }
        return arrayListPemasukan;
    }

    public ArrayList<HashMap<String,String>> tampilSemuaPemasukanRutin(){
        ArrayList<HashMap<String,String>> arrayListPemasukanRutin = new ArrayList<>();
//        String query = "SELECT SUM(jumlah_pemasukan) as total from tabel_pemasukan";
        SQLiteDatabase database = this.getWritableDatabase();
//        database.execSQL(query);


        Cursor cursor = database.rawQuery("SELECT * FROM tabel_pemasukan_rutin",null);

        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> hashMapPemasukan = new HashMap<>();

                hashMapPemasukan.put("id_pemasukan",cursor.getString(0));
                hashMapPemasukan.put("nama_pemasukan",cursor.getString(1));
                hashMapPemasukan.put("jumlah_pemasukan",cursor.getString(2));
                hashMapPemasukan.put("deskripsi_pemasukan",cursor.getString(3));
                hashMapPemasukan.put("tanggal_pemasukan",cursor.getString(4));
                hashMapPemasukan.put("jam_pemasukan",cursor.getString(5));

                arrayListPemasukanRutin.add(hashMapPemasukan);
            } while (cursor.moveToNext());
        }
        return arrayListPemasukanRutin;
    }

    public ArrayList<HashMap<String,String>> tampilUnion(){
        ArrayList<HashMap<String,String>> arrayListPemasukan = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM tabel_pemasukan UNION ALL SELECT * FROM tabel_pengeluaran",null);

        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> hashMapPemasukan = new HashMap<>();

                hashMapPemasukan.put("id_pemasukan",cursor.getString(0));
                hashMapPemasukan.put("nama_pemasukan",cursor.getString(1));
                hashMapPemasukan.put("jumlah_pemasukan",cursor.getString(2));
                hashMapPemasukan.put("deskripsi_pemasukan",cursor.getString(3));
                hashMapPemasukan.put("tanggal_pemasukan",cursor.getString(4));
                hashMapPemasukan.put("jam_pemasukan",cursor.getString(5));

                arrayListPemasukan.add(hashMapPemasukan);
            } while (cursor.moveToNext());
        }
        return arrayListPemasukan;
    }

    public ArrayList<HashMap<String,String>> tampilUnionTanggal(String tanggal){
        ArrayList<HashMap<String,String>> arrayListPemasukan = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM tabel_pemasukan WHERE tanggal_pemasukan ='"+tanggal+"' UNION ALL SELECT * FROM tabel_pengeluaran WHERE tanggal_pengeluaran ='"+tanggal+"'",null);

        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> hashMapPemasukan = new HashMap<>();

                hashMapPemasukan.put("id_pemasukan",cursor.getString(0));
                hashMapPemasukan.put("nama_pemasukan",cursor.getString(1));
                hashMapPemasukan.put("jumlah_pemasukan",cursor.getString(2));
                hashMapPemasukan.put("deskripsi_pemasukan",cursor.getString(3));
                hashMapPemasukan.put("tanggal_pemasukan",cursor.getString(4));
                hashMapPemasukan.put("jam_pemasukan",cursor.getString(5));

                arrayListPemasukan.add(hashMapPemasukan);
            } while (cursor.moveToNext());
        }
        return arrayListPemasukan;
    }

    public ArrayList<HashMap<String,String>> tampilSemuaPengeluaran(){
        ArrayList<HashMap<String,String>> arrayListPengeluaran = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM tabel_pengeluaran",null);

        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> hashMapPengeluaran = new HashMap<>();

                hashMapPengeluaran.put("id_pengeluaran", cursor.getString(0));
                hashMapPengeluaran.put("nama_pengeluaran", cursor.getString(1));
                hashMapPengeluaran.put("jumlah_pengeluaran", cursor.getString(2));
                hashMapPengeluaran.put("deskripsi_pengeluaran", cursor.getString(3));
                hashMapPengeluaran.put("tanggal_pengeluaran",cursor.getString(4));
                hashMapPengeluaran.put("jam_pengeluaran",cursor.getString(5));

                arrayListPengeluaran.add(hashMapPengeluaran);
            } while (cursor.moveToNext());
        }
        return arrayListPengeluaran;
    }

    public ArrayList<HashMap<String,String>> tampilSemuaPengeluaranRutin(){
        ArrayList<HashMap<String,String>> arrayListPengeluaran = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM tabel_pengeluaran_rutin",null);

        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> hashMapPengeluaran = new HashMap<>();

                hashMapPengeluaran.put("id_pengeluaran", cursor.getString(0));
                hashMapPengeluaran.put("nama_pengeluaran", cursor.getString(1));
                hashMapPengeluaran.put("jumlah_pengeluaran", cursor.getString(2));
                hashMapPengeluaran.put("deskripsi_pengeluaran", cursor.getString(3));
                hashMapPengeluaran.put("tanggal_pengeluaran",cursor.getString(4));
                hashMapPengeluaran.put("jam_pengeluaran",cursor.getString(5));

                arrayListPengeluaran.add(hashMapPengeluaran);
            } while (cursor.moveToNext());
        }
        return arrayListPengeluaran;
    }

    public ArrayList<HashMap<String,String>> getAllSpinerPemasukan(){
        ArrayList<HashMap<String,String>> labels;
        labels =  new ArrayList<>();
        String query = "SELECT * FROM pemasukan_spinner";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<>();
                map.put("id",cursor.getString(0));
                map.put("nama",cursor.getString(1));
                labels.add(map);
            }while (cursor.moveToNext());
        }
        return labels;
    }

    public ArrayList<HashMap<String,String>> getAllPin(){
        ArrayList<HashMap<String,String>> labels;
        labels =  new ArrayList<>();
        String query = "SELECT * FROM tabel_pin";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<>();
                map.put("id",cursor.getString(0));
                map.put("pin",cursor.getString(1));
                labels.add(map);
            }while (cursor.moveToNext());
        }
        return labels;
    }

    public ArrayList<HashMap<String,String>> findPin(String pin){
        ArrayList<HashMap<String,String>> labels;
        labels =  new ArrayList<>();
        String query = "SELECT * FROM tabel_pin WHERE pin='"+pin+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<>();
                map.put("id",cursor.getString(0));
                map.put("pin",cursor.getString(1));
                labels.add(map);
            }while (cursor.moveToNext());
        }
        return labels;
    }

    public ArrayList<HashMap<String,String>> getAllSpinerPengeluaran(){
        ArrayList<HashMap<String,String>> labels;
        labels =  new ArrayList<>();
        String query = "SELECT * FROM pengeluaran_spinner";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<>();
                map.put("id_s_pengeluaran",cursor.getString(0));
                map.put("nama_s_pengeluaran",cursor.getString(1));
                labels.add(map);
            }while (cursor.moveToNext());
        }
        return labels;
    }

    public ArrayList<HashMap<String,String>> getAllSpinerPemasukanRutin(){
        ArrayList<HashMap<String,String>> labels;
        labels =  new ArrayList<>();
        String query = "SELECT * FROM pemasukan_rutin_spinner";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<>();
                map.put("id",cursor.getString(0));
                map.put("nama",cursor.getString(1));
                labels.add(map);
            }while (cursor.moveToNext());
        }
        return labels;
    }

    public ArrayList<HashMap<String,String>> getAllSpinerPengeluaranRutin(){
        ArrayList<HashMap<String,String>> labels;
        labels =  new ArrayList<>();
        String query = "SELECT * FROM pengeluaran_rutin_spinner";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<>();
                map.put("id_s_pengeluaran",cursor.getString(0));
                map.put("nama_s_pengeluaran",cursor.getString(1));
                labels.add(map);
            }while (cursor.moveToNext());
        }
        return labels;
    }

    public ArrayList<HashMap<String,String>> pengeluaranSering(){
        ArrayList<HashMap<String,String>> ps;
        ps = new ArrayList<>();
        String query = "select nama_pengeluaran,count(nama_pengeluaran) as n from tabel_pengeluaran\n" +
                "group by nama_pengeluaran\n" +
                "order by n desc\n" +
                "limit 1";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<>();
                map.put("nama_pengeluaran",cursor.getString(0));
                ps.add(map);
            }while (cursor.moveToFirst());
        }
        return ps;
    }

//    ==============================================================================================

//    ======================================= UPDATE ===============================================

    public int updatePemasukan(int id,String nama,int jumlah,String deskripsi){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues recordPemasukan = new ContentValues();
        System.out.println("ini sqlietehelper");
        System.out.println(id);
        System.out.println(nama);
        System.out.println(jumlah);
        System.out.println(deskripsi);

        recordPemasukan.put("nama_pemasukan", nama);
        recordPemasukan.put("jumlah_pemasukan",jumlah);
        recordPemasukan.put("deskripsi_pemasukan",deskripsi);
        return database.update("tabel_pemasukan",recordPemasukan,"id_pemasukan="+ id,null);
    }

    public int updatePengeluaran(int id,String nama,int jumlah,String deskripsi){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues recordPengeluaran = new ContentValues();
        System.out.println("ini sqlietehelper");
        System.out.println(id);
        System.out.println(nama);
        System.out.println(jumlah);
        System.out.println(deskripsi);

        recordPengeluaran.put("nama_pengeluaran", nama);
        recordPengeluaran.put("jumlah_pengeluaran", jumlah);
        recordPengeluaran.put("deskripsi_pengeluaran", deskripsi);
        return database.update("tabel_pengeluaran",recordPengeluaran,"id_pengeluaran="+ id,null);
    }

//    ==============================================================================================

//    ======================================== HAPUS ===============================================

    public void hapusPemasukan(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM  tabel_pemasukan WHERE id_pemasukan='" + id + "'");
        database.close();
    }

    public void hapusPemasukanRutin(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM  tabel_pemasukan_rutin WHERE id_pemasukan='" + id + "'");
        database.close();
    }

    public void hapusPengeluaran(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM  tabel_pengeluaran WHERE id_pengeluaran='" + id + "'");
        database.close();
    }

    public void hapusPengeluaranRutin(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM  tabel_pengeluaran_rutin WHERE id_pengeluaran='" + id + "'");
        database.close();
    }

    public void hapusKategoriPemasukan(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM  pemasukan_spinner WHERE id='" + id + "'");
        database.close();
    }

    public void hapusKategoriPemasukanRutin(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM  pemasukan_rutin_spinner WHERE id='" + id + "'");
        database.close();
    }

    public void hapusKategoriPengeluaran(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM  pengeluaran_spinner WHERE id_s_pengeluaran='" + id + "'");
        database.close();
    }

    public void hapusKategoriPengeluaranRutin(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM  pengeluaran_rutin_spinner WHERE id='" + id + "'");
        database.close();
    }
//    ==============================================================================================

    public HashMap<String, String> tampilPemasukanBerdasarkanId(int id) {
        SQLiteDatabase database = this.getReadableDatabase();

        HashMap<String, String> hasMapPemasukan = new HashMap<>();
        Cursor cursor = database.rawQuery("SELECT * FROM tabel_pemasukan WHERE id_pemasukan=" + id + "", null);

        if (cursor.moveToFirst()) {
            do {
                hasMapPemasukan.put("id_pemasukan", cursor.getString(0));
                hasMapPemasukan.put("nama_pemasukan", cursor.getString(1));
                hasMapPemasukan.put("jumlah_pemasukan", cursor.getString(2));
                hasMapPemasukan.put("deskripsi_pemasukan", cursor.getString(3));
            } while (cursor.moveToNext());
        }

        return hasMapPemasukan;
    }

    public HashMap<String, String> tampilPengeluaranBerdasarkanId(int id) {
        SQLiteDatabase database = this.getReadableDatabase();

        HashMap<String, String> hasMapPengeluaran = new HashMap<>();
        Cursor cursor = database.rawQuery("SELECT * FROM tabel_pengeluaran WHERE id_pengeluaran=" + id + "", null);

        if (cursor.moveToFirst()) {
            do {
                hasMapPengeluaran.put("id_pengeluaran", cursor.getString(0));
                hasMapPengeluaran.put("nama_pengeluaran", cursor.getString(1));
                hasMapPengeluaran.put("jumlah_pengeluaran", cursor.getString(2));
                hasMapPengeluaran.put("deskripsi_pengeluaran", cursor.getString(3));
            } while (cursor.moveToNext());
        }

        return hasMapPengeluaran;
    }

//    ==============================================================================================

//    =================== spinner===================================================================
    public List<String> getAllSpinnerPemasukan(){
        List<String> pemasukan_s = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM pemasukan_spinner";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                pemasukan_s.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return pemasukan_s;
    }

    public List<String> getAllSpinnerPemasukanRutin(){
        List<String> pemasukan_s = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM pemasukan_rutin_spinner";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                pemasukan_s.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return pemasukan_s;
    }

    public List<String> getAllSpinnerPengeluaran(){
        List<String> pengeluaran_s = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM pengeluaran_spinner";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                pengeluaran_s.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return pengeluaran_s;
    }

    public List<String> getAllSpinnerPengeluaranRutin(){
        List<String> pengeluaran_s = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM pengeluaran_rutin_spinner";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                pengeluaran_s.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return pengeluaran_s;
    }

    public List<String> spinnerPin(){
        List<String> pengeluaran_s = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT * FROM tabel_pin";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                pengeluaran_s.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return pengeluaran_s;
    }

    public List<String> getAllSpinnerTanggal(){
        List<String> pengeluaran_s = new ArrayList<String>();

        // Select All Query
        String selectQuery = "select tanggal_pemasukan as b from tabel_pemasukan union select tanggal_pengeluaran as b from tabel_pengeluaran group by b";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                pengeluaran_s.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return pengeluaran_s;
    }


}
