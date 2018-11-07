package pe.ibao.agromovil;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EvaluationModelParser {

    private final String SESSION = "evaluationModels";
    private static String downloaded ="";

    public static boolean download(Context ctx)  {
        boolean res=false;
        try{
            String str="";
            StringBuffer buf = new StringBuffer();
            InputStream is = ctx.getResources().openRawResource(R.raw.ejemplojson);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            if (is!=null) {
                while ((str = reader.readLine()) != null) {
                    buf.append(str + "\n" );
                }
            }
            is.close();
            downloaded=buf.toString();
            res=true;
        }catch (IOException e){
            res=false;
        }

        return res;
    }





}
