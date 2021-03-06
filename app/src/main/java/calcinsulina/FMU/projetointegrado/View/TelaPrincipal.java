package calcinsulina.FMU.projetointegrado.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import calcinsulina.FMU.projetointegrado.Model.Usuario;
import calcinsulina.FMU.projetointegrado.R;

public class TelaPrincipal {

    MainActivity act;
    ImageView btnCalc, btnConfig, btnReport;
    TelaCadastro tela_cadastro;
    TelaCalculadora tela_calculadora;
    TelaConfig tela_config;
    TelaRelatorio tela_relatorio;
    TelaCarregando tela_carregando;
    SplashScreen splashScreen;
    String telaAnterior;

    public TelaPrincipal(MainActivity act, String telaAnterior) {
        this.act = act;
        this.telaAnterior = telaAnterior;
    }

    public void CarregarTela() {
        act.setContentView(R.layout.tela_principal);
        btnCalc = act.findViewById(R.id.btnCalc);
        btnConfig = act.findViewById(R.id.btnConfig);
        btnReport = act.findViewById(R.id.btnReport);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCalc.getLayoutParams().width = 200;
                btnCalc.getLayoutParams().height = 230;
                act.tela_calculadora.flagInserirDadosNoForm = false;
                act.tela_calculadora.CarregarTela();
            }
        });
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnConfig.getLayoutParams().width = 120;
                btnConfig.getLayoutParams().height = 130;
                act.tela_config.CarregarTela();
            }
        });
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnReport.getLayoutParams().width = 120;
                btnReport.getLayoutParams().height = 130;
                if(act.getaCalculo().size() == 0) {
                    Toast.makeText(act, "Não há calculos registrados para relatório", Toast.LENGTH_SHORT).show();
                }else{
                    act.tela_relatorio.CarregarTela();
                }
            }
        });

//         Verificando se já existe um cadastro do Usuário e a tela anterios é a SplashScreen
        if (!act.getaUsuario().isEmpty() && telaAnterior.equalsIgnoreCase("SplashScreen")) {
            String username = usernameSearch(act.getaUsuario());
            Toast.makeText(act, "Bem Vindo " + username, Toast.LENGTH_SHORT).show();
        }

    }

    public void setTelaCalculadora(TelaCalculadora tela_calculadora) {
        this.tela_calculadora = tela_calculadora;
    }

    public void setTelaConfig(TelaConfig tela_config) {
        this.tela_config = tela_config;
    }

    public void setTelaRelatorio(TelaRelatorio tela_relatorio){
        this.tela_relatorio = tela_relatorio;
    }

    public void setTelaCadastro(TelaCadastro tela_cadastro) {
        this.tela_cadastro = tela_cadastro;
    }

    public void setTelaCarregando(TelaCarregando tela_carregando) {
        this.tela_carregando = tela_carregando;
    }

    public void setSplashScreen(SplashScreen splashScreen) {
        this.splashScreen = splashScreen;
    }

    public String usernameSearch(List<Usuario> array) {
        return array.get(0).getNome();
    }

    protected void onCreate(Bundle savedInstanceState) {
        //teste
    }

}

