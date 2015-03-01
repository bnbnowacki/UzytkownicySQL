package com.example.nowacki.uzytkownicysql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class DeleteActivity extends ActionBarActivity {
    EditText textNazwa, textHaslo;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        textNazwa=(EditText)findViewById(R.id.editNazwa);
        textHaslo=(EditText)findViewById(R.id.editHaslo);
        context = getApplicationContext();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
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

    public void ClickUsun_Delete(View view) {
        SQLOperacje dbHelper = new SQLOperacje(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String nazwa = textNazwa.getText().toString();
        String haslo = textHaslo.getText().toString();
        String[] kolumny={SQLContract.SQLDane.COLUMN_NAME_NAZWA, SQLContract.SQLDane.COLUMN_NAME_HASLO};
        String where = SQLContract.SQLDane.COLUMN_NAME_NAZWA+" = ?";
        String[] whereArgs = {nazwa};
        Cursor c = db.query(SQLContract.SQLDane.TABLE_NAME, kolumny, where, whereArgs, null, null, null);

        c.moveToFirst();
        if(c.isNull(0)){
            Toast.makeText(context, "Podałeś złą nazwę użytkownika", Toast.LENGTH_LONG).show();
        }else if(haslo.equals(c.getString(1))){
            db.delete(SQLContract.SQLDane.TABLE_NAME, where, whereArgs);
            finish();
        }else{
            Toast.makeText(context, "Podałeś błędne hasło lub nazwę użytkownika", Toast.LENGTH_LONG).show();
            textNazwa.setText("");
            textHaslo.setText("");
        }

    }
}
