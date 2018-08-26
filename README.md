# 修改部分
> * 修复乱码问题，生成文件一律转为utf-8
> * 修改配置catalog时，生成代码为Schema..table的问题
> * 修改生成注释类，将英文注释去掉，改为数据库字段注释
> org.mybatis.generator.api.IntrospectedTable，生成的文件结尾修正。eg:TBank.java,TBankCriteria.java,TBankMapper.java,TBankMapper.xml
> TINYINT 对应的字段生成 Integer类型,所有带小数点的类型统一转成BigDecimal
>

# 使用方法

先下载源码到本地
git clone git@github.com:johnlhh/mybatis-generator-core.git
mvn clean install

在项目的src/main/resources目录下 生成一个generatorConfig.xml文件
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>

    <!-- <classPathEntry> 加载运行时需要的jar或zip包 完整路径名称或要添加到类路径中的目录 最好放到类路径下，方便统一开发 -->
    <classPathEntry
            location="/Users/luohuahua/.m2/repository/mysql/mysql-connector-java/5.1.32/mysql-connector-java-5.1.32.jar"/>

    <!-- <context> 元素用于指定生成一组对象的环境。 子元素用于指定要连接到的数据库、
         要生成对象的类型和要内省的表。 多个 <context> 元素可以在 <generatorConfiguration>
          元素中列出来，这样可以在同一个MyBatis Generator (MBG)从不同的数据库或者使用不同的生
          成生成器参数生成对象
          属性：
          id 为必填  是唯一标示，用于区分不同的环境

          defaultModelType
               可选择的值 conditional（默认 如果一个表的主键是两个字段组成，会多生产一个单独的类）
                         flat 只生成一个和表一一对应字段的一个类
                         hierarchical 有主键时会生产2个类

          targetRuntime  MyBatis3（默认） MyBatis3Simple（只有简单的sql操作xml）



     属性的先后顺序(property*,plugin*,commentGenerator?,jdbcConnection,javaTypeResolver?,javaModelGenerator,sqlMapGenerator,javaClientGenerator?,table+)

    -->
    <context id="MySqlTables" targetRuntime="MyBatis3" defaultModelType="flat">


        <!-- 指定生产Java文件的编码 -->
        <property name="javaFileEncoding" value="UTF-8"/>
        <!-- 当值为true时，分隔SQL关键字或维护表字段 -->
        <property name="autoDelimitKeywords" value="false"/>

        <!-- 关键字开始和结尾 隔开字符mysql'  oracle "  -->
        <property name="beginningDelimiter" value="`"/>
        <property name="endingDelimiter" value="`"/>

        <!-- 注释生成器 -->
        <commentGenerator
                type="org.mybatis.generator.internal.CustomeCommentGenerator">
            <property name="javaFileEncoding" value="UTF-8" />

            <property name="suppressAllComments" value="false" />
            <property name="suppressDate" value="false" />
        </commentGenerator>


        <!-- jdbc数据库连接 -->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/test"
                        userId="root"
                        password="root">
        </jdbcConnection>


        <!-- 指定JDBC类型和Java类型如何转换
             默认情况下的转换规则为：
            如果精度>0或者长度>18，就会使用java.math.BigDecimal
            如果精度=0并且10<=长度<=18，就会使用java.lang.Long
            如果精度=0并且5<=长度<=9，就会使用java.lang.Integer
            如果精度=0并且长度<5，就会使用java.lang.Short
            如果设置为true，那么一定会使用java.math.BigDecimal
        -->
        <javaTypeResolver>
            <property name="forceBigDecimals" value="true"/>
        </javaTypeResolver>


        <javaModelGenerator targetPackage="com.**.entity" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
            <!--<property name="trimStrings" value="true"/>-->
            <!-- 实体类是否需要生成构造器 -->
            <property name="constructorBased" value="false"/>
        </javaModelGenerator>

        <sqlMapGenerator targetPackage="mapper" targetProject="src/main/resources">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>

        <javaClientGenerator type="XMLMAPPER" targetPackage="com.**.dao" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>

        <table schema="DB2ADMIN" tableName="Account" domainObjectName="Account"

               enableCountByExample="false" enableUpdateByExample="false"
               enableDeleteByExample="false" enableSelectByExample="false"
               selectByExampleQueryId="false"
        >
            <property name="useActualColumnNames" value="true"/>
            <generatedKey column="id" sqlStatement="Mysql"/>
        </table>
    </context>
</generatorConfiguration>


```


编辑pom.xml，在plugins元素下新增插件

```

 <plugin>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>1.3.2</version>
        <configuration>
          <configurationFile>
            ${basedir}/src/main/resources/generatorConfig.xml
          </configurationFile>
          <overwrite>true</overwrite>
        </configuration>
        <dependencies>
          <dependency>
            <groupId>com.shizhe.mybatis</groupId>
            <artifactId>mybatis-generator-core</artifactId>
            <version>1.0.1</version>
          </dependency>
        </dependencies>
      </plugin>
```

最后maven项目刷新一下，加载插件，然后运行mybatis-generator插件