package pe.ibao.agromovil.utilities;

public class Utilities {

    public static final String DATABASE_NAME="data";

    public static final String TABLE_USUARIO="usuario",
            TABLE_USUARIO_COL_ID                ="id",
            TABLE_USUARIO_TYPECOL_ID            ="INTEGER",
            TABLE_USUARIO_COL_USER              ="user",
            TABLE_USUARIO_TYPECOL_USER          ="varchar(50)",
            TABLE_USUARIO_COL_PASSWORD          ="password",
            TABLE_USUARIO_TYPECOL_PASSWORD      ="varchar(50)",
            TABLE_USUARIO_COL_NAME              ="name",
            TABLE_USUARIO_TYPECOL_NAME          ="varchar(50)",
            TABLE_USUARIO_COL_LASTNAME          ="lastname",
            TABLE_USUARIO_TYPECOL_LASTNAME      ="varchar(50)";

    public static final String TABLE_EMPRESA="empresa",
            TABLE_EMPRESA_COL_ID                ="id",
            TABLE_EMPRESA_TYPECOL_ID            ="INTEGER",
            TABLE_EMPRESA_COL_NAME              ="name",
            TABLE_EMPRESA_TYPECOL_NAME          ="varchar(50)";

    public static final String TABLE_FUNDO  ="fundo",
            TABLE_FUNDO_COL_ID                  ="id",
            TABLE_FUNDO_TYPECOL_ID              ="INTEGER",
            TABLE_FUNDO_COL_NAME                ="name",
            TABLE_FUNDO_TYPECOL_NAME            ="varchar(50)",
            TABLE_FUNDO_COL_IDEMPRESA           ="idEmpresa",
            TABLE_FUNDO_TYPECOL_IDEMPRESA       ="INTEGER";

    public static final String TABLE_CULTIVO="cultivo",
            TABLE_CULTIVO_COL_ID                ="id",
            TABLE_CULTIVO_TYPECOL_ID            ="INTEGER",
            TABLE_CULTIVO_COL_NAME              ="name",
            TABLE_CULTIVO_TYPECOL_NAME          ="varchar(50)";

    public static final String TABLE_VARIEDAD="variedad",
            TABLE_VARIEDAD_COL_ID               ="id",
            TABLE_VARIEDAD_TYPECOL_ID           ="id",
            TABLE_VARIEDAD_COL_NAME             ="name",
            TABLE_VARIEDAD_TYPECOL_NAME         ="name",
            TABLE_VARIEDAD_COL_IDCULTIVO        ="idCultivo",
            TABLE_VARIEDAD_TYPECOL_IDCULTIVO    ="idCultivo";


    public static final String TABLE_FUNDOVARIEDAD="fundoVariedad",
            TABLE_FUNDOVARIEDAD_COL_ID="id",
            TABLE_FUNDOVARIEDAD_TYPECOL_ID="INTEGER",
            TABLE_FUNDOVARIEDAD_COL_IDFUNDO="idFundo",
            TABLE_FUNDOVARIEDAD_TYPECOL_IDFUNDO="INTEGER",
            TABLE_FUNDOVARIEDAD_COL_IDVARIEDAD="idVariedad",
            TABLE_FUNDOVARIEDAD_TYPECOL_IDVARIEDAD="INTEGER";

    public static final String TABLE_CONFIGURACIONCRITERIO="configuracionCriterio",
            TABLE_CONFIGURACIONCRITERIO_COL_ID                  ="id",
            TABLE_CONFIGURACIONCRITERIO_TYPECOL_ID              ="INTEGER",
            TABLE_CONFIGURACIONCRITERIO_COL_IDFUNDOVARIEDAD     ="idFundoVariedad",
            TABLE_CONFIGURACIONCRITERIO_TYPECOL_IDFUNDOVARIEDAD ="INTEGER",
            TABLE_CONFIGURACIONCRITERIO_COL_IDCRITERIO          ="idCriterio",
            TABLE_CONFIGURACIONCRITERIO_TYPECOL_IDCRITERIO      ="INTEGER";

    public static final String TABLE_CRITERIO       ="criterio",
            TABLE_CRITERIO_COL_ID                   ="id",
            TABLE_CRITERIO_TYPECOL_ID               ="INTEGER",
            TABLE_CRITERIO_COL_NAME                 ="name",
            TABLE_CRITERIO_TYPECOL_NAME             ="varchar(50)",
            TABLE_CRITERIO_COL_TIPO                 ="tipo",
            TABLE_CRITERIO_TYPECOL_TIPO             ="varchar(50)", //"int","string","list",float
            TABLE_CRITERIO_COL_MAGNITUD             ="magnitud",
            TABLE_CRITERIO_TYPECOL_MAGNITUD         ="varchar(50)",//"°C" , "mm." , "ml/s²" , "bajo-medio-alto"
            TABLE_CRITERIO_COL_IDTIPOINSPECCION     ="idTipoInspeccion",
            TABLE_CRITERIO_TYPECOL_IDTIPOINSPECCION ="INTEGER";

    public static final String TABLE_TIPOINSPECCION="tipoInspeccion",
            TABLE_TIPOINSPECCION_COL_ID             ="id",
            TABLE_TIPOINSPECCION_TYPECOL_ID         ="INTEGER",
            TABLE_TIPOINSPECCION_COL_NAME           ="name",
            TABLE_TIPOINSPECCION_TYPECOL_NAME       ="varchar(50)";

    //SCRIPTS SQL CREATE TABLES

