package com.example.nowacki.uzytkownicysql;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegistrationActivity extends ActionBarActivity {
    EditText textLogin, textPass, textPassConfirm;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        textLogin = (EditText)findViewById(R.id.editNazwa);
        textPass = (EditText)findViewById(R.id.editHaslo);
        textPassConfirm = (EditText)findViewById(R.id.editHasloConf);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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

    public void ClickRejestruj(View view) {
        String login = textLogin.getText().toString();
        String pass = textPass.getText().toString();
        String passConf = textPassConfirm.getText().toString();

        if(!(pass.equals(passConf))){
            Toast.makeText(getApplicationContext(), "Hasła się nie zgadzają.", Toast.LENGTH_SHORT).show();
            textPass.setText("");
            textPassConfirm.setText("");
        }else{
            SQLOperacje DB = new SQLOperacje(context);
            DB.onInsert(DB, login, pass);
            Toast.makeText(getApplicationContext(), "Utworzono użytkownika o nazwie "+login, Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
