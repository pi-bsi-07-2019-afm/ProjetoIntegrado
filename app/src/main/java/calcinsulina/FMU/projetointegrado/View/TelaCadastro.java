package calcinsulina.FMU.projetointegrado.View;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import calcinsulina.FMU.projetointegrado.R;

public class TelaCadastro extends AppCompatActivity {

    TextView txtCad;
    Button btnCadastrar;
    EditText edNome, edIdade, edPeso, edSensibFator;
    CheckBox cbEULA;

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
    }
}
