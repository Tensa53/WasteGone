package it.giga.wastegone;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.widget.TextView;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicReference;

import it.giga.wastegone.gestioneEventiSensibilizzazione.RecuperoEventoActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTestEvento {

    @Rule
    public ActivityScenarioRule<RecuperoEventoActivity> activityScenarioRule = new ActivityScenarioRule<RecuperoEventoActivity>(RecuperoEventoActivity.class);

    TextView tvValue;

    @Test
    public void checkStato1() {

        String data1 = "11/12/2024";

        DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );

        LocalDate ldt1 = LocalDate.parse(data1, f);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        onView(withId(R.id.btStatoEvento1)).perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        final String[] data2 = new String[1];

        activityScenarioRule.getScenario().onActivity(activity -> {
            tvValue = activity.findViewById(R.id.tvValue);
            data2[0] = tvValue.getText().toString();
        });

        LocalDate ldt2 = LocalDate.parse(data2[0], f);

        assertTrue(ldt2.isAfter(ldt1));

    }
}