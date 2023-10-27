package com.refout.sqltracer.handler;

/**
 * 数据查询语言 DQL (全称：Data Query Language）：对数据查询操作
 * <p>
 * 基本结构是由SELECT子句，FROM子句，WHERE子句组成的查询块：SELECT <字段名表>FROM <表或视图名>WHERE <查询条件>
 * <p>
 * <code>
 * SELECT 列名称 FROM 表名称
 * <p>
 * SELECT 列名称 FROM 表名称 WHERE 列 运算符 值
 * </code>
 *
 * @author oo w
 * @version 1.0
 * @since 2023/10/26 8:51
 */
public class DQLSqlParse extends SqlParse {

    /**
     * 生成回滚 sql
     *
     * @param rawSql 原 sql
     * @return 回滚 sql
     */
    @Override
    public String rollbackSql(String rawSql) {
        return null;
    }

}
