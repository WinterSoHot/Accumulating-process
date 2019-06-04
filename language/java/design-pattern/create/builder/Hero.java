package gu.java.pattern.create.builder;

/**
 * 英雄
 *
 * @author gudongxian
 * @create 2018-04-26 下午5:49
 **/
public class Hero {

    private final String name;
    private final Profession profession;

    private final Hair hair;
    private final HairColor hairColor;
    private final Weapon weapon;

    private Hero(Builder builder) {
        this.name = builder.name;
        this.profession = builder.profession;
        this.hair = builder.hair;
        this.hairColor = builder.hairColor;
        this.weapon = builder.weapon;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", profession=" + profession +
                ", hair=" + hair +
                ", hairColor=" + hairColor +
                ", weapon=" + weapon +
                '}';
    }

    public String getName() {
        return name;
    }

    public Profession getProfession() {
        return profession;
    }

    public Hair getHair() {
        return hair;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public static class Builder {
        private final String name;
        private final Profession profession;

        private Hair hair;
        private HairColor hairColor;
        private Weapon weapon;

        public Builder(String name, Profession profession) {
            this.name = name;
            this.profession = profession;
        }

        public Builder withHairType(Hair hairType) {
            this.hair = hairType;
            return this;
        }

        public Builder withHairColor(HairColor hairColor) {
            this.hairColor = hairColor;
            return this;
        }

        public Builder withWeapon(Weapon weapon) {
            this.weapon = weapon;
            return this;
        }

        public Hero build() {
            return new Hero(this);
        }
    }

}
