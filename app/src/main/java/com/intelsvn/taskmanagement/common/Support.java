package com.intelsvn.taskmanagement.common;

import java.util.ArrayList;

public class Support {
    public static String[] convertParams(ArrayList<String> params) {
        String[] strParams = null;
        if (params != null) {
            strParams = new String[params.size()];
            for (int i = 0; i < params.size(); i++) {
                strParams[i] = params.get(i);
            }
        }
        return  strParams;
    }
}
