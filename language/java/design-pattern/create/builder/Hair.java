package gu.java.pattern.create.builder;

/**
 * 头发
 */
public enum Hair {
    LOGN_HAIR,NOT_HAIR,SHORT_HAIR;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
