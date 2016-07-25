package com.example.admin.proyectoandroid.InterfazUsuario;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Calendar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.widget.Toast;

import com.example.admin.proyectoandroid.InterfazUsuario.AlertDialogos.DialogoPerfil;
import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.InterfazUsuario.Servicios.LlenarDatosDiarios;
import com.example.admin.proyectoandroid.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private PendingIntent pendingIntent;
    private boolean viewIsAtHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();


        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.AM_PM,Calendar.PM);

        Intent myIntent = new Intent(MainActivity.this, LlenarDatosDiarios.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000 , pendingIntent);

        agregarToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            prepararDrawer(navigationView);

            // Seleccionar item por defecto
            int item = 0;
            if(getIntent().getExtras() != null && getIntent().getExtras().containsKey("ItemMenu")) {
                item = getIntent().getExtras().getInt("ItemMenu");
                seleccionarItem(navigationView.getMenu().getItem(item).getItemId());
                getIntent().removeExtra("ItemMenu");
            }
            else{
                seleccionarItem(navigationView.getMenu().getItem(item).getItemId());
            }
        }

        if(((AplicacionPrincipal)getApplication()).getMisionesActivas().size() == 0 ||
                ((AplicacionPrincipal)getApplication()).getUsuario().getNombre().equals("") ||
                ((AplicacionPrincipal)getApplication()).getUsuario().getNombre() == null) {
            dialogoPerfil();
        }
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem.getItemId());
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    public void seleccionarItem(int idView) {
        Fragment fragmento = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        String title = getString(R.string.app_name);

        switch (idView) {
            case R.id.opcion_inicio:
                fragmento= new FragmentInicio();
                title = "Inicio";
                viewIsAtHome = true;
                if(getIntent().getExtras() != null && getIntent().getExtras().containsKey("PosicionTab")){
                    Bundle args = new Bundle();
                    args.putInt("PosicionTab", getIntent().getExtras().getInt("PosicionTab"));
                    fragmento.setArguments(args);
                    getIntent().removeExtra("PosicionTab");
                }
                break;
            case R.id.opcion_cuenta:
                fragmento = new FragmentCuenta();
                title = "Mi Cuenta";
                viewIsAtHome = false;
                break;
            case R.id.opcion_logros:
                fragmento = new FragmentLogros();
                title = "Logros";
                viewIsAtHome = false;
                break;
            case R.id.opcion_creditos:
                fragmento = new FragmentCreditos();
                title = "Créditos";
                viewIsAtHome = false;
                break;
            case R.id.opcion_contacto:
                fragmento = new FragmentContacto();
                title = "Contacto";
                viewIsAtHome = false;
                break;
        }
        if (fragmento != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmento)
                    .addToBackStack(null)
                    .commit();
        }

        // Setear título actual
        setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (!viewIsAtHome) {
            seleccionarItem(R.id.opcion_inicio);
        } else {
            moveTaskToBack(true);
        }
    }


    public void dialogoPerfil(){
        DialogoPerfil dialogoPersonalizado = new DialogoPerfil();
        dialogoPersonalizado.show(getSupportFragmentManager(), "personalizado");
        dialogoPersonalizado.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        dialogoPersonalizado.setCancelable(false);
        android.app.Fragment frag = getFragmentManager().findFragmentByTag("personalizado");

        if (frag != null) {
            getFragmentManager().beginTransaction().remove(frag).commit();
        }
    }
}
