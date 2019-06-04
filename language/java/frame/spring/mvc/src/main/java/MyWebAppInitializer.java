import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import spitter.config.RootConfig;
import spitter.config.WebConfig;

/**
 * Created by gudongxian on 2017/3/23.
 * 配置DispatcherServlet 前段控制器 支持Servlet 3.0
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
