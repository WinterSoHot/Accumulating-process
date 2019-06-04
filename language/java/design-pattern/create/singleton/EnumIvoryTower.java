package gu.java.pattern.create.singleton;

public enum EnumIvoryTower {
    INSTANCE;

    @Override
    public String toString() {
        return getDeclaringClass().getCanonicalName()+"@"+hashCode();
    }
}
