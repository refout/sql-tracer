package com.refout.sqltracer.handler;

/**
 * 数据定义语言 DDL (全称：Data Query Language）：定义数据库的逻辑结构，包括定义数据库、基本表、视图和索引四个部分
 * <p>
 * 包括动词CREATE（创造），ALTER（修改）和DROP（删除）。在数据库中创建新表或修改、删除表（CREAT TABLE 或 DROP TABLE）；表加入索引等
 * <p>
 * <code>
 * 创建库
 * CREATE DATABASE 数据库名称
 * <p>
 * 创建表
 * CREATE TABLE 表名称 (
 * 列名称1 数据类型,
 * 列名称2 数据类型,
 * 列名称3 数据类型,
 * ....
 * )
 * <p>
 * 删除表
 * DROP TABLE 表名称
 * </code>
 *
 * @author oo w
 * @version 1.0
 * @since 2023/10/26 8:51
 */
public class DDLSqlParse extends SqlParse {

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
