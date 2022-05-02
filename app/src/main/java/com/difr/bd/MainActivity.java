package com.difr.bd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargar();

        EditText usuario = findViewById(R.id.et_usuario);
        EditText comentario = findViewById(R.id.et_comentario);
        Button ingresar1 =(Button) findViewById(R.id.bn_ingresar1);
        Button ingresar2 =(Button) findViewById(R.id.bn_ingresar2);

        MySQLiteHelper dbHelper = new MySQLiteHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db!=null){
            //Acciones dentro de la BD
            Toast.makeText(this,"DB OPEN",Toast.LENGTH_LONG).show();
            ingresar1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String user = usuario.getText().toString();
                    String comment = comentario.getText().toString();
                        db.execSQL("INSERT INTO comments (user,comment,type) VALUES ('"+user+"','"+comment+"','execSQL');");
                        cargar();
                        usuario.setText("");
                        comentario.setText("");
                }
            });
            ingresar2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String user = usuario.getText().toString();
                    String comment = comentario.getText().toString();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("user",user);
                        contentValues.put("comment",comment);
                        contentValues.put("type","Insert()");
                        db.insert("comments",null,contentValues);
                        cargar();
                        usuario.setText("");
                        comentario.setText("");
                }
            });
        }

    }

    public void cargar(){
        MySQLiteHelper dbHelper = new MySQLiteHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db!=null){
            Cursor cursor = db.rawQuery("SELECT * FROM comments",null);
            int cantidad = cursor.getCount();
            int i = 0;
            String[]arreglo = new String[cantidad];
            if (cursor.moveToFirst()){
                do {
                    String linea = cursor.getInt(0)+"\n     USUARIO:  "+cursor.getString(1)+"\n     COMENTARIO:  "+cursor.getString(2)+"\n     TIPO:  "+cursor.getString(3);
                    arreglo[i] = linea;
                    i++;
                } while (cursor.moveToNext());
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arreglo);
            ListView lista = (ListView) findViewById(R.id.data);
            lista.setAdapter(arrayAdapter);
        }
    }

}