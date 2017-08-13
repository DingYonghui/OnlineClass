package schoolstudy.view.entity;

/**
 * 这个类是课程实体类，是listView中的item的实体类
 */

public class Course {
    private String id;
    private String imageUri;
    private String title;
    private String lecturer;
    private String videoUri;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }

    public Course(String imageUri, String title, String lecturer,String videoUri,String id) {
        this.imageUri = imageUri;
        this.title = title;
        this.lecturer = lecturer;
        this.videoUri = videoUri;
        this.id = id;

    }
    public Course(){

    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public String toString() {
        return imageUri+lecturer+title;
    }
}
