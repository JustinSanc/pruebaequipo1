package com.example.appgo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Ubicacion extends AppCompatActivity {
    TextView vRegistros;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion2);
        vRegistros = findViewById(R.id.tvUbicacion);

        SqlLocalizacion lugar = new SqlLocalizacion(this, "ubicaciones", null, 1);
        SQLiteDatabase db = lugar.getReadableDatabase();

        c = db.rawQuery("SELECT * FROM ubicaciones", null);
        StringBuilder registros = new StringBuilder();

        if (c != null && c.moveToFirst()) {
            do {
                Integer id = c.getInt(0);
                String calle = c.getString(1);
                Double latitud = c.getDouble(2);
                Double longitud = c.getDouble(3);
                registros.append("ID: ").append(id).append("\nDirección: ").append(calle)
                        .append("\nLatitud: ").append(latitud).append("\nLongitud: ").append(longitud)
                        .append("\n\n");
            } while (c.moveToNext());
            vRegistros.setText(registros.toString());
        } else {
            vRegistros.setText("No se encontraron registros.");
        }
        if (c != null) c.close();
        db.close();
    }
}
