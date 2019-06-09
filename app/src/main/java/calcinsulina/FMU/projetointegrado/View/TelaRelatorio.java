package calcinsulina.FMU.projetointegrado.View;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import calcinsulina.FMU.projetointegrado.Model.Calculo;
import calcinsulina.FMU.projetointegrado.R;

public class TelaRelatorio{

    ListView Registros;
    TextView txtTotalCalcInt, txtTotalInsulInt;
    TelaRelatorio tela_relatorio;
    Button btnVoltarRel;
    MainActivity act;
    List<Calculo> listCalculos;

    public TelaRelatorio(MainActivity act, TelaPrincipal tela_principal) {
        this.act = act;
        this.tela_relatorio = tela_relatorio;
    }

    public void CarregarTela(List<Calculo> listCalculos){
        this.listCalculos = listCalculos;
        act.setContentView(R.layout.tela_relatorio);
        btnVoltarRel = act.findViewById(R.id.btnVoltarRel);
        Registros = act.findViewById(R.id.listRegistros);
        txtTotalCalcInt = act.findViewById(R.id.txtTotalCalcInt);
        txtTotalInsulInt = act.findViewById(R.id.txtTotalInsulInt);

        ArrayAdapter<Calculo> arrayAdapter = new ArrayAdapter<Calculo>(act, android.R.layout.simple_list_item_1, listCalculos);
        Registros.setAdapter(arrayAdapter);
        Registros.clearAnimation();

        btnVoltarRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.tela_principal.CarregarTela();
            }
        });
    }
}
