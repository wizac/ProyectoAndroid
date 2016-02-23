package com.example.admin.proyectoandroid.InterfazUsuario;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin.proyectoandroid.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private boolean viewIsAtHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregarToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0).getItemId());
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

    private void seleccionarItem(int idView) {
        Fragment fragmento = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        String title = getString(R.string.app_name);

        switch (idView) {
            case R.id.opcion_inicio:
                fragmento= new FragmentInicio();
                title = "Inicio";
                viewIsAtHome = true;
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
}
