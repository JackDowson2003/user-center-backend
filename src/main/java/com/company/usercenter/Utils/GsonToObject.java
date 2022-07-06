package com.company.usercenter.Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * @author chenlong
 * @version 2020.2.3
 * @Date 2022/6/22 23:34
 */
public class GsonToObject <T>{

    public T toObject(Gson gson,String src){
        Type type = new TypeToken<T>(){}.getType();
        return gson.fromJson(src, type);
    }

}
