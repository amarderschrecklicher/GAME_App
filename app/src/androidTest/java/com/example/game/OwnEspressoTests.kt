package com.example.game

import android.content.pm.ActivityInfo
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.PositionAssertions
import androidx.test.espresso.assertion.PositionAssertions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.w3c.dom.Text

@RunWith(AndroidJUnit4::class)
class OwnEspressoTests {

    fun hasItemCount(n: Int) = object : ViewAssertion {
        override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            Assert.assertTrue("View nije tipa RecyclerView", view is RecyclerView)
            var rv: RecyclerView = view as RecyclerView
            ViewMatchers.assertThat(
                "GetItemCount RecyclerView broj elementa: ",
                rv.adapter?.itemCount,
                CoreMatchers.`is`(n)
            )
        }
    }


   // Funkcija za pritiskanje željene igrice game liste u HomeFragment


    fun clickGameItem(game :Game){
        Espresso.onView(withId(R.id.game_list)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                CoreMatchers.allOf(
                    hasDescendant(withText(game.title)),
                    hasDescendant(withText(game.releaseDate)),
                    hasDescendant(withText(game.rating.toString()))
                ), ViewActions.click()
            )
        )
    }

    @get:Rule
    var homeRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    var igre = GameData.getAll()


     //   -U ovome testu na početku provjeravamo da li se prikazuje layout_main pomoću funkcije isDisplayed(), te zatim da li dugmad od bottom
     //    bara ne rade što je zahtjev po spirali funckijom isNotEnabled().
     //   -Zatim provjeramo kada uđemo u gameDetails fragment da li je novi layout onakav kakav je zamišljen, tj., gdje se koji podatak o igri
     //    nalazi na ekranu.
     //   -Te na kraju provjeravamo da li se bottom bar nalazi na samome dnu i provjeravamo da li su prelaskom u landscape, naši fragmenti
     //    podjeljeni u 40:60 omjeru pomoću funckije check(matches(isDisplayingAtLeast(40))).



    @Test
    fun test1(){
        homeRule.scenario.onActivity { activity -> activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT }

        Espresso.onView(withId(R.id.layout_main))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.homeItem)).check(matches(isNotEnabled()))
        Espresso.onView(withId(R.id.gameDetailsItem)).check(matches(isNotEnabled()))

        clickGameItem(igre[0])

        Espresso.onView(withId(R.id.bottom_nav)).check(matches(isEnabled()))

        Espresso.onView(withId(R.id.homeItem)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.gameDetailsItem)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.publisherGame_textview))
            .check(matches(isDisplayed()))

        Espresso.onView(withText(igre[0].description))
            .check(matches(isDisplayingAtLeast(10)))


        Espresso.onView(withId(R.id.esrb_rating_textview))
            .check(isCompletelyLeftOf(withId(R.id.platform_textview)))
        Espresso.onView(withId(R.id.developer_textview))
            .check(isCompletelyLeftOf(withId(R.id.platform_textview)))
        Espresso.onView(withId(R.id.publisher_textview))
            .check(isCompletelyLeftOf(withId(R.id.platform_textview)))
        Espresso.onView(withId(R.id.genre_textview))
            .check(isCompletelyLeftOf(withId(R.id.platform_textview)))
        Espresso.onView(withId(R.id.platform_textview))
            .check(isCompletelyBelow(withId(R.id.cover_imageview)))
        Espresso.onView(withId(R.id.esrb_rating_textview))
            .check(isCompletelyBelow(withId(R.id.cover_imageview)))
        Espresso.onView(withId(R.id.recyclerView))
            .check(isCompletelyBelow(withId(R.id.description_textview)))
        Espresso.onView(withId(R.id.description_textview))
            .check(isCompletelyBelow(withId(R.id.genre_textview)))

        Espresso.onView(withId(R.id.bottom_nav)).check(isBottomAlignedWith(withChild(withId(R.id.bottom_nav))))

        homeRule.scenario.onActivity { activity -> activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE }

        Espresso.onView(withId(R.id.land_host_fragment))
            .check(matches(isDisplayingAtLeast(40)))

    }


       //     -U ovome testu provjeravamo pokretanje aplikacije iz landscape mode, te i sami layout game details u landscape što podrazumjeva
       //      provjeru pozicije svih opisa igrice na ekranu.
       //     -Zatim pritiskom na svaku igricu liste gameList u HomeFragment s lijeve strane, provjeravamo da li se zaista ona pojavljuje u
       //      GameDetailsFragment fragmentu sa desne strane, pomoću funkcije clickGameItem(game :Game).
       //     -Zatim peralazimo u portrait mode provjeravamo da li se prikazuje HomeFragment (sopsteveni zahtjev), funckijom isDisplayed().
       //      Također, klikom na desno dugme bottombar-a i ulaskom u GameDetailsFragment, provjeravamo da li se prikazuju detalji one
       //      zadnje igre pritisnute u landscape mode. Za tu provjeru gledali smo da li se detalji igre poklapaju sa onom zadnjom pritisnutom,
       //      pomoću funckije matches.(Regex.fromLiteral(igre[0].)).


    @Test
    fun test2(){

        homeRule.scenario.onActivity { activity -> activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE }

        Espresso.onView(withId(R.id.item_title_textview)).toString().matches(Regex.fromLiteral(igre[0].title))


        Espresso.onView(withId(R.id.platform_textview))
            .check(isCompletelyLeftOf(withId(R.id.esrb_rating_textview)))
        Espresso.onView(withId(R.id.release_date2))
            .check(isCompletelyBelow(withId(R.id.cover_imageview)))
        Espresso.onView(withId(R.id.developer_textview))
            .check(isCompletelyBelow(withId(R.id.platform_textview)))
        Espresso.onView(withId(R.id.publisher_textview))
            .check(isCompletelyRightOf(withId(R.id.developer_textview)))
        Espresso.onView(withId(R.id.genre_textview))
            .check(isCompletelyBelow(withId(R.id.release_date2)))
        Espresso.onView(withId(R.id.genre_textview))
            .check(matches(isCompletelyDisplayed()))
        Espresso.onView(withId(R.id.recyclerView))
            .check(isCompletelyBelow(withId(R.id.description_textview)))


        for(game : Game in igre) {
            clickGameItem(game)
            Espresso.onView(withId(R.id.item_title_textview)).toString().matches(Regex.fromLiteral(game.title))
            Espresso.onView(withId(R.id.description_textview)).toString().matches(Regex.fromLiteral(game.description))
        }


        homeRule.scenario.onActivity { activity -> activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT }

        Espresso.onView(withId(R.id.homeItem)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.gameDetailsItem)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.item_title_textview)).toString().matches(Regex.fromLiteral(igre.last().title))
        Espresso.onView(withId(R.id.description_textview)).toString().matches(Regex.fromLiteral(igre.last().description))

    }


    //    -Počinjemo iz portrait mode te klikom na drugu igru liste ulazimo u detalje igre, te provjeravamo zatim kada odatle prelazimo u landscape
    //     da li će ta igra biti prikazana.
    //    -Odatle biramo petu igru i vraćamo se u portrait mode, pa vršimo provjeru da li se aplikacija vraća u HomeFragment (sopstveni zahtjev).
    //    -Također usput provjeravamo da li je searchbar-a lijevo od search button, u portrait pa kasnije u landscape mode.
    //    -Pritiskom na drugo dugme bottombar-a, provjeravamo da li će biti prikazana peta igra.



    @Test
    fun test3(){

        homeRule.scenario.onActivity { activity -> activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT}

        clickGameItem(igre[1])

        homeRule.scenario.onActivity { activity -> activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE}
        Espresso.onView(withId(R.id.item_title_textview)).toString().matches(Regex.fromLiteral(igre[1].title))
        Espresso.onView(withId(R.id.description_textview)).toString().matches(Regex.fromLiteral(igre[1].description))

        Espresso.onView(withId(R.id.search_query_edittext))
            .check(isCompletelyLeftOf(withId(R.id.search_button)))

        clickGameItem(igre[5])
        homeRule.scenario.onActivity { activity -> activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT}

        Espresso.onView(withId(R.id.item_title_textview)).toString().matches(Regex.fromLiteral(igre[5].title))
        Espresso.onView(withId(R.id.description_textview)).toString().matches(Regex.fromLiteral(igre[5].description))
        Espresso.onView(withId(R.id.homeItem)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.search_query_edittext))
            .check(isCompletelyLeftOf(withId(R.id.search_button)))

    }



}