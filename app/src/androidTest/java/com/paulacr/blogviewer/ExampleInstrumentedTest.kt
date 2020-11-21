package com.paulacr.blogviewer

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.paulacr.blogviewer.authors.AuthorsListActivity
import com.paulacr.domain.Author
import org.hamcrest.core.IsNot.not
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    var mActivityTestRule : ActivityTestRule<AuthorsListActivity> = ActivityTestRule(AuthorsListActivity::class.java, true, false)

    @Before
    fun setUp() {
        val intent = Intent()
        mActivityTestRule.launchActivity(intent)
    }

    @Test
    fun shouldShowLoadingBeforeGettingData() {
        onView(withId(R.id.loadingView)).check(matches(isDisplayed()))
        onView(withId(R.id.authorsList)).check(matches(not(isDisplayed())))

        Thread.sleep(2000)
        onView(withId(R.id.loadingView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.authorsList)).check(matches(isDisplayed()))
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.paulacr.blogviewer", appContext.packageName)
    }


}
