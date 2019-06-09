package calcinsulina.FMU.projetointegrado.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import calcinsulina.FMU.projetointegrado.Model.Calculo;
import calcinsulina.FMU.projetointegrado.R;

public class TelaRelatorio{

    ListView Registros;
    TextView txtTotalCalcInt, txtTotalInsulInt;
    TelaRelatorio tela_relatorio;
    Button btnVoltarRel;
    MainActivity act;

    public TelaRelatorio(MainActivity act, TelaPrincipal tela_principal) {
        this.act = act;
        this.tela_relatorio = tela_relatorio;
    }

    public void CarregarTela(){
        act.setContentView(R.layout.tela_relatorio);
        btnVoltarRel = act.findViewById(R.id.btnVoltarRel);
        Registros = act.findViewById(R.id.listRegistros);
        txtTotalCalcInt = act.findViewById(R.id.txtTotalCalcInt);
        txtTotalInsulInt = act.findViewById(R.id.txtTotalInsulInt);

        int totalCalculo = act.getaCalculo().size();
        int totalInsulina = 0;
        for (int i = 0; i < act.getaCalculo().size(); i++ ){
            totalInsulina += act.getaCalculo().get(i).getTotalInsulina();
        }

        txtTotalCalcInt.setText(String.valueOf(totalCalculo));
        txtTotalInsulInt.setText(String.valueOf(totalInsulina));

        List<String> listStrCalculos = new ArrayList<String>();
        for (int i = 0; i < act.getaCalculo().size(); i++){
            String str = "Cálculo: " + act.getaCalculo().get(i).getId() + "\nGlicemia Obtida: " + act.getaCalculo().get(i).getGlicemiaObtida() +
                    "\nGlicemia Alvo: " + act.getaCalculo().get(i).getGlicemiaAlvo() + "\nData/Hora: " + act.getaCalculo().get(i).getDataRegistro();
            listStrCalculos.add(str);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, listStrCalculos);
        Registros.setAdapter(arrayAdapter);
        Registros.clearAnimation();

        Registros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int index, final long l) {
            }
        });

        btnVoltarRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.tela_principal.CarregarTela();
            }
        });
    }

}
