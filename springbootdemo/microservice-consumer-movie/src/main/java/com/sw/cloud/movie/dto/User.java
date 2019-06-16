package com.sw.cloud.movie.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author SONGWEI
 * @version 1.0
 * Date: 2019/6/16/016
 */
@Data
public class User {

    private Long id;

    private String userName;

    private String name;

    private Integer age;

    private BigDecimal balance;
}
