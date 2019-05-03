package calcinsulina.FMU.projetointegrado.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Date;

public class DAO extends SQLiteOpenHelper{

    private static final int VERSAO = 1;
    private static final String NOME_BANCO = "dbCalculina";

    //ALIMENTO
    private static final String TABELA_ALIMENTO = "ALIMENTO";
    private static final String ID_ALIMENTO = "ID_ALIMENTO";
    private static final String NOME_ALIMENTO = "NOME";
    private static final String TIPO_MEDIDA = "TIPO_MEDIDA";
    private static final String GOUML = "gOuML";
    private static final String QNT_CARBOIDRATO = "QNT_CARBOIDRATO";
    private static final String CALORIAS = "CALORIAS";
    private static final String QNT_CARB_G = "QNT_CARB_G";

    //USUÁRIO
    private static final String TABELA_USER = "USUARIO";
    private static final String ID_USER = "ID_USER";
    private static final String NOME_USER = "NOME";
    private static final String PESO = "PESO";
    private static final String DATA_NASC = "DATA_NASC";
    private static final String FATOR_SENSIBIL = "FATOR_SENSIBIL";
    private static final String EMAIL = "EMAIL";
    private static final String DATA_REGISTRO_U = "DATA_REGISTRO";

    //CALCULO
    private static final String TABELA_CALCULO = "CALCULO";
    private static final String ID_CALC = "ID_CALC";
    private static final String GLICEMIA_ALVO = "GLIC_ALVO";
    private static final String CONJUNTO_ALIMENTOS = "CONJ_ALIMENTO";
    private static final String TOTAL_CARB = "TOTAL_CARB";
    private static final String TOTAL_INSULINA = "TOTAL_INSULINA";
    private static final String DATA_REGISTRO_C = "DATA_REGISTRO";

    //TIPO DE MEDIDA
    private static final String TABELA_TM = "TIPO_MEDIDA";
    private static final String ID_TIPO_MED = "ID_TIPO_MED";
    private static final String NOME_TIPOMED = "NOME_TIPOMED";
    private static final String MEDIDO_EM_ML = "MEDIDO_EM_ML";

