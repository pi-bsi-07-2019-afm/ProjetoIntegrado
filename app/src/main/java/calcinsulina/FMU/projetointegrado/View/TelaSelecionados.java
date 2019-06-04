package calcinsulina.FMU.projetointegrado.View;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import calcinsulina.FMU.projetointegrado.Model.Calculo;
import calcinsulina.FMU.projetointegrado.R;

public class TelaSelecionados {

    MainActivity act;
    ListView listResults;
    Button btnVoltar, btnRemover;


    public void CarregarTela(Calculo x){
        act.setContentView(R.layout.tela_selecionados);
        listResults = act.findViewById(R.id.listResults);
        btnVoltar = act.findViewById(R.id.btnVoltar);
        btnRemover = act.findViewById(R.id.btnRemover);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.tela_calculadora.CarregarTela();
            }
        });
    }

}
