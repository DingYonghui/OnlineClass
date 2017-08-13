package schoolstudy.view.entity;

import java.util.List;

/**
 * 这个类是章的类，里面封装着有多少节
 */
public class Section {
    private List<Part> parts;
    private String name;

    public List<Part> getCount() {
        return parts;
    }

    public void setCount(List<Part> count) {
        this.parts = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Section(List<Part> count, String name) {
        this.parts = count;
        this.name = name;
    }

    public Section(){

    }
}
