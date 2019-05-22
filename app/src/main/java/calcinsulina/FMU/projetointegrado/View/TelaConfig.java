package calcinsulina.FMU.projetointegrado.View;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import calcinsulina.FMU.projetointegrado.R;

public class TelaConfig {

    MainActivity act;
    TelaPrincipal tela_principal;
    Button btnVoltar, btnAltCad, btnAbout, btnDelete;

    public TelaConfig(MainActivity act, TelaPrincipal tela_principal) {
        this.act = act;
        this.tela_principal = tela_principal;
    }

    public void CarregarTela() {
        act.setContentView(R.layout.tela_config);
        btnVoltar = act.findViewById(R.id.btnVoltar);
        btnAltCad = act.findViewById(R.id.btnAltCad);
        btnAbout = act.findViewById(R.id.btnAbout);
        btnDelete = act.findViewById(R.id.btnDelete);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.tela_principal.CarregarTela();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //act.dropBD();
                act.getaUsuario().remove(0);
                Toast toast = Toast.makeText(act.getApplicationContext(), "Banco excluído. Feche o aplicativo.", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        btnAltCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.tela_cadastro.telaAnterior = "TelaConfig";
                act.tela_cadastro.CarregarTela();
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder Dialogo = new AlertDialog.Builder(act);
                Dialogo.setTitle("Projeto Desenvolvido por: ");
                Dialogo.setMessage("• Allan Keiiti Nakakita - 7925889\n" +
                        "• Felippe Wohlers Leão - 8049443\n" +
                        "• Matheus Henrique - 8054672\n" +
                        "• Michel Andrade - 8253225\n" +
                        "• Natalia Martins - 8074081\n" +
                        "\nAlunos do curso de Bacharelado em Sistemas de Informação da FMU.\n" +
                        "\nOrientação por Paulo Sérgio Rangel Garcia." );
                Dialogo.setNeutralButton("OK", null);
                Dialogo.show();

            }
        });
    }
}
