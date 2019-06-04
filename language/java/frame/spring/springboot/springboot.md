# SpringBoot
## chapter1 
1. 端口的配置 server.port
2. spring.profile.active 生产环境的配置
3. 在配置文件自定义一些数据 ,通过@Value("${name}")获取
4. Controller的配置 请求方法等等@ModelAttribute @PathVariable 的使用
5. Aspect 的使用 切面AOP如下

```
@Aspect
@Component
public class HttpAspect {
    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Before("execution(public * com.springboot.chapter1.web.*Controller.*(..))")
    public void httpBefore(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url={}", request.getRequestURL());
        //id
        logger.info("ip={}", request.getRemoteAddr());
        //method
        logger.info("method={}", request.getMethod());
        //类方法
        logger.info("class_method={}", point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        //参数
        logger.info("args={}", point.getArgs());
    }
}
```
## chapter2 freemarker 模板的使用
>index.ftl  文件 在请求向Resp域中加入host值

```
<!DOCTYPE html>
<html>
    <head lang="en">
        <meta charset="UTF-8" />
        <title></title>
    </head>
    <body>
        FreeMarker模板引擎
        <h1>${host}</h1>
    </body>
</html>
```
## chapter3 统一异常处理 自定义异常信息

```
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ModelAndView defaultException(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", e);
        mv.addObject("url", req.getRequestURL());
        return mv;
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public ErrorInfo myExceptionHandler(HttpServletRequest req, MyException e) throws Exception {
        ErrorInfo<String> errorInfo = new ErrorInfo<>();
        errorInfo.setCode(ErrorInfo.ERROR);
        errorInfo.setMessage(e.getMessage());
        errorInfo.setUrl(req.getRequestURL().toString());
        errorInfo.setData("Error");
        return errorInfo;
    }
}

```

## chapter4 使用jdbcTemplate 访问MySQL
1. 配置数据源 等信息
2. @Autowired JdbcTemplate jdbcTemplate; 获取
3. 执行Sql

## chapter5 JPA 
1. 设置JPA 属性
2. 创建实体类 增加JPA注解 @Entity @Id  @Column 等
3. 创建interface 集成 JPA中的接口
4. 通过依赖注入获取 接口对象 进行数据库操作
5. 对接口进行扩展 使用 @Query  或者 采用命名规则实现查询

## chapter6 db多数据源的配置 以及优先级的配置
1. application.properties文件

```
spring.datasource.primary.url=jdbc:mysql://localhost:3306/test1
spring.datasource.primary.username=root
spring.datasource.primary.password=root
spring.datasource.primary.driver-class-name=com.mysql.jdbc.Driver

spring.datasource.secondary.url=jdbc:mysql://localhost:3306/test2
spring.datasource.secondary.username=root
spring.datasource.secondary.password=root
spring.datasource.secondary.driver-class-name=com.mysql.jdbc.Driver
```

2. 多数据源配置

```
/**
 * 配置多个数据库源
 */
@Configuration
public class DataSourceConf {

    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("secondaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
```

3. 使用   @Qualifier 注解过滤依赖注入

## chapter7 jpa 配置  在chapter6的基础上对多个数据源进行配置
> Code

```
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryBeanPrimary",
        transactionManagerRef = "transactionManagerPrimary",
        basePackages = {"com.springboot.chapter7jpaconf.domain.a"} //设置Repository所在位置
)
public class PrimaryConfig {

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Primary
    @Bean(name = "entityManagerFactoryBeanPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanPrimary(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(primaryDataSource)
                .properties(getVendorProperties(primaryDataSource))
                .packages("com.springboot.chapter7jpaconf.domain.a") //设置实体所在的位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }


    @Primary
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryBeanPrimary(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "transactionManagerPrimary")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryBeanPrimary(builder).getObject());
    }

```

## chapter redis 的使用
1. 配置redis 对应属性
2. 创建实体 必须实现 Serializable 接口
3. redis 自定义redis 序列化

```
public class RedisObjectSerializer implements RedisSerializer<Object> {

    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    static final byte [] EMPTY_ARRAY = new byte[0];

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o==null){
            return EMPTY_ARRAY;
        }

        try {
            return         serializer.convert(o);
        }catch (Exception e){
            return EMPTY_ARRAY;
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (isEmpty(bytes)){
            return null;
        }
        try {
            return deserializer.convert(bytes);
        }catch (Exception e){
            throw new SerializationException("不能反序列化",e);
        }
    }

    private boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }
}
```

4. redis配置

