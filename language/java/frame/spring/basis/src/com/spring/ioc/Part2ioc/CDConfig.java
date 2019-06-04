package com.spring.ioc.Part2ioc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gudongxian on 2017/3/15.
 * JavaConfig
 * @ComponentScan 自动扫描
 */
@Configuration
@ComponentScan(basePackages = {"com.spring.ioc.Part2ioc"})
public class CDConfig {
}
