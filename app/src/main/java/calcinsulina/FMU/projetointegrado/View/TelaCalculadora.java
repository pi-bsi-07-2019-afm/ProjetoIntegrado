package calcinsulina.FMU.projetointegrado.View;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import calcinsulina.FMU.projetointegrado.R;

import calcinsulina.FMU.projetointegrado.Model.Calculo;

public class TelaCalculadora {

    MainActivity act;
    TelaPrincipal tela_principal;
    Button btnCalcular,btnVoltarCalc,btnAdicionar,btnEditar;
    EditText edFSensibil, edGlicemAlvo, edGlicemObt, edCarboidrato, edBolus;
    ImageView BolusHint;

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
        BolusHint = act.findViewById(R.id.BolusHint);

        if(flagInserirDadosNoForm){
            setFormCalculadora();
        }else{
            int novoId = act.getaCalculo().size()+1;
            objCalculo = new Calculo(novoId,0.0,0.0,0.0,new int[0],new double[0],0.0,0);
            this.edCarboidrato.setText(String.valueOf(objCalculo.getTotalCarb()));
        }

        BolusHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExibeMensagem("Quantidade de Carboidratos que 1 unidade de insulina cobre. (De acordo com o Fabricante da medicação)");
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double FatorSensibilidade, GlicemiaAlvo, GlicemiaObtida, Carboidrato, Bolus;

                try {
                    FatorSensibilidade = act.getaUsuario().get(0).getFatorSensibilidade();
                    GlicemiaAlvo = act.getDoubleFromEd(edGlicemAlvo.getText().toString());
                    GlicemiaObtida = act.getDoubleFromEd(edGlicemObt.getText().toString());
                    Carboidrato = act.getDoubleFromEd(edCarboidrato.getText().toString());
                    Bolus = act.getDoubleFromEd(edBolus.getText().toString());

                    //Validação de dados do cálculo. Limite de 300 para testes, necessário checar com médico qual seria o valor máximo possível para um humano, e atualizar.
                    if (FatorSensibilidade <= 0 || FatorSensibilidade > 300 || GlicemiaAlvo <= 0 || GlicemiaAlvo >= 300 || GlicemiaObtida <= 0 || GlicemiaObtida >= 300 || Carboidrato <= 0 || Bolus <= 0) {
                        Toast toast = Toast.makeText(act.getApplicationContext(), "Por favor, Insira todas as informações corretamente.", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        // Parte do cálculo
                        double Glicemia = GlicemiaObtida - GlicemiaAlvo;
                        double CorrecaoInsul = Glicemia / FatorSensibilidade;
                        double InsulinaAComer =  Carboidrato / Bolus;
                        final int NumeroDeDoses =  (int) (CorrecaoInsul + InsulinaAComer);
                        saveObjCalculo();
                        objCalculo.setTotalInsulina(NumeroDeDoses);
                        act.tela_carregando.CarregarTela(objCalculo);

//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast toast = Toast.makeText(act.getApplicationContext(), "Número de Doses: " + NumeroDeDoses, Toast.LENGTH_LONG);
//                            toast.show();
//                        }
//                    }, 3000);

                    }
                }catch(Exception ex){
                    Toast.makeText(act.getApplicationContext(),"Ops, algo deu errado. Reinicie o app e tente novamente.",Toast.LENGTH_LONG);
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
            @RequiresApi(api = Build.VERSION_CODES.N)
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

    public void ExibeMensagem(String mensagem) {
        AlertDialog.Builder Dialogo = new AlertDialog.Builder(act);
        Dialogo.setIcon(android.R.drawable.ic_dialog_alert);
        Dialogo.setTitle("Atenção");
        Dialogo.setMessage(mensagem);
        Dialogo.setNeutralButton("OK", null);
        Dialogo.show();
    }

}
