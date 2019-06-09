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

        ArrayAdapter<Calculo> arrayAdapter = new ArrayAdapter<Calculo>(act, android.R.layout.simple_list_item_1, act.getaCalculo());
        Registros.setAdapter(arrayAdapter);
        Registros.clearAnimation();

        Registros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int index, final long l) {
//                startRotinaRemocao(index);
            }
        });

        btnVoltarRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.tela_principal.CarregarTela();
            }
        });
    }

//    public void startRotinaRemocao(final int index){
//        final AlertDialog.Builder dialogo = new AlertDialog.Builder(act);
//        dialogo.setIcon(android.R.drawable.ic_dialog_alert);
//        dialogo.setTitle("Confirmação: ");
//        dialogo.setMessage("Você deseja remover este Calculo dos Registros?");
//        dialogo.setNegativeButton("Não", null);
//        dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                removerCalculoDoRegistro();
//                Toast.makeText(act, "Calculo removido dos registros.", Toast.LENGTH_SHORT).show();
//            }
//        });
//        dialogo.show();
//    }
}
