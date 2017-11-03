package com.github.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.Calendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/4 01:50
 * @email: renhongqiang1397@gmail.com
 */
public class CommentPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * link table is " + introspectedTable.getFullyQualifiedTableNameAtRuntime());
        topLevelClass.addJavaDocLine(" * Copyright © " + String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + ", github and/or its affiliates. All rights reserved. */");
        topLevelClass.addJavaDocLine(" **/");
        return true;
    }


    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable, Plugin.ModelClassType modelClassType) {
        generateFieldExplain(field, introspectedColumn);
        return true;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
                                              IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, Plugin.ModelClassType modelClassType) {
        generateMethodExplain(method, introspectedColumn);
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
                                              IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, Plugin.ModelClassType modelClassType) {
        generateMethodExplain(method, introspectedColumn);
        return true;
    }

    private void generateFieldExplain(Field field, IntrospectedColumn introspectedColumn) {
        String comment = (introspectedColumn.getRemarks() == null ? "" : introspectedColumn.getRemarks());
        field.addJavaDocLine("/** " + comment + " */");
    }

    private void generateMethodExplain(Method method, IntrospectedColumn introspectedColumn) {
        String comment = (introspectedColumn.getRemarks() == null ? "" : introspectedColumn.getRemarks());
        method.addJavaDocLine("/** " + comment + " */");
    }
}
