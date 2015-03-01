package com.example.nowacki.uzytkownicysql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class ChangePassActivity extends ActionBarActivity {
    EditText textStare, textNowe, textNoweConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        textStare=(EditText)findViewById(R.id.editStare);
        textNowe=(EditText)findViewById(R.id.editNowe);
        textNoweConf=(EditText)findViewById(R.id.editNoweConf);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_pass, menu);
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

    public void ClickZmien(View view) {
        SQLOperacje dbHelper = new SQLOperacje(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] kolumny={SQLContract.SQLDane.COLUMN_NAME_NAZWA, SQLContract.SQLDane.COLUMN_NAME_HASLO};
        String where = SQLContract.SQLDane.COLUMN_NAME_NAZWA+" = ?";
        String[] whereArgs = {SQLContract.zalogowany};
        String stare = textStare.getText().toString();
        String nowe = textNowe.getText().toString();
        String noweConf = textNoweConf.getText().toString();

        Cursor c = db.query(SQLContract.SQLDane.TABLE_NAME, kolumny, where, whereArgs, null, null, null);

        c.moveToFirst();
        if(SQLContract.zalogowany==""){
            Toast.makeText(getApplicationContext(), "Zaloguj się najpierw!", Toast.LENGTH_LONG).show();
            finish();
        }else if(!(stare.equals(c.getString(1)))){
            Toast.makeText(getApplicationContext(), "Błędne hasło...", Toast.LENGTH_LONG).show();
        }else if(!(nowe.equals(noweConf))){
            Toast.makeText(getApplicationContext(), "Hasła nie zgadzają się...", Toast.LENGTH_LONG).show();
        }else{
            ContentValues values = new ContentValues();
            values.put(SQLContract.SQLDane.COLUMN_NAME_HASLO, nowe);
            db.update(SQLContract.SQLDane.TABLE_NAME, values, where, whereArgs);
            Toast.makeText(getApplicationContext(), "Hasło zostało zmienione pomyślnie.", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
