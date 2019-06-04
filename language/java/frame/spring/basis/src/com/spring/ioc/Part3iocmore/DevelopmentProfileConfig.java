package com.spring.ioc.Part3iocmore;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 配置开发环境的Profile
 * Created by gudongxian on 2017/3/15.
 * 只有当环境处于 dev profile 处于 Active 配置的bean才会创建
 */
@Configuration
@Profile("dev")
public class DevelopmentProfileConfig {
}
