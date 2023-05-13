package com.example;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {

//        String url = "jdbc:mysql://127.0.0.1:3306/我的";
        String url = "jdbc:mysql://106.13.236.41:10028/labmanage";
        String username = "root";
//        String password = "123456";
        String password = "labmanageDatabase";
        String moudleName = "sys";
        String mapperLocation = "D:\\A2暂存\\IDEA\\spring项目\\x-admin\\src\\main\\resources\\mapper\\" + moudleName;
        String tableNames = "allclass,LabType,RepairApply,StuApplyLab,Students,TeacherApplyLab,Teachers,Testers"; //用逗号分割


        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("wow") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\A2暂存\\IDEA\\spring项目\\x-admin\\src\\main\\java"); // 指定输出目录
                })

                .packageConfig(builder -> {
                    builder.parent("com.example") // 设置父包名
                            .moduleName(moudleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames) // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
