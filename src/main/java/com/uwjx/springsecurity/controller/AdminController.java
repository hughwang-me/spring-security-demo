package com.uwjx.springsecurity.controller;

import com.uwjx.springsecurity.dao.UserMapper;
import com.uwjx.springsecurity.domain.ImageCode;
import com.uwjx.springsecurity.domain.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "admin")
@Slf4j
public class AdminController {

    @Resource
    UserMapper userMapper;

    @RequestMapping("/user")
    public String test(@RequestParam("name")String name){

        List<UserEntity> users = userMapper.selectAll();
        users.forEach(user -> {
            log.warn("user-> {}" , user.toString());
        });
        return "测试";
    }


    @RequestMapping("/code")
    public void code(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String sessionId = request.getSession().getId();
        log.warn("sessionId -> {}" , sessionId);
        ServletWebRequest webRequest = new ServletWebRequest(request);
        ImageCode imageCode = createImageCode(webRequest);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    private ImageCode createImageCode(ServletWebRequest request) {
        ImageCode imageCode = generate(request);


        return imageCode;
    }

    public ImageCode generate(ServletWebRequest request) {
        ImageCode imageCode = new ImageCode();
        int width = 120;
        int height = 60;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        imageCode.setCode(sRand);
        imageCode.setImage(image);
        return imageCode;
    }

    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
