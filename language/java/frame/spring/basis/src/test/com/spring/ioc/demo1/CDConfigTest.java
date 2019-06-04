package test.com.spring.ioc.demo1;

import com.spring.ioc.Part2ioc.CDConfig;
import com.spring.ioc.Part2ioc.CompactDisc;
import com.spring.ioc.Part2ioc.MediaPlayer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDConfig.class)
public class CDConfigTest {

    @Autowired
    CompactDisc compactDisc;

    @Test
    public  void cdshouldNotBeNul(){
        Assert.assertNotNull(compactDisc);
    }
    @Autowired
    MediaPlayer player;
    @Test
    public  void play(){
        player.play();
        Assert.assertEquals("","");
    }
}