```
@Configuration
public class RedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate<String,User> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,User> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

}
```
5. 单元测试

```
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<String,User> userRedisTemplate;

    @Test
    public void test() throws Exception {

        stringRedisTemplate.opsForValue().set("hello","123");

        Assert.assertEquals("123",stringRedisTemplate.opsForValue().get("hello"));

        // 保存对象
        User user = new User("超人", 20);
        userRedisTemplate.opsForValue().set(user.getName(), user);

        user = new User("蝙蝠侠", 30);
        userRedisTemplate.opsForValue().set(user.getName(), user);

        user = new User("蜘蛛侠", 40);
        userRedisTemplate.opsForValue().set(user.getName(), user);

        Assert.assertEquals(20, userRedisTemplate.opsForValue().get("超人").getAge().longValue());
        Assert.assertEquals(30, userRedisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
        Assert.assertEquals(40, userRedisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());

    }
}
```
## chapter8 mongodb 
1. 配置数据源地址
2. 创建实体 
3. 创建接口集成JPA提供的接口


## chapter9 mysql 整合 mybatis
> 创建Mapper类

```
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user u WHERE u.name=#{name} ")
    User findByName(@Param("name") String name);


    @Insert("INSERT INTO user(name,age) VALUES(#{name},#{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);


    // 深入使用mybaits annotations

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    @Select("SELECT u.id,u.name,u.age FROM user u")
    List<User> findAll();

    @Update("UPDATE user SET age=#{age} WHERE name=#{name}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id=#{id}")
    void delete(Long id);

    @Insert("INSERT INTO user(name, age) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);
}
```

## chapter 整合Swagger2 接口文档
1. Swagger2 配置

```
@Configuration
@EnableSwagger2
public class Swagger2 {
    /**
     * 访问
     * http://localhost:8080/swagger-ui.html 查看RestFul 文档
     *
     * @return
     */

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springboot.charpter3.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot 使用Swagger2 构建RESTFUL API")
                .description("gudongxian")
                .contact("gudongxian")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
```

2. Controller 加Swagger2 注解

```
 @ApiOperation(value = "获取所有用户")
    @GetMapping("")
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>(users.values());
        return userList;
    }

    @ApiOperation(value = "创建用户", notes = "根据User用户创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体", required = true, dataType = "User")
    @PostMapping("")
    public String postUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return "success";
    }
```

## chapter11 Schedule 实现定时任务
1. 在 启动类中加入 @EnableScheduling

```
/**
 * 定时任务
 */
@Component
public class ScheduleTask {

    private static final SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 3000L)
    public void repeatCurrentTime() {
        System.out.println("Current Time is " + sf.format(new Date()));
    }
}

```
## chapter12 Aysc  异步任务
> 在启动类中加入 @EnableAsync


```
/**
 * 异步任务
 *
 * @author gudongxian
 * @create 2017-11-22 下午3:12
 **/
@Component
public class AsyncTask {

    public static Random random = new Random();

    @Async
    public Future<String> doTaskOne() throws Exception {
        System.out.println("do Task one");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("completing task one spends " + (end - start) + " ms");
        return new AsyncResult<>("task one completed");
    }

    @Async
    public Future<String> doTaskTwo() throws Exception {
        System.out.println("do Task Two");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("completing task Two spends " + (end - start) + " ms");
        return new AsyncResult<>("task Two completed");
    }


    @Async
    public Future<String> doTaskThree() throws Exception {
        System.out.println("do Task Three");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("completing task Three spends " + (end - start) + " ms");
        return new AsyncResult<>("task Three completed");
    }


}
```

```
@Autowired
    AsyncTask asyncTask;

    @Test
    public void test() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> doTaskOne = asyncTask.doTaskOne();
        Future<String> doTaskTwo = asyncTask.doTaskTwo();
        Future<String> doTaskThree = asyncTask.doTaskThree();


        while (true) {
            if (doTaskOne.isDone() && doTaskTwo.isDone() && doTaskThree.isDone()) {
                System.out.println(doTaskOne.get());
                System.out.println(doTaskTwo.get());
                System.out.println(doTaskThree.get());
                break;
            }

            Thread.sleep(1000L);
        }

        long end = System.currentTimeMillis();
        System.out.println("Tasks Completed that spends " + (end - start) + "ms");
    }
```
##chapter 13 log4j 配置
>log4j.properties

