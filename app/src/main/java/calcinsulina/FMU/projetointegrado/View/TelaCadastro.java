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

import calcinsulina.FMU.projetointegrado.Model.Usuario;
import calcinsulina.FMU.projetointegrado.R;

public class TelaCadastro {

    MainActivity act;
    TelaPrincipal tela_principal;
    TextView txtCad;
    Button btnCadastrar;
    EditText edNome, edPeso, edSensibFator, edDataNasc, edEmail;
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
                            String msgErro = "";

                            if (edNome.getText().toString().length() <= 0 ){
                                msgErro.concat("\n-Informar o nome corretamente.");
                            }

                            if (edPeso.getText().toString().length() <= 0){
                                msgErro.concat("\n-Informar o peso corretamente.");
                            }

                            String[] testaData = edDataNasc.getText().toString().split("/");
                            if (edDataNasc.getText().toString().length() <= 0 || testaData.length != 3){
                                if (testaData.length == 3){
                                    if (testaData[2].length() != 4){
                                        msgErro.concat("\n-Informar a data de nascimento separando dia, mês e ano por barras. \n\n Exemplo: 01/04/1993");
                                    }
                                }else{
                                    msgErro.concat("\n-Informar a data de nascimento.");
                                }
                            }

                            if (edSensibFator.getText().toString().length() <= 0){
                                msgErro.concat("\n-Informar o fator de sensibilidade.");
                            }

                            if (edEmail.getText().toString().length() <= 0 || edEmail.getText().toString().indexOf("@") < 0 ){
                                msgErro.concat("\n-Informar o e-mail corretamente.");
                            }

                            if (cbEULA.isChecked()){

                            String nome = edNome.getText().toString();
                            double peso = Double.parseDouble(edPeso.getText().toString());
                            String dataNasc = edDataNasc.getText().toString();
                            double fatorSensibilidade = Double.parseDouble(edSensibFator.getText().toString());
                            String email = edEmail.getText().toString();
                            act.getaUsuario().add(new Usuario(nome, peso, dataNasc, fatorSensibilidade, email));
                            Toast.makeText(act, "Feito o cadastro.", Toast.LENGTH_SHORT).show();
                            act.tela_principal.CarregarTela();

                            }else{
                                msgErro.concat("\n-Aceitar os termos de uso.");
                            }

                            if (msgErro.length() > 0){
                                msgErro = "Para prosseguir, é necessário: \n" + msgErro;
                                AlertDialog.Builder dialogoValida = new AlertDialog.Builder(act);
                                dialogoValida.setTitle("Atenção");
                                dialogoValida.setMessage(msgErro);
                                dialogoValida.setNeutralButton("Ok", null);
                                dialogoValida.show();
                            }
                        }
                    });
                    dialogo.show();
                }
            });

        }
}