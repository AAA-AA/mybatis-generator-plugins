package com.github.mybatis.generator.comment;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/4 02:25
 * @email: renhongqiang1397@gmail.com
 */
public class CommentGenerator extends DefaultCommentGenerator {

    //是否添加注解
    private boolean isAddComments = true;

    public CommentGenerator() {
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        Optional.ofNullable(properties.getProperty("isAddComments")).ifPresent((e)->this.isAddComments=Boolean.parseBoolean(e));
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (isAddComments) {
            Optional.ofNullable(introspectedColumn.getRemarks()).filter((e)->e.length()!=0).ifPresent((e)->{
                field.addJavaDocLine("/**"+introspectedColumn.getRemarks()+"*/");
            });
        }
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (this.isAddComments) {
            topLevelClass.addJavaDocLine("/**");
            topLevelClass.addJavaDocLine(" * link table is " + introspectedTable.getFullyQualifiedTableNameAtRuntime());
            topLevelClass.addJavaDocLine(" * Copyright © " + String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + ", github and/or its affiliates. All rights reserved.");
            topLevelClass.addJavaDocLine(" **/");
        }
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }
}
