package com.learning.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author fanyuwen
 * @date 2019/7/2 10:43
 */
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class RestRequest<T> implements Serializable {
    private static final long serialVersionUID = -1593488825638534777L;

    private RestRequestHeader header = new RestRequestHeader();

    private T body;

    public RestRequest() {
    }

    public RestRequest(T body) {
        this.body = body;
    }

    public static <T> RestRequest<T> instance(T body) {
        return new RestRequest<>(body);
    }

    public RestRequestHeader getHeader() {
        return header;
    }

    public void setHeader(RestRequestHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RestRequest)) {
            return false;
        }
        RestRequest<T> other = (RestRequest<T>) obj;
        RestRequestHeader otherHeader = other.header;
        if (otherHeader != header && (otherHeader == null || !otherHeader.equals(header))) {
            return false;
        }
        T otherBody = other.body;
        return otherBody == body || otherBody != null && otherBody.equals(body);
    }

    @Override
    public int hashCode() {
        int result = (1 << 4) + 1;
        final int value = (1 << 5) - 1;
        result = result * value + (header == null ? 0 : header.hashCode());
        result = result * value + (body == null ? 0 : body.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "RestRequest(header=" + getHeader() + ", body=" + getBody() + ")";
    }
}
