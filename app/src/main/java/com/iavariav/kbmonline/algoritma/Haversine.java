package com.iavariav.kbmonline.algoritma;

import android.util.Log;

public class Haversine {

    private static final int RADIUS_BUMI = 6371; //km

    public static double hitungJarak(double latUser, double longUser,
                                     double latTujuan, double longTujuan) {

        // mengubah sudut yang diukur dalam derajat ke sudut yang kurang lebih sama
        double dLat = Math.toRadians((latTujuan - latUser));
        double dLong = Math.toRadians((longTujuan - longUser));

        Log.d("haversine", "Hasil (latTujuan-latawal) : " + dLat);
        Log.d("haversine", "Hasil (longTujuan-longawal) : " + dLong);

        latUser = Math.toRadians(latUser);
        latTujuan = Math.toRadians(latTujuan);

        // Rumus A namun disederhanakan menggunakan method haversin
        double a = haversin(dLat) + Math.cos(latUser) * Math.cos(latTujuan) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)); // rumus c

        Log.d("haversine", "Rumus haversine 1 : " + a);
        Log.d("haversine", "Rumus haversine 2 : " + c);

        return RADIUS_BUMI * c; // <-- d
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
