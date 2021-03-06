package com.lsl.common.jvm.bitclass;

import com.lsl.common.jvm.classloader.PathUtil;
import javassist.*;

public class JavassisUtils {

    /**
     * 创建一个Person 对象
     *
     * @throws Exception
     */
    public static CtClass createPseson() throws Exception {
        ClassPool pool = ClassPool.getDefault();

        // 1. 创建一个空类
        CtClass cc = pool.makeClass("com.lsl.domain.Person");

        // 2. 新增一个字段 private String name;
        // 字段名为name
        CtField param = new CtField(pool.get("java.lang.String"), "name", cc);
        // 访问级别是 private
        param.setModifiers(Modifier.PRIVATE);
        // 初始值是 "xiaoming"
        cc.addField(param, CtField.Initializer.constant("xiaoming"));

        // 3. 生成 getter、setter 方法
        cc.addMethod(CtNewMethod.setter("setName", param));
        cc.addMethod(CtNewMethod.getter("getName", param));

        // 4. 添加无参的构造函数
        CtConstructor cons = new CtConstructor(new CtClass[] {}, cc);
        cons.setBody("{name = \"xiaohong\";}");
        cc.addConstructor(cons);

        // 5. 添加有参的构造函数
        cons = new CtConstructor(new CtClass[] {pool.get("java.lang.String")}, cc);
        // $0=this / $1,$2,$3... 代表方法参数
        cons.setBody("{$0.name = $1;}");
        cc.addConstructor(cons);

        // 6. 创建一个名为printName方法，无参数，无返回值，输出name值
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "printName", new CtClass[] {}, cc);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println(name);}");
        cc.addMethod(ctMethod);
        // 这里会将这个创建的类对象编译为.class文件
        String path = PathUtil.targetDir();
        // cc.writeFile(path+File.separator+"com"+File.separator+"lsl"+File.separator+"domain");
        return cc;
    }

    public static void main(String[] args) throws Exception {
        CtClass ctPseson = JavassisUtils.createPseson();
        Class<?> aClass = ctPseson.toClass();
        Object person = aClass.newInstance();
        System.out.println(person);
    }
}
