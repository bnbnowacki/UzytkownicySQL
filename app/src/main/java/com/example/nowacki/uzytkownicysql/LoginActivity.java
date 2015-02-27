package com.example.nowacki.uzytkownicysql;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {
    TextView login, haslo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(TextView)findViewById(R.id.editNazwa);
        haslo=(TextView)findViewById(R.id.editHaslo);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void ClickLoguj(View view) {
        SQLOperacje dbHelper = new SQLOperacje(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        boolean loginStatus = false;
        String textLogin = login.getText().toString();
        String textPass = haslo.getText().toString();
        String nazwa="";
        String[] kolumny = {SQLContract.SQLDane.COLUMN_NAME_NAZWA, SQLContract.SQLDane.COLUMN_NAME_HASLO};
        Cursor c = db.query(SQLContract.SQLDane.TABLE_NAME, kolumny, null, null, null, null, null);
        c.moveToFirst();
        do{
            if(textLogin.equals(c.getString(0))&& textPass.equals(c.getString(1))){
                loginStatus = true;
                nazwa = c.getString(0);
            }
        }while(c.moveToNext());
        if(loginStatus==true){
            Toast.makeText(getApplicationContext(), "Gratulacje! Zalogowałeś się jako: "+nazwa, Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(),"Sorry, błędne hasło lub nazwa...", Toast.LENGTH_SHORT).show();
        }
    }
}
