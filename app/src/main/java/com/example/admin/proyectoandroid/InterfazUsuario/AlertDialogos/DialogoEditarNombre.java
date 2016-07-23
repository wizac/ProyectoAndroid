package com.example.admin.proyectoandroid.InterfazUsuario.AlertDialogos;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.InterfazUsuario.FragmentCreditos;
import com.example.admin.proyectoandroid.InterfazUsuario.FragmentCuenta;
import com.example.admin.proyectoandroid.InterfazUsuario.FragmentEstadisticas;
import com.example.admin.proyectoandroid.InterfazUsuario.FragmentInicio;
import com.example.admin.proyectoandroid.InterfazUsuario.FragmentPerfil;
import com.example.admin.proyectoandroid.R;

import org.w3c.dom.Text;

public class DialogoEditarNombre extends DialogFragment  {

    public DialogoEditarNombre() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogo_editar, container);

        int maxLength = 20;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);

        Button btnguardar = (Button) view.findViewById(R.id.boton_guardar);
        Button btncancelar = (Button) view.findViewById(R.id.boton_cancelar);
        final TextView tituloDialog = (TextView) view.findViewById(R.id.tv_titulo_dialog_edit);
        final EditText nombre = (EditText) view.findViewById(R.id.campo_edit);
        tituloDialog.setText("Puedes modificar el nombre de tu perfil");
        nombre.setHint("nombre");
        nombre.setFilters(fArray);
        nombre.setText(((AplicacionPrincipal) getActivity().getApplication()).getUsuario().getNombre());

        btnguardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nombreNuevo = nombre.getText().toString();
                        String email = ((AplicacionPrincipal) getActivity().getApplication()).getUsuario().getEmail();
                        if (!nombreNuevo.equals("") && nombreNuevo != null) {
                            ((AplicacionPrincipal) getActivity().getApplication()).insertarUsuario(nombreNuevo, email);
                            Fragment fragmento = new FragmentCuenta();
                            Bundle args = new Bundle();
                            args.putInt("varOrdenTabCuenta", 0);
                            fragmento.setArguments(args);
                            if (fragmento != null) {
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.contenedor_principal, fragmento);
                                ft.commit();
                            }
                            dismiss();
                        } else {
                            dismiss();
                        }
                    }
                }

        );

        btncancelar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }

        );

        return view;
    }

}

