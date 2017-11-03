package com.github.mybatis.generator.plugin;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.internal.util.StringUtility;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/4 01:21
 * @email: renhongqiang1397@gmail.com
 */
public class XmlFileOverwritePlugin extends PluginAdapter {

    private boolean overwrite = true;

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMapFile, IntrospectedTable introspectedTable) {
        if (overwrite) {
            try {
                Field mergedField = GeneratedXmlFile.class.getDeclaredField("isMergeable");
                if (!mergedField.isAccessible()) {
                    mergedField.setAccessible(true);
                }
                mergedField.setBoolean(sqlMapFile, false);
                System.out.println(sqlMapFile.getFileName() + " isMergeable " + sqlMapFile.isMergeable());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.sqlMapGenerated(sqlMapFile, introspectedTable);
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        if (!isBlank(properties.getProperty("overwrite"))) {
            this.overwrite = StringUtility.isTrue(properties.getProperty("overwrite"));
        }
    }

    private boolean isBlank(CharSequence s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
