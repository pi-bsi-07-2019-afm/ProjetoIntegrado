package calcinsulina.FMU.projetointegrado.View;

import android.widget.Button;
import android.widget.TextView;
import android.view.View;

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

    public void CarregarTela(final int unidadesResultado){

        act.setContentView(R.layout.tela_resultado);

        txtUnidResultado = act.findViewById(R.id.txtUnidResultado);
        txtDescResultado2 = act.findViewById(R.id.txtDescResultado2);
        btnConfirmar = act.findViewById(R.id.btnConfirmar);

        if (unidadesResultado == 1){
            txtDescResultado2.setText("Unidade");
        }
        txtUnidResultado.setText(String.valueOf(unidadesResultado));

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.tela_principal.CarregarTela();
            }
        });

    }

}
