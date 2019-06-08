package calcinsulina.FMU.projetointegrado.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import calcinsulina.FMU.projetointegrado.Model.Alimento;
import calcinsulina.FMU.projetointegrado.Model.Calculo;
import calcinsulina.FMU.projetointegrado.R;

public class TelaSelecionados {

    ListView listResults;
    Button btnVoltar;
    Calculo objCalculo = new Calculo();
    MainActivity act;
    String telaAnterior = "TelaCalculadora";
    List<Alimento> listResultsSet;

    public TelaSelecionados(MainActivity act, String telaAnterior) {
        this.act = act;
        this.telaAnterior = telaAnterior;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void CarregarTela(final Calculo objCalculo) {
        this.objCalculo = objCalculo;
        int conjuntoAlimentosId[] = objCalculo.getConjuntoAlimentos();
        act.setContentView(R.layout.tela_selecionados);

        listResults = act.findViewById(R.id.listResults);
        btnVoltar = act.findViewById(R.id.btnVoltar);

        // Falta implementar esta parte - Allan
        final List<Alimento> listaListView = new ArrayList<Alimento>();
        for (int i = 0; i < conjuntoAlimentosId.length; i++){
            listaListView.add(act.getaAlimento().get(conjuntoAlimentosId[i-0]));
        }

        ArrayAdapter<Alimento> arrayAdapter = new ArrayAdapter<Alimento>(act, android.R.layout.simple_list_item_1, listaListView);
        listResults.setAdapter(arrayAdapter);
        listResults.clearAnimation();

        final AlertDialog.Builder dialogo = new AlertDialog.Builder(act);

        listResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int index, final long l) {
                String Alimento = (listResults.getItemAtPosition(index)).toString();
                dialogo.setTitle("Atenção");
                dialogo.setMessage("Tem certeza em remover o Aliemnto: " + Alimento + " ?");
                dialogo.setNegativeButton("Não", null);
                dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listaListView.remove(index);
                        Toast.makeText(act, "Alimento removido da seleção.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.tela_calculadora.setObjCalculo(objCalculo);
                act.tela_calculadora.flagInserirDadosNoForm = true;
                act.tela_calculadora.CarregarTela();
            }
        });
    }
}
