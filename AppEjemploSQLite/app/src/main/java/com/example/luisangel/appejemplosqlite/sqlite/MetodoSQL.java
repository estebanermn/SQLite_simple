package com.example.luisangel.appejemplosqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.luisangel.appejemplosqlite.modelos.Persona;

import java.util.ArrayList;

public class MetodoSQL {

    private ManageOpenHelper conexion;

    public MetodoSQL(Context context){

        conexion = new ManageOpenHelper(context);
    }
    public ArrayList<Persona> listarTodo(){
        ArrayList<Persona> lista = new ArrayList<>();
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery
                ("select * from tb_persona", null);
        if(cursor.moveToFirst()){
            do {
                Persona persona = new Persona(
                        cursor.getInt(
                                cursor.getColumnIndex("id")),
                        cursor.getString(
                                cursor.getColumnIndex("nombre")),
                        cursor.getString(
                                cursor.getColumnIndex("apellido")),
                        cursor.getString(
                                cursor.getColumnIndex("genero"))
                );
                lista.add(persona);
            }while (cursor.moveToNext());
        }
        return lista;
    }
    public void agregarPersona(Persona persona){
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", persona.getNombre());
        contentValues.put("apellido", persona.getApellido());
        contentValues.put("genero", persona.getGenero());
        db.insert("tb_persona",null, contentValues);
    }
    public void actualizarPersona(Persona persona){
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", persona.getNombre());
        contentValues.put("apellido", persona.getApellido());
        contentValues.put("genero", persona.getGenero());
        db.update("tb_persona",
                contentValues,
                "id=?",
                new String[]{String.valueOf(persona.getId())});
    }
    public void eliminarPersona(int idPersona){
        SQLiteDatabase db = conexion.getWritableDatabase();
        db.delete("tb_persona",
                "id=?",
                new String[]{String.valueOf(idPersona)});
    }



}
