package com.xcyf.springcloud.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liaojiamin
 * @description: mybatis-plus必要对象注入
 * @date: 11:32 2021/5/11
 * @return 
 */
@Configuration
@MapperScan("com.xcyf.springcloud.mapper*")
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件，如果你不配置，分页插件将不生效
        // 指定数据库方言为 MYSQL
        PaginationInnerInterceptor pageInterceptor =new PaginationInnerInterceptor(DbType.MYSQL);
        //溢出总页数后是否进行处理
        pageInterceptor.setOverflow(false);
        //单页条数最多500
        pageInterceptor.setMaxLimit(500L);
        interceptor.addInnerInterceptor(pageInterceptor);
        return interceptor;
    }
}
