package schoolstudy.view.entity;

/**
 * 这个类是一个javabean，是GridView中的每个item的实体类
 */
public class Department {
    private String imageUri;
    private String name;

    public Department(String imageUri, String name) {
        this.imageUri = imageUri;
        this.name = name;
    }

    public Department() {
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