    public DAO(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    //onCreate é executado quando a aplicação cria o DB pela primeira vez.
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_ALIMENTO = "CREATE TABLE IF NOT EXISTS " + TABELA_ALIMENTO + " ( "
                + ID_ALIMENTO + " INTEGER, "
                + NOME_ALIMENTO + " TEXT, "
                + TIPO_MEDIDA + " TEXT, "
                + GOUML + " REAL, "
                + QNT_CARBOIDRATO + " REAL, "
                + CALORIAS + " REAL, "
                + QNT_CARB_G + " REAL ); ";
        System.out.println(CREATE_TABLE_ALIMENTO);
        db.execSQL(CREATE_TABLE_ALIMENTO);
        String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " + TABELA_USER + " ( "
                + ID_USER + " INTEGER, "
                + NOME_USER + " TEXT, "
                + PESO + " REAL, "
                + DATA_NASC + " TEXT, "
                + FATOR_SENSIBIL + " REAL, "
                + EMAIL + " TEXT ); ";
        System.out.println(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_USER);
        String CREATE_TABLE_CALC = "CREATE TABLE IF NOT EXISTS " + TABELA_CALCULO + " ( "
                + ID_CALC + " INTEGER, "
                + GLICEMIA_ALVO + " REAL, "
                + CONJUNTO_ALIMENTOS + " TEXT, "
                + TOTAL_CARB + " REAL, "
                + TOTAL_INSULINA + " REAL, "
                + DATA_REGISTRO_C + " REAL);";
        System.out.println(CREATE_TABLE_CALC);
        db.execSQL(CREATE_TABLE_CALC);
        String CREATE_TABLE_TM = "CREATE TABLE IF NOT EXISTS " + TABELA_TM + " ( "
                + ID_TIPO_MED + " INTEGER, "
                + NOME_TIPOMED + " TEXT, "
                + MEDIDO_EM_ML + " INTEGER);";
        System.out.println(CREATE_TABLE_TM);
        db.execSQL(CREATE_TABLE_TM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_ALIMENTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CALCULO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_TM);
        this.onCreate(db);
    }

    public ArrayList<Alimento> recuperaAlimentos(){
        ArrayList<Alimento> array_AL = new ArrayList<>();

        String selectQuery = "SELECT " + ID_ALIMENTO + " , " + NOME_ALIMENTO + " , " + TIPO_MEDIDA + " , " + GOUML + " , " + QNT_CARBOIDRATO + " , " + CALORIAS + " , " + QNT_CARB_G + " FROM " + TABELA_ALIMENTO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Alimento al = new Alimento();
                al.setId(cursor.getInt(cursor.getColumnIndex(ID_ALIMENTO)));
                al.setNome(cursor.getString(cursor.getColumnIndex(NOME_ALIMENTO)));
                al.setTipoMedida(cursor.getInt(cursor.getColumnIndex(TIPO_MEDIDA)));
                al.setgOuMl(cursor.getDouble(cursor.getColumnIndex(GOUML)));
                al.setQuantCarb(cursor.getDouble(cursor.getColumnIndex(QNT_CARBOIDRATO)));
                al.setCalorias(cursor.getDouble(cursor.getColumnIndex(CALORIAS)));
                al.setQuantCarbPorG(cursor.getDouble(cursor.getColumnIndex(QNT_CARB_G)));
                array_AL.add(al);
            } while (cursor.moveToNext());
        }db.close();
        return array_AL;
    }

    public ArrayList<Usuario> recuperaUsuarios(){
        ArrayList<Usuario> array_User = new ArrayList<>();

        String selectQuery = "SELECT " + ID_USER + " , " + NOME_USER + " , " + PESO + " , " + DATA_NASC + " , " + FATOR_SENSIBIL + " , "
                + EMAIL + " FROM " + TABELA_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Usuario us = new Usuario();
                us.setId(cursor.getInt(cursor.getColumnIndex(ID_USER)));
                us.setNome(cursor.getString(cursor.getColumnIndex(NOME_USER)));
                us.setPeso(cursor.getDouble(cursor.getColumnIndex(PESO)));
                us.setDataNascimento(cursor.getString(cursor.getColumnIndex(DATA_NASC)));
                us.setFatorSensibilidade(cursor.getDouble(cursor.getColumnIndex(FATOR_SENSIBIL)));
                us.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
//                us.setDataRegistro(cursor.getString(cursor.getColumnIndex(DATA_REGISTRO_U)));
            } while (cursor.moveToNext());
        }db.close();
        return array_User;
    }

    public ArrayList<Calculo> recuperaCalculo(){
        ArrayList<Calculo> array_Calc = new ArrayList<>();
        String selectQuery = "SELECT " + ID_CALC + " , " + GLICEMIA_ALVO + " , " + CONJUNTO_ALIMENTOS + " , " + TOTAL_CARB + " , " + TOTAL_INSULINA + " , " + DATA_REGISTRO_C
                + " FROM " + TABELA_CALCULO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Calculo cl = new Calculo();
                cl.setId(cursor.getInt(cursor.getColumnIndex(ID_CALC)));
                cl.setGlicemiaAlvo(cursor.getDouble(cursor.getColumnIndex(GLICEMIA_ALVO)));
                cl.setConjuntoAlimentos(cursor.getString(cursor.getColumnIndex(CONJUNTO_ALIMENTOS)));
                cl.setTotalCarb(cursor.getDouble(cursor.getColumnIndex(TOTAL_CARB)));
                cl.setTotalInsulina(cursor.getInt(cursor.getColumnIndex(TOTAL_INSULINA)));
                cl.setDataRegistro(cursor.getString(cursor.getColumnIndex(DATA_REGISTRO_C)));
            } while (cursor.moveToNext());
        }db.close();

        String teste = "1997/12/01";
        String vetor[] = teste.split("/");
        Date objData = new Date(Integer.parseInt(vetor[0]),Integer.parseInt(vetor[1]),Integer.parseInt(vetor[2]) );

        int diferencaDias = objData.getDate();

        return array_Calc;
    }

    public ArrayList<TipoMedida> recuperaTipoMedida(){
        ArrayList<TipoMedida> array_TM = new ArrayList<>();
        String selectQuery = "SELECT " + ID_TIPO_MED + " , " + NOME_TIPOMED + " , " + MEDIDO_EM_ML + " FROM "
                + TABELA_TM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                TipoMedida tm = new TipoMedida();
                tm.setId(cursor.getInt(cursor.getColumnIndex(ID_TIPO_MED)));
                tm.setNome(cursor.getString(cursor.getColumnIndex(NOME_TIPOMED)));
                tm.setMedidoEmMl(cursor.getInt(cursor.getColumnIndex(MEDIDO_EM_ML)));
            } while (cursor.moveToNext());
        }db.close();
        return array_TM;
    }

    public void insereAlimento(ArrayList<Alimento> alimento){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        for (int i = 0; i < alimento.size(); i++) {
            valores.put(ID_ALIMENTO, alimento.get(i).getId());
            valores.put(NOME_ALIMENTO, alimento.get(i).getNome());
            valores.put(TIPO_MEDIDA, alimento.get(i).getTipoMedida());
            valores.put(GOUML, alimento.get(i).getgOuMl());
            valores.put(QNT_CARBOIDRATO, alimento.get(i).getQuantCarb());
            valores.put(CALORIAS, alimento.get(i).getCalorias());
            valores.put(QNT_CARB_G, alimento.get(i).getQuantCarbPorG());
            db.insert(TABELA_ALIMENTO, null, valores);
        }
        db.close();
    }

    public void insereUsuario(ArrayList<Usuario> usuario){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        for (int i = 0; i < usuario.size(); i++) {
            valores.put(ID_USER, usuario.get(i).getId());
            valores.put(NOME_USER, usuario.get(i).getNome());
            valores.put(PESO, usuario.get(i).getPeso());
            valores.put(DATA_NASC, usuario.get(i).getDataNascimento());
            valores.put(FATOR_SENSIBIL, usuario.get(i).getFatorSensibilidade());
            valores.put(EMAIL, usuario.get(i).getEmail());
//            valores.put(DATA_REGISTRO_U, usuario.get(i).getDataRegistro());
            db.insert(TABELA_USER, null, valores);
        }
        db.close();
    }

    public void insereCalculo(ArrayList<Calculo> calculo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        for (int i = 0; i < calculo.size(); i++) {
            valores.put(ID_CALC, calculo.get(i).getId());
            valores.put(QNT_CARB_G, calculo.get(i).getQuantCarbPorUnidInsulina());
            valores.put(GLICEMIA_ALVO, calculo.get(i).getGlicemiaAlvo());
            valores.put(CONJUNTO_ALIMENTOS, calculo.get(i).getConjuntoAlimentos());
            valores.put(TOTAL_CARB, calculo.get(i).getTotalCarb());
            valores.put(TOTAL_INSULINA, calculo.get(i).getTotalInsulina());
            valores.put(DATA_REGISTRO_C, calculo.get(i).getDataRegistro());
            db.insert(TABELA_CALCULO, null, valores);
        }
        db.close();
    }

    public void insereTipoMedida(ArrayList<TipoMedida> tipoMedida){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        for (int i = 0; i < tipoMedida.size(); i++) {
            valores.put(ID_USER, tipoMedida.get(i).getId());
            valores.put(NOME_TIPOMED, tipoMedida.get(i).getNome());
            db.insert(TABELA_TM, null, valores);
        }
        db.close();
    }

    public void limpaTabela(){
        SQLiteDatabase db = this.getWritableDatabase();
        String limpaQuery = "DELETE FROM " + TABELA_ALIMENTO;
        db.execSQL(limpaQuery);
        String limpaQuery2 = "DELETE FROM " + TABELA_USER;
        db.execSQL(limpaQuery2);
        String limpaQuery3 = "DELETE FROM " + TABELA_TM;
        db.execSQL(limpaQuery3);
        String limpaQuery4 = "DELETE FROM " + TABELA_CALCULO;
        db.execSQL(limpaQuery4);
        db.close();
    }

}
