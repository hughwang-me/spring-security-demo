package com.uwjx.springsecurity.domain.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * hugh_user
 * @author 
 */
@Table(name = "hugh_user_role_ref")
@Data
public class UserRoleRefEntity {

    private Integer id;
    private Integer userId;
    private Integer roleId;

}