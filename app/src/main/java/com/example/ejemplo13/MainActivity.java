package com.example.ejemplo13;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtcontactos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtcontactos = (TextView)findViewById(R.id.txtRegistro);


    }

    public void leerContactos(View view) {
        String sColumnas[] = { ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.TIMES_CONTACTED };
        Cursor cursorContactos = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, sColumnas, null, null,
                ContactsContract.Contacts.DISPLAY_NAME);
        StringBuffer datos = new StringBuffer();
        cursorContactos.moveToFirst();
        while (!cursorContactos.isAfterLast()) {
            String nombrecontacto = cursorContactos.getString(cursorContactos
                    .getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
            int numerollamadas = cursorContactos.getInt(cursorContactos
                    .getColumnIndexOrThrow(ContactsContract.Contacts.TIMES_CONTACTED));

            datos.append(nombrecontacto);
            datos.append(", Número de llamadas -> ");
            datos.append(numerollamadas);
            datos.append("\n");

            cursorContactos.moveToNext();
        }
        cursorContactos.close();
        txtcontactos.setText(datos);
    }

}