```
# LOG4J配置
log4j.rootCategory=INFO, stdout, file, errorfile
#log4j.category.com.springboot.log4j=DEBUG, didifile
log4j.category.com.springboot.log4j=${logging.level.gu}, didifile 
log4j.logger.error=errorfile

# 控制台输出
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1}:%L - %m%n

# root日志输出
log4j.appender.file=org.apache.log4j.DailyRollingFileAppender
log4j.appender.file.file=logs/all.log
log4j.appender.file.DatePattern='.'yyyy-MM-dd
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1}:%L - %m%n

# error日志输出
log4j.appender.errorfile=org.apache.log4j.DailyRollingFileAppender
log4j.appender.errorfile.file=logs/error.log
log4j.appender.errorfile.DatePattern='.'yyyy-MM-dd
log4j.appender.errorfile.Threshold = ERROR
log4j.appender.errorfile.layout=org.apache.log4j.PatternLayout
log4j.appender.errorfile.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1}:%L - %m%n

# com.didispace下的日志输出
log4j.appender.didifile=org.apache.log4j.DailyRollingFileAppender
log4j.appender.didifile.file=logs/my.log
log4j.appender.didifile.DatePattern='.'yyyy-MM-dd
log4j.appender.didifile.layout=org.apache.log4j.PatternLayout
log4j.appender.didifile.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1}:%L ---- %m%n
```
## chapter14 log4j 实现自定义日志 保存到mongodb

```
# LOG4J配置
log4j.rootCategory=INFO, stdout
log4j.logger.mongodb=INFO, mongodb

# 控制台输出
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1}:%L - %m%n

# mongodb输出
log4j.appender.mongodb=com.springboot.chapter14log4jweb.log.MongoAppender
log4j.appender.mongodb.connectionUrl=mongodb://localhost:27017
log4j.appender.mongodb.databaseName=logs
log4j.appender.mongodb.collectionName=logs_request
```

```
public class MongoAppender extends AppenderSkeleton {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<BasicDBObject> logsCollection;

    private String connectionUrl;
    private String databaseName;
    private String collectionName;

    @Override
    protected void append(LoggingEvent loggingEvent) {

        if(mongoDatabase == null) {
            MongoClientURI connectionString = new MongoClientURI(connectionUrl);
            mongoClient = new MongoClient(connectionString);
            mongoDatabase = mongoClient.getDatabase(databaseName);
            logsCollection = mongoDatabase.getCollection(collectionName, BasicDBObject.class);
        }
        logsCollection.insertOne((BasicDBObject) loggingEvent.getMessage());

    }

    @Override
    public void close() {
        if(mongoClient != null) {
            mongoClient.close();
        }
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
```
> 日志切面

```
/**
 * @author gudongxian
 * @create 2017-11-30 下午9:45
 * @Order标记定义了组件的加载顺序。
 **/
@Aspect
@Component
@Order(1)
public class LogWebAspect {

    private Logger logger = Logger.getLogger("mongodb");

    @Pointcut("execution(public * com.springboot.chapter14log4jweb.web.*.*(..))")
    public void weblog() {
    }

    @Before("weblog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        BasicDBObject loginfo = getBasicDBObject(request, joinPoint);
        logger.info(loginfo);
    }

    private BasicDBObject getBasicDBObject(HttpServletRequest request, JoinPoint joinPoint) {
        // 基本信息
        BasicDBObject r = new BasicDBObject();
        r.append("requestURL", request.getRequestURL().toString());
        r.append("requestURI", request.getRequestURI());
        r.append("queryString", request.getQueryString());
        r.append("remoteAddr", request.getRemoteAddr());
        r.append("remoteHost", request.getRemoteHost());
        r.append("remotePort", request.getRemotePort());
        r.append("localAddr", request.getLocalAddr());
        r.append("localName", request.getLocalName());
        r.append("method", request.getMethod());
        r.append("headers", getHeadersInfo(request));
        r.append("parameters", request.getParameterMap());
        r.append("classMethod", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        r.append("args", Arrays.toString(joinPoint.getArgs()));
        return r;
    }

    /**
     * 获取头信息
     *
     * @param request
     * @return
     */
    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}

```
##　chapter15 
/**
 * 在Spring Boot的众多Starter POMs中有一个特殊的模块，
 * 它不同于其他模块那样大多用于开发业务功能或是连接一些其他外部资源。
 * 它完全是一个用于暴露自身信息的模块，所以很明显，它的主要作用是用于监控与管理，
 * 它就是：spring-boot-starter-actuator。
 */

> application,yml management:
  security:
    enabled: false
    
