package forum.org.hipda.data.net;

import android.support.annotation.Nullable;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.Util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by silong on 2015/6/28.
 */
public class ApiConnection implements Callable<String> {

    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "text/html; charset=UTF-8";

    private URL url;
    private String response;

    private ApiConnection(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public static ApiConnection createGET(String url) throws MalformedURLException {
        return new ApiConnection(url);
    }

    public String postSyncCall(Map<String,String> map){
        FormEncodingBuilder formEncodingBuilder=new FormEncodingBuilder();
        for(Map.Entry<String,String> entry:map.entrySet()){
            formEncodingBuilder.add(entry.getKey(),entry.getValue());
        }
        RequestBody requestBody=formEncodingBuilder.build();
        Request request=new Request.Builder().url(url)
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient = this.createClient();
        try {
            response=okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Do a request to an api synchronously.
     * It should not be executed in the main thread of the application.
     *
     * @return A string response
     */
    @Nullable
    public String requestSyncCall() {
        connectToApi();
        return response;
    }

    private void connectToApi() {
        OkHttpClient okHttpClient = this.createClient();
        final Request request = new Request.Builder()
                .url(this.url)

                //.addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .get()
                .build();

        try {
            Response response1=okHttpClient.newCall(request).execute();
            Headers responseHeaders = response1.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }
            this.response = new String(response1.body().bytes(), "GBK");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OkHttpClient createClient() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);
        okHttpClient.setConnectTimeout(15000, TimeUnit.MILLISECONDS);

        return okHttpClient;
    }

    @Override public String call() throws Exception {
        return requestSyncCall();
    }
}
