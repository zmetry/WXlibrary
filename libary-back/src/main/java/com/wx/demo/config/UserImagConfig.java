package com.wx.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("all")
@Configuration
public class UserImagConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        try {

            File directory1 = new File("images");
            File directory2 = new File("squareimg");
            String path1 = directory1.getCanonicalPath();
            String path2 = directory2.getCanonicalPath();
            registry.addResourceHandler("/static/images/**").addResourceLocations("classpath:/static/images/");
            //windows本地文件目录
            registry.addResourceHandler("/image/**").addResourceLocations("file:"+path1+"/");
            registry.addResourceHandler("/squareimg/**").addResourceLocations("file:"+path2+"/");
            super.addResourceHandlers(registry);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
