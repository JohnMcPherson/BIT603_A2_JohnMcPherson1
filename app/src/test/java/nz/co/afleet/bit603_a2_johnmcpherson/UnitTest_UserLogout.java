package nz.co.afleet.bit603_a2_johnmcpherson;

import org.junit.Test;

import static nz.co.afleet.bit603_a2_johnmcpherson.UnitTest_User_And_Login.TRINI;
import static nz.co.afleet.bit603_a2_johnmcpherson.UnitTest_User_And_Login.TRINI_PASSWORD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest_UserLogout {
    @Test
    public void testLogout() {
        User.loginUser(TRINI, TRINI_PASSWORD);

        // confirm that Trini is logged in
        assertEquals(User.getAllUsers().get(TRINI), User.getLoggedInUser());

        // logout Harry
        User.logoutUser();

        // confirm no logged in user
        assertNull(User.getLoggedInUser());
    }
}