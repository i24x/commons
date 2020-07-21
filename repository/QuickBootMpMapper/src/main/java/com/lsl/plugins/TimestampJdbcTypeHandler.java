package com.lsl.plugins;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.apache.ibatis.type.LocalDateTimeTypeHandler;
import org.springframework.stereotype.Component;

@Component
public class TimestampJdbcTypeHandler extends LocalDateTimeTypeHandler {

    @Override
    public LocalDateTime getResult(ResultSet rs, String columnName) throws SQLException {
        Object object = rs.getObject(columnName);
        System.out.println(object);
        if (object instanceof Timestamp) {
            // 在这里强行转换，将sql的时间转换为LocalDateTime
            return LocalDateTime// 可以根据自己的需要进行转化
                .ofInstant(((Timestamp)object).toInstant(), ZoneOffset.ofHours(0));
        }
        return super.getResult(rs, columnName);
    }
}