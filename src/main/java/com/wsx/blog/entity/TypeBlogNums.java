package com.wsx.blog.entity;

public class TypeBlogNums {
    private int typeId;
    private int blogNums;
    private String typeName;

    public TypeBlogNums() {
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getBlogNums() {
        return blogNums;
    }

    public void setBlogNums(int blogNums) {
        this.blogNums = blogNums;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "TypeBlogNums{" +
                "typeId=" + typeId +
                ", blogNums=" + blogNums +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
