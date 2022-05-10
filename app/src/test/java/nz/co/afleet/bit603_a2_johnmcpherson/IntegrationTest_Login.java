/*
ASSUMPTIONS
    -   Integration testing is not a requirement of the Assessment.
        But one of the course videos mentioned Robolectric. I wanted to give it a try, and keep it as a reference
*/

package nz.co.afleet.bit603_a2_johnmcpherson;

import android.app.Activity;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class IntegrationTest_Login {
    private LoginActivity loginActivity;
    private TextView userName;
    private TextView password;

    @Before
    public void setupLoginActivity() {
        loginActivity = Robolectric.setupActivity(LoginActivity.class);
        userName = loginActivity.findViewById(R.id.editTextUserName);
        password = loginActivity.findViewById(R.id.editTextPassword);
    }

    @Test
    public void initialText_isCorrect() {
        testMandatoryIndicatorIsCorrect(R.id.mandatoryUser);
        testMandatoryIndicatorIsCorrect(R.id.mandatoryPassword);
        confirmTextViewTextIsCorrect(R.id.textUserLabel, "User");
        confirmTextViewTextIsCorrect(R.id.textPasswordLabel, "Password");
        confirmTextViewTextIsCorrect(R.id.buttonLogin, "Login");
        confirmErrorMessage("");
     }

    private void confirmTextViewTextIsCorrect(int viewId, String requiredText) {
        TextView viewToTest = loginActivity.findViewById(viewId);
        assertTrue(viewToTest.getText().toString().equals(requiredText));
    }

    private void testMandatoryIndicatorIsCorrect(int indicatorViewId) {
        TextView viewToTest = loginActivity.findViewById(indicatorViewId);
        assertEquals(viewToTest.getCurrentTextColor(), Color.RED);
        assertTrue(viewToTest.getText().toString().equals("*"));
    }

    @Test
    public void errorMessages_areCorrect() {
        // check our starting point
        confirmErrorMessage("");

        Button loginButton = loginActivity.findViewById(R.id.buttonLogin);
        //simulate a click and check initial error message
        loginButton.callOnClick();
        confirmErrorMessage("Please enter your User Name and Password");

        //enter a user name and check missing password message
        userName.setText("Test User");
        loginButton.callOnClick();
        confirmErrorMessage("Please enter your Password");

        //enter a user name and check missing password message
        password.setText("junkpassword");
        loginButton.callOnClick();
        confirmErrorMessage("");

        // clear the user name and check missing user name message
        userName.setText("");
        loginButton.callOnClick();
        confirmErrorMessage("Please enter your User Name");
    }

    private void confirmErrorMessage(String requiredText) {
        confirmTextViewTextIsCorrect(R.id.textErrorMessage, requiredText);
    }

}
