package com.refout.sqltracer.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public abstract class SqlParse {

    /**
     * On UNIX systems, it returns "\n"; on Microsoft Windows systems it returns "\r\n".
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private static final String LINE_SEPARATOR_UNIX = "\\n";

    private static final String LINE_SEPARATOR_WINDOWS = "\\r\\n";

    private static final String DEFAULT_DELIMITER = ";";

    private static final String COMMENT_SHARP_SEPARATORS = "#";

    private static final String COMMENT_LINE_SEPARATORS = "--";

    /**
     * 生成回滚 sql
     *
     * @param rawSql 原 sql
     * @return 回滚 sql
     */
    public abstract String rollbackSql(String rawSql);

    /**
     * SQL 文件多条 SQL 语句分割
     *
     * @param reader 文件 {@link Reader}
     * @return 多条 SQL 语句
     */
    public List<String> splitSqlFile(Reader reader) {
        List<String> sqlList = new ArrayList<>();
        StringBuilder lines = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (isCommentLine(line)) {
                    continue;
                }
                if (isEmptyLine(line)) {
                    continue;
                }
                lines.append(line).append(LINE_SEPARATOR);
                if (line.endsWith(DEFAULT_DELIMITER)) {
                    sqlList.add(lines.toString());
                    lines = new StringBuilder();
                }
            }
            return sqlList;
        } catch (Exception e) {
            log.error("SQL文件分割异常", e);
            return null;
        }
    }

    @Nullable
    public String cleanSql(@Nullable String rawSql) {
        if (rawSql == null) {
            return null;
        }
        String trimmedSql = rawSql.trim();
        if (isEmptyLine(trimmedSql)) {
            return null;
        }

        String[] split = trimmedSql.split("(\\r\\n)|(\\n)");
        if (split.length <= 1) {
            return null;
        }

        StringBuilder sql = new StringBuilder();
        for (String s : split) {
            if (isEmptyLine(s) || isCommentLine(s.trim())) {
                continue;
            }
            sql.append(s).append(" ");
        }
        return sql.toString().trim();
    }

    protected String removeSpace(String rawSql) {
        if (rawSql == null || rawSql.isBlank()) {
            return rawSql;
        }
        return rawSql.replaceAll("\\s{2,}(?=([^']*'[^']*')*[^']*$)", " ");
    }

    /**
     * 是否为空行
     *
     * @param line 文件中的一行
     * @return 是否为空行
     */
    protected boolean isEmptyLine(String line) {
        return line == null || line.isBlank();
    }

    /**
     * 是否为注释行
     *
     * @param line 文件中的一行，已去除空格
     * @return 是否为注释行
     */
    protected boolean isCommentLine(@NonNull String line) {
        String trimmedLine = line.trim();
        return trimmedLine.startsWith(COMMENT_SHARP_SEPARATORS) || trimmedLine.startsWith(COMMENT_LINE_SEPARATORS);
    }

    protected boolean isSql(@Nullable String sql) {
        if (sql == null) {
            return false;
        }
        String trimmedSql = sql.trim();
        if (isEmptyLine(trimmedSql)) {
            return false;
        }
        if (isCommentLine(trimmedSql)) {
            String[] split = trimmedSql.split("(\\r\\n)|(\\n)");
            if (split.length <= 1) {
                return false;
            }
            return Arrays.stream(split).anyMatch(it -> !isEmptyLine(it.trim()) && !isCommentLine(it.trim()));
        }
        return true;

    }

}
