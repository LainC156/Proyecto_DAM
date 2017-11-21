package com.example.randallflagg.dmellitus2_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Randall Flagg on 19/10/2017.
 */

public class base_de_datos extends SQLiteOpenHelper {

    public base_de_datos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //aquí creamos las tablas paciente y doctor
        //db.execSQL("create table paciente(nombre_p text primary key, direccion_p text, telefono_p integer,
        // edad integer, peso integer, altura integer, " +
          //      "fdn text, medicamento text, correo_p text, usuario text, contraseña text)");
        db.execSQL("CREATE TABLE paciente_db ("+
                "paciente_id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                "nombre_p TEXT NOT NULL,"+
                "direccion_p TEXT NOT NULL,"+
                "telefono_p TEXT NOT NULL,"+
                "edad TEXT NOT NULL,"+
                "peso TEXT NOT NULL,"+
                "altura TEXT NOT NULL,"+
                "fdn TEXT NOT NULL,"+
                "medicamento TEXT NOT NULL,"+
                "correo_p TEXT NOT NULL,"+
                "usuario TEXT NOT NULL,"+
                "contraseña TEXT NOT NULL)");

        db.execSQL("CREATE TABLE doctor_db ("+
                "doctor_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                "nombre_d TEXT NOT NULL,"+
                "correo_d text NOT NULL,"+
                "direccion_d text NOT NULL,"+
                "telefono_d TEXT NOT NULL)");

        db.execSQL("CREATE TABLE mediciones ("+
                "med_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                "tipo TEXT NOT NULL,"+
                "tiempo TEXT NOT NULL,"+
                "nivel TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists paciente_db");
        db.execSQL("CREATE TABLE paciente_db ("+
                "paciente_id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                "nombre_p TEXT NOT NULL,"+
                "direccion_p TEXT NOT NULL,"+
                "telefono_p TEXT NOT NULL,"+
                "edad TEXT NOT NULL,"+
                "peso TEXT NOT NULL,"+
                "altura TEXT NOT NULL,"+
                "fdn TEXT NOT NULL,"+
                "medicamento TEXT NOT NULL,"+
                "correo_p TEXT NOT NULL,"+
                "usuario TEXT NOT NULL,"+
                "contraseña TEXT NOT NULL)");
        db.execSQL("drop table if exists doctor");
        db.execSQL("CREATE TABLE doctor ("+
                "doctor_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                "nombre_d TEXT NOT NULL,"+
                "correo_d text NOT NULL,"+
                "direccion_d text NOT NULL,"+
                "telefono_d TEXT NOT NULL)");
        //db.execSQL("drop table if exists doctor");
        //db.execSQL("create table paciente(nombre text primary key, direccion text, telefono integer,  edad integer, peso integer, altura integer, " +
                //"fdn text, medicamento text, correo text, usuario text, contraseña text)");
        //db.execSQL("create table doctor(nombre text primary key, correo text, direccion text, telefono_d integer");
    }
}
