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

import calcinsulina.FMU.projetointegrado.Model.Calculo;
import calcinsulina.FMU.projetointegrado.R;

public class TelaCalculadora  {

    MainActivity act;
    TelaPrincipal tela_principal;
    Button btnCalcular;
    EditText edFSensibil, edGlicemAlvo, edGlicemObt, edCarboidrato, edBolus;

    //Futuramente este handler será retirado desta activity, ela só está presente para "concept"
    Handler handler = new Handler();
    int index = 0;

    public TelaCalculadora(MainActivity act, TelaPrincipal tela_principal){
        this.act = act;
        this.tela_principal = tela_principal;
    }

    public void CarregarTela() {
        edFSensibil = act.findViewById(R.id.edFSensibil);
        edGlicemAlvo = act.findViewById(R.id.edGlicAlvo);
        edGlicemObt = act.findViewById(R.id.edGlicObt);
        edCarboidrato = act.findViewById(R.id.edCarboidrato);
        edBolus = act.findViewById(R.id.edBolus);

        btnCalcular = act.findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FatorSensibilidade, GlicemiaAlvo, GlicemiaObtida, Carboidrato, Bolus;

                FatorSensibilidade = edFSensibil.getText().toString();
                GlicemiaAlvo = edGlicemAlvo.getText().toString();
                GlicemiaObtida = edGlicemObt.getText().toString();
                Carboidrato = edCarboidrato.getText().toString();
                Bolus = edBolus.getText().toString();


                if(FatorSensibilidade.length() == 0 || GlicemiaAlvo.length() == 0 || GlicemiaObtida.length() == 0 || Carboidrato.length() == 0 || Bolus.length() == 0){
                    Toast toast = Toast.makeText(act.getApplicationContext(), "Por favor, Insira todas as informações", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    // Parte do cálculo

                    int Glicemia = Integer.parseInt(GlicemiaObtida) - Integer.parseInt(GlicemiaAlvo);
                    int CorrecaoInsul = Glicemia / Integer.parseInt(FatorSensibilidade);
                    int InsulinaAComer = Integer.parseInt(Carboidrato) / Integer.parseInt(Bolus);

                    final int NumeroDeDoses = CorrecaoInsul + InsulinaAComer;

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast toast = Toast.makeText(act.getApplicationContext(), "Número de Doses: " + NumeroDeDoses, Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }, 16000);

                }

            }
        });
    }
}
