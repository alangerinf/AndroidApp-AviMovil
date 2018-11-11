package pe.ibao.agromovil.models.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.ConexionSQLiteHelper;
import pe.ibao.agromovil.models.vo.entitiesDB.EmpresaVO;
import static pe.ibao.agromovil.utilities.Utilities.DATABASE_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EMPRESA;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EMPRESA_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EMPRESA_COL_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FUNDO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FUNDO_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FUNDO_COL_IDEMPRESA;

public class EmpresaDAO {

    Context ctx;
    ConexionSQLiteHelper c;
    public EmpresaDAO(Context ctx) {
        c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        this.ctx=ctx;
    }

    public EmpresaVO consultarEmpresaByid(int id){
        SQLiteDatabase db = c.getReadableDatabase();
        EmpresaVO temp = null;
        try{
            temp = new EmpresaVO();
            Cursor cursor = db.rawQuery("SELECT E."+TABLE_EMPRESA_COL_ID+", E."+TABLE_EMPRESA_COL_NAME+" FROM "+TABLE_EMPRESA+" as E",null);
            cursor.moveToFirst();
                temp.setId(cursor.getInt(0));
                temp.setName(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT);
        }
        return temp;
    }

    public EmpresaVO consultarEmpresaByIdFundo(int idFundo){

        SQLiteDatabase db = c.getReadableDatabase();
        EmpresaVO empresaVO = null;
        try{
            empresaVO = new EmpresaVO();
            Cursor cursor = db.rawQuery(
                    " SELECT "+"E."+TABLE_EMPRESA_COL_ID+", "+"E."+TABLE_EMPRESA_COL_NAME+
                            " FROM "+TABLE_EMPRESA+" as E, "+TABLE_FUNDO+" as F"+
                            " WHERE "+"F."+TABLE_FUNDO_COL_ID+"="+  String.valueOf(idFundo)+
                            " AND "+"F."+TABLE_FUNDO_COL_IDEMPRESA+" = "+"E."+TABLE_EMPRESA_COL_ID, null);
            cursor.moveToFirst();
            empresaVO.setId(cursor.getInt(0));
            empresaVO.setName(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT).show();
        }
        return empresaVO;
    }
    public List<EmpresaVO> listEmpresas(){
        SQLiteDatabase db = c.getReadableDatabase();
        List<EmpresaVO> empresas = new  ArrayList<EmpresaVO>();
        try{
            String[] campos = {TABLE_EMPRESA_COL_ID,TABLE_EMPRESA_COL_NAME};
            Cursor cursor= db.query(TABLE_EMPRESA,campos,null,null,null,null,null);
            while(cursor.moveToNext()){
                EmpresaVO temp = new EmpresaVO();
                    temp.setId(cursor.getInt(0));
                    temp.setName(cursor.getString(1));
                empresas.add(temp);
                Toast.makeText(ctx,temp.getName(),Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT).show();
        }
        return empresas;
    }
    public void EmpresaDAOCloseConection() {
        c.close();
    }


}
