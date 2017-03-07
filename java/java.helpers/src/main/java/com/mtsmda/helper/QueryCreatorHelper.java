package com.mtsmda.helper;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by dminzat on 9/5/2016.
 */
public class QueryCreatorHelper {
    public static final String INSERT_INTO = "INSERT INTO";
    public static final String VALUES = "VALUES";
    public static final String UPDATE = "UPDATE";
    public static final String SET = "SET";
    public static final String WHERE = "WHERE";
    public static final String DELETE_FROM = "DELETE FROM";
    public static final String SELECT = "SELECT";
    public static final String DISTINCT = "DISTINCT ";
    public static final String FROM = "FROM";
    public static final String GROUP_BY = "GROUP BY";
    public static final String HAVING = "HAVING";
    public static final String ORDER_BY = "ORDER BY";
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";
    public static final String AND = "AND";
    public static final String OR = "OR";
    public static final String AS = "AS";
    public static final String INNER_JOIN = "INNER JOIN";
    public static final String ON = "ON";
    public static final String SPACE = " ";
    public static final String STAR = "*";
    public static final String SELECT_ALL = SELECT + SPACE + STAR;
    public static final String OPEN_PARENTHESIS = "(";
    public static final String CLOSE_PARENTHESIS = ")";
    public static final String COMMA = ",";
    public static final String COLON = ":";
    public static final String SEMICOLON = ";";
    public static final String EQUAL = "=";
    public static final String DOT = ".";
    public static final String APOSTROF = "'";

    public static final String INSERT_INTO_WITH_SPACE = INSERT_INTO + SPACE;
    public static final String VALUES_WITH_OPEN_PARENTHESIS = VALUES + SPACE + OPEN_PARENTHESIS;
    public static final String SPACE_VALUES_WITH_OPEN_PARENTHESIS = SPACE + VALUES_WITH_OPEN_PARENTHESIS;
    public static final String UPDATE_WITH_SPACE = UPDATE + SPACE;
    public static final String SET_SPACE_BOTH = SPACE + SET + SPACE;
    public static final String WHERE_SPACE_BOTH = SPACE + WHERE + SPACE;
    public static final String DELETE_FROM_WITH_SPACE = DELETE_FROM + SPACE;
    public static final String SELECT_WITH_SPACE = SELECT + SPACE;
    public static final String SELECT_ALL_WITH_SPACE = SELECT_ALL + SPACE;
    public static final String FROM_WITH_SPACE = FROM + SPACE;
    public static final String FROM_SPACE_BOTH = SPACE + FROM_WITH_SPACE;
    public static final String AND_SPACE_BOTH = SPACE + AND + SPACE;
    public static final String OR_SPACE_BOTH = SPACE + OR + SPACE;
    public static final String ORDER_BY_WITH_SPACE = ORDER_BY + SPACE;
    public static final String ORDER_BY_SPACE_BOTH = SPACE + ORDER_BY_WITH_SPACE;
    public static final String DISTINCT_WITH_SPACE = DISTINCT + SPACE;
    public static final String GROUP_BY_WITH_SPACE = GROUP_BY + SPACE;
    public static final String HAVING_WITH_SPACE = HAVING + SPACE;
    public static final String SPACE_ASC = SPACE + ASC;
    public static final String SPACE_DESC = SPACE + DESC;
    public static final String EQUAL_SPACE_BOTH = SPACE + EQUAL + SPACE;
    public static final String SPACE_WITH_OPEN_PARENTHESIS = SPACE + OPEN_PARENTHESIS;
    public static final String COMMA_WITH_SPACE = COMMA + SPACE;
    public static final String INNER_JOIN_SPACE_BOTH = SPACE + INNER_JOIN + SPACE;
    public static final String ON_SPACE_BOTH = SPACE + ON + SPACE;

    public static final String TO_DATE_ORACLE = "TO_DATE";
    public static final String TO_DATE_ORACLE_PATTERN = "hh24:mi:ss dd.mm.yyyy";

    public static String queryCreator(String... strings) {
        StringBuilder stringBuilderResult = new StringBuilder();
        for (String current : strings) {
            stringBuilderResult.append(current);
        }
        return stringBuilderResult.toString();
    }

