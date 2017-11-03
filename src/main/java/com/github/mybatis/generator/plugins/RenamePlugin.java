package com.github.mybatis.generator.plugins;


import com.github.mybatis.generator.type.FileFullName;
import com.github.mybatis.generator.type.TypeFullName;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;
import java.util.Optional;

/**
 * Created by lgs on 16-2-16.
 */
public class RenamePlugin extends PluginAdapter {
    public static final String IGNORE_TABLE_PREFIX = "ignoreTablePrefix";
    public static final String XML_SUFFIX = "xmlSuffix";
    public static final String EXAMPLE_SUFFIX = "exampleSuffix";
    public static final String DAO_SUFFIX = "exampleSuffix";
    private String daoSuffix = "Mapper";
    private String xmlSuffix = "Mapper";
    private String exampleSuffix = "Example";
    private String ignoreTablePrefix = "t";


    public RenamePlugin() {
    }

    @Override
    public boolean validate(List<String> strings) {
        return true;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        String tableName = getTableName(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        String daopack = introspectedTable.getContext().getJavaClientGeneratorConfiguration().getTargetPackage();
        String modelpack = introspectedTable.getContext().getJavaModelGeneratorConfiguration().getTargetPackage();

        Optional.ofNullable(properties.getProperty(XML_SUFFIX)).ifPresent(e -> {
            this.xmlSuffix = properties.getProperty(XML_SUFFIX);
        });
        Optional.ofNullable(properties.getProperty(EXAMPLE_SUFFIX)).ifPresent(e -> {
            this.exampleSuffix = properties.getProperty(EXAMPLE_SUFFIX);
        });
        Optional.ofNullable(properties.getProperty(DAO_SUFFIX)).ifPresent(e -> {
            this.daoSuffix = properties.getProperty(DAO_SUFFIX);
        });

        introspectedTable.setBaseRecordType(modelpack.concat(".").concat(tableName));
        introspectedTable.setMyBatis3JavaMapperType(daopack.concat(".").concat(tableName).concat(this.daoSuffix));
        introspectedTable.setMyBatis3XmlMapperFileName(tableName.concat(this.xmlSuffix).concat(".xml"));
        introspectedTable.setExampleType(modelpack.concat(".").concat(tableName).concat(this.exampleSuffix));
        super.initialized(introspectedTable);

    }

    private String getTableName(String tableName) {
        String ignoreTablePrefix = this.properties.getProperty(IGNORE_TABLE_PREFIX);
        StringBuffer bf = new StringBuffer();
        if (null != ignoreTablePrefix && ignoreTablePrefix.length() > 0) {
            String[] splits = tableName.replaceFirst(ignoreTablePrefix, "").split("_");
            for (String s : splits) {
                if (s == null || s.length() == 0) {
                    continue;
                }
                bf.append(s.substring(0, 1).toUpperCase());
                if (s.length() > 1) {
                    bf.append(s.substring(1));
                }
            }
        }
        return bf.toString();
    }
}

