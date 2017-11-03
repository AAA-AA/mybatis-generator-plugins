package com.github.mybatis.generator.type;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;
import org.mybatis.generator.internal.util.StringUtility;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/4 00:53
 * @email: renhongqiang1397@gmail.com
 */
public class JavaTypeResolver extends JavaTypeResolverDefaultImpl {
    protected boolean forceLocalDateTime;
    protected boolean forceTinyintInteger;
    protected boolean forceSmallIntInteger;

    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        this.forceLocalDateTime = StringUtility.isTrue(properties.getProperty("forceLocalDateTime"));
        this.forceTinyintInteger = StringUtility.isTrue(properties.getProperty("forceTinyintInteger"));
        this.forceSmallIntInteger = StringUtility.isTrue(properties.getProperty("forceSmallIntInteger"));
        if (this.forceLocalDateTime) {
            super.typeMap.put(Types.DATE, new JdbcTypeInformation("DATE", new FullyQualifiedJavaType(LocalDateTime.class.getName())));
            super.typeMap.put(Types.TIME, new JdbcTypeInformation("TIME", new FullyQualifiedJavaType(LocalDateTime.class.getName())));
            super.typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP", new FullyQualifiedJavaType(LocalDateTime.class.getName())));
        }
        if (this.forceTinyintInteger) {
            super.typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
        }
        if (this.forceSmallIntInteger) {
            super.typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT", new FullyQualifiedJavaType(Integer.class.getName())));
        }
    }
}
