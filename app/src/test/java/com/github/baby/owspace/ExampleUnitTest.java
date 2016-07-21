package com.github.baby.owspace;

import com.github.baby.owspace.model.api.ApiClient;
import com.github.baby.owspace.model.entity.Detail;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void addition1() throws Exception {
        System.out.println("asdadadadadada");
        Call<Detail> detail = ApiClient.service.getDetail("api","getPost",292296,1);
        detail.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<Detail> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}