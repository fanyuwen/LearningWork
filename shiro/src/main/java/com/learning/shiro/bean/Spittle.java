package com.learning.shiro.bean;

import java.util.Date;
import java.util.Objects;

/**
 * @author fanyuwen
 * @date 2019/6/20 11:43
 */
public class Spittle {
    private final Long id;
    private final String message;
    private final Date time;
    private Double latitude;
    private Double longitude;

    public Spittle(String message, Date time) {
        this(message, time, null, null);
    }

    public Spittle(String message, Date time, Double latitude, Double longitude) {
        this.id = null;
        this.message = message;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Spittle)) {
            return false;
        }
        Spittle that = (Spittle) obj;
        return (that.id == id || id != null && that.id != null && Double.compare(id, that.id) == 0) &&
                (Objects.equals(time, that.time));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + (id == null ? 0 : id.hashCode());
        result = result * 31 + (time == null ? 0 : time.hashCode());
        return result;
    }
}
