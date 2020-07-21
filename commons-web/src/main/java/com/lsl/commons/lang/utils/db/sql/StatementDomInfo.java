package com.lsl.commons.lang.utils.db.sql;

import org.dom4j.Element;

public class StatementDomInfo {
    private String staticStatement;
    private String id;
    private String name;
    private String title;
    private boolean isDynamicStatement;
    private StatementType statementType;
    private SqlCommandType sqlCommandType;
    private Element statementDom;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Element getStatementDom() {
        return statementDom;
    }

    public void setStatementDom(Element statementDom) {
        this.statementDom = statementDom;
    }

    public String getStaticStatement() {
        return staticStatement;
    }

    public void setStaticStatement(String staticStatement) {
        this.staticStatement = staticStatement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDynamicStatement() {
        return isDynamicStatement;
    }

    public void setDynamicStatementFleg(boolean dynamicedStatementFleg) {
        this.isDynamicStatement = dynamicedStatementFleg;
    }

    public StatementType getStatementType() {
        return statementType;
    }

    public void setStatementType(StatementType statementType) {
        this.statementType = statementType;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public StatementDomInfo(String id, SqlCommandType sqlCommandType, Element statementDom) {
        this.id = id;
        this.sqlCommandType = sqlCommandType;
        this.statementDom = statementDom;
    }

    public StatementDomInfo() {}
}

enum StatementType {
    STATEMENT, PREPARED, CALLABLE
}

enum SqlCommandType {
    UNKNOWN, INSERT, UPDATE, DELETE, SELECT, FLUSH;

    public String value() {
        return this.toString().toLowerCase();
    }
}

enum TagAttribute {
    NAMESPACE, ID, NAME, TITLE, MARK, PARAMTYPE, RESULTTYPE, PREPEND, OPEN, CLOSE, CONJUNCTION, PROPERTY;

    public String value() {
        return this.toString().toLowerCase();
    }
}

enum TagType {
    isEmpty, isNotEmpty, iterate;

    public String value() {
        return this.toString();
    }
}
