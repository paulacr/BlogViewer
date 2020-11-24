package com.paulacr.blogviewer.authors

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.paulacr.blogviewer.R
import com.paulacr.blogviewer.feature.authors.AuthorsListActivity
import java.io.IOException
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.core.IsNot.not
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AuthorsListActivityTest {

    var activityScenario: ActivityScenario<AuthorsListActivity> = ActivityScenario.launch(AuthorsListActivity::class.java)
    val server = MockWebServer()
    val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setupMockWebServer() {
        server.start(8080)
    }

    @After
    fun teardown() {
        server.shutdown()
    }

    @Test @Ignore("To be fixed later")
    fun shouldShowLoadingBeforeGettingData() {

        // Given
        val response = context.readJsonAsset("authors_response.json")
        server.enqueue(
            MockResponse().setResponseCode(200).setBody(response)
        )

        activityScenario.moveToState(Lifecycle.State.CREATED)
        activityScenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.loadingView)).check(matches(isDisplayed()))
        onView(withId(R.id.authorsList)).check(matches(not(isDisplayed())))

        onView(withId(R.id.loadingView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.authorsList)).check(matches(isDisplayed()))

        activityScenario.moveToState(Lifecycle.State.DESTROYED)
        activityScenario.close()
    }
}

@Throws(IOException::class)
fun Context.readJsonAsset(fileName: String): String {
    val inputStream = assets.open(fileName)
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    return String(buffer, Charsets.UTF_8)
}
