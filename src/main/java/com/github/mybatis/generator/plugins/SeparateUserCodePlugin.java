package com.github.mybatis.generator.plugins;

import com.github.mybatis.generator.type.FileFullName;
import com.github.mybatis.generator.type.TypeFullName;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgs on 16-2-16.
 */
public class SeparateUserCodePlugin extends PluginAdapter {
    public static final String TARGET_PACKAGE_PROPERTY_NAME = "separateUserCodePlugin.targetPackage";
    public static final String SEARCH_PROPERTY_NAME = "separateUserCodePlugin.search";
    public static final String REPLACE_PROPERTY_NAME = "separateUserCodePlugin.replace";
    public static final String PREFIX_PROPERTY_NAME = "separateUserCodePlugin.prefix";
    public static final String SUFFIX_PROPERTY_NAME = "separateUserCodePlugin.suffix";
    private List<GeneratedJavaFile> generatedJavaFileList = new ArrayList();
    private List<GeneratedXmlFile> generatedXmlFileList = new ArrayList();

    public SeparateUserCodePlugin() {
    }

    @Override
    public boolean validate(List<String> strings) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String userCodePackage = this.context.getJavaClientGeneratorConfiguration().getProperty(TARGET_PACKAGE_PROPERTY_NAME);
        if (userCodePackage == null) {
            userCodePackage = this.context.getJavaClientGeneratorConfiguration().getTargetPackage();
        }

        String userCodeSearch = this.context.getJavaClientGeneratorConfiguration().getProperty(SEARCH_PROPERTY_NAME);
        String userCodeReplace = this.context.getJavaClientGeneratorConfiguration().getProperty(REPLACE_PROPERTY_NAME);
        String userCodePrefix = this.context.getJavaClientGeneratorConfiguration().getProperty(PREFIX_PROPERTY_NAME);
        String userCodeSuffix = this.context.getJavaClientGeneratorConfiguration().getProperty(SUFFIX_PROPERTY_NAME);
        TypeFullName userInterfaceTypeFullName = new TypeFullName(userCodePackage, interfaze.getType().getShortName());
        userInterfaceTypeFullName.replaceTypeShortName(userCodeSearch, userCodeReplace).fixTypeShortName(userCodePrefix, userCodeSuffix);
        String userMapperPackage1 = this.context.getSqlMapGeneratorConfiguration().getProperty(TARGET_PACKAGE_PROPERTY_NAME);
        if (userMapperPackage1 == null) {
            userMapperPackage1 = this.context.getSqlMapGeneratorConfiguration().getTargetPackage();
        }

        String userMapperSearch = this.context.getSqlMapGeneratorConfiguration().getProperty(SEARCH_PROPERTY_NAME);
        String userMapperReplace = this.context.getSqlMapGeneratorConfiguration().getProperty(REPLACE_PROPERTY_NAME);
        String userMapperPrefix = this.context.getSqlMapGeneratorConfiguration().getProperty(PREFIX_PROPERTY_NAME);
        String userMapperSuffix = this.context.getSqlMapGeneratorConfiguration().getProperty(SUFFIX_PROPERTY_NAME);
        TypeFullName userMapperTypeFullName = new TypeFullName(userMapperPackage1, userInterfaceTypeFullName.getTypeShortName());
        userMapperTypeFullName.replaceTypeShortName(userMapperSearch, userMapperReplace).fixTypeShortName(userMapperPrefix, userMapperSuffix);
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
        return this.generatedJavaFileList;
    }

    @Override
    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles() {
        return this.generatedXmlFileList;
    }
}
