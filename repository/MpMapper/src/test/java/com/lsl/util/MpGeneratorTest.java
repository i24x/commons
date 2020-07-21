package com.lsl.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

/**
 * <p>
 * 通过junit test 生成代码 演示：自定义代码模板 默认不会覆盖已有文件，如果需要覆盖，配置GlobalConfig.setFileOverride(true)
 * </p>
 *
 * @author yuxiaobin
 * @date 2018/11/29
 */
public class MpGeneratorTest {

    @Test
    public void generateCode() {
        // generate("user", "user");
        // generate("article", "article");
        generate("account", "t_account");
    }

    private void generate(String moduleName, String... tableNamesInclude) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/src/main/java";
        System.out.println("====================>" + projectPath);
        gc.setOutputDir(projectPath);
        gc.setAuthor("Chrow Yeung");
        gc.setOpen(false).setBaseColumnList(true).setBaseResultMap(true);
        // 默认不覆盖，如果文件存在，将不会再生成，配置true就是覆盖
        gc.setFileOverride(true);
        // 指定模板引擎
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(
            "jdbc:mysql://localhost:3306/spring?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent("com.lsl");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // strategy.setSuperEntityClass("com.lsl.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        // strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
        strategy.setInclude(tableNamesInclude);
        // strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.entityTableFieldAnnotationEnable(true);
        strategy.setEntityColumnConstant(true);
        gc.setEntityName("%s");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");

        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        // mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        configCustomizedCodeTemplate(mpg);
        configInjection(mpg);

        mpg.execute();
    }

    /**
     * 自定义模板
     * 
     * @param mpg
     */
    private void configCustomizedCodeTemplate(AutoGenerator mpg) {
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        // 配置 自定义模板
        TemplateConfig tc = new TemplateConfig();
        // templateConfig.setEntity("templates/MyEntityTemplate.java")//指定Entity生成使用自定义模板
        // .setXml(null);//不生成xml

        tc.setController("/templates/mp/controller.java.vm");
        // tc.setService("/templates/mp/service.java.vm");
        // tc.setServiceImpl("/templates/mp/serviceImpl.java.vm");
        // tc.setEntity("/templates/mp/entity.java.vm");
        // tc.setMapper("/templates/mp/mapper.java.vm");
        // tc.setXml("/templates/mp/mapper.xml.vm");

        mpg.setTemplate(tc);
    }

    /**
     * 配置自定义参数/属性
     *
     * @param mpg
     */
    private void configInjection(AutoGenerator mpg) {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
                /*
                自定义属性注入: 模板配置：abc=${cfg.abc}
                 */
            }
        };
        // List<FileOutConfig> focList = new ArrayList<>();
        // focList.add(new FileOutConfig("/templates/MyEntityTemplate.java.ftl") {
        // @Override
        // public String outputFile(TableInfo tableInfo) {
        // // 指定模板生，自定义生成文件到哪个地方
        // return "D:/abc";
        // }
        // });
        // cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }
}
