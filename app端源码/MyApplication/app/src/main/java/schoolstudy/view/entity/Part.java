package schoolstudy.view.entity;

/**
 * Created by gouge on 2015/11/9.
 */
public class Part {
    private String name;
    private String videoUri;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Part(String name, String videoUri,String id) {
        this.name = name;
        this.videoUri = videoUri;
        this.id = id;
    }

    public Part(){

    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
