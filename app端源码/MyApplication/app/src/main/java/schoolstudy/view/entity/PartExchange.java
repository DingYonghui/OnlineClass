package schoolstudy.view.entity;

/**
 * Created by liaoliao on 2015/11/29.
 */
public class PartExchange {
    private String name;
    private String content;
    private String partId;

    public PartExchange(String name, String content,String partId) {
        this.name = name;
        this.content = content;
        this.partId = partId;
    }

    public PartExchange(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }
}
