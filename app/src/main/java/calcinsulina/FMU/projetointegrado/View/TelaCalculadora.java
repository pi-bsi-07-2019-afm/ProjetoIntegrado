package calcinsulina.FMU.projetointegrado.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import calcinsulina.FMU.projetointegrado.R;

public class TelaCalculadora extends AppCompatActivity {

    Button btnCalcular;
//    EditText edPeso, edIdade;
    EditText edFSensibil, edGlicemAlvo, edGlicemObt, edCarboidrato, edBolus;
    //Futuramente este handler será retirado desta activity, ela só está presente para "concept"
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_calculo);
        //Remove a barra de título
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

//        edPeso = findViewById(R.id.edPeso);
//        edIdade = findViewById(R.id.edIdade);
        edFSensibil = findViewById(R.id.edFSensibil);
        edGlicemAlvo = findViewById(R.id.edGlicAlvo);
        edGlicemObt = findViewById(R.id.edGlicObt);
        edCarboidrato = findViewById(R.id.edCarboidrato);
        edBolus = findViewById(R.id.edBolus);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Peso, Idade, FatorSensibilidade, GlicemiaAlvo, GlicemiaObtida, Carboidrato, Bolus;

//                Peso = edPeso.getText().toString();
//                Idade = edIdade.getText().toString();
                FatorSensibilidade = edFSensibil.getText().toString();
                GlicemiaAlvo = edGlicemAlvo.getText().toString();
                GlicemiaObtida = edGlicemObt.getText().toString();
                Carboidrato = edCarboidrato.getText().toString();
                Bolus = edBolus.getText().toString();

                if(
//                        Peso.length() == 0 || Idade.length() == 0 ||
                        FatorSensibilidade.length() == 0 || GlicemiaAlvo.length() == 0 || GlicemiaObtida.length() == 0 || Carboidrato.length() == 0 || Bolus.length() == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "Por favor, Insira todas as informações", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    // Parte do cálculo

                    int Glicemia = Integer.parseInt(GlicemiaAlvo) - Integer.parseInt(GlicemiaObtida);
                    int CorrecaoInsul = Glicemia / Integer.parseInt(FatorSensibilidade);
                    int InsulinaAComer = Integer.parseInt(Carboidrato) / Integer.parseInt(Bolus);

                    final int NumeroDeDoses = CorrecaoInsul + InsulinaAComer;
                    Intent intent = new Intent(TelaCalculadora.this, TelaCarregando.class);
                    startActivity(intent);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast toast = Toast.makeText(getApplicationContext(), "Número de Doses: " + NumeroDeDoses, Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }, 16000);

                }

            }
        });
    }
}
