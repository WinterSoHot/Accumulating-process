package gu.java.pattern.create.builder;

/**
 * 盔甲
 *
 * @author gudongxian
 * @create 2018-04-26 下午5:52
 **/
public enum Armor {
    CLOSTHES("colthes"), UNDEFINE("undefine");
    private String title;

    @Override
    public String toString() {
        return "Armor{" +
                "title='" + title + '\'' +
                '}';
    }

    Armor(String title) {
        this.title = title;
    }
}
