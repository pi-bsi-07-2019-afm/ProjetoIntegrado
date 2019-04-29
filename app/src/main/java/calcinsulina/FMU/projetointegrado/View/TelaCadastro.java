package calcinsulina.FMU.projetointegrado.View;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import calcinsulina.FMU.projetointegrado.Model.Usuario;
import calcinsulina.FMU.projetointegrado.R;

public class TelaCadastro {

    int index;
    MainActivity act;
    TelaPrincipal tela_principal;
    TextView txtCad;
    Button btnCadastrar;
    EditText edNome, edIdade, edPeso, edSensibFator, edDataNasc, edEmail;
    CheckBox cbEULA;

    public TelaCadastro (MainActivity act, TelaPrincipal tela_principal) {
        this.tela_principal = tela_principal;
        this.act = act;
    }

    public void CarregarTela() {
        act.setContentView(R.layout.tela_cadastro);
        txtCad = act.findViewById(R.id.txtCadastro);
        btnCadastrar = act.findViewById(R.id.btnCalcular);
        edNome = act.findViewById(R.id.edNome);
        edIdade = act.findViewById(R.id.edIdade);
        edPeso = act.findViewById(R.id.edPeso);
        edSensibFator = act.findViewById(R.id.edSensibFator);
        cbEULA = act.findViewById(R.id.cbEULA);

        edEmail = act.findViewById(R.id.edEmail);
        edDataNasc = act.findViewById(R.id.edDataNasc);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(act);
                    dialog.setTitle("Atenção");
                    dialog.setMessage("As informações foram preenchidas corretamente ?");
                    dialog.setNegativeButton("Não", null);
                    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            act.getaUsuario().set(index, new Usuario(edNome.getText().toString(), Double.parseDouble(edPeso.getText().toString()),
                                    edDataNasc.getText().toString(), Double.parseDouble(edSensibFator.getText().toString()), edEmail.getText().toString()));
                        }
                    });
                }
            });

        }
}