package calcinsulina.FMU.projetointegrado.View;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import calcinsulina.FMU.projetointegrado.R;

public class TelaConfig {

    MainActivity act;
    TelaPrincipal tela_principal;
    TextView nome, peso, email, datanasc, fatorSenbil;
    Button button2;

    public TelaConfig(MainActivity act, TelaPrincipal tela_principal){
        this.act = act;
        this.tela_principal = tela_principal;
    }

    public void CarregarTela() {
        act.setContentView(R.layout.tela_config);
        nome = act.findViewById(R.id.Nome);
        peso = act.findViewById(R.id.Peso);
        email = act.findViewById(R.id.email);
        datanasc = act.findViewById(R.id.dataNasc);
        fatorSenbil = act.findViewById(R.id.FatorSensibilidade);
        button2 = act.findViewById(R.id.button2);

        nome.setText(act.getaUsuario().get(0).getNome());
        peso.setText(act.getaUsuario().get(0).getDataRegistro());
        email.setText(act.getaUsuario().get(0).getEmail());
        datanasc.setText(act.getaUsuario().get(0).getDataNascimento());
//        fatorSenbil.setText(act.getaUsuario().get(0).getFatorSensibilidade());

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.tela_principal.CarregarTela();
            }
        });

    }
}
