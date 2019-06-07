package calcinsulina.FMU.projetointegrado.View;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import calcinsulina.FMU.projetointegrado.R;

import calcinsulina.FMU.projetointegrado.Model.Calculo;

public class TelaCalculadora {

    MainActivity act;
    TelaPrincipal tela_principal;
    Button btnCalcular,btnVoltarCalc,btnAdicionar,btnEditar;
    EditText edFSensibil, edGlicemAlvo, edGlicemObt, edCarboidrato, edBolus;

    Calculo objCalculo;
    public boolean flagInserirDadosNoForm;

    //Futuramente este handler será retirado desta activity, ela só está presente para "concept"
    Handler handler = new Handler();
    int index = 0;

    public TelaCalculadora(MainActivity act, TelaPrincipal tela_principal) {
        this.act = act;
        this.tela_principal = tela_principal;
    }

    public void setObjCalculo(Calculo objCalculo){
        this.objCalculo = objCalculo;
    }

    public void saveObjCalculo(){
        double GlicemiaAlvo, GlicemiaObtida, Carboidrato, Bolus;
        try {
            GlicemiaAlvo = act.getDoubleFromEd(edGlicemAlvo.getText().toString());
            GlicemiaObtida = act.getDoubleFromEd(edGlicemObt.getText().toString());
            Carboidrato = act.getDoubleFromEd(edCarboidrato.getText().toString());
            Bolus = act.getDoubleFromEd(edBolus.getText().toString());
            objCalculo.setGlicemiaAlvo(GlicemiaAlvo);
            objCalculo.setGlicemiaObtida(GlicemiaObtida);
            objCalculo.setTotalCarb(Carboidrato);
            objCalculo.setQuantCarbPorUnidInsulina(Bolus);
        }catch (Exception x){
            //dummy;
        }
    }

    public void setFormCalculadora(){
        edGlicemAlvo = act.findViewById(R.id.edGlicAlvo);
        edGlicemObt = act.findViewById(R.id.edGlicObt);
        edCarboidrato = act.findViewById(R.id.txtCarboidratos);
        edBolus = act.findViewById(R.id.edBolus);
        try {
            edGlicemAlvo.setText(act.getStringFromObjPropDouble(objCalculo.getGlicemiaAlvo()));
            edGlicemObt.setText(act.getStringFromObjPropDouble(objCalculo.getGlicemiaObtida()));
            edCarboidrato.setText(String.valueOf(objCalculo.getTotalCarb()));
            edBolus.setText(act.getStringFromObjPropDouble(objCalculo.getQuantCarbPorUnidInsulina()));
        }catch (Exception e){
            //dummy;
        }

    }

    public void incrementaTotalCarboidrato(double carbASomar){
        objCalculo.setTotalCarb(objCalculo.getTotalCarb() + carbASomar);
    }

    public void CarregarTela() {
        act.setContentView(R.layout.tela_calculo);
        btnCalcular = act.findViewById(R.id.btnCalcular);
        edGlicemAlvo = act.findViewById(R.id.edGlicAlvo);
        edGlicemObt = act.findViewById(R.id.edGlicObt);
        edCarboidrato = act.findViewById(R.id.txtCarboidratos);
        edBolus = act.findViewById(R.id.edBolus);
        btnVoltarCalc = act.findViewById(R.id.btnVoltarCalc);
        btnAdicionar = act.findViewById(R.id.btnAdicionar);
        btnEditar = act.findViewById(R.id.btnEditar);

        if(flagInserirDadosNoForm){
            setFormCalculadora();
        }else{
            int novoId = act.getaCalculo().size()+1;
            objCalculo = new Calculo(novoId,0.0,0.0,0.0,new int[0],new double[0],0.0,0);
            this.edCarboidrato.setText(String.valueOf(objCalculo.getTotalCarb()));
        }

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double FatorSensibilidade, GlicemiaAlvo, GlicemiaObtida, Carboidrato, Bolus;

                FatorSensibilidade = act.getaUsuario().get(0).getFatorSensibilidade();
                GlicemiaAlvo = Double.parseDouble(edGlicemAlvo.getText().toString());
                GlicemiaObtida = Double.parseDouble(edGlicemObt.getText().toString());
                Carboidrato = Double.parseDouble(edCarboidrato.getText().toString());
                Bolus = Double.parseDouble(edBolus.getText().toString());

                if ((FatorSensibilidade.toString().length()) == 0 || (GlicemiaAlvo.toString().length()) == 0 || (GlicemiaObtida.toString().length()) == 0 || (Carboidrato.toString().length()) == 0 || (Bolus.toString().length()) == 0 || FatorSensibilidade <= 0 || FatorSensibilidade > 300 || GlicemiaAlvo <= 0 || GlicemiaAlvo >= 300 || GlicemiaObtida <= 0 || GlicemiaObtida >= 300 || Carboidrato <= 0 || Bolus <= 0) {
                    Toast toast = Toast.makeText(act.getApplicationContext(), "Por favor, Insira todas as informações corretamente.", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    // Parte do cálculo
                    double Glicemia = GlicemiaObtida - GlicemiaAlvo;
                    double CorrecaoInsul = Glicemia / FatorSensibilidade;
                    double InsulinaAComer =  Carboidrato / Bolus;
                    final int NumeroDeDoses =  (int) (CorrecaoInsul + InsulinaAComer);
                    act.tela_carregando.CarregarTela(NumeroDeDoses);

//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast toast = Toast.makeText(act.getApplicationContext(), "Número de Doses: " + NumeroDeDoses, Toast.LENGTH_LONG);
//                            toast.show();
//                        }
//                    }, 3000);

                }

            }
        });

        btnVoltarCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.tela_principal.CarregarTela();
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveObjCalculo();
                act.tela_pesquisa.CarregarTela(objCalculo);
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(objCalculo.getConjuntoAlimentos().length == 0){
                    Toast.makeText(act, "Não há alimentos selecionados.", Toast.LENGTH_SHORT).show();
                }else{
                    saveObjCalculo();
                    act.tela_selecionados.CarregarTela(objCalculo);
                }
            }
        });
    }

}
