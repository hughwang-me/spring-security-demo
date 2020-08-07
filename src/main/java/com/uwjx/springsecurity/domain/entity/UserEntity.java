package com.uwjx.springsecurity.domain.entity;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.Table;

/**
 * hugh_user
 * @author 
 */
@Table(name = "hugh_user")
@Data
public class UserEntity {

    private Integer id;

    private String username;

    private String password;

    private Integer status;

}