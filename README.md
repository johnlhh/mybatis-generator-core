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

```
假定Account表的建表语句为：

CREATE TABLE `test`.`Account` (
	`id` int(20) NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL COMMENT '姓名',
	`email` varchar(200) NOT NULL COMMENT '邮箱',
	`mobile` varchar(20) NOT NULL,
	`password` varchar(100) NOT NULL,
	`timeCreate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`timeUpdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`lastLoginTime` datetime DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `unique_name` USING BTREE (`name`) comment '',
	INDEX `unique_email` USING BTREE (`email`) comment '',
	INDEX `unique_mobile` USING BTREE (`mobile`) comment ''
) ENGINE=`InnoDB` AUTO_INCREMENT=1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ROW_FORMAT=DYNAMIC COMMENT='账户' CHECKSUM=0 DELAY_KEY_WRITE=0;

生成的实体类为：

import java.util.Date;

public class Account {
    /**
     * 
     * 表 : Account
     * 对应字段 : id
     */
    private Integer id;

    /**
     * 姓名
     * 表 : Account
     * 对应字段 : name
     */
    private String name;

    /**
     * 邮箱
     * 表 : Account
     * 对应字段 : email
     */
    private String email;

    /**
     * 
     * 表 : Account
     * 对应字段 : mobile
     */
    private String mobile;

    /**
     * 
     * 表 : Account
     * 对应字段 : password
     */
    private String password;

    /**
     * 
     * 表 : Account
     * 对应字段 : timeCreate
     */
    private Date timeCreate;

    /**
     * 
     * 表 : Account
     * 对应字段 : timeUpdate
     */
    private Date timeUpdate;

    /**
     * 
     * 表 : Account
     * 对应字段 : lastLoginTime
     */
    private Date lastLoginTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Date getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Date timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}

生成的dao:

import java.util.List;
import java.util.Map;

public interface AccountMapper {
    /**
     * 根据条件统计账户个数
     *
     * @param params
     */
    int countByMap(Map params);

    /**
     * 根据条件分页查询账户
     *
     * @param params
     */
    List<Account> findByMap(Map params);

    /**
     * 根据主键删除账户
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增账户
     *
     * @param record
     */
    int insert(Account record);

    /**
     * 根据主键获取账户
     *
     * @param id
     */
    Account selectByPrimaryKey(Integer id);

    /**
     * 根据主键来更新部分账户信息
     *
     * @param record
     */
    int updateByPrimaryKeySelective(Account record);
}

生成的Accountmapper.xml为:

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.smartzhe.demo.domain.dao.AccountMapper">
  <!--表字段映射--> 
  <resultMap id="BaseResultMap" type="com.smartzhe.demo.domain.entity.Account">
    <id column="id" jdbcType="INTEGER" property="id" />
    <result column="name" jdbcType="VARCHAR" property="name" />
    <result column="email" jdbcType="VARCHAR" property="email" />
    <result column="mobile" jdbcType="VARCHAR" property="mobile" />
    <result column="password" jdbcType="VARCHAR" property="password" />
    <result column="timeCreate" jdbcType="TIMESTAMP" property="timeCreate" />
    <result column="timeUpdate" jdbcType="TIMESTAMP" property="timeUpdate" />
    <result column="lastLoginTime" jdbcType="TIMESTAMP" property="lastLoginTime" />
  </resultMap>
  <!--账户 基础列名--> 
  <sql id="Base_Column_List">
    id, name, email, mobile, password, timeCreate, timeUpdate, lastLoginTime
  </sql>
  <!--查询条件语句--> 
  <sql id="Map_Where_Clause">
    <where>
      <if test="id != null">
         and id = #{id}
      </if>
      <if test="name != null">
         and name = #{name}
      </if>
      <if test="email != null">
         and email = #{email}
      </if>
      <if test="mobile != null">
         and mobile = #{mobile}
      </if>
      <if test="password != null">
         and password = #{password}
      </if>
      <if test="timeCreate != null">
         and timeCreate = #{timeCreate}
      </if>
      <if test="timeUpdate != null">
         and timeUpdate = #{timeUpdate}
      </if>
      <if test="lastLoginTime != null">
         and lastLoginTime = #{lastLoginTime}
      </if>
    </where>
  </sql>
  <!--统计查询账户个数--> 
  <select id="countByMap" parameterType="java.util.Map" resultType="java.lang.Integer">
    select count(*) 
    from Account
    <include refid="Map_Where_Clause" />
  </select>
  <!--分页查询账户--> 
  <select id="findByMap" parameterType="java.util.Map" resultMap="BaseResultMap">
    select 
    <include refid="Base_Column_List" />
    from Account
    <include refid="Map_Where_Clause" />
    <if test="offSet != null and rowsPerPage != null">
       limit #{offSet} , #{rowsPerPage}
    </if>
  </select>
  <!--通过主键查询账户--> 
  <select id="selectByPrimaryKey" parameterType="java.lang.Integer" resultMap="BaseResultMap">
    select 
    <include refid="Base_Column_List" />
    from Account
    where id = #{id}
  </select>
  <!--通过主键删除账户--> 
  <delete id="deleteByPrimaryKey" parameterType="java.lang.Integer">
    delete from Account
    where id = #{id}
  </delete>
  <!--新增账户--> 
  <insert id="insert" keyProperty="id" parameterType="com.smartzhe.demo.domain.entity.Account" useGeneratedKeys="true">
    insert into Account (name, email, mobile, password, lastLoginTime)
    values (#{name}, #{email}, #{mobile}, #{password}, #{lastLoginTime})
  </insert>
  <!--通过主键选择性修改账户--> 
  <update id="updateByPrimaryKeySelective" parameterType="com.smartzhe.demo.domain.entity.Account">
    update Account
    <set>
      <if test="name != null">
        name = #{name},
      </if>
      <if test="email != null">
        email = #{email},
      </if>
      <if test="mobile != null">
        mobile = #{mobile},
      </if>
      <if test="password != null">
        password = #{password},
      </if>
      <if test="lastLoginTime != null">
        lastLoginTime = #{lastLoginTime},
      </if>
    </set>
    where id =  #{id}
  </update>
</mapper>
```