package com.example.nowacki.uzytkownicysql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;


public class UserListActivity extends ActionBarActivity {
    ListView list;
    SQLiteDatabase db;
    SQLOperacje dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        list=(ListView)findViewById(R.id.viewUsers);
        String[] kolumny = {SQLContract.SQLDane.COLUMN_NAME_ID, SQLContract.SQLDane.COLUMN_NAME_NAZWA};
        dbHelper = new SQLOperacje(getApplicationContext());
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(SQLContract.SQLDane.TABLE_NAME, kolumny, null, null, null, null, null);
        cursor.moveToFirst();
        int[] toViews = {R.id.textView1};
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getApplicationContext(),R.layout.list_item_layout, cursor, kolumny, toViews, 0);
        list.setAdapter(cursorAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_list, menu);
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

    //public void staryKod(){
        //lista=(TextView)findViewById(R.id.textLista);
        //dbHelper = new SQLOperacje(getApplicationContext());
        //db = dbHelper.getReadableDatabase();
        //String[] kolumny = {SQLContract.SQLDane.COLUMN_NAME_NAZWA, SQLContract.SQLDane.COLUMN_NAME_HASLO};
        //Cursor c = db.query(SQLContract.SQLDane.TABLE_NAME, kolumny, null, null, null, null, null);
        //c.moveToFirst();
        //while(c.moveToNext()){
        //    lista.setText(lista.getText()+"Login: "+c.getString(0)+" Hasło: "+c.getString(1)+"\n");
       // }
    //}
}
