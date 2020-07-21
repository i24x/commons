package com.lsl.com;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.lsl.mapper.UserMapper;
import com.lsl.pojo.User;
import com.lsl.pojo.UserExample;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserMapperTest {

    // 支持拦截的方法
    //
    // 执行器Executor（update、query、commit、rollback等方法）；
    // 参数处理器ParameterHandler（getParameterObject、setParameters方法）；
    // 结果集处理器ResultSetHandler（handleResultSets、handleOutputParameters等方法）；
    // SQL语法构建器StatementHandler（prepare、parameterize、batch、update、query等方法）；
    //
    // 链接：https://www.jianshu.com/p/96a417f8cf5f
    @Test
    public void selectByExample() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameLike("han%");
        List<User> users = mapper.selectByExample(userExample);
        users.stream().forEach(v -> {
            System.out.println(v);
        });

    }

}
