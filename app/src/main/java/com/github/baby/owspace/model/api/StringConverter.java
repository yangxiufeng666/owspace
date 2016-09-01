package com.github.baby.owspace.model.api;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/9/1
 * owspace
 */
public class StringConverter implements Converter<ResponseBody, String> {

    @Override
    public String convert(ResponseBody value) throws IOException {
        return value.string();
    }
}