    /*
    * insert into cities (city_name, city_country_id) values(:city_name, city_country_id);
    * */
    public static String insertGenerate(String tableName, List<String> fieldNames) {
        if (StringUtils.isBlank(tableName)) {
            throw new RuntimeException("table name is null or empty!");
        }
        ObjectHelper.objectIsNullThrowException(fieldNames);
        if (ListHelper.listIsNullOrEmpty(fieldNames)) {
            throw new RuntimeException("list is null or empty");
        }

        StringBuilder sbResult = new StringBuilder(INSERT_INTO);
        sbResult.append(SPACE).append(tableName).append(SPACE).append(OPEN_PARENTHESIS);
        StringBuilder temp = new StringBuilder();
        fieldNames.forEach(fieldName -> {
            ObjectHelper.objectIsNullThrowException(fieldName);
            sbResult.append(fieldName).append(COMMA);
            temp.append(COLON).append(fieldName).append(COMMA);
        });
        checkEndCommaSymbolAndDeleteHim(sbResult);
        checkEndCommaSymbolAndDeleteHim(temp);
        sbResult.append(CLOSE_PARENTHESIS).append(SPACE).append(VALUES).append(OPEN_PARENTHESIS).append(temp)
                .append(CLOSE_PARENTHESIS).append(SEMICOLON);

        return sbResult.toString();
    }

    /*
    * update cities set city_name = :city_name, city_country_id = :city_country_id where city_id = :city_id;
    * */
    public static String updateGenerate(String tableName, List<String> fieldNamesForChange, String whereClause) {
        if (StringUtils.isBlank(tableName)) {
            throw new RuntimeException("table name is null or empty!");
        }
        if (StringUtils.isBlank(whereClause)) {
            throw new RuntimeException("where clause is null or empty!");
        }
        ObjectHelper.objectIsNullThrowException(fieldNamesForChange);
        if (ListHelper.listIsNullOrEmpty(fieldNamesForChange)) {
            throw new RuntimeException("list is null or empty");
        }

        StringBuilder sbResult = new StringBuilder(UPDATE);
        sbResult.append(SPACE).append(tableName).append(SPACE).append(SET);
        fieldNamesForChange.forEach(fieldName -> {
            ObjectHelper.objectIsNullThrowException(fieldName);
            sbResult.append(sameEquals(fieldName)).append(COMMA);
        });
        checkEndCommaSymbolAndDeleteHim(sbResult);
        sbResult.append(SPACE).append(WHERE).append(sameEquals(whereClause))
                .append(SEMICOLON);

        return sbResult.toString();
    }

    /*
    * delete from cities where city_id = :city_id;
    * if @param whereClause is null should be - delete from cities;
    * */
    public static String deleteGenerate(String tableName, String whereClause) {
        /*if (StringUtils.isBlank(tableName)) {
            throw new RuntimeException("table name is null or empty!");
        }
        StringBuilder sbResult = new StringBuilder(DELETE_FROM);
        sbResult.append(SPACE).append(tableName);
        if (StringUtils.isNotBlank(whereClause)) {
            sbResult.append(SPACE).append(WHERE).append(sameEquals(whereClause));
        }
        sbResult.append(SEMICOLON);
        return sbResult.toString();*/
        return deleteGenerateWithMoreThanOneWhereClause(tableName, ObjectHelper.objectIsNull(whereClause) ? null : ListHelper.getListWithData(whereClause), null);
    }

