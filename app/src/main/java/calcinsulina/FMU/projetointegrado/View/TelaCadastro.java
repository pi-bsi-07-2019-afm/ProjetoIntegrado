package calcinsulina.FMU.projetointegrado.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import calcinsulina.FMU.projetointegrado.Model.Usuario;
import calcinsulina.FMU.projetointegrado.R;

public class TelaCadastro {

    MainActivity act;
    TelaPrincipal tela_principal;
    TextView txtCad;
    Button btnCadastrar;
    EditText edNome, edIdade, edPeso, edSensibFator, edDataNasc, edEmail;
    CheckBox cbEULA;

    public TelaCadastro (MainActivity act, TelaPrincipal tela_principal) {
        this.act = act;
        this.tela_principal = tela_principal;
    }

    public void CarregarTela() {
        act.setContentView(R.layout.tela_cadastro);
        txtCad = act.findViewById(R.id.txtCadastro);
        btnCadastrar = act.findViewById(R.id.btnCadastrar);
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
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(act);
                    dialogo.setTitle("Atenção");
                    dialogo.setMessage("As informações foram preenchidas corretamente?");
                    dialogo.setNegativeButton("Não", null);
                    dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String nome = edNome.getText().toString();
                            double peso = Double.parseDouble(edPeso.getText().toString());
                            String dataNasc = edDataNasc.getText().toString();
                            double fatorSensibilidade = Double.parseDouble(edSensibFator.getText().toString());
                            String email = edEmail.getText().toString();
                            String pattern = "dd-MM-yyyy";
                            DateFormat df = new SimpleDateFormat(pattern);
                            Date today = Calendar.getInstance().getTime();
                            String dataRegistro = df.format(today);

                            act.getaUsuario().add(new Usuario(nome, peso, dataNasc, fatorSensibilidade, email, dataRegistro));
                            act.tela_principal.CarregarTela();
                        }
                    });
                    dialogo.show();
                }
            });

        }
}