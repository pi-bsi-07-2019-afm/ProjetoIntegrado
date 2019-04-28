package calcinsulina.FMU.projetointegrado.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import calcinsulina.FMU.projetointegrado.Model.Usuario;
import calcinsulina.FMU.projetointegrado.R;

public class TelaCadastro extends AppCompatActivity {

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    int index;
    MainActivity ma;
    TextView txtCad;
    Button btnCadastrar;
    EditText edNome, edIdade, edPeso, edSensibFator, edDataNasc, ;
    CheckBox cbEULA;
    String NomeUser;

    public TelaCadastro(MainActivity ma){
        this.ma = ma;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);
        //Remove a barra de título
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        txtCad = findViewById(R.id.txtCadastro);
        btnCadastrar = findViewById(R.id.btnCalcular);
        edNome = findViewById(R.id.edNome);
        edIdade = findViewById(R.id.edIdade);
        edPeso = findViewById(R.id.edPeso);
        edSensibFator = findViewById(R.id.edSensibFator);
        cbEULA = findViewById(R.id.cbEULA);
        btnCadastrar.setEnabled(false);

        if(ma.getaUsuario().size() > 0) {
            NomeUser = PesquisaNomeUsuario(ma.getaUsuario());
            Intent intent = new Intent(TelaCadastro.this, TelaPrincipal.class);
            startActivity(intent);
            Toast.makeText(ma, "Bem Vindo de Volta " + NomeUser, Toast.LENGTH_SHORT).show();
            System.out.println("USUÁRIO: " + NomeUser);
            finish();
        } else {
            btnCadastrar.setEnabled(true);
        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ma);
                dialog.setTitle("Atenção");
                dialog.setMessage("As informações foram preenchidas corretamente ?");
                dialog.setNegativeButton("Não", null);
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ma.getaUsuario().set(index, new Usuario(edNome.getText().toString(), Double.parseDouble(edPeso.getText().toString()),
                                edDataNasc.getText().toString(), Double.parseDouble(edSensibFator.getText().toString()), edEmail.getText().toString(), dateFormat.format(date)));
                    }
                });
            }
        });
    }

    public String PesquisaNomeUsuario(ArrayList<Usuario> array){
        return array.get(0).getNome();
    }
}
