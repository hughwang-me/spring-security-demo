package com.uwjx.springsecurity.domain.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * hugh_user
 * @author 
 */
@Table(name = "hugh_role")
@Data
public class RoleEntity {

    private Integer id;

    private String name;

}