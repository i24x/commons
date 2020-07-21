package com.lsl.commons.lang.utils.db.sql;

import java.util.LinkedHashMap;

public class SqlBound {
    private String sql;
    private LinkedHashMap<Integer, Object> paramTable;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public LinkedHashMap<Integer, Object> getParamTable() {
        return paramTable;
    }

    public void setParamTable(LinkedHashMap<Integer, Object> paramTable) {
        this.paramTable = paramTable;
    }

    public SqlBound(String sql, LinkedHashMap<Integer, Object> paramMap) {
        this.sql = sql;
        this.paramTable = paramMap;
    }

    public SqlBound() {

    }
}
