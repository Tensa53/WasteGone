package it.giga.wastegone;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import it.giga.wastegone.gestioneSmaltimentoRifiuti.RecuperoRifiutoActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTestRifiuti {

    @Rule
    public ActivityScenarioRule<RecuperoRifiutoActivity> activityScenarioRule = new ActivityScenarioRule<RecuperoRifiutoActivity>(RecuperoRifiutoActivity.class);

    @Test
    public void checkColore1() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        onView(withId(R.id.btColoreRifiuto1)).perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        onView(withId(R.id.tvValue)).check(matches(withText("PLASTICA")));

    }

    @Test
    public void checkColore2() {
    }

    @Test
    public void checkGiorno1() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        onView(withId(R.id.btGiornoRifiuto1)).perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        onView(withId(R.id.tvValue)).check(matches(withText("PLASTICA")));

    }

    @Test
    public void checkGiorno2() {
    }


}