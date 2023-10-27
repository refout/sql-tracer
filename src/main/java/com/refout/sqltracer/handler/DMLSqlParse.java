package com.refout.sqltracer.handler;

/**
 * 数据操作语言 DML (全称：Data Manipulation Language)：对数据插入、删除和更新三种操作
 * <p>
 * 包括动词 INSERT，UPDATE和DELETE，分别用于增加修改和删除
 * <p>
 * <code>
 * 增加
 * INSERT INTO 表名称 VALUES (值1, 值2,....) INSERT INTO 表名称 (列1, 列2,...) VALUES (值1, 值2,....)
 * <p>
 * 修改
 * UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
 * <p>
 * 删除
 * DELETE FROM 表名称 WHERE 列名称 = 值
 * </code>
 *
 * @author oo w
 * @version 1.0
 * @since 2023/10/26 8:51
 */
public class DMLSqlParse extends SqlParse {

    /**
     * 生成回滚 sql
     *
     * @param rawSql 原 sql
     * @return 回滚 sql
     */
    @Override
    public String rollbackSql(String rawSql) {
        String sql = cleanSql(rawSql);
        if (sql == null || sql.isBlank()) {
            return null;
        }
        sql = removeSpace(sql);


        return null;
    }

    private String rollbackSqlInsert(String sql) {
        return null;
    }

    private String rollbackSqlUpdate(String sql) {
        return null;
    }

    private String rollbackSqlDelete(String sql) {
        return null;
    }

}
