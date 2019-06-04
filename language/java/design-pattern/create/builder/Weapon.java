package gu.java.pattern.create.builder;

/**
 * 武器
 */
public enum Weapon {
    SHORT_WORD("short word"),SPEAR("spear"),AXU("axu");

    private String title;

    Weapon(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "title='" + title + '\'' +
                '}';
    }
}
