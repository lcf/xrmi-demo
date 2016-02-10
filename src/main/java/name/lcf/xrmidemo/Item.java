package name.lcf.xrmidemo;

import java.io.Serializable;

public class Item implements Serializable {

    private String code;
    private String description;

    public Item(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
