package com.example.veraj.aplicacionandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //private List<Fotografia> fotografias;
    private RecyclerView recycler;
    TextView compra;
    TextView venta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

      /*  RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.contentLayout);
        TextView texto1 = new TextView(this);
        texto1.setText("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        texto1.setX(200);
        texto1.setY(600);
        mainLayout.addView(texto1);*/


        /*Muy importante         re recupea el grupo recicle*/
        recycler = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linLayManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linLayManager);
        recycler.setHasFixedSize(true);

        //inicializarDatos();
        //initializarAdapter();
        buscarPelicula();
    }

    /*public void inicializarDatos(){
        fotografias = new ArrayList<>();
        fotografias.add(new Fotografia("Foto Autor: Javier Mendoza", R.drawable.arequipa));
        fotografias.add(new Fotografia("Foto Author: Alejandra Sotilla", R.drawable.lima));
        fotografias.add(new Fotografia("Foto Author: Fernando Fernandez", R.drawable.iquitos));
        fotografias.add(new Fotografia("Foto Author: Jose Carrillo", R.drawable.cusco));
        fotografias.add(new Fotografia("Foto Author: Milagros Gutierrez", R.drawable.paisaje));
    }

    public void initializarAdapter(){
        RecyclerAdaptador adapter = new RecyclerAdaptador(fotografias);
        recycler.setAdapter(adapter);
    }

*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        TextView nom = (TextView) findViewById(R.id.newUser);
        //ImageView ima = (ImageView)findViewById(R.id.US);

        if(nom != null){
            Bundle bundle = getIntent().getExtras();
            nom.setText(bundle.getString("key"));
            //ima.setImageDrawable();
        }

        getMenuInflater().inflate(R.menu.content, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            buscarPelicula();
        } else if (id == R.id.nav_gallery) {
            Intent myIntent = new Intent(ContentActivity.this, Cuadro.class);
            //myIntent.putExtra("key", texto.getText().toString()); //Optional parameters
            ContentActivity.this.startActivity(myIntent);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void buscarPelicula(){
        //EditText et = (EditText)findViewById(R.id.editText3);

        //String titulo = et.getText().toString();
        //if(!TextUtils.isEmpty(titulo)){
            String url = "http://code.staffsystems.us/webservices/tipo-de-cambio/serverside.php?work=get_sunat&mes=02&anho=2016";
            //String url = "http://dades.eicub.net/api/1/museusexposicions-visitants?Equipament="+titulo;
            new LoadFilmTask().execute(url);

        //}
    }
    private class  LoadFilmTask extends AsyncTask<String, Long, String> {
        protected String doInBackground(String... urls){
            try{
                return HttpRequest.get(urls[0]).accept("application/json").body();
            }catch (HttpRequest.HttpRequestException exception){
                return null;
            }

        }
        protected void onPostExecute(String response){
            Busqueda busqueda = getPeliculas(response);
            if(busqueda != null){
                mostrarPelicula(busqueda);
            }
        }
    }


    private  void mostrarPelicula(Busqueda busqueda){

        List<Dato> da= busqueda.getData();
        RecyclerAdaptador adaptador = new RecyclerAdaptador(da,getApplicationContext());
        recycler.setAdapter(adaptador);


        compra = (TextView)findViewById(R.id.preActualCompra);
        venta = (TextView)findViewById(R.id.precioActualVenta);
        int tamanio = da.size();

        compra.setText(busqueda.getData().get(tamanio - 1).getCompra().toString());
        venta.setText(busqueda.getData().get(tamanio - 1).getVenta().toString());

        /*List<pelicula> peliculas = busqueda.getSearch();
        RecyclerAdaptador adaptador = new RecyclerAdaptador(peliculas,getApplicationContext());
        recycler.setAdapter(adaptador);*/
    }

    //aqui nada
    private Busqueda getPeliculas(String json){
        Gson gson = new Gson();
        Busqueda busqueda = gson.fromJson(json,Busqueda.class);
        return  busqueda;
    }
    public void CompraDolares(View v){
        EditText soles = (EditText)findViewById(R.id.ComDolSoles);
        TextView dolares = (TextView)findViewById(R.id.ComDolTotal);


        Toast mensaje = Toast.makeText(getApplicationContext(),"La casilla no puede estar vacia", Toast.LENGTH_SHORT);
        mensaje.show();

        double cantida =  Double.parseDouble(soles.getText().toString()) / Double.parseDouble(compra.getText().toString());
        dolares.setText("$. "+cantida);





    }
    public void VentaDolares(View v){
        EditText dolares;
        TextView soles;
        dolares = (EditText)findViewById(R.id.VenDolDolares);
        soles = (TextView)findViewById(R.id.venDolToal);

        Toast mensaje = Toast.makeText(getApplicationContext(),"La casilla no puede estar vacia", Toast.LENGTH_SHORT);
        mensaje.show();

        double cantidad = Double.parseDouble(venta.getText().toString()) * Double.parseDouble(dolares.getText().toString());
        soles.setText("S/. " + cantidad);



    }
}
