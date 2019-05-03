package calcinsulina.FMU.projetointegrado.View;

import android.widget.TextView;

import calcinsulina.FMU.projetointegrado.R;

public class TelaConfig {

    MainActivity act;
    TelaPrincipal tela_principal;
    TextView nome, peso, email, datanasc, fatorSenbil;

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

        nome.setText(act.getaUsuario().get(0).getNome());
//        peso.setText((int) act.getaUsuario().get(0).getPeso());
        email.setText(act.getaUsuario().get(0).getEmail());
        datanasc.setText(act.getaUsuario().get(0).getDataNascimento());
//        fatorSenbil.setText((int) act.getaUsuario().get(0).getFatorSensibilidade());

    }
}
