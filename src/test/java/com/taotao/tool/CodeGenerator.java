package com.taotao.tool;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * mybatis-plus 自动生成代码
 *
 * @author caojiantao
 * @since 2022-08-21
 */
public class CodeGenerator {

    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/taotao-tool?useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
        String username = "root";
        String password = "123456";

        String moduleName = "todo";

        String finalProjectPath = "D:\\IdeaProjects\\taotao-tool";
        String tables = "todo";
        FastAutoGenerator generator = FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("caojiantao") // 设置作者
                            .disableOpenDir() //禁止打开输出目录
                            .outputDir(finalProjectPath + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.taotao.tool." + moduleName) // 设置父包名
                            .entity("model") //设置entity包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, finalProjectPath + "/src/main/resources/mapper/" + moduleName)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)
                            .entityBuilder()
//                            .enableFileOverride()
                            .enableLombok()
                            .disableSerialVersionUID()
//                            .addSuperEntityColumns("id", "gmt_create", "gmt_modified")
//                            .superClass(BaseModel.class)
//                            .controllerBuilder()
                            .enableFileOverride();
                });
        generator.execute();
    }
}
