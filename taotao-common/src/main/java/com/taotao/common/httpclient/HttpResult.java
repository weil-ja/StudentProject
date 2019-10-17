package com.taotao.common.httpclient;

public class HttpResult {
    private Integer code;
    private String body;

    public HttpResult() {
    }

    public HttpResult(Integer code, String body) {
        this.code = code;
        this.body = body;
    }

    public Integer getCode() {
        return code;
    }

    public HttpResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getBody() {
        return body;
    }

    public HttpResult setBody(String body) {
        this.body = body;
        return this;
    }
}
