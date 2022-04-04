package com.example.config;

import com.example.interceptor.HelloInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * created by dfk
 * 4/4/2022
 * 1 扫描组件 2 视图解析器 thymeleaf 3 view-controller 4 default-servlet-handler
 * 5 mvc注解 6 文件上传 7 异常处理 8 拦截器
 */
@Configuration
@ComponentScan({"com.example.controller", "com.example.interceptor"}) //1 扫描组件
@EnableWebMvc // 5 mvc 注解驱动
public class WebConfig implements WebMvcConfigurer {

    //------------2 thymeleaf 视图解析器 begin------------------
    //配置模板解析器
    @Bean
    public ITemplateResolver templateResolver() {
        WebApplicationContext con = ContextLoader.getCurrentWebApplicationContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(Objects.requireNonNull(con.getServletContext()));
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    //配置引擎
    @Bean
    public SpringTemplateEngine springTemplateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    //配置视图解析器
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine engine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(engine);
        return viewResolver;
    }

    //------------2 thymeleaf 视图解析器 end------------------

    //配置资源解析器 4 default-servlet-handler
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //添加拦截器8 拦截器

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有请求
        //测试发现没有拦截静态资源的请求
        registry.addInterceptor(new HelloInterceptor()).addPathPatterns("/**");
    }

    // 设置 3 view-controller
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hello").setViewName("hello");
    }

    //文件上传解析
    @Bean
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    //配置异常解析器,也可以通过@AdviceController实现
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("java.lang.ArithmeticException", "error");
        resolver.setExceptionMappings(properties);
        resolver.setExceptionAttribute("ex");
        resolvers.add(resolver);
    }
}
