package com.sauloaguiar.githubcorecommitter

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by sauloaguiar on 1/13/17.
 */
@RunWith(AndroidJUnit4::class)
class AboutDialogEspressoTest {

    @Rule @JvmField
    var activityRule = ActivityTestRule<ReposActivity>(ReposActivity::class.java)

    @Test
    fun clickOnMenu() {
        // open the menu
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.action_about)).perform(click())

        // check if name is displayed on dialog
        onView(withId(R.id.name))
                .inRoot(isDialog())
                .check(matches(withText(R.string.developer_name)))
                .check(matches(isDisplayed()))
    }

}