package calcinsulina.FMU.projetointegrado.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import calcinsulina.FMU.projetointegrado.Model.Alimento;
import calcinsulina.FMU.projetointegrado.Model.Calculo;
import calcinsulina.FMU.projetointegrado.R;

public class TelaSelecionados {

    ListView listaResults;
    Button btnVoltar;

    Calculo objCalculo;

    MainActivity act;
    String telaAnterior = "TelaCalculadora";

    public TelaSelecionados(MainActivity act, String telaAnterior) {
        this.act = act;
        this.telaAnterior = telaAnterior;
    }

    public void CarregarTela(final Calculo objCalculo) {
        this.objCalculo = objCalculo;
        act.setContentView(R.layout.tela_selecionados);

        listaResults = act.findViewById(R.id.listResults);
        btnVoltar = act.findViewById(R.id.btnVoltar);

        final List<Alimento> list = new ArrayList<Alimento>();
        ArrayAdapter<Alimento> arrayAdapter = new ArrayAdapter<Alimento>(act, android.R.layout.simple_list_item_multiple_choice, list);
        final AlertDialog.Builder dialogo = new AlertDialog.Builder(act);

        listaResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int index, final long l) {
                dialogo.setTitle("Atenção");
                dialogo.setMessage("Tem certeza em remover este alimento?");
                dialogo.setNegativeButton("Não", null);
                dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(index);
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
