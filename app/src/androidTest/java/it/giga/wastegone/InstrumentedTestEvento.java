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

import it.giga.wastegone.gestioneEventiSensibilizzazione.RecuperoEventoActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTestEvento {

    @Rule
    public ActivityScenarioRule<RecuperoEventoActivity> activityScenarioRule = new ActivityScenarioRule<RecuperoEventoActivity>(RecuperoEventoActivity.class);

    TextView tvValue;

    @Test
    public void checkStato1() {

        // recupero data
        String data1 = "14/12/2024";
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

        // la data deve coincidere con la data odierna
        assertEquals(ldt1, ldt2);

    }


    @Test
    public void checkStato2() {

        // recupero data
        String data1 = "14/12/2024";
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
        LocalDate ldt1 = LocalDate.parse(data1, f);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        onView(withId(R.id.btStatoEvento2)).perform(click());

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

        // la data deve essere nel passato
        assertTrue(ldt2.isBefore(ldt1));

    }


    @Test
    public void checkStato3() {

        // recupero data
        String data1 = "14/12/2024";
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
        LocalDate ldt1 = LocalDate.parse(data1, f);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        onView(withId(R.id.btStatoEvento3)).perform(click());

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

        // la data deve essere nel futuro o uguale alla data odierna
        assertTrue(ldt2.isEqual(ldt1) || ldt2.isAfter(ldt1));

    }


}