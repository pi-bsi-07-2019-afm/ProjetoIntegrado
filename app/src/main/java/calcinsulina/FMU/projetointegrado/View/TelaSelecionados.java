package calcinsulina.FMU.projetointegrado.View;

import android.app.AlertDialog;
import android.content.Context;
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

    public void CarregarTela(final Calculo objCalculo) {
        this.objCalculo = objCalculo;
        int conjuntoAlimentosId[] = objCalculo.getConjuntoAlimentos();
        act.setContentView(R.layout.tela_selecionados);

        listResults = act.findViewById(R.id.listResults);
        btnVoltar = act.findViewById(R.id.btnVoltar);

        final List<String> listaListView = new ArrayList<String>();
        listResultsSet = new ArrayList<Alimento>();
        List<Alimento> aAlimentos = act.getaAlimento();
        for (int i = 0; i < conjuntoAlimentosId.length; i++){
            listaListView.add(aAlimentos.get(conjuntoAlimentosId[i]-1).getNome());
            listResultsSet.add(aAlimentos.get(conjuntoAlimentosId[i]-1));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, listaListView);
        listResults.setAdapter(arrayAdapter);
        listResults.clearAnimation();


        listResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int index, final long l) {
                startRotinaRemocao(index);
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

    public void removerAlimentoDoCalculo(Calculo objCalculo, int idAlimentoARemover){
        List<Integer> conjuntoAlimentos = new ArrayList<Integer>();
        List<Double> conjuntoMultiplicadores = new ArrayList<Double>();
        double totalCarbARemover = 0.0;
        for (int i = 0; i < objCalculo.getConjuntoAlimentos().length; i++){
            if(objCalculo.getConjuntoAlimentos()[i] != idAlimentoARemover){
                conjuntoAlimentos.add(objCalculo.getConjuntoAlimentos()[i]);
                conjuntoMultiplicadores.add(objCalculo.getConjuntoMultiplicadores()[i]);
            }else{
                int index = objCalculo.getConjuntoAlimentos()[i];
                double valorCarbAlimento = act.getaAlimento().get(index - 1).getQuantCarb();
                double multiplicador = objCalculo.getConjuntoMultiplicadores()[i];
                totalCarbARemover = valorCarbAlimento * multiplicador;
            }
        }
        int[] novoConjuntoAlimentos = new int[conjuntoAlimentos.size()];
        double[] novoConjuntoMultiplicadores = new double[conjuntoMultiplicadores.size()];
        if (novoConjuntoAlimentos.length == novoConjuntoMultiplicadores.length){
            for(int i = 0; i < novoConjuntoAlimentos.length; i++){
                novoConjuntoAlimentos[i] = conjuntoAlimentos.get(i).intValue();
                novoConjuntoMultiplicadores[i] = conjuntoMultiplicadores.get(i).doubleValue();
            }
            objCalculo.setConjuntoAlimentos(novoConjuntoAlimentos);
            objCalculo.setConjuntoMultiplicadores(novoConjuntoMultiplicadores);
            objCalculo.setTotalCarb(objCalculo.getTotalCarb() - totalCarbARemover);
            act.tela_selecionados.CarregarTela(objCalculo);
        }else{
            throw new RuntimeException("Erro na carga de cálculo. Feche o app e tente novamente.");
        }
    }

    public void startRotinaRemocao(final int index){
        final AlertDialog.Builder dialogo = new AlertDialog.Builder(act);
        dialogo.setIcon(android.R.drawable.ic_dialog_alert);
        dialogo.setTitle("Confirmação: ");
        dialogo.setMessage("Você deseja remover o alimento \"" + listResultsSet.get(index).getNome() + "\" deste cálculo?");
        dialogo.setNegativeButton("Não", null);
        dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removerAlimentoDoCalculo(objCalculo,listResultsSet.get(index).getId());
                Toast.makeText(act, "Alimento removido da seleção.", Toast.LENGTH_SHORT).show();
            }
        });
        dialogo.show();
    }

}
