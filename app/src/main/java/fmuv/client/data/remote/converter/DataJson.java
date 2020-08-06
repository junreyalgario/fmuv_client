package fmuv.client.data.remote.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fmuv.client.data.remote.dto.Data;

public class DataJson {

    public static Gson create() {

        return new GsonBuilder()
                .registerTypeAdapter(Data.class, new JsonDataConverter<Data>())
                .create();
    }

}
