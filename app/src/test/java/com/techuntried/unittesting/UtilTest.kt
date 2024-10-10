package com.techuntried.unittesting

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilTest {

    @Test
    fun checkIsUsernameValid() {
        val username1 = "hello"
        val username2 = "hello df"
        val username3 = "achintwadhwa"
        val username4 = "ahint*wadhwa"
        val result1 = Util.checkIsUsernameValid(username1)
        val result2 = Util.checkIsUsernameValid(username2)
        val result3 = Util.checkIsUsernameValid(username3)
        val result4 = Util.checkIsUsernameValid(username4)
        assertEquals(false, result1)
        assertEquals(false, result2)
        assertEquals(true, result3)
        assertEquals(false, result4)
    }

    @Test
    fun checkIsPasswordValid() {
        val password1 = "hello"
        val password2 = "hello df"
        val password3 = "achintwadhwa"
        val password4 = "ahint*wadhwa"
        val password5 = "aCHINT45@"
        val password6 = " fdklfjslkdj"
        val result1 = Util.checkPasswordValid(password1)
        val result2 = Util.checkPasswordValid(password2)
        val result3 = Util.checkPasswordValid(password3)
        val result4 = Util.checkPasswordValid(password4)
        val result5 = Util.checkPasswordValid(password5)
        val result6 = Util.checkPasswordValid(password6)
        assertEquals(false, result1)
        assertEquals(false, result2)
        assertEquals(false, result3)
        assertEquals(false, result4)
        assertEquals(true, result5)
        assertEquals(false, result6)
    }


}