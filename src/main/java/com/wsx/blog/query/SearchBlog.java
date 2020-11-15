package com.wsx.blog.query;


public class SearchBlog {
    private String title ;
    private Integer typeId;
    private boolean recomment;
    private Integer num;

    public SearchBlog() {
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public boolean isRecomment() {
        return recomment;
    }

    public void setRecomment(boolean recomment) {
        this.recomment = recomment;
    }

    @Override
    public String toString() {
        return "SearchBlog{" +
                "title='" + title + '\'' +
                ", typeId=" + typeId +
                ", recomment=" + recomment +
                '}';
    }
}
