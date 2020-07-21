package com.lsl;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lsl.*.mapper")
public class QuickstartApplication {

    // <bean id="sqlSessionFactory"
    // class="org.mybatis.spring.SqlSessionFactoryBean">
    // <property name="dataSource" ref="datasource"></property>
    // <property name="configLocation" value="classpath:context/mybatis-config.xml"></property>
    // <property name="mapperLocations" value="classpath*:/com/tx/demo/**/*SqlMap.xml"/>
    /*
    
    SqlSessionTemplate底层通过sqlSessionProxy实现拦截
    org.mybatis.spring.SqlSessionTemplate
    com.baomidou.mybatisplus.MybatisSqlSessionTemplate
    
    SqlSessionInterceptor
    org.mybatis.spring.SqlSessionTemplate.SqlSessionInterceptor
    拦截session创建
    
    
    <bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg name="sqlSessionFactory" ref="sqlSessionFactory"></constructor-arg>
        <constructor-arg name="executorType" ref="SIMPLE"></constructor-arg>
        <constructor-arg name="exceptionTranslator" ref="myBatisExceptionTranslator"></constructor-arg>
    </bean>
    
    MapperScannerConfigurer
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="org.mybatis.spring.sample.mapper" />
        <property name="sqlSessionTemplateBeanName" value="sqlSessionTemplate" />
    </bean>
    //解析Mapper接口并注册
    
    //Spring代理入口
    MapperFactoryBean
    
    //Mybatis代理入口
    MapperRegistry
    MapperProxyFactory
    MapperProxy
    MapperMethod
    https://blog.csdn.net/fighterandknight/article/details/51448161
    https://my.oschina.net/xianggao/blog/551161
    
    GlobalConfiguration
    AutoSqlInjector  扩展逻辑删除
    MetaObjectHandler @TableField(fill=FieldFill.INSERT_UPDATE) 公共字段填充
    association 多表关联
    PerformanceInterceptor 性能分析
    */

    private static final Logger LOGGER = LoggerFactory.getLogger(QuickstartApplication.class);

    static {
        // 设置日志目录
        setLogDir();
    }

    public static void main(String[] args) {
        init(args);
        SpringApplication.run(QuickstartApplication.class, args);
        after(args);
    }

    static void setLogDir() {
        File deskDir = FileSystemView.getFileSystemView().getHomeDirectory();
        File logDir = new File(deskDir, "log");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
        System.setProperty("log.dir", logDir.getAbsolutePath());
    }

    static void init(String[] args) {
        LOGGER.debug("设置日志目录:" + System.getProperty("log.dir"));
        LOGGER.info("开始启动项目");
    }

    static void after(String[] args) {
        LOGGER.error("启动完毕");
    }

    /*
    
    Mybatisplus 自定义sql 使用条件构造器
            两种方式
    注解方式
    动态查找：
    @Select("select ${ew.SqlSelect} from ${tableName} ${ew.customSqlSegment}")
    List<File> listFileByCondition(@Param("tableName") String tableName, @Param("ew") Wrapper wrapper);
    
    ew.SqlSelect：所需要查找的字段
    
    tableName：使用的是那张表
    
    ew.customSqlSegment：条件
    用法：allFileMapper.listFileByCondition(tableName,Wrappers.query().select("*").in("relation_uuid", uuids));
    结果： select * from tablName where relation_uuid in ()
    
    
    动态修改：
    @Update("update ${tableName} set ${ew.sqlSet} ${ew.customSqlSegment}")
    int updateByCondition(@Param("tableName") String tableName, @Param("ew") Wrapper wrapper);
    
    ew.sqlSet：修改的字段
    
    tableName：使用的是那张表
    
    ew.customSqlSegment：条件
    
    用法：
            mapper.updateByCondition(tableName, Wrappers.update().set("state", "初始状态").in("id", ids));
    结果： update tableName set state = '初始状态' where id in ()
    xml方式
    查找：
    <select id="listFileByCondition" resultType="com.example.entity.File">
        SELECT ${ew.SqlSelect} FROM ${tableName} ${ew.customSqlSegment}
    </select>
    
    
    修改：
    <update id="listFileByCondition" >
        update ${tableName} ${ew.SqlSelect}  ${ew.customSqlSegment}
    </update>
    
    查找带分页
    xml用法：
    Page<File> selectPage(Page page, @Param("tableName") String tableName, @Param("ew") Wrapper wrapper);
    
    <select id="selectPage" resultType="com.example.entity.File">
        select * from ${tableName} ${ew.customSqlSegment}
    </select>
    */

}
