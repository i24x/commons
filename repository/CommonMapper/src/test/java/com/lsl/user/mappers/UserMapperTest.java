package com.lsl.user.mappers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.lsl.user.entities.User;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

public class UserMapperTest {

    public static void main(String[] args) throws IOException {

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory factory = builder.build(inputStream);

        SqlSession session = factory.openSession();

        // 按照Java方式整合通用Mapper的特殊设置
        // i.创建MapperHelper对象
        MapperHelper mapperHelper = new MapperHelper();

        // ii.通过MapperHelper对象对MyBatis原生的Configuration对象进行处理
        Configuration configuration = session.getConfiguration();
        mapperHelper.processConfiguration(configuration);

        UserMapper mapper = session.getMapper(UserMapper.class);
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andGreaterThan("age", 10);
        List<User> users = mapper.selectByExample(example);
        users.stream().forEach(System.out::println);

    }
}
