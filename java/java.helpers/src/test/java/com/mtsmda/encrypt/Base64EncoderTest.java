package com.mtsmda.encrypt;

import org.junit.Test;

import static com.mtsmda.encrypt.Base64Encoder.*;
import static org.junit.Assert.*;

/**
 * Created by dminzat on 3/1/2017.
 */
public class Base64EncoderTest {

    private String username = "username";
    private String password = "password";

    @Test
    public void testSuccess(){
        String encodeBase64 = getEncodeBase64(username, password);
        assertNotNull(encodeBase64);
        assertFalse(encodeBase64.isEmpty());
        System.out.println(encodeBase64);
    }

    @Test
    public void testUnSuccess(){
        testUnSuccessProcess(null, password, USERNAME_EXCEPTION_MESSAGE);
        testUnSuccessProcess("", password, USERNAME_EXCEPTION_MESSAGE);
        testUnSuccessProcess("            ", password, USERNAME_EXCEPTION_MESSAGE);

        testUnSuccessProcess(username, null, PASSWORD_EXCEPTION_MESSAGE);
        testUnSuccessProcess(username, "", PASSWORD_EXCEPTION_MESSAGE);
        testUnSuccessProcess(username, "            ", PASSWORD_EXCEPTION_MESSAGE);
    }

    private void testUnSuccessProcess(String username, String password, String errorMessage){
        try {
            getEncodeBase64(username, password);
        }
        catch (RuntimeException e){
            assertNotNull(e);
            assertNotNull(e.getMessage());
            assertEquals(e.getMessage(), errorMessage);
        }
    }

}