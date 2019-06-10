package calcinsulina.FMU.projetointegrado.View;

import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import calcinsulina.FMU.projetointegrado.Model.Alimento;
import calcinsulina.FMU.projetointegrado.Model.Calculo;
import calcinsulina.FMU.projetointegrado.R;

public class TelaPesquisa {
    ListView listaPesquisa;
    Button btnPesquisar, btnVoltar;
    EditText edPesquisa;
    Calculo objCalculo;
    MainActivity act;
    String telaAnterior = "TelaCalculadora";
    List<Alimento> listResultsSet;

    public TelaPesquisa(MainActivity act, String telaAnterior) {
        this.act = act;
        this.telaAnterior = telaAnterior;
    }

    public void CarregarTela(final Calculo objCalculo) {
        this.objCalculo = objCalculo;
        act.setContentView(R.layout.tela_pesquisa);
        List<String> listaListView = new ArrayList<String>();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, listaListView);
        listaPesquisa = (ListView) act.findViewById(R.id.listResults);
        listaPesquisa.setAdapter(arrayAdapter);

        btnPesquisar = act.findViewById(R.id.btnPesquisar);
        btnVoltar = act.findViewById(R.id.btnVoltar);
        edPesquisa = act.findViewById(R.id.edPesquisa);

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPesquisa.onEditorAction(EditorInfo.IME_ACTION_DONE);
                List<Alimento> alimentosEmSessao = act.getaAlimento();
                List<Alimento> listResults = new ArrayList<Alimento>();
                String conteudoBuscar = edPesquisa.getText().toString();
                String[] listArgs = new String[0];
                if (conteudoBuscar.length() != 0) {
                    listArgs = conteudoBuscar.split(" ");
                }
                arrayAdapter.clear();
                //LIMPAR LISTA GEMEA ALIMENTO

                if (listArgs.length > 0) {
                    for (int i = 0; i < alimentosEmSessao.size(); i++) {
                        boolean flagAdicionar = false;
                        for (int j = 0; j < listArgs.length; j++) {
                            if (act.getLowercaseSimpleString(alimentosEmSessao.get(i).getNome()).indexOf(act.getLowercaseSimpleString(listArgs[j])) != -1) {
                                boolean flagIsRepeated = false;

                                for (int k = 0; k < listResults.size(); k++){
                                    if (alimentosEmSessao.get(i).getId() == listResults.get(k).getId()){
                                        flagAdicionar = false;
                                        break;
                                    }
                                }

                                if (!flagIsRepeated) {
                                    flagAdicionar = true;
                                }else{
                                    flagAdicionar = false;
                                    break;
                                }
                            }else{
                                flagAdicionar = false;
                                break;
                            }
                        }
                        if(flagAdicionar){
                            listResults.add(alimentosEmSessao.get(i));
                        }
                    }
                    ;
                    if (listResults.size() > 0) {
                        for (int i = 0; i < listResults.size(); i++) {
                            arrayAdapter.add(listResults.get(i).getNome());
                        }
                        listResultsSet = listResults;
                        edPesquisa.clearFocus();
                    } else {
                        Toast.makeText(act, "A Busca não obteve resultados.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(act, "Por favor insira o nome do alimento antes de pesquisar.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listaPesquisa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                //Object clickItemObj = adapterView.getAdapter().getItem(index);
                Alimento objAlimento = listResultsSet.get(index);
                act.tela_detalhe.CarregarTela(objAlimento, objCalculo);
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
