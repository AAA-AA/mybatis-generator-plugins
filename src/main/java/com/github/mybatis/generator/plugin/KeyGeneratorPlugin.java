package com.github.mybatis.generator.plugin;//

import java.util.List;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.PrimitiveTypeWrapper;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * Created by renhongqiang on 2020-08-09 19:30
 */
public class KeyGeneratorPlugin extends PluginAdapter {
    private static final String USE_GENERATED_KEYS = "useGeneratedKeys";
    private static final String KEY_PROPERTY = "keyProperty";
    private static final String USE_GEN_KEY = "useGenKey";

    public KeyGeneratorPlugin() {
    }

    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return this.addKeyGen(element, introspectedTable);
    }

    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return this.addKeyGen(element, introspectedTable);
    }

    private boolean addKeyGen(XmlElement element, IntrospectedTable introspectedTable) {
        if (!this.preCheck(introspectedTable)) {
            return true;
        } else {
            List<IntrospectedColumn> keyColumns = introspectedTable.getPrimaryKeyColumns();
            if (keyColumns.size() != 1) {
                return true;
            } else {
                IntrospectedColumn keyColumn = (IntrospectedColumn)keyColumns.get(0);
                FullyQualifiedJavaType javaType = keyColumn.getFullyQualifiedJavaType();
                if (!this.isNumber(javaType) || element.getName().equalsIgnoreCase("mapper")) {
                    return true;
                } else {
                    element.addAttribute(new Attribute("useGeneratedKeys", "true"));
                    element.addAttribute(new Attribute("keyProperty", keyColumn.getJavaProperty()));
                    return true;
                }
            }
        }
    }

    private boolean isNumber(FullyQualifiedJavaType javaType) {
        return PrimitiveTypeWrapper.getLongInstance().equals(javaType) || PrimitiveTypeWrapper.getIntegerInstance().equals(javaType) || PrimitiveTypeWrapper.getShortInstance().equals(javaType);
    }

    private boolean preCheck(IntrospectedTable introspectedTable) {
        String value = introspectedTable.getTableConfigurationProperty("useGenKey");
        if (!StringUtility.stringHasValue(value)) {
            return false;
        } else {
            Boolean isGen = Boolean.valueOf(value);
            return isGen;
        }
    }

    public boolean validate(List<String> list) {
        return true;
    }
}

