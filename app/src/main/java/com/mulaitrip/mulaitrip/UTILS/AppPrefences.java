package com.mulaitrip.mulaitrip.UTILS;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.mulaitrip.mulaitrip.R;

/**
 * Created by Master on 23/07/2019.
 */

public class AppPrefences {
    public static SharedPreferences PREFERENCIAS;

    public static SharedPreferences get(@NonNull Context ctx) {
        instanciar(ctx);
        return PREFERENCIAS;
    }

    /**
     * Duevuelve un editor SharedPreferences
     * @param ctx Contexto desde donde se llama
     * @return SharedPreferences.Editor
     */
    public static SharedPreferences.Editor getEditor(@NonNull Context ctx) {
        instanciar(ctx);
        return PREFERENCIAS.edit();
    }

    /**
     * Instancia un objeto SharedPreferences
     * @param ctx Contexto desde donde se llama
     */
    private static void instanciar (@NonNull Context ctx) {
        if(PREFERENCIAS == null)
            PREFERENCIAS = ctx.getSharedPreferences(appName(ctx), Context.MODE_PRIVATE);
    }

    /**
     * Para definir el fichero de preferencias igual que el nombre de la App
     * @param ctx Contexto desde donde se llama
     * @return String
     */
    private static String appName(@NonNull Context ctx) {
        return ctx.getString(R.string.app_name);
    }
}
