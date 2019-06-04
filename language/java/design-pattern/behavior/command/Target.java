package gu.java.pattern.behavior.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for spell targets.
 * @author gudongxian
 * @create 2018-04-29 上午8:07
 **/
public abstract class Target {
    private static final Logger LOGGER = LoggerFactory.getLogger(Target.class);

    private Size size;
    private Visibility visibility;

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    @Override
    public abstract String toString();

    public void printStatus() {
        LOGGER.info("{}, [Size = {}] [Visibility = {}]", this, getSize(), getVisibility());
    }
}
