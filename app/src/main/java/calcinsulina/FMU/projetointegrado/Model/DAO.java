package calcinsulina.FMU.projetointegrado.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class DAO extends SQLiteOpenHelper{

    private static int VERSAO = 1;
    private static final String NOME_BANCO = "dbCalculina";
    private static final String TABELA_ALIMENTO = "ALIMENTO";
    private static final String ID_ALIMENTO = "ID_ALIMENTO";
    private static final String NOME_ALIMENTO = "NOME";
    private static final String TIPO_MEDIDA = "TIPO_MEDIDA";
    private static final String GOUML = "gOuML";
    private static final String QNT_CARBOIDRATO = "QNT_CARBOIDRATO";
    private static final String CALORIAS = "CALORIAS";
    private static final String QNT_CARB_G = "QNT_CARB_G";

    private static final String TABELA_USER = "USUARIO";
    private static final String ID_USER = "ID_USER";
    private static final String NOME_USER = "NOME";
    private static final String PESO = "PESO";
    private static final String DATA_NASC = "DATA_NASC";
    private static final String FATOR_SENSIBIL = "FATOR_SENSIBIL";
    private static final String EMAIL = "EMAIL";
    private static final String DATA_REGISTRO = "DATA_REGISTRO";

    public DAO(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    //onCreate é executado quando a aplicação cria o DB pela primeira vez.
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABELA_ALIMENTO + " ( "
                + ID_ALIMENTO + " INTEGER, "
                + NOME_ALIMENTO + " TEXT, "
                + TIPO_MEDIDA + " TEXT, "
                + GOUML + " REAL, "
                + QNT_CARBOIDRATO + " REAL, "
                + CALORIAS + " REAL, "
                + QNT_CARB_G + "REAL);";
        System.out.println(CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
        String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " + TABELA_USER + " ( "
                + ID_USER + " INTEGER, "
                + NOME_USER + " TEXT, "
                + PESO + "REAL, "
                + DATA_NASC + " TEXT "
                + FATOR_SENSIBIL + "REAL, "
                + EMAIL + " EMAIL, "
                + DATA_REGISTRO + " TEXT); ";
        System.out.println(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_ALIMENTO);
        onCreate(db);
    }

    public ArrayList<Alimento> recuperaAlimentos(){
        ArrayList<Alimento> array_AL = new ArrayList<>();

        String selectQuery = "SELECT " + ID_ALIMENTO + " , " + NOME_ALIMENTO + " , " + TIPO_MEDIDA + " , " + GOUML + " , "
                + QNT_CARBOIDRATO + " , " + CALORIAS + " , " + QNT_CARB_G + " FROM " + TABELA_ALIMENTO;
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
                + EMAIL + " , " + DATA_REGISTRO + " FROM " + TABELA_USER;
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
                us.setDataRegistro(cursor.getString(cursor.getColumnIndex(DATA_REGISTRO)));
            } while (cursor.moveToNext());
        }db.close();
        return array_User;
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
            valores.put(DATA_REGISTRO, usuario.get(i).getDataRegistro());
            db.insert(TABELA_ALIMENTO, null, valores);
        }
        db.close();
    }

    public void limpaTabela(){
        SQLiteDatabase db = this.getWritableDatabase();
        String limpaQuery = "DELETE FROM " + TABELA_ALIMENTO;
        db.execSQL(limpaQuery);
        System.out.println(TABELA_ALIMENTO + " TRUNCADA!");
        db.close();
    }

}