    /*
    * delete from cities where city_id = :city_id AND city_name = :city_name;
    * if @param whereClause is null should be - delete from cities;
    * */
    public static String deleteGenerateWithMoreThanOneWhereClause(String tableName, List<String> whereClauses, Boolean isAndOperation) {
        if (StringUtils.isBlank(tableName)) {
            throw new RuntimeException("table name is null or empty!");
        }
        if (ObjectHelper.objectIsNotNull(whereClauses) && whereClauses.size() > 1 && ObjectHelper.objectIsNull(isAndOperation)) {
            throw new RuntimeException("Cannot be is where clause had more than 1 clause and 'AND' or 'OR' operation is null");
        }
        StringBuilder sbResult = new StringBuilder(DELETE_FROM);
        sbResult.append(SPACE).append(tableName);
        if (ListHelper.listIsNotNullAndNotEmpty(whereClauses)) {
            sbResult.append(SPACE).append(WHERE);
            for (int i = 0; i < whereClauses.size(); i++) {
                sbResult.append(sameEquals(whereClauses.get(i)));
                if (i != whereClauses.size() - 1) {
                    sbResult.append(SPACE).append(isAndOperation ? AND : OR);
                }
            }
            whereClauses.forEach(current -> {


            });

        }
        sbResult.append(SEMICOLON);
        return sbResult.toString();
    }

    /*
    *  city_id = :city_id
    * */
    public static String sameEquals(String fieldName) {
        return new StringBuilder(SPACE).append(fieldName).append(SPACE).append(EQUAL).append(SPACE).
                append(COLON).append(fieldName).toString();
    }

    /*
    * select * from cities c where city_id = :city_id;
    * */
    public static String selectById(String tableName, String whereClause) {
        if (StringUtils.isBlank(tableName)) {
            throw new RuntimeException("table name is null or empty!");
        }
        if (StringUtils.isBlank(whereClause)) {
            throw new RuntimeException("where clause is null or empty!");
        }
        StringBuilder sbResult = new StringBuilder(SELECT_ALL);
        sbResult.append(SPACE).append(FROM).append(SPACE).append(tableName).append(SPACE);
        sbResult.append(WHERE).append(sameEquals(whereClause)).append(SEMICOLON);

        return sbResult.toString();
    }

    public static String getAlias(String tableName) {
        return new Character(tableName.charAt(0)).toString().toLowerCase();
    }

    /*
    * select * from cities;
    * */
    public static String selectAll(String tableName) {
        if (StringUtils.isBlank(tableName)) {
            throw new RuntimeException("table name is null or empty!");
        }
        StringBuilder sbResult = new StringBuilder(SELECT_ALL);
        sbResult.append(SPACE).append(FROM).append(SPACE).append(tableName).append(SEMICOLON);

        return sbResult.toString();
    }

    /*
    * select c.name, c.age from cities c where c.id = :id
    * */
    public static String selectCustom(List<String> selectParams, String tableName, List<String> whereParam, boolean isAnd) {
        if (StringUtils.isBlank(tableName)) {
            throw new RuntimeException("table name is null or empty!");
        }
        final StringBuilder sbResult = new StringBuilder();
        String alias = getAlias(tableName);
        if (ListHelper.listIsNullOrEmpty(selectParams)) {
            sbResult.append(SELECT_ALL).append(SPACE);
        } else {
            sbResult.append(SELECT).append(SPACE);
            selectParams.forEach(selectParam -> {
                if (StringUtils.isBlank(selectParam)) {
                    throw new RuntimeException("Select param is null or empty!");
                }
                sbResult.append(alias).append(DOT).append(selectParam).append(COMMA);
            });
            if (sbResult.toString().endsWith(COMMA)) {
                String temp = sbResult.substring(0, sbResult.length() - 1);
                sbResult.delete(0, sbResult.length()).append(temp);
            }
            sbResult.append(SPACE);
        }
        checkEndCommaSymbolAndDeleteHim(sbResult);
        sbResult.append(FROM).append(SPACE).append(tableName).append(SPACE).append(alias);

        if (ListHelper.listIsNotNullAndNotEmpty(whereParam)) {
            sbResult.append(SPACE).append(WHERE);
            whereParam.forEach(currentWhereParam -> {
                sbResult.append(SPACE).append(alias).append(DOT).append(currentWhereParam).append(SPACE).append(EQUAL).append(SPACE)
                        .append(COLON).append(currentWhereParam);
                if (isAnd) {
                    sbResult.append(SPACE).append(AND);
                } else {
                    sbResult.append(SPACE).append(OR);
                }
            });
            String tempResult = sbResult.toString();
            if (isAnd) {
                if (tempResult.endsWith(AND)) {
                    return sbResult.substring(0, sbResult.length() - 3).trim();
                }
            } else {
                if (tempResult.endsWith(OR)) {
                    return sbResult.substring(0, sbResult.length() - 2).trim();
                }
            }
        }
        return sbResult.toString().trim();
    }

