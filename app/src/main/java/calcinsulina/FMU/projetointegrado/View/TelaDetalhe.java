package calcinsulina.FMU.projetointegrado.View;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import calcinsulina.FMU.projetointegrado.Model.Alimento;
import calcinsulina.FMU.projetointegrado.R;

import calcinsulina.FMU.projetointegrado.Model.Alimento;
import calcinsulina.FMU.projetointegrado.Model.Calculo;

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
                    if (multiplicador != 0.0){
                        double carbASomar = objAlimento.getQuantCarb() * multiplicador;
                        int[] conjuntoAlimentos = objCalculo.getConjuntoAlimentos();
                        int[] newConjuntoAlimentos = new int[]
                        ;

                    }else{
                        Toast.makeText(act, "O Multiplicador precisa ser diferente de zero.", Toast.LENGTH_SHORT).show();
                    }
                }catch (RuntimeException e){
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
