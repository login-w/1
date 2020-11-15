package com.wsx.blog.entity;

public class TypeList {
    private int typeId;
    private int num;

    public TypeList() {
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "TypeList{" +
                "typeId=" + typeId +
                ", num=" + num +
                '}';
    }
}
