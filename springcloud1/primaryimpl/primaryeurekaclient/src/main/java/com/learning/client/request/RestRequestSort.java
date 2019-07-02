package com.learning.client.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author fanyuwen
 * @date 2019/7/2 9:52
 */
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class RestRequestSort implements Serializable {
    private static final long serialVersionUID = -8332278413459014922L;
    private String field;
    private String type;

    public RestRequestSort() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RestRequestSort)) {
            return false;
        }
        RestRequestSort other = (RestRequestSort) obj;
        String other_field = other.field;
        if (!Objects.equals(other_field, field)) {
            return false;
        }
        String other_type = other.type;

        return Objects.equals(other_type, type);
    }

    @Override
    public int hashCode() {
        int result = (1 << 4) + 1;
        final int value = (1 << 5) - 1;
        result = value * result + (field == null ? 0 : field.hashCode());
        result = value * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "RestRequestSort(field=" + getField() + ", type=" + getType() + ")";
    }
}