package spitter.data;

import org.springframework.stereotype.Component;
import spitter.entity.Spittle;

import java.util.List;

/**
 * Created by gudongxian on 2017/3/24.
 */
public interface SpittleRepository{
    List<Spittle> findSpittles(long max, int count);
}
