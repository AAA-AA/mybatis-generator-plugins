# mybatis-generator-plugins
mybatis-generator-plugins版本1.2.0版本相关特性
* 支持对生成的model、dao以及example类的重命名
* lombok支持
* 注释支持
* 批量插入支持
* 插入后自动生成主键支持
* 支持多种类型强转，tinyint，smallint 转 Integer, datatime 或者 timestamp 转 LocalDateTime

User Guide
1. 使用命令行 git clone git@github.com:AAA-AA/mybatis-generator-plugins.git 下载源代码
2. 使用idea或者eclipse打开该工程文件，并使用mvn将其package并install到本地repository
3. 在其他有需要的项目中的pom下引入maven依赖，然后将本项目中的generatorConfig.xml，复制到该项目的resources目录下，接着按自己所需修改配置信息即可。注：不要复制到resources子目录下
4. 启动执行。启动执行有多种方式，以下介绍两种
   * 命令行的方式启动：mvn mybatis-generator:generate
   * maven插件的方式启动：idea右侧工具栏会自带maven插件，点开里面的plugins，找到mybatis-generator，打开点击执行即可
 
 
```
<plugin>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-maven-plugin</artifactId>
    <version>1.3.5</version>
    <configuration>
        <!--重新指定配置文件路径时可以配置<configurationFile>src/main/resources/mybatis-generator-config.xml</configurationFile>-->
        <verbose>true</verbose>
        <overwrite>true</overwrite>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.mybatis.generator/mybatis-generator-maven-plugin -->
        <dependency>
            <groupId>com.github.mybatis.generator</groupId>
            <artifactId>mybatis-generator-plugins</artifactId>
            <version>1.4.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
</plugin>
```


And More
* if any question, tell me please, wait for your feedback!😂😂😂 you can touch me by: 
```
{
    "wechat": "RR-ViVi",
    "QQ": "820941512",
    "Sina Weibo": "北斗星君君"
}

```
