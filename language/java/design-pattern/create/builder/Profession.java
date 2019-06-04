package gu.java.pattern.create.builder;

/**
 * 职业
 */
public enum Profession {
    ZHANSHI("战士"), FASHI("法师"), GONGJIANSHOU("猎人"), SHUSHI("术士");

    private String title;

    Profession(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Profession{" +
                "title='" + title + '\'' +
                '}';
    }
}
