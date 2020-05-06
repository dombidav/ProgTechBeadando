package com.hl5u4v.progtech.core.db;

public class TableField {
    private String name;
    private String type;
    private String props = "";

    public TableField(String fieldName, String fieldType, String fieldProperties) {
        this.name = fieldName;
        this.type = fieldType;
        this.props = fieldProperties;
    }

    public TableField(String fieldName, String fieldType, String... fieldProperties) {
        this.name = fieldName;
        this.type = fieldType;
        this.props = String.join(" ", fieldProperties);
    }

    public TableField(String fieldName, String fieldType) {
        this.name = fieldName;
        this.type = fieldType;
    }

    public String getQuery() {
        return "`" + name + "` " + type + " " + props;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProps() {
        return props;
    }

    public void setProps(String props) {
        this.props = props;
    }

    public void addProp(String s) {
        this.props += s;
    }
}
