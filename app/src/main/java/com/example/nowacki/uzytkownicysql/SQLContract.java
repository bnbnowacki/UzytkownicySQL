package com.example.nowacki.uzytkownicysql;

import android.provider.BaseColumns;

/**
 * Created by Nowacki on 2015-02-26.
 */
public class SQLContract {
    public SQLContract() {}

    public static abstract class SQLDane implements BaseColumns{
        public static final String TABLE_NAME = "uzytkownicy";
        public static final String COLUMN_NAME_ID="_id";
        public static final String COLUMN_NAME_NAZWA="nazwa";
        public static final String COLUMN_NAME_HASLO="haslo";
        public static final String DATABASE_NAME="moja_baza";
    }
}
