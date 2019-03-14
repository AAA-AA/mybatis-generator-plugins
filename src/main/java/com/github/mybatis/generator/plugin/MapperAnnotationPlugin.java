package com.github.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * Created by renhongqiang on 2019-03-14 14:42
 */
public class MapperAnnotationPlugin extends PluginAdapter {

    private FullyQualifiedJavaType dataAnnotation;

    public MapperAnnotationPlugin() {
        this.dataAnnotation = new FullyQualifiedJavaType("org.springframework.stereotype.Repository");
    }

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addRepositoryAnnotation(interfaze);
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    protected void addRepositoryAnnotation(Interface interfaze) {
        interfaze.addImportedType(dataAnnotation);
        interfaze.addAnnotation("@Repository");
    }
}
