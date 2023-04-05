package com.example.game

import com.example.game.GameData.gameObject.getAll


class GameData {

companion object gameObject{

         fun getAll(): List<Game>{
             return listOf(
                 Game(
                     "Candy Crush Saga",
                     "Android, IOS",
                     "Nov 15, 2012",
                     4.5,
                     "",
                     "PEGI 3",
                     "King",
                     "King",
                     "Puzzle",
                     "Master the legendary match 3 puzzle game from King! With over a trillion matching levels played, Candy Crush Saga is the popular match 3 puzzle game.\n" +
                             "\n" +
                             "Match, pop, and blast candies in this tasty puzzle adventure to progress to the next level and get a sugar blast! Master match 3 puzzles with quick thinking and smart matching moves to be rewarded with sugar bonuses and tasty candy combos.",
                     candyCrush()
                 ),

                 Game(
                     "Papers , Please",
                     "PC, Android, IOS",
                     "Jul 27, 2022",
                     4.8,
                     "",
                     "PEGI 18",
                     "3909",
                     "3909",
                     "Action",
                     "The communist state of Arstotzka has just ended a 6-year war with neighboring Kolechia and reclaimed its rightful half of the border town, Grestin.\n" +
                             "\n" +
                             "Your job as immigration inspector is to control the flow of people entering the Arstotzkan side of Grestin from Kolechia. Among the throngs of immigrants and visitors looking for work are hidden smugglers, spies, and terrorists.\n" +
                             "\n" +
                             "Using only the documents provided by travelers and the Ministry of Admission's primitive inspect, search, and fingerprint systems you must decide who can enter Arstotzka and who will be turned away or arrested.",
                     papersPlease()
                 ),

                 Game(
                     "Subway Surfers",
                     "Android, IOS",
                     "Sep 20, 2012",
                     4.5,
                     "",
                     "PEGI 7",
                     "SYBO Games",
                     "SYBO Games",
                     "Action",
                     "DASH as fast as you can!\n" +
                             "DODGE the oncoming trains!\n" +
                             "\n" +
                             "Help Jake, Tricky & Fresh escape from the grumpy Inspector and his dog.",
                     emptyList()
                 ),

                 Game(
                     "Block Blast Adventure Master",
                     "Android, IOS",
                     "Sep 23, 2022",
                     4.3,
                     "",
                     "PEGI 3",
                     "Hungry Studios",
                     "Hungry Studios",
                     "Puzzle",
                     "Meet Block Blast Adventure Master, the great block puzzle game that will relax your mind while you from block puzzle after block puzzle and go on massage your brain, fun and addicting!",
                     emptyList()
                 ),

                 Game(
                     "Magic Tiles 3",
                     "Android, IOS",
                     "Feb 24, 2017",
                     4.0,
                     "",
                     "PEGI 3",
                     "AMANOTES PTE LTD",
                     "AMANOTES PTE LTD",
                     "Music",
                     "How fun is it to capture the most precious moments of the year with a wink of rhythm? Let’s MUSIC your soul with all your favorite country tunes!",
                     emptyList()
                 ),


                 Game(
                     "Going Balls",
                     "Android, IOS",
                     "Feb 2, 2021",
                     4.0,
                     "",
                     "PEGI 3",
                     "Supersonic Studios LTD",
                     "Supersonic Studios LTD",
                     "Action",
                     "Get ready to navigate through a wild and wacky world in Going Balls - the addictive rolling ball platformer! With over 200 million downloads, this game is a must-try for anyone who loves a good challenge.",
                     emptyList()
                 ),


                 Game(
                     "Among Us",
                     "PC, Android, IOS",
                     "Jun 15, 2018",
                     3.6,
                     "",
                     "PEGI 7",
                     "Innersloth LLC",
                     "Innersloth LLC",
                     "Action",
                     "Play online or over local WiFi with 4-15 players as you attempt to prep your spaceship for departure, but beware as one will be an impostor bent on killing everyone!",
                     emptyList()
                 ),


                 Game(
                     "Tomb of the Mask",
                     "Android, IOS",
                     "Jun 19, 2018",
                     4.5,
                     "",
                     "PEGI 3",
                     "Playgendary Limited",
                     "Playgendary Limited",
                     "Action",
                     "Tomb of the Mask is an arcade game with an infinite procedurally generated vertical labyrinth. Seeking for adventure you get into a tomb where you find a strange mask. You put it on and suddenly realize that you can now climb walls - easily and promptly. And that's when all the fun begins",
                     emptyList()
                 ),


                 Game(
                     "Geometry Dash",
                     "Android, IOS",
                     "Aug 12, 2013",
                     4.7,
                     "",
                     "PEGI 3",
                     "RobTop Games",
                     "RobTop Games",
                     "Action",
                     "Jump and fly your way through danger in this rhythm-based action platformer!\n" +
                             "\n" +
                             "Prepare for a near impossible challenge in the world of Geometry Dash. Push your skills to the limit as you jump, fly and flip your way through dangerous passages and spiky obstacles.\n" +
                             "\n" +
                             "Simple one touch game play with lots of levels that will keep you entertained for hours!",
                     emptyList()
                 ),

                 Game(
                     "Grand Theft Auto: San Andreas",
                     "PC, PlayStation, Xbox,  Android, IOS",
                     "Dec 19, 2013",
                     4.0,
                     "",
                     "PEGI 18",
                     "Rockstar Games",
                     "Rockstar Games",
                     "Action",
                     "Five years ago, Carl Johnson escaped from the pressures of life in Los Santos, San Andreas, a city tearing itself apart with gang trouble, drugs and corruption. Where filmstars and millionaires do their best to avoid the dealers and gangbangers.\n" +
                             "\n" +
                             "Now, it’s the early 90’s. Carl’s got to go home. His mother has been murdered, his family has fallen apart and his childhood friends are all heading towards disaster.\n" +
                             "\n" +
                             "On his return to the neighborhood, a couple of corrupt cops frame him for homicide. CJ is forced on a journey that takes him across the entire state of San Andreas, to save his family and to take control of the streets.\n" +
                             "\n" +
                             "Rockstar Games brings its biggest release to mobile yet with a vast open-world covering the state of San Andreas and its three major cities – Los Santos, San Fierro and Las Venturas – with enhanced visual fidelity and over 70 hours of gameplay.",
                     emptyList()
                 )
             )
     }
    fun candyCrush(): List<UserImpression> {
        return listOf(
            UserRating("June Flynt",12005599,5.0),
            UserRating("Ahmad Woods",12005567,5.0),
            UserReview("June Flynt",12005533,"Keeps my attention and helps me focus and have fun. It's challenging, but not like some games where I'm stuck on one level for days because not enough lives and very aggravating when you get to a few hundred levels and then you get stuck and can't continue til you pass a level. But what if someone can't for some reason pass a level . Then I just give up the game that once was fun. But when it's to difficult after 30 plus tries, would be great to have a free pass somehow.. B 4 in other games."),
            UserRating("Janiston Kniola",12005578,4.0),
            UserReview("Dmitriy Kutepov",12005588,"I have been playing for a while and I wish some of the levels were made easier, especially the nightmare difficulty. It seems they are made impossible unless you spend money on boosters or extra moves. I don't feel that is right especially when its one after another back to back, and I did pay to pass the previous level.... Getting to the point when I will just delete the game and forget about it..."),
            UserRating("Leigh Sandt",12005565,5.0),
            UserReview("Crystol Woods",12005550,"It's a fun,relaxing game that speaks to my sense of order and need to organize. The drawbacks are that after a certain level the game becomes competitive, even if it's just against yourself! Most of the levels require boosters to beat which you can win free but the temptation is there to buy. If you don't want to spend real money on a game then resign yourself to win the levels without boosters. Over all addictive and entertaining. If you find level too hard w/o \$\$...just start over!"),
            UserReview("Adam Sandt",12005511,"Love the smoothness of the game. Nice grafics and interesting events. The fishes at times leaves me frustrated when it seems that they just doesn't go where I'd hoped. And can't use candies to create fishes. The new Superstars event has made me very, very excited. Thank you for this awesome event. This a one of kind event, speaking for myself, I haven't heard or came across this kind of event in all the games I've played and I've played a lot. Superbly done"),
            UserRating("Dom Kutepov",12005520,1.0),
            UserReview("Jenni Kniola",12005533,"I love this game... I have for years, but I hate when the option to watch a video for extra moves disappears... I don't know why it does that. It will appear for a long time, then just disappear for weeks... this makes it ridiculously hard to beat the levels without having to buy gold in order to continue playing. I'm debating on taking a break from it for a while.")
            )
    }
    fun papersPlease(): List<UserImpression> {
        return listOf(
            UserRating("Dominic Wells", 12005566, 3.0),
            UserRating("Brooke Turtle", 12005590, 3.0),
            UserReview("Dom Wells",12005587 ,"I absolutely love the game, the artstyle and gameplay are just magnificent. My only problem is that at least once per in-game day the game will just randomly have a frame rate crash and can only be fixed when exiting out of the app and opening it back up. I'm glad the game saves after every person because if it didn't this game would be practically unplayable. However this is a simple bug fix and once that's out of the way you're working with an easy 5 star game here"),
            UserReview("Hazel Baumgartner", 12005577, "This game is frustrating, but in a good way. Having to focus on the repetitive daily task of (you guessed it) checking papers while also watching the story unfold and somehow controlling the fate of a nation (or at the very least your family) from behind a desk is a really cool concept. It's harder than you think though. Took me about eight tries before I reached an ending that didn't have me dead or in prison. Will be trying again to get other good endings since there are like 150."),
            UserRating("Dan Baumgartner", 12005564, 5.0),
            UserReview("Brov Turtle", 12005544, "I love this on pc and wanted to try it on mobile too. The layout is different which is expected, but I can't get past the fact you are stuck looking at the notice, audio script file, or the rule book when not looking at passports/tickets/etc. I want to close them out and have a blank space to look at unless I click something directly to look at. Please allow this and I'll give 5 stars."),
            UserReview("Ri Wo", 12005540, "So happy that this is on the Google Play store! I've seen some of my fav YouTubers play this & I always wanted to try it myself, but don't have a gaming computer. Nor do I have Steam. Now that I've been able to play it, it's fun! A little intense at times, but as I've watched the gameplay b4, this was expected. I really like the characters. Especially the storylines of certain ones. The only change I'd make is probs just to emphasize the 3 bullets in the tranquilizer gun."),
            UserRating("Ri Tu", 12005521, 5.0),
            UserReview("Eowynn Zu", 12005507, "As someone who loved the game since kubzscouts or jay played it many years ago, I was joyed that I found it on mobile as it's more accessible than playing on my computer, this port is very good, it changes the booth upgrades to fit playing without a keyboard and using your hands/fingers. It also keeps most events from the original computer game, which is nice as some ports change events. I honestly recommend the game if you enjoy detailed semi-repetitive game play that does not get boring!")
            ,UserRating("Eow Hu", 12005517, 5.0),
        )
    }

    }

   fun getDetails(title: String): Game {
        val gameList: ArrayList<Game> = arrayListOf()
        gameList.addAll(getAll())
        val game = gameList.find { game-> title == game.title }
        return game?: Game("","","",0.0,"","","","" +
                "","","", emptyList()
        )

    }

}