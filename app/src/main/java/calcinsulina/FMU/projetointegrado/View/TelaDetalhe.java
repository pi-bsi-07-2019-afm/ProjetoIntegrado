package calcinsulina.FMU.projetointegrado.View;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import calcinsulina.FMU.projetointegrado.Model.Alimento;
import calcinsulina.FMU.projetointegrado.Model.Calculo;
import calcinsulina.FMU.projetointegrado.R;

public class TelaDetalhe {

    MainActivity act;
    Button btnAdicionar, btnVoltar;
    EditText edNomeAlimento, edCarboidratos, edCalorias, edGOuMl, edMultiplicador, edDescTipoMedida;

    Calculo objCalculo = new Calculo();
    Alimento objAlimento = new Alimento();

    public void CarregarTela(final Alimento objAlimento, final Calculo objCalculo) {
        this.objAlimento = objAlimento;
        this.objCalculo = objCalculo;

        act.setContentView(R.layout.tela_detalhe);

        btnAdicionar = act.findViewById(R.id.btnAdicionar);
        btnVoltar = act.findViewById(R.id.btnVoltar);
        edNomeAlimento = act.findViewById(R.id.edNomeAlimento);
        edCarboidratos = act.findViewById(R.id.edCarboidratos);
        edCalorias = act.findViewById(R.id.edCalorias);
        edGOuMl = act.findViewById(R.id.edGouMl);
        edMultiplicador = act.findViewById(R.id.edMultiplicador);
        edDescTipoMedida = act.findViewById(R.id.edDescTipoMedida);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double multiplicador = Double.valueOf(edMultiplicador.getText().toString());
                    if (multiplicador != 0.0) {
                        double carbASomar = objAlimento.getQuantCarb() * multiplicador;
                        int[] conjuntoAlimentos = objCalculo.getConjuntoAlimentos();
                        double[] conjuntoMultiplicadores = objCalculo.getConjuntoMultiplicadores();
                        int[] newConjuntoAlimentos = new int[conjuntoAlimentos.length + 1];
                        double[] newConjuntoMultiplicadores = new double[conjuntoMultiplicadores.length + 1];
                        for (int i = 0; i < newConjuntoAlimentos.length; i++) {
                            if (i != newConjuntoAlimentos.length - 1) {
                                newConjuntoAlimentos[i] = conjuntoAlimentos[i];
                            } else {
                                newConjuntoAlimentos[i] = objAlimento.getId();
                            }
                        }
                        objCalculo.setConjuntoAlimentos(newConjuntoAlimentos);
                        for (int i = 0; i < newConjuntoMultiplicadores.length; i++) {
                            if (i != newConjuntoMultiplicadores.length - 1) {
                                newConjuntoMultiplicadores[i] = conjuntoAlimentos[i];
                            } else {
                                newConjuntoMultiplicadores[i] = multiplicador;
                            }
                        }
                        objCalculo.setConjuntoMultiplicadores(newConjuntoMultiplicadores);
                        objCalculo.setTotalCarb(objCalculo.getTotalCarb() + carbASomar);
                        act.tela_calculadora.setObjCalculo(objCalculo);
                        act.tela_calculadora.flagInserirDadosNoForm = true;
                        act.tela_calculadora.CarregarTela();

                    } else {
                        Toast.makeText(act, "O Multiplicador precisa ser diferente de zero.", Toast.LENGTH_SHORT).show();
                    }
                } catch (RuntimeException e) {
                    Toast.makeText(act, "OPS! Algo deu errado. Por favor reinicie o App", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.tela_pesquisa.CarregarTela(objCalculo);
            }
        });
    }
}