    public static String getToDateOracle(String localDateTimeStr, String pattern) {
        ObjectHelper.objectIsNullThrowException(localDateTimeStr);
        ObjectHelper.objectIsNullThrowException(pattern);
        return new StringBuilder(TO_DATE_ORACLE).append(OPEN_PARENTHESIS).append(APOSTROF).append(localDateTimeStr)
                .append(APOSTROF).append(COMMA).append(SPACE).append(APOSTROF).append(pattern)
                .append(APOSTROF).append(CLOSE_PARENTHESIS).toString();
    }

    public static String getToDateOracleDefaultPattern(String localDateTimeStr) {
        return getToDateOracle(localDateTimeStr, TO_DATE_ORACLE_PATTERN);
    }

    public static String getToDateOracleDefaultPattern(LocalDateTime localDateTime) {
        return getToDateOracle(LocalDateTimeHelper.convertLocalDateTimeToString(localDateTime, LocalDateTimeHelper.NORMAL_DATE_TIME_FORMAT_VICE_VERSA), TO_DATE_ORACLE_PATTERN);
    }

    public static String getParam(String paramName) {
        return COLON + paramName;
    }

    public static String getSelectParam(String paramName) {
        return paramName + COMMA_WITH_SPACE;
    }

    public static String getParam(String paramName, boolean isComma, boolean isLast) {
        if (isComma) {
            if (isLast) {
                return getParam(paramName) + COMMA;
            } else {
                return getParam(paramName) + COMMA + SPACE;
            }
        }
        return getParam(paramName);
    }

    public static String getInsertParam(String param, boolean isLast) {
        if (isLast) {
            return param + CLOSE_PARENTHESIS;
        }
        return param + COMMA_WITH_SPACE;
    }

    public static String getUpdateParam(String param, boolean isLast) {
        if (isLast) {
            return param + EQUAL_SPACE_BOTH + getParam(param);
        }
        return param + EQUAL_SPACE_BOTH + getParam(param, true, isLast);
    }

    public static String getInnerJoinOn(String prefixFirstTable, String firstTableJoinField,
                                        String prefixSecondTable, String secondTableJoinField) {
        return new StringBuilder(ON_SPACE_BOTH).append(prefixFirstTable).append(DOT).append(firstTableJoinField)
                .append(EQUAL_SPACE_BOTH).append(prefixSecondTable).append(DOT)
                .append(secondTableJoinField).toString();
    }

    public static String getInnerJoinFirstJoin(String firstTableName, String prefixFirstTable, String firstTableJoinField,
                                                String secondTableName, String prefixSecondTable, String secondTableJoinField) {
        return new StringBuilder(firstTableName).append(SPACE).append(prefixFirstTable).append(INNER_JOIN_SPACE_BOTH)
                .append(secondTableName).append(SPACE).append(prefixSecondTable)
                .append(getInnerJoinOn(prefixFirstTable, firstTableJoinField, prefixSecondTable, secondTableJoinField)).toString();
    }

    //t inner join c on t.id = c.id
    //inner join v on t.id = v.id
    public static String getInnerJoinNotFirstJoin(String thirdTableName, String prefixThirdTable, String thirdTableJoinField,
                                                  String forthTableName, String prefixForthTable, String forthTableJoinField) {
        return new StringBuilder(INNER_JOIN_SPACE_BOTH).append(thirdTableName).append(SPACE).append(prefixThirdTable)
                .append(getInnerJoinOn(prefixThirdTable, thirdTableJoinField, prefixForthTable, forthTableJoinField)).toString();
    }

    private static boolean checkEndCommaSymbol(StringBuilder textQuery) {
        return new Character(textQuery.charAt(textQuery.length() - 1)).toString().equals(COMMA);
    }

    private static void checkEndCommaSymbolAndDeleteHim(StringBuilder textQuery) {
        if (checkEndCommaSymbol(textQuery)) {
            textQuery.deleteCharAt(textQuery.length() - 1);
        }
    }

}