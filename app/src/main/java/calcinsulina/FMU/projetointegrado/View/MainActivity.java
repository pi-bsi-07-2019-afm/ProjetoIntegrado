package calcinsulina.FMU.projetointegrado.View;
import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import calcinsulina.FMU.projetointegrado.Model.Alimento;
import calcinsulina.FMU.projetointegrado.Model.Calculo;
import calcinsulina.FMU.projetointegrado.Model.DAO;
import calcinsulina.FMU.projetointegrado.Model.TipoMedida;
import calcinsulina.FMU.projetointegrado.Model.Usuario;

public class MainActivity extends Activity {
    private ArrayList<Usuario> aUsuario = new ArrayList<>();
    private ArrayList<Calculo> aCalculo = new ArrayList<>();
    private ArrayList<TipoMedida> aTipoMedida = new ArrayList<>();
    private ArrayList<Alimento> aAlimento = new ArrayList<>();
    TelaPrincipal tela_principal;
    TelaCalculadora tela_calculadora;
    TelaConfig tela_config;
    TelaCadastro tela_cadastro;
    TelaCarregando tela_carregando;
    SplashScreen splashScreen;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tela_principal = new TelaPrincipal(this);
        tela_cadastro = new TelaCadastro(this, tela_principal);
        tela_calculadora = new TelaCalculadora(this, tela_principal);
        tela_config = new TelaConfig(this, tela_principal);
        tela_carregando = new TelaCarregando(this, tela_principal);
        splashScreen = new SplashScreen(this, tela_principal);
        tela_principal.setTelaCadastro(tela_cadastro);
        tela_principal.setTelaCalculadora(tela_calculadora);
        tela_principal.setTelaConfig(tela_config);
        tela_principal.setTelaCarregando(tela_carregando);
        tela_principal.setSplashScreen(splashScreen);
        dao = new DAO(this);

        //Inserindo Valores do DB para as ArrayList
        aAlimento = dao.recuperaAlimentos();
        aCalculo = dao.recuperaCalculo();
        aTipoMedida = dao.recuperaTipoMedida();
        aUsuario = dao.recuperaUsuarios();

        splashScreen.CarregarTela();
    }

    @Override
    protected void onStop(){
        super.onStop();
        dao.limpaTabela();
        dao.insereUsuario(aUsuario);
        dao.insereAlimento(aAlimento);
        dao.insereCalculo(aCalculo);
        dao.insereTipoMedida(aTipoMedida);
    }

    protected void onRestart(){
        super.onRestart();

    }

    protected void onDestroy(){
        super.onDestroy();
        System.out.println('A');
        for (int i = 0; i < aUsuario.size(); i++){
            dao.limpaTabela();
            dao.insereUsuario(aUsuario);
        }
        dao.insereAlimento(aAlimento);
        dao.insereCalculo(aCalculo);
        dao.insereTipoMedida(aTipoMedida);
    }

    public ArrayList<Usuario> getaUsuario(){
        return aUsuario;
    }
    public ArrayList<Calculo> getaCalculo(){
        return aCalculo;
    }
    public ArrayList<TipoMedida> getaTipoMedida(){
        return aTipoMedida;
    }
    public ArrayList<Alimento> getaAlimento(){
        return aAlimento;
    }
}
