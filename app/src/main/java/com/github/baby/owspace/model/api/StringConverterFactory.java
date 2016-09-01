package com.github.baby.owspace.model.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/9/1
 * owspace
 */
public class StringConverterFactory extends Converter.Factory {

    public static StringConverterFactory create(){
        return new StringConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            return new StringConverter();
        }
        //其它类型我们不处理，返回null就行
        return null;
    }
}