## chapter16 cache 缓存的实现
1. 添加jar包
2. 添加注解@EnableCache

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>
```

```
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User,Long> {

    @Cacheable(key = "#p0",condition = "#p0.length() < 10")
    User findByName(String name);

}

```

```
<!--encahe.xml-->
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="encache.xml">

    <cache name="users"
           maxEntriesLocalHeap="200"
           timeToLiveSeconds="600">
    </cache>

</ehcache>

```
## chapter Secuity 权限控制
1. 安全配置

```
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll() //允许所有用户访问
                .anyRequest().authenticated() //其他需要认证
                .and()
                .formLogin()  //表单登录
                .loginPage("/login") //登录的地址  认证失败 重定向 /login?error
                .permitAll()
                .and()
                .logout() //登出 重定向 /login?logout
                .permitAll();
    }
//不配置内存用户默认用户名为user
//    /**
//     * 配置内存用户
//     */
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()//记忆认证
//                .withUser("user").password("password").roles("USER");
//    }
}

```
## chapter17 redis 实现缓存
1. @EnableCache注解
2. 配置redis 数据源 数据库  JPA配置
3. 数据仓库配置

```
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User,Long> {

    //根据名字 判断你是否从缓存中取出数据
    @Cacheable(key = "#p0")
    User findByName(String name);

    @Cacheable(key = "#p0.name")
    User save(User user);
}

```

## chapter 18 邮件支持
1. application.properties 邮件配置

```
spring.mail.host=smtp.qq.com
spring.mail.username=gudongxian@qq.com
#授权码
spring.mail.password=授权码  
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>
```


```
@Autowired
    JavaMailSender sender;

    @Test
    public void sendSimpleMail() {
        SimpleMailMessage smsg = new SimpleMailMessage();
        smsg.setFrom("gudongxian@qq.com");
        smsg.setTo("gudongxian@qq.com");
        smsg.setSubject("主题:简单邮件");
        smsg.setText("测试邮件内容");

        sender.send(smsg);
    }

    @Test
    public void sendAttachmentsMail() throws Exception {

        MimeMessage mimeMessage = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("gudongxian@qq.com");
        helper.setTo("gudongxian@qq.com");
        helper.setSubject("主题：有附件");
        helper.setText("有附件的邮件");

        FileSystemResource file = new FileSystemResource(new File("background.jpg"));
        helper.addAttachment("附件-1.jpg", file);
        helper.addAttachment("附件-2.jpg", file);

        sender.send(mimeMessage);
    }

    @Test
    public void sendInlineMail() throws Exception {

        MimeMessage mimeMessage = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("gudongxian@qq.com");
        helper.setTo("gudongxian@qq.com");
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html><body><img src=\"cid:background\" ></body></html>", true);

        FileSystemResource file = new FileSystemResource(new File("background.jpg"));
        helper.addInline("background", file);

        sender.send(mimeMessage);
    }
```
## chapter 状态机 statemachine

```
        <dependency>
            <groupId>org.springframework.statemachine</groupId>
            <artifactId>spring-statemachine-core</artifactId>
            <version>1.2.0.RELEASE</version>
        </dependency>
```
1. 创建状态的枚举类
2. 创建事件的枚举类
3. 配置状态机

```
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
                .initial(States.UNPAID)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        super.configure(transitions);
        transitions
                .withExternal()
                .source(States.UNPAID).target(States.WAITING_FOR_RECEIVE)
                .event(Events.PAY)
                .and()
                .withExternal()
                .source(States.WAITING_FOR_RECEIVE).target(States.DONE)
                .event(Events.RECEIVE);
    }
}
```

4. 事件配置

```
@WithStateMachine
public class EventConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @OnTransition(target = "UNPAID")
    public void create() {
        logger.info("订单创建完成,待支付");
    }

    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void pay() {
        logger.info("用户支付完成,待收货.");
    }

    @OnTransitionStart(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void payStart() {
        logger.info("用户完成支付，待收货: start");
    }

    @OnTransitionEnd(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void payEnd() {
        logger.info("用户完成支付，待收货: end");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive() {
        logger.info("用户已收货，订单完成");
    }
}
```
## chapter maven  git插件

```
            <plugin>
                <!--maven-git-commit-id-plugin插件，jar包带上git commit相关信息-->
                <groupId>pl.project13.maven</groupId>
                <artifactId>git-commit-id-plugin</artifactId>
                <version>2.1.15</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>revision</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <dotGitDirectory>${project.basedir}/.git</dotGitDirectory>
                </configuration>
            </plugin>
```


























 



"SpringBoot_base" 
