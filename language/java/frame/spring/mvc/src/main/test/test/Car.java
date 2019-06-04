package test;

/**
 * Created by gudongxian on 2017/3/24.
 */
public class Car {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj.getClass() == getClass()) {
            if (((Car) obj).getId() == getId()) {
                return true;
            }
        }
        return false;
    }
}
