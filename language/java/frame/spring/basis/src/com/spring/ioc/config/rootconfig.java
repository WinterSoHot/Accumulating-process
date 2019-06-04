package com.spring.ioc.config;

import com.spring.ioc.Part2ioc.CDConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by gudongxian on 2017/3/15
 * Spring JavaConfig. 导入其他配置文件步骤
 */
@Configuration
@Import(value = {CDConfig.class})
@ImportResource("classpath:config.xml")
public class rootconfig {
}
