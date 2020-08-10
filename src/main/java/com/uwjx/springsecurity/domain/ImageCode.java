package com.uwjx.springsecurity.domain;

import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public class ImageCode {

    private String uuid;
    private String code;
    private BufferedImage image;
    private int expired;


}
