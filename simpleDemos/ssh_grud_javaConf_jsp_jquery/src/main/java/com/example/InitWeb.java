package com.example;

import com.example.conf.ApplicationConfig;
import com.example.conf.WebConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * created by dfk
 * 4/4/2022
 */
public class InitWeb extends AbstractAnnotationConfigDispatcherServletInitializer {
    //组件配置
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class};
    }

    //web 配置
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    //DispaterServlet 映射配置
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //配置过滤器
    @Override
    protected Filter[] getServletFilters() {
        //提前设置请求和相应编码
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceResponseEncoding(true);

        //表单支持 PUT 和delete请求
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();

        //PUT DELETE 的请求体可以被解析
        FormContentFilter formContentFilter = new FormContentFilter();
        return new Filter[]{characterEncodingFilter, hiddenHttpMethodFilter, formContentFilter};
    }
}
