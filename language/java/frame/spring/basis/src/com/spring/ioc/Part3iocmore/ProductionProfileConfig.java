package com.spring.ioc.Part3iocmore;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 配置环境
 * Created by gudongxian on 2017/3/15.
 */
@Configuration
@Profile("prod")
public class ProductionProfileConfig {
}
