package calcinsulina.FMU.projetointegrado.View;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import calcinsulina.FMU.projetointegrado.R;

public class TelaConfig {

    MainActivity act;
    TelaPrincipal tela_principal;
    Button btnVoltar, btnAltCad, btnAbout;

    public TelaConfig(MainActivity act, TelaPrincipal tela_principal){
        this.act = act;
        this.tela_principal = tela_principal;
    }

    public void CarregarTela() {
        act.setContentView(R.layout.tela_config);
        btnVoltar = act.findViewById(R.id.btnVoltar);
        btnAltCad = act.findViewById(R.id.btnAltCad);
        btnAbout = act.findViewById(R.id.btnAbout);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.tela_principal.CarregarTela();
            }
        });

        btnAltCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.tela_cadastro.CarregarTela();
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder Dialogo = new AlertDialog.Builder(act);
                Dialogo.setTitle("Projeto Desenvolido por: ");
                Dialogo.setMessage("Felippe Wohlers Leão - 8049443\nAllan Keiiti Nakakita - 7925889\nMichel Andrade - 8253225\nMatheus\nNatalia");
                Dialogo.setNeutralButton("OK", null);
                Dialogo.show();

            }
        });

//        nome.setText(act.getaUsuario().get(0).getNome());
//        peso.setText(act.getaUsuario().get(0).getDataRegistro());
//        email.setText(act.getaUsuario().get(0).getEmail());
//        datanasc.setText(act.getaUsuario().get(0).getDataNascimento());
//        fatorSenbil.setText(act.getaUsuario().get(0).getFatorSensibilidade());

    }
}
