package test.com.spring.ioc.Part4aop; 

import com.spring.ioc.Part4aop.Config;
import com.spring.ioc.Part4aop.Perform;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* Test Tester. 
* 
* @author <Authors name> 
* @since
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
public class TestTest {

    @Autowired
       Perform perform;
@Before
public void before() throws Exception { 
}


@After
public void after() throws Exception { 
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain()  {
    perform.perform();
}


} 
