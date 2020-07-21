package com.lsl.commons.lang.utils.io;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ResourceFileUtil {

    /**
     * 保存Properties到文件
     *
     * @param filePath
     * @param p
     *            待保存信息
     * @param comments
     *            注释
     */
    public static void save2File(String filePath, Properties p, String comments) {
        try {
            if (p != null && !p.isEmpty()) {
                Writer writer = new FileWriter(filePath);
                p.store(writer, comments);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 冲文件加载Properties
     *
     * @param classPath
     *            加载路径
     * @return 返回Properties对象
     * @throws IOException
     *             IO异常
     */
    public static Properties loadProperties(String classPath) throws IOException {
        Properties props = new Properties();
        InputStream resourceStream = ClassUtils.getDefaultClassLoader().getResourceAsStream(classPath);
        props.load(resourceStream);
        return props;
    }

    /**
     * 获Properties取值
     *
     * @param prop
     *            属性集合
     * @param key
     *            键名称
     * @return 值
     */
    public static String getStringValue(Properties prop, String key) {

        Object obj = prop.get(key);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    /**
     * @param classPath
     *            支持ant表达式，如com.github.lsl.*..*sqlMap.xml
     * @return 资源数组
     * @throws IOException
     *             IO异常
     */
    public static Resource[] getResourceFromClassPath(String classPath) throws IOException {
        ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
        return resourceLoader.getResources(classPath);
    }

    /**
     * 从资源中加载文档
     *
     * @param resource
     *            资源
     * @return 文档对象
     * @throws Exception
     *             抛出IO异常
     */
    public static Document getDocument(Resource resource) throws Exception {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(resource.getInputStream());
        return doc;
    }

    /**
     * 从流中加载文档
     * 
     * @param stream
     * @return
     * @throws Exception
     */
    public static Document getDocument(InputStream stream) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(stream);
        return doc;
    }

    /**
     * 从classPath加载文档
     * 
     * @param classpath
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static List<Document> getDocumentsFromClassPath(String classpath) throws IOException, DocumentException {
        List<Document> list = new ArrayList<Document>();
        Resource[] rs = ResourceFileUtil.getResourceFromClassPath(classpath);
        for (Resource r : rs) {
            list.add(ResourceFileUtil.getDocument(r.getInputStream()));
        }
        return list;
    }

}
