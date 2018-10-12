package com.materialdesign.javaTest;

/**
 * Created by Administrator on 2018/10/9
 * 邮箱 18780569202@163.com
 */
public class P2 extends P1{
    BuilderMode.Builder builder = new BuilderMode.Builder();
    BuilderMode builderMode = builder.setName("22").setAge(12)
            .setPage("11").setTitle("33").build();
}
