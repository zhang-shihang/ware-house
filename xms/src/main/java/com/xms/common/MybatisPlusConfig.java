package com.xms.common;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//分页拦截器，注意配置
@Configuration
public class MybatisPlusConfig {
 @Bean
 public MybatisPlusInterceptor mybatisPlusInterceptor() {
 MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
// 换成MySQL
 interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
 return interceptor;
 }
}
