package com.materialdesign.javaTest;

/**
 * Created by Administrator on 2018/10/12
 * 邮箱 18780569202@163.com
 */
public class BuilderMode {
    private String name;
    private int age;
    private String title;
    private String page;

    BuilderMode(String name, int age, String title, String page) {
        this.name = name;
        this.age = age;
        this.title = title;
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getTitle() {
        return title;
    }

    public String getPage() {
        return page;
    }

    public static class Builder{
        private String name;
        private int age;
        private String title;
        private String page;
        Builder() {
        }
        public Builder setName(String name){
            this.name = name;
            return this;
        }
        public Builder setAge(int age){
            this.age = age;
            return this;
        }
        public Builder setTitle(String title){
            this.title = title;
            return this;
        }
        public Builder setPage(String page){
            this.page = page;
            return this;
        }
        public BuilderMode build(){
            return new BuilderMode(name,age,title,page);
        }
    }
}
