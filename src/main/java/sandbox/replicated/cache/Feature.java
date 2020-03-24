package sandbox.replicated.cache;

import java.io.Serializable;
import java.util.Objects;

public class Feature implements Serializable {

    private String name;
    private Object value;

    public Feature() {
    }

    public Feature(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature feature = (Feature) o;
        return Objects.equals(name, feature.name) &&
                Objects.equals(value, feature.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
