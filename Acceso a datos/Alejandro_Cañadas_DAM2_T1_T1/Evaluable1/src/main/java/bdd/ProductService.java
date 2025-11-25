package bdd;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import models.Productos;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import models.Productos;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ProductService {
    public List<Productos> fetchProducts() throws IOException {
        // Crear cliente HTTP
        OkHttpClient client = new OkHttpClient();

        // Solicitud a la URL para traer los productos
        Request request = new Request.Builder()
                .url("https://dummyjson.com/products")
                .build();

        // Respuesta del servidor
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonData = response.body().string();

                // Imprimir respuesta para verificar que contiene datos
                System.out.println("JSON recibido: " + jsonData);

                // Parsear JSON usando Gson
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);
                Type productListType = new TypeToken<List<Productos>>() {}.getType();

                return gson.fromJson(jsonObject.get("products"), productListType);
            } else {
                throw new IOException("Error en la respuesta: " + response.code());
            }
        }
    }
}