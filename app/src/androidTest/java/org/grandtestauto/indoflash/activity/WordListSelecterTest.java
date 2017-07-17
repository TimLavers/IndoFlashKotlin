package org.grandtestauto.indoflash.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.grandtestauto.indoflash.testui.WordListSelecterProxy;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4.class)
public class WordListSelecterTest {

    @Rule
    public ActivityTestRule<WordListSelecter> activityRule = new ActivityTestRule<>(WordListSelecter.class);

    @Test
    public void ensureChaptersShow() throws Exception {
        List<String> expectedChapters = new ArrayList<>(6);
        for (int i = 1; i <= 10; i++) expectedChapters.add("Lesson " + i);
        expectedChapters.add("Favourites");

        new WordListSelecterProxy().checkElements(expectedChapters);
    }
}