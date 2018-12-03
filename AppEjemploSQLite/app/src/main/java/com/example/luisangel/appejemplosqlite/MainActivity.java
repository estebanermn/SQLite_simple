package com.example.luisangel.appejemplosqlite;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.luisangel.appejemplosqlite.adapters.PersonaAdapter;
import com.example.luisangel.appejemplosqlite.sqlite.MetodoSQL;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvDatos;
    private FloatingActionButton fabplus;
    private PersonaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvDatos = (RecyclerView)findViewById(R.id.rvDatos);
        fabplus = (FloatingActionButton)findViewById(R.id.fabAgregar);
        adapter = new PersonaAdapter();
        rvDatos.setHasFixedSize(true);
        final GridLayoutManager layoutManager =
                new GridLayoutManager(this, 3);
        rvDatos.setLayoutManager(layoutManager);
        rvDatos.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        MetodoSQL metodoSQL = new MetodoSQL(getApplicationContext());
        adapter.agregar(metodoSQL.listarTodo());
    }
    @Override
    protected void onResume() {
        super.onResume();
        fabplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this,
                        registroActivity.class);
                startActivity(intent);
            }
        });
    }
}
