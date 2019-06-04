package calcinsulina.FMU.projetointegrado.View;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import calcinsulina.FMU.projetointegrado.Model.Alimento;
import calcinsulina.FMU.projetointegrado.R;

public class TelaDetalhe {

    MainActivity act;
    Button btnAdicionar, btnVoltar;
    EditText edGOuMl, edTotalConsumido, edNomeAlimento,
            edDescTipoMedida1, edDescTipoMedida2, edDescDefault, edTotalGOuMlDefault;


    public void CarregarTela(Alimento x) {
        try {
            act.setContentView(R.layout.tela_detalhe);
            btnAdicionar = act.findViewById(R.id.btnAdicionar);
            btnVoltar = act.findViewById(R.id.btnVoltar);
            edGOuMl = act.findViewById(R.id.edGOuMl);
            edTotalConsumido = act.findViewById(R.id.edTotalConsumido);
            edNomeAlimento = act.findViewById(R.id.edNomeAlimento);
            edDescTipoMedida1 = act.findViewById(R.id.edDescTipoMedida1);
            edDescTipoMedida2 = act.findViewById(R.id.edDescTipoMedida2);
            edDescDefault = act.findViewById(R.id.edDescDefault);
            edTotalGOuMlDefault = act.findViewById(R.id.edTotalGOuMlDefault);


            btnAdicionar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nomeAlimento = edNomeAlimento.getText().toString();
                    String tipoMedida1 = edDescTipoMedida1.getText().toString();
                    String tipoMedida2 = edDescTipoMedida2.getText().toString();
                    double gOuMl = Double.parseDouble(edGOuMl.getText().toString());
                    double totalConsumido = Double.parseDouble(edTotalConsumido.getText().toString());

                    act.getaAlimento().add(new Alimento());
                }
            });

            btnVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    act.tela_calculadora.CarregarTela();
                }
            });
        }catch (RuntimeException e){
            Toast.makeText(act, "OPS! Algo deu errado. Por favor reinicie o App", Toast.LENGTH_SHORT).show();
        }
    }
}
