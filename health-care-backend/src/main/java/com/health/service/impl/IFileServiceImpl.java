package com.health.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.health.service.IFileService;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @author YueLiMin
 * @version 1.0.0
 * @since 11
 */
@Slf4j
@Component
public class IFileServiceImpl implements IFileService {
    private String url = "http://127.0.0.1:56090/minio";
    private String id = "d44e1e800ac74bd789987ff2868cca6b";
    private String ak = "JDJhJDEwJDFlUnYzdzUybjlrLkpTdEhFWmN4WC45ZlhhNkFIRzRYQTQxWENzNERydlpPY1B6akpIZmp5";
    private String sk = "JDJhJDEwJDFlUnYzdzUybjlrLkpTdEhFWmN4WC5JM3kzRi9WcWxSU0JIMkJmSElpZVllSnhHd0pRaEpl";

    @Override
    public String imageUpload(String fileName, byte[] image) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file",
                        fileName,
                        RequestBody.create(MediaType.parse("application/octet-stream"), image))
                .build();

        Request request = new Request.Builder()
                .url(url + "/image/" + fileName)
                .method("POST", body)
                .addHeader("AppId", id)
                .addHeader("AccessKey", ak)
                .addHeader("SecretKey", sk)
                .build();
        String string = Objects.requireNonNull(client.newCall(request).execute().body()).string();

        log.info("调用文件服务-获取响应信息: {}", string);

        Map<String, Object> map = JSONObject.parseObject(string, new TypeReference<Map<String, Object>>() {
        });

        if (!(Boolean) map.get("flag")) {
            throw new RuntimeException("文件上传失败, 请重新上传");
        }

        return map.get("data").toString();
    }
}