    public static final String CREATE_TABLE_TIPOINSPECCION=
            "CREATE TABLE "+TABLE_TIPOINSPECCION+" (" +
                    TABLE_TIPOINSPECCION_COL_ID     +" "+TABLE_TIPOINSPECCION_TYPECOL_ID+" PRIMARY KEY," +
                    TABLE_TIPOINSPECCION_COL_NAME   +" "+TABLE_TIPOINSPECCION_TYPECOL_NAME+
                    ")";
    public static final String CREATE_TABLE_CRITERIO =
            "CREATE TABLE "+TABLE_CRITERIO+" (" +
                    TABLE_CRITERIO_COL_ID               +" "+TABLE_CRITERIO_TYPECOL_ID+" PRIMARY KEY,"+
                    TABLE_CRITERIO_COL_NAME             +" "+TABLE_CRITERIO_TYPECOL_NAME+","+
                    TABLE_CRITERIO_COL_TIPO             +" "+TABLE_CRITERIO_TYPECOL_TIPO+","+
                    TABLE_CRITERIO_COL_MAGNITUD         +" "+TABLE_CRITERIO_TYPECOL_MAGNITUD+","+
                    TABLE_CRITERIO_COL_IDTIPOINSPECCION +" "+TABLE_CRITERIO_TYPECOL_IDTIPOINSPECCION+
                    ")";

    public static final String CREATE_TABLE_CONFIGURACIONCRITERIO =
            "CREATE TABLE "+TABLE_CONFIGURACIONCRITERIO+" (" +
                    TABLE_CONFIGURACIONCRITERIO_COL_ID                  +" "+TABLE_CONFIGURACIONCRITERIO_TYPECOL_ID+" PRIMARY KEY," +
                    TABLE_CONFIGURACIONCRITERIO_COL_IDFUNDOVARIEDAD     +" "+TABLE_CONFIGURACIONCRITERIO_TYPECOL_IDFUNDOVARIEDAD+","+
                    TABLE_CONFIGURACIONCRITERIO_COL_IDCRITERIO          +" "+TABLE_CONFIGURACIONCRITERIO_TYPECOL_IDCRITERIO+
                    ")";

    public static final String CREATE_TABLE_FUNDOVARIEDAD =
            "CREATE TABLE "+TABLE_FUNDOVARIEDAD+" (" +
                    TABLE_FUNDOVARIEDAD_COL_ID        +" "+TABLE_FUNDOVARIEDAD_TYPECOL_ID+" PRIMARY KEY," +
                    TABLE_FUNDOVARIEDAD_COL_IDFUNDO   +" "+TABLE_FUNDOVARIEDAD_TYPECOL_IDFUNDO+","+
                    TABLE_FUNDOVARIEDAD_COL_IDVARIEDAD+" "+TABLE_FUNDOVARIEDAD_TYPECOL_IDVARIEDAD+
                    ")";

    public static final String CREATE_TABLE_VARIEDAD =
            "CREATE TABLE "+TABLE_VARIEDAD+" (" +
                    TABLE_VARIEDAD_COL_ID        +" "+TABLE_VARIEDAD_TYPECOL_ID+" PRIMARY KEY," +
                    TABLE_VARIEDAD_COL_NAME      +" "+TABLE_VARIEDAD_TYPECOL_NAME+","+
                    TABLE_VARIEDAD_COL_IDCULTIVO +" "+TABLE_VARIEDAD_TYPECOL_IDCULTIVO+
                    ")";

    public static final String CREATE_TABLE_CULTIVO =
            "CREATE TABLE "+TABLE_CULTIVO+" (" +
                    TABLE_CULTIVO_COL_ID        +" "+TABLE_CULTIVO_TYPECOL_ID+" PRIMARY KEY," +
                    TABLE_CULTIVO_COL_NAME      +" "+TABLE_CULTIVO_TYPECOL_NAME+
                    ")";
    public static final String CREATE_TABLE_FUNDO =
            "CREATE TABLE "+TABLE_FUNDO+" (" +
                    TABLE_FUNDO_COL_ID        +" "+TABLE_FUNDO_TYPECOL_ID+" PRIMARY KEY," +
                    TABLE_FUNDO_COL_NAME      +" "+TABLE_FUNDO_TYPECOL_NAME+","+
                    TABLE_FUNDO_COL_IDEMPRESA +" "+TABLE_FUNDO_TYPECOL_IDEMPRESA+
                    ")";

    public static final String CREATE_TABLE_EMPRESA =
            "CREATE TABLE "+TABLE_EMPRESA+" (" +
                    TABLE_EMPRESA_COL_ID        +" "+TABLE_EMPRESA_TYPECOL_ID+" PRIMARY KEY," +
                    TABLE_EMPRESA_COL_NAME      +" "+TABLE_EMPRESA_TYPECOL_NAME+
                    ")";


    public static final String CREATE_TABLE_USUARIO =
            "CREATE TABLE "+TABLE_USUARIO+" (" +
                    TABLE_USUARIO_COL_ID        +" "+TABLE_USUARIO_TYPECOL_ID+" PRIMARY KEY," +
                    TABLE_USUARIO_COL_USER      +" "+TABLE_USUARIO_TYPECOL_USER+"," +
                    TABLE_USUARIO_COL_PASSWORD  +" "+TABLE_USUARIO_TYPECOL_PASSWORD+"," +
                    TABLE_USUARIO_COL_NAME      +" "+TABLE_USUARIO_TYPECOL_NAME+","+
                    TABLE_USUARIO_COL_LASTNAME  +" "+TABLE_USUARIO_TYPECOL_LASTNAME+
                    ")";

}
