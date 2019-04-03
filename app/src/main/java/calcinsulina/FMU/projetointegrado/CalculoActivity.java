package calcinsulina.FMU.projetointegrado;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculoActivity extends AppCompatActivity {

    Button btnCalcular;
    EditText edPeso, edIdade, edFSensibil, edGlicemAlvo, edGlicemObt, edCarboidrato;
    TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);
        //Remove a barra de título
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        edPeso = findViewById(R.id.edPeso);
        edIdade = findViewById(R.id.edIdade);
        edFSensibil = findViewById(R.id.edFSensibil);
        edGlicemAlvo = findViewById(R.id.edGlicAlvo);
        edGlicemObt = findViewById(R.id.edGlicObt);
        edCarboidrato = findViewById(R.id.edCarboidrato);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Peso, Idade, FatorSensibilidade, GlicemiaAlvo, GlicemiaObtida, Carboidrato;

                Peso = edPeso.getText().toString();
                Idade = edIdade.getText().toString();
                FatorSensibilidade = edFSensibil.getText().toString();
                GlicemiaAlvo = edGlicemAlvo.getText().toString();
                GlicemiaObtida = edGlicemObt.getText().toString();
                Carboidrato = edCarboidrato.getText().toString();

                int Glicemia = Integer.parseInt(GlicemiaAlvo) - Integer.parseInt(GlicemiaObtida);
                int CorrecaoInsul = Glicemia / Integer.parseInt(FatorSensibilidade);
                int InsulinaAComer = Integer.parseInt(Carboidrato) / 15;

                int Resultado = InsulinaAComer + CorrecaoInsul;

                txtResultado = findViewById(R.id.txtResultado);
                txtResultado.setText(Resultado + "Unidades");
            }
        });
    }
}
