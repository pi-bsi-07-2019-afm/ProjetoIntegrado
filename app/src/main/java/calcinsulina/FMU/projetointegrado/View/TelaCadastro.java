package calcinsulina.FMU.projetointegrado.View;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                    String nome = edNome.getText().toString();
                    double peso = Double.parseDouble(edPeso.getText().toString());
                    String dataNasc = edDataNasc.getText().toString();
                    double fatorSensibilidade = Double.parseDouble(edSensibFator.getText().toString());
                    String email = edEmail.getText().toString();

                    act.getaUsuario().add(new Usuario(nome, peso, dataNasc, fatorSensibilidade, email));
                    Toast.makeText(act, "Feito o cadastro.", Toast.LENGTH_SHORT).show();
                    act.tela_principal.CarregarTela();
                }
            });

        }
}