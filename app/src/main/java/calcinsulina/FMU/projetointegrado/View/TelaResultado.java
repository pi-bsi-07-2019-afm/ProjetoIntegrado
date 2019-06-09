package calcinsulina.FMU.projetointegrado.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import calcinsulina.FMU.projetointegrado.Model.Calculo;
import calcinsulina.FMU.projetointegrado.R;

public class TelaResultado {

    TextView txtUnidResultado,txtDescResultado2;
    Button btnConfirmar;

    MainActivity act;
    String telaAnterior = "MainActivity";

    public TelaResultado(MainActivity act, String telaAnterior) {
        this.act = act;
        this.telaAnterior = telaAnterior;
    }

    public void CarregarTela(final Calculo objCalculo){

        act.setContentView(R.layout.tela_resultado);

        txtUnidResultado = act.findViewById(R.id.txtUnidResultado);
        txtDescResultado2 = act.findViewById(R.id.txtDescResultado2);
        btnConfirmar = act.findViewById(R.id.btnConfirmar);

        if (objCalculo.getTotalInsulina() == 1){
            txtDescResultado2.setText("Unidade");
        }
        txtUnidResultado.setText(String.valueOf(objCalculo.getTotalInsulina()));

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder Dialogo = new AlertDialog.Builder(act);
                Dialogo.setIcon(android.R.drawable.ic_dialog_alert);
                Dialogo.setTitle("Confirmação: ");
                Dialogo.setMessage("Deseja salvar este cálculo em seu histórico?" );
                Dialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ConfirmaRecalculo();
                    }
                });
                Dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        act.getaCalculo().add(objCalculo);
                        act.tela_principal.CarregarTela();
                    }
                });
                Dialogo.show();
            }
        });

    }

    public void ConfirmaRecalculo(){
        AlertDialog.Builder Dialogo = new AlertDialog.Builder(act);
        Dialogo.setIcon(android.R.drawable.ic_dialog_alert);
        Dialogo.setTitle("Confirmação: ");
        Dialogo.setMessage("Deseja calcular novamente?" );
        Dialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                act.tela_principal.CarregarTela();
            }
        });
        Dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                act.tela_calculadora.flagInserirDadosNoForm = false;
                act.tela_calculadora.CarregarTela();
            }
        });
        Dialogo.show();
    }

}
