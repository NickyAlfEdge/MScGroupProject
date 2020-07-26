package com.team.project.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ZQJ
 * @date 4/23/2020
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.team.project.mapper"})
public class MybatisConfig {
}
