package com.mtsmda.helper;

import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mtsmda.helper.QueryCreatorHelper.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by dminzat on 9/5/2016.
 * {@link QueryCreatorHelper}
 */
public class QueryCreatorHelperTest {

    private String tableName = "cities";
    private List<String> fields = ListHelper.getListWithData("city_name", "city_country_id");

    @Test
    public void insertGenerateTrueTest() {
        String cities = insertGenerate(tableName, fields);
        assertNotNull(cities);
        assertEquals(cities, INSERT_INTO + " " + tableName + " (city_name,city_country_id) " + VALUES + "(:city_name,:city_country_id)");
    }

    @Test
    public void insertGenerateWithDateTest(){
        fields.add("date");
        String cities = insertGenerateWithDate(tableName, fields, ListHelper.getListWithData(getToDateOracleAsParam("date", TO_DATE_ONLY_DATE_ORACLE_PATTERN)));
        assertNotNull(cities);
        assertEquals(cities, "INSERT INTO cities (city_name,city_country_id,date) VALUES(:city_name,:city_country_id,TO_DATE(:date, 'dd.mm.yyyy'))");

    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void insertGenerateFalseTestBothParamsIsNull() {
        insertGenerate(null, null);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void insertGenerateFalseTestTableNameIsEmptyFieldsIsNull() {
        insertGenerate("", null);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void insertGenerateFalseTestFieldsIsNull() {
        insertGenerate(this.tableName, null);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void insertGenerateFalseTestFieldIsEmpty() {
        insertGenerate(this.tableName, new ArrayList<String>());
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void insertGenerateFalseTestFieldIsEmpty2() {
        List<String> fields = new ArrayList<>();
        insertGenerate(this.tableName, fields);
    }

    @Test
    public void updateGenerateTrueTest() {
        List<String> fields = ListHelper.getListWithData("city_name", "city_country_id");
        String cities = updateGenerate(tableName, fields, "city_id");
        assertNotNull(cities);
        assertEquals(cities, "UPDATE cities SET city_name = :city_name, city_country_id = :city_country_id WHERE city_id = :city_id;");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void updateGenerateFalseTestTableNameIsNull() {
        List<String> fields = ListHelper.getListWithData("city_name", "city_country_id");
        String cities = updateGenerate(null, fields, "city_id");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void updateGenerateFalseTestTableNameIsEmpty() {
        List<String> fields = ListHelper.getListWithData("city_name", "city_country_id");
        String cities = updateGenerate("", fields, "city_id");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void updateGenerateFalseTestFieldsIsNull() {
        String cities = updateGenerate(this.tableName, null, "city_id");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void updateGenerateFalseTestWhereClauseIsNull() {
        List<String> fields = ListHelper.getListWithData("city_name", "city_country_id");
        String cities = updateGenerate("", fields, null);
    }

    @Test
    public void sameEqualsTrueTest() {
        String cityName = sameEquals("city_name");
        assertNotNull(cityName);
        assertEquals(cityName, " city_name = :city_name");
    }

    @Test
    public void deleteGenerateTrueTest() {
        String cityName = deleteGenerate(this.tableName, "city_name");
        assertNotNull(cityName);
        assertEquals(cityName, "DELETE FROM cities WHERE city_name = :city_name;");
    }

    @Test
    public void deleteGenerateWithMoreThanOneWhereClauseTrueTest() {
        String cityName = deleteGenerateWithMoreThanOneWhereClause(this.tableName, ListHelper.getListWithData("city_name", "city_id"), new Boolean(true));
        assertNotNull(cityName);
        assertEquals(cityName, "DELETE FROM cities WHERE city_name = :city_name AND city_id = :city_id;");

        cityName = deleteGenerateWithMoreThanOneWhereClause(this.tableName, ListHelper.getListWithData("city_name", "city_id"), new Boolean(false));
        assertNotNull(cityName);
        assertEquals(cityName, "DELETE FROM cities WHERE city_name = :city_name OR city_id = :city_id;");

        cityName = deleteGenerateWithMoreThanOneWhereClause(this.tableName, ListHelper.getListWithData("city_name", "city_id", "new_field"), new Boolean(false));
        assertNotNull(cityName);
        assertEquals(cityName, "DELETE FROM cities WHERE city_name = :city_name OR city_id = :city_id OR new_field = :new_field;");

        cityName = deleteGenerateWithMoreThanOneWhereClause(this.tableName, ListHelper.getListWithData("city_name"), null);
        assertNotNull(cityName);
        assertEquals(cityName, "DELETE FROM cities WHERE city_name = :city_name;");

        try {
            cityName = deleteGenerateWithMoreThanOneWhereClause(this.tableName, ListHelper.getListWithData("city_name", "city_id"), null);
        }
        catch (RuntimeException e){
            assertEquals(e.getMessage(), "Cannot be is where clause had more than 1 clause and 'AND' or 'OR' operation is null");
        }
    }

    @Test
    public void deleteGenerateTrueTestDeleteAll() {
        String cityName = deleteGenerate(this.tableName, null);
        assertNotNull(cityName);
        assertEquals(cityName, "DELETE FROM cities;");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void deleteGenerateFalseTestTableNameIsNull() {
        String cityName = deleteGenerate(null, "city_name");
    }

    @Test
    public void selectByIdTrueTest() {
        String selectById = selectById(this.tableName, this.fields.get(0));
        assertNotNull(selectById);
        assertEquals(selectById, "SELECT * FROM cities WHERE city_name = :city_name;");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void selectByIdFalseTestTableNameIsNull() {
        String selectById = selectById(null, this.fields.get(0));
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void selectByIdFalseTestWhereClauseIsNull() {
        String selectById = selectById(this.tableName, null);
    }

    @Test
    public void getAliasTrueTest() {
        String alias = getAlias(this.tableName);
        assertNotNull(alias);
        assertEquals(alias, "c");
    }

    @Test
    public void selectAllTrueTest() {
        String selectAll = selectAll(this.tableName);
        assertNotNull(selectAll);
        assertEquals(selectAll, "SELECT * FROM cities;");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void selectAllFalseTestTableNameIsNull() {
        String selectAll = selectAll(null);
    }

    @Test
    public void selectCustomTest(){
        String expected = "SELECT c.name,c.age FROM cities c WHERE c.id = :id";
        String s = selectCustom(ListHelper.getListWithData("name", "age"), "cities",
                ListHelper.getListWithData("id"), true);
        assertNotNull(s);
        assertEquals(s, expected);

        expected = "SELECT * FROM cities c WHERE c.id = :id";
        s = selectCustom(null, "cities",
                ListHelper.getListWithData("id"), true);
        assertNotNull(s);
        assertEquals(s, expected);

        expected = "SELECT * FROM cities c WHERE c.id = :id";
        s = selectCustom(new ArrayList<>(), "cities",
                ListHelper.getListWithData("id"), true);
        assertNotNull(s);
        assertEquals(s, expected);

        expected = "SELECT * FROM cities c";
        s = selectCustom(new ArrayList<>(), "cities",
                null, true);
        assertNotNull(s);
        assertEquals(s, expected);
    }

    @Test
    public void getToDateOracleTest(){
        String toDateOracle = getToDateOracle("10:15:25 10.02.2017", TO_DATE_ORACLE_PATTERN);
        assertNotNull(toDateOracle);
        assertEquals(toDateOracle, "TO_DATE('10:15:25 10.02.2017', '" + TO_DATE_ORACLE_PATTERN + "')");
    }

    @Test
    public void getToDateOracleAsParamTest(){
        String toDateOracle = getToDateOracleAsParam("date");
        assertNotNull(toDateOracle);
        assertEquals(toDateOracle, "TO_DATE(:date, 'hh24:mi:ss dd.mm.yyyy')");

        toDateOracle = getToDateOracleAsParam("date", TO_DATE_ONLY_TIME_ORACLE_PATTERN);
        assertNotNull(toDateOracle);
        assertEquals(toDateOracle, "TO_DATE(:date, 'hh24:mi:ss')");
    }

    @Test
    public void getToDateOracleDefaultPatternTest(){
        String toDateOracleDefaultPattern = getToDateOracleDefaultPattern("10:15:25 10.02.2017");
        assertNotNull(toDateOracleDefaultPattern);
        assertEquals(toDateOracleDefaultPattern, "TO_DATE('10:15:25 10.02.2017', '" + TO_DATE_ORACLE_PATTERN + "')");
    }

    @Test
    public void getToDateOracleDefaultPattern2Test(){
        String toDateOracleDefaultPattern = getToDateOracleDefaultPattern(LocalDateTime.of(2017, 02, 10, 10, 15, 25));
        assertNotNull(toDateOracleDefaultPattern);
        assertEquals(toDateOracleDefaultPattern, "TO_DATE('10:15:25 10.02.2017', '" + TO_DATE_ORACLE_PATTERN + "')");

        String query = "INSERT INTO T_USER_ATTEMPTS(ACCOUNT_USER_ID, ATTEMPTS, LAST_MODIFIED) values (1,1,TO_DATE('10:15:25 10.02.2017', 'hh24:mi:ss dd.mm.yyyy'))";
        toDateOracleDefaultPattern = getToDateOracleDefaultPattern(LocalDateTime.of(2017, 02, 10, 10, 15, 25));
        assertNotNull(toDateOracleDefaultPattern);
        assertEquals("INSERT INTO T_USER_ATTEMPTS(ACCOUNT_USER_ID, ATTEMPTS, LAST_MODIFIED) values (1,1," + toDateOracleDefaultPattern + ")",
                query);
    }

    @Test
    public void getParamTest(){
        String name = getParam("name");
        assertNotNull(name);
        assertEquals(":name", name);

        name = getParam("name", true, true);
        assertNotNull(name);
        assertEquals(":name,", name);

        name = getParam("name", true, false);
        assertNotNull(name);
        assertEquals(":name, ", name);
    }

    @Test
    public void getUpdateParamTest(){
        String name = getUpdateParam("name", true);
        assertNotNull(name);
        assertEquals("name = :name", name);

        name = getUpdateParam("name", false);
        assertNotNull(name);
        assertEquals("name = :name, ", name);
    }

    @Test
    public void getInnerJoinOnTest(){
        String innerJoinOn = getInnerJoinOn("p", "person_id", "l", "person_id_f");
        assertNotNull(innerJoinOn);
        assertEquals(" ON p.person_id = l.person_id_f", innerJoinOn);
    }

    @Test
    public void getInnerJoinFirstJoinTest(){
        String innerJoinOn = getInnerJoinFirstJoin("Person", "p", "person_id", "L_Person", "l", "person_id_f");
        assertNotNull(innerJoinOn);
        assertEquals("Person p INNER JOIN L_Person l ON p.person_id = l.person_id_f", innerJoinOn);
    }

    @Test
    public void getInnerJoinNotFirstJoinTest(){
        String innerJoinNotFirstJoin = getInnerJoinNotFirstJoin("City", "c", "city_id", "Person", "p", "person_city_id");
        assertNotNull(innerJoinNotFirstJoin);
        assertEquals(" INNER JOIN City c ON c.city_id = p.person_city_id", innerJoinNotFirstJoin);
    }

    @Test
    public void getTableWithPrefixTest(){
        String tableWithPrefix = getTableWithPrefix("Person", "pr");
        assertNotNull(tableWithPrefix);
        assertEquals("Person pr ", tableWithPrefix);
    }

}