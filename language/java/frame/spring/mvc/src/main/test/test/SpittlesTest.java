package test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spitter.data.SpittleRepository;
import spitter.entity.Spittle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gudongxian on 2017/3/24.
 */
public class SpittlesTest {


    @Test
    public void shouldShowRecentSpittles() throws  Exception{
        List<Spittle> expectedSpittles = createSpittleList(20);
        SpittleRepository movkRepository;

    }

    private List<Spittle> createSpittleList(int count) {
        List<Spittle> spittleList = new ArrayList<Spittle>();
        for (int i=0;i<count;i++){
            spittleList.add(new Spittle("Splite"+i,new Date()));
        }
        return spittleList;
    }
}


