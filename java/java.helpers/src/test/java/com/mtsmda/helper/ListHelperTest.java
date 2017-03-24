package com.mtsmda.helper;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mtsmda.helper.ListHelper.*;
import static com.mtsmda.helper.ListHelper.firstListAddSecondList;
import static org.testng.Assert.*;
/**
 * Created by dminzat on 9/2/2016.
 * {@link ListHelper}
 */
public class ListHelperTest {
    private int count = 0;
    private int delimiterLength;

    @Test
    public void listIsNotNullAndNotEmptyTest() {
        List<String> strings = null;
        assertFalse(listIsNotNullAndNotEmpty(strings));
        strings = new ArrayList<>();
        assertFalse(listIsNotNullAndNotEmpty(strings));
        strings.add("");
        assertTrue(listIsNotNullAndNotEmpty(strings));
    }

    @Test
    public void listAndFirstElementIsNotNullTest(){
        List<String> strings = null;
        assertFalse(listAndFirstElementIsNotNull(strings));
        strings = new ArrayList<>();
        assertFalse(listAndFirstElementIsNotNull(strings));
        strings.add(null);
        assertFalse(listAndFirstElementIsNotNull(strings));
        strings.add("");
        assertFalse(listAndFirstElementIsNotNull(strings));
        strings.set(0, "");
        assertTrue(listAndFirstElementIsNotNull(strings));
    }

    @Test
    public void getListWithDataTest(){
        assertNotNull(getListWithData("String1", "Ionescu", "Stanescu", "Vasiliev"));
        assertNotNull(getListWithData("String1"));
        assertNull(getListWithData());
        assertNull(getListWithData(null));
    }

    @Test
    public void listIsNullOrEmptyTest(){
        assertTrue(listIsNullOrEmpty(null));
        List<String> strings = new ArrayList<>();
        assertTrue(listIsNullOrEmpty(strings));
        strings.add(null);
        assertFalse(listIsNullOrEmpty(strings));
    }

    @Test
    public void testGetListAsStringWithDelimiter(){
        List<String> listWithData = getListWithData("String1", "Ionescu", "Stanescu", "Vasiliev");
        testGetListAsStringWithDelimiterProcess(listWithData, null);
        testGetListAsStringWithDelimiterProcess(listWithData, ";;");
        testGetListAsStringWithDelimiterProcess(listWithData, "|BlA|");
    }

    @Test
    public void toStringListTest(){
        String s = toStringList(Arrays.asList("First", "Second", "Third", "Forth", "Fifth"));
        assertNotNull(s);
        assertFalse(s.isEmpty());
        assertEquals(s, "First, Second, Third, Forth, Fifth");
    }

    @Test
    public void firstListAddSecondListTest(){
        firstListAddSecondListWithException(null, new ArrayList<String>(), true);
        firstListAddSecondListWithException(new ArrayList<String>(), new ArrayList<String>(), true);
        firstListAddSecondListWithException(ListHelper.getListWithData("Name"), null, false);
        firstListAddSecondListWithException(ListHelper.getListWithData("Name"), new ArrayList<String>(), false);

        List<Integer> first = getListWithData(15, 19, 16, 25, 89);
        int initFirstSize = first.size();
        assertFalse(first.contains(30));
        List<Integer> second = getListWithData(30);
        firstListAddSecondList(first, second);
        assertNotNull(first);
        assertTrue((initFirstSize + second.size()) == first.size());
        assertTrue(first.contains(30));
    }

    @Test
    public void getIndexFirstContainsItemTest(){
        List<String> listWithData = ListHelper.getListWithData("Name", "Fistname", "MiddleName", "LastName", "Username");
        int username = getIndexFirstContainsItem(listWithData, "username", true);
        assertEquals(username, -1);
        username = getIndexFirstContainsItem(listWithData, "name", true);
        assertEquals(username, 1);
        username = getIndexFirstContainsItem(listWithData, "Use", true);
        assertEquals(username, 4);

    }

    private <T>void firstListAddSecondListWithException(List<T> mainList, List<T> secondList, boolean isFirst){
        try{
            firstListAddSecondList(mainList, secondList);
        }
        catch (RuntimeException e){
            assertNotNull(e);
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
            if(isFirst){
                assertEquals(e.getMessage(), "First list is null or empty");
            }else{
                assertEquals(e.getMessage(), "Second list is null or empty");
            }
        }
    }

    private void testGetListAsStringWithDelimiterProcess(List<String> listWithData, String delimiter){
        if(StringUtils.isNotBlank(delimiter)){
            delimiterLength = delimiter.length();
        }else{
            delimiterLength = 1;
        }
        count = 0;
        String listAsStringWithDelimiter = getListAsStringWithDelimiter(listWithData, delimiter);
        assertNotNull(listAsStringWithDelimiter);
        System.out.println(listAsStringWithDelimiter);

        listWithData.forEach(s -> {
            count += s.length() + delimiterLength;
        });

        assertTrue((count - delimiterLength) == listAsStringWithDelimiter.length());
    }

}