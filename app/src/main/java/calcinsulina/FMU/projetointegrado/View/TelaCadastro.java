package calcinsulina.FMU.projetointegrado.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

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
    EditText edNome, edPeso, edSensibFator, edDataNasc, edEmail;
    CheckBox cbEULA;
    String telaAnterior;

    public TelaCadastro (MainActivity act, String telaAnterior) {
        this.act = act;
        this.telaAnterior = telaAnterior;
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

        if(telaAnterior.equalsIgnoreCase("TelaConfig")){
            txtCad.setText("Alterar Cadastro");
            btnCadastrar.setText("Salvar");
            edNome.setText(act.getaUsuario().indexOf(1));
            edEmail.setText(act.getaUsuario().indexOf(5));
            edDataNasc.setText(act.getaUsuario().indexOf(3));
            edPeso.setText(act.getaUsuario().indexOf(2));
            edSensibFator.setText(act.getaUsuario().indexOf(4));
        }

        SimpleMaskFormatter SMDataNasc = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher maskDataNasc = new MaskTextWatcher(edDataNasc, SMDataNasc);
        edDataNasc.addTextChangedListener(maskDataNasc);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(act);
                    if(edNome.getText().toString().length() == 0){
                        ExibeMensagem("Informar o nome corretamente.");
                    }else if(edPeso.getText().length() == 0){
                        ExibeMensagem("Informar o peso corretamente.");
                    }else if(edDataNasc.getText().length() == 0 || edDataNasc.length() < 10){
                        ExibeMensagem("Informar a data de nascimento separando dia, mês e ano por barras. \n\n Exemplo: 01/04/1993");
                    }else if(edSensibFator.getText().length() == 0){
                        ExibeMensagem("Informar o fator de sensibilidade.");
                    }else if(edEmail.getText().length() == 0 || edEmail.getText().toString().indexOf("@") < 0) {
                        ExibeMensagem("Informar o E-Mail corretamente.");
                    }else if(!cbEULA.isChecked()){
                        ExibeMensagem("Aceitar os Termos de Uso.");
                    }else{
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
                                Toast.makeText(act, "Feito o cadastro.", Toast.LENGTH_SHORT).show();
                                act.tela_principal.CarregarTela();
                            }
                        });
                        dialogo.show();
                    }
                }
            });

        }
        public void ExibeMensagem(String mensagem){
            AlertDialog.Builder Dialogo = new AlertDialog.Builder(act);
                                Dialogo.setTitle("Atenção");
                                Dialogo.setMessage(mensagem);
                                Dialogo.setNeutralButton("OK", null);
                                Dialogo.show();
        }
}