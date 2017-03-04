package com.mtsmda.helper;

/**
 * Created by dminzat on 9/2/2016.
 */

import org.testng.annotations.Test;

import static com.mtsmda.helper.ObjectHelper.*;
import static org.testng.Assert.*;

/**
 * {@link ObjectHelper}
 * */
public class ObjectHelperTest {

    String f = "Ivanov";
    String s = "Ivanov";

    @Test
    public void objectIsNullThrowExceptionTest(){
        ObjectHelper.objectIsNullThrowException(new Object());
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void objectIsNullThrowExceptionThrowExceptionTest(){
        ObjectHelper.objectIsNullThrowException(null);
    }

    @Test
    public void objectIsNullTest(){
        assertTrue(ObjectHelper.objectIsNull(null));
        assertFalse(ObjectHelper.objectIsNull(new String()));
    }

    @Test
    public void objectIsNotNullTest(){
        assertTrue(ObjectHelper.objectIsNotNull(new String()));
        assertFalse(ObjectHelper.objectIsNotNull(null));
    }

    @Test
    public void isSameObjectsTest(){
        assertTrue(ObjectHelper.isSameObjects(f, s));
        f = new String(f);
        assertFalse(ObjectHelper.isSameObjects(f, s));
        f = f.intern();
        assertTrue(ObjectHelper.isSameObjects(f, s));
    }

    @Test
    public void isNotSameObjectsTest(){
        assertTrue(ObjectHelper.isNotSameObjects(f, new String(f)));
        assertFalse(ObjectHelper.isNotSameObjects(f, s));
        f = f.intern();
        assertFalse(ObjectHelper.isNotSameObjects(f, s));
    }

    @Test
    public void isEqualsObjectsTest(){
        assertTrue(ObjectHelper.isEqualsObjects(f, new String(f)));
        assertTrue(ObjectHelper.isEqualsObjects(f, s));
        assertTrue(ObjectHelper.isEqualsObjects(f, s.intern()));
        assertFalse(ObjectHelper.isEqualsObjects(f, s.toLowerCase()));
        assertTrue(ObjectHelper.isEqualsObjects(f, s));
        System.out.println("----------------------");
        PersonWithoutEquals personWithoutEqualsF = new PersonWithoutEquals(f);
        PersonWithoutEquals personWithoutEqualsS = new PersonWithoutEquals(f);
        assertFalse(ObjectHelper.isEqualsObjects(personWithoutEqualsF, personWithoutEqualsS));
    }

    @Test
    public void isNotEqualsObjectsTest(){
        assertFalse(ObjectHelper.isNotEqualsObjects(f, new String(f)));
        assertFalse(ObjectHelper.isNotEqualsObjects(f, s));
        assertFalse(ObjectHelper.isNotEqualsObjects(f, s.intern()));
        assertTrue(ObjectHelper.isNotEqualsObjects(f, s.toLowerCase()));
        assertFalse(ObjectHelper.isNotEqualsObjects(f, s));
        System.out.println("----------------------");
        PersonWithoutEquals personWithoutEqualsF = new PersonWithoutEquals(f);
        PersonWithoutEquals personWithoutEqualsS = new PersonWithoutEquals(f);
        assertTrue(ObjectHelper.isNotEqualsObjects(personWithoutEqualsF, personWithoutEqualsS));

        PersonWithEquals personWithEqualsF = new PersonWithEquals(personWithoutEqualsF);
        PersonWithEquals personWithEqualsS = new PersonWithEquals(personWithoutEqualsS);
        assertFalse(ObjectHelper.isNotEqualsObjects(personWithEqualsF, personWithEqualsS));
    }

    @Test
    public void objectIsNullThrowExceptionWithCustomMessageTest(){
        String customMessage = "This is null";
        try {
            objectIsNullThrowExceptionWithCustomMessage(null, customMessage);
        }
        catch (RuntimeException e){
            assertNotNull(e);
            assertNotNull(e.getMessage());
            assertEquals(e.getMessage(), customMessage + " " + OBJECT_IS_NULL);
        }
    }

    @Test
    public void objectIsNullThrowExceptionWithOnlyCustomMessageTest(){
        String message = "NULL_OBJ";
        try{
            objectIsNullThrowExceptionWithOnlyCustomMessage(null, message);
        }
        catch (RuntimeException e){
            assertNotNull(e);
            assertNotNull(e.getMessage());
            assertEquals(e.getMessage(), message);
        }
    }

    private class PersonWithoutEquals{
        private String name;

        public PersonWithoutEquals(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private class PersonWithEquals extends PersonWithoutEquals{

        public PersonWithEquals(String name) {
            super(name);
        }

        public PersonWithEquals(PersonWithoutEquals personWithoutEquals){
            this(personWithoutEquals.getName());
        }

        @Override
        public int hashCode() {
            return getName().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return this.getName().equals(((PersonWithEquals)obj).getName());
        }
    }

}