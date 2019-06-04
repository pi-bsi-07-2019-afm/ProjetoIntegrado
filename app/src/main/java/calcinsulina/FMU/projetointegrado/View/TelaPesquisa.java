package calcinsulina.FMU.projetointegrado.View;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import calcinsulina.FMU.projetointegrado.Model.Alimento;
import calcinsulina.FMU.projetointegrado.Model.Calculo;
import calcinsulina.FMU.projetointegrado.R;

public class TelaPesquisa {

    TextView txtPesquisa;
    ListView listaPesquisa;
    Button btnPesquisar, btnVoltar;

    Calculo objCalculo;


    MainActivity act;
    String telaAnterior = "TelaCalculadora";

    public TelaPesquisa(MainActivity act, String telaAnterior) {
        this.act = act;
        this.telaAnterior = telaAnterior;
    }

    public void CarregarTela(final Calculo objCalculo){
        this.objCalculo = objCalculo;
        act.setContentView(R.layout.tela_pesquisa);

        listaPesquisa = act.findViewById(R.id.listResults);
        btnPesquisar = act.findViewById(R.id.btnPesquisar);
        btnVoltar = act.findViewById(R.id.btnVoltar);
        listaPesquisa.clearAnimation();

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Alimento> listSearch = act.getaAlimento();
                List<Alimento> listResults = new ArrayList<Alimento>();
                String[] listArgs = txtPesquisa.getText().toString().split(" ");
                if(listArgs.length > 0){
                    for (int i = 0; i < listSearch.size(); i++){
                        for (int j = 0; j < listArgs.length; j++ ){
                            if (listSearch.get(i).getNome().indexOf(listArgs[j]) != -1){
                                boolean flagIsRepeated = false;
                                for (int k = 0; k < listSearch.size(); k++){
                                    if (listSearch.get(i).equals(listResults.get(k))){
                                        flagIsRepeated = true;
                                        break;
                                    }
                                }
                                if (!flagIsRepeated){
                                    listResults.add(listSearch.get(i));
                                }
                            }
                        }
                    };

                    if (listResults.size() > 0){
                        ArrayAdapter<Alimento> arrayAdapter = new ArrayAdapter<Alimento>(act, android.R.layout.simple_list_item_multiple_choice, listResults);
                        listaPesquisa.setAdapter(arrayAdapter);
                    }else{
                        //Implementar mensagem "A busca não obteve resultados."
                    }

                }else{
                    //Implementar mensagem "Por favor insira o nome do alimento antes de pesquisar."
                }
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

        listaPesquisa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                //Object clickItemObj = adapterView.getAdapter().getItem(index);
                Object clickItemObj = adapterView.getAdapter().getItem(index);
                if(clickItemObj != null) {
                    act.tela_detalhe.CarregarTela(clickItemObj);
                }
            }
        });


    }

}
