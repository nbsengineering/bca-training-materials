package com.nbs.composemigration.model

data class Club(
    val id: Int,
    val name: String,
    val logoUrl: String,
    val primaryColorHex: String,
)

data class TopScorer(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val club: Club,
    val goal: Int,
)

data class DetailedScorerBio(
    val playerId: Int,
    val position: String,
    val nationality: String,
    val preferredFoot: String,
    val dateOfBirth: String,
    val appearances: Int,
    val assists: Int,
    val shirtNumber: Int
)

val dummyClubs = listOf(
    Club(id = 43, name = "Manchester City", logoUrl = "https://resources.premierleague.com/premierleague25/badges-alt/43.svg", primaryColorHex = "#FF6CABDE"),
    Club(id = 94, name = "Brentford", logoUrl = "https://resources.premierleague.com/premierleague25/badges-alt/94.svg", primaryColorHex = "#FFD20027"),
    Club(id = 36, name = "Brighton & Hove Albion", logoUrl = "https://resources.premierleague.com/premierleague25/badges-alt/36.svg", primaryColorHex = "#FF0054A6"),
    Club(id = 91, name = "Bournemouth", logoUrl = "https://resources.premierleague.com/premierleague25/badges-alt/91.svg", primaryColorHex = "#FFCC0000"),
    Club(id = 31, name = "Crystal Palace", logoUrl = "https://resources.premierleague.com/premierleague25/badges-alt/31.svg", primaryColorHex = "#FF1B458F"),
    Club(id = 1, name = "Manchester United", logoUrl = "https://resources.premierleague.com/premierleague25/badges-alt/1.svg", primaryColorHex = "#FFDA291C"),
    Club(id = 8, name = "Chelsea", logoUrl = "https://resources.premierleague.com/premierleague25/badges-alt/8.svg", primaryColorHex = "#FF034694"),
    Club(id = 4, name = "Newcastle United", logoUrl = "https://resources.premierleague.com/premierleague25/badges-alt/4.svg", primaryColorHex = "#FF242424"),
    Club(id = 90, name = "Burnley", logoUrl = "https://resources.premierleague.com/premierleague25/badges-alt/90.svg", primaryColorHex = "#FF6C1D45"),
)

val clubIdMap = dummyClubs.associateBy { it.id }

val topScorers = listOf(
    TopScorer(
        id = 223094,
        name = "Erling Haaland",
        imageUrl = "https://resources.premierleague.com/premierleague25/photos/players/110x140/223094.png",
        club = clubIdMap[43]!!,
        goal = 14,
    ),
    TopScorer(
        id = 502500,
        name = "Igor Thiago",
        imageUrl = "https://resources.premierleague.com/premierleague25/photos/players/110x140/502500.png",
        club = clubIdMap[94]!!,
        goal = 8,
    ),
    TopScorer(
        id = 50175,
        name = "Danny Welbeck",
        imageUrl = "https://resources.premierleague.com/premierleague25/photos/players/110x140/50175.png",
        club = clubIdMap[36]!!,
        goal = 6,
    ),
    TopScorer(
        id = 437730,
        name = "Antoine Semenyo",
        imageUrl = "https://resources.premierleague.com/premierleague25/photos/players/110x140/437730.png",
        club = clubIdMap[91]!!,
        goal = 6,
    ),
    TopScorer(
        id = 231747,
        name = "Jean-Philippe Mateta",
        imageUrl = "https://resources.premierleague.com/premierleague25/photos/players/110x140/231747.png",
        club = clubIdMap[31]!!,
        goal = 6,
    ),
    TopScorer(
        id = 446008,
        name = "Bryan Mbeumo",
        imageUrl = "https://resources.premierleague.com/premierleague25/photos/players/110x140/446008.png",
        club = clubIdMap[1]!!,
        goal = 5,
    ),
    TopScorer(
        id = 560262,
        name = "Junior Kroupi",
        imageUrl = "https://resources.premierleague.com/premierleague25/photos/players/110x140/560262.png",
        club = clubIdMap[91]!!,
        goal = 4,
    ),
    TopScorer(
        id = 475168,
        name = "João Pedro",
        imageUrl = "https://resources.premierleague.com/premierleague25/photos/players/110x140/475168.png",
        club = clubIdMap[8]!!,
        goal = 4,
    ),
    TopScorer(
        id = 470313,
        name = "Nick Woltemade",
        imageUrl = "https://resources.premierleague.com/premierleague25/photos/players/110x140/470313.png",
        club = clubIdMap[4]!!,
        goal = 4,
    ),
    TopScorer(
        id = 444180,
        name = "Jaidon Anthony",
        imageUrl = "https://resources.premierleague.com/premierleague25/photos/players/110x140/444180.png",
        club = clubIdMap[90]!!,
        goal = 4,
    ),
)

val detailedBios = listOf(
    DetailedScorerBio(playerId = 223094, position = "Forward", nationality = "Norway", preferredFoot = "Left", dateOfBirth = "21/07/2000", appearances = 18, assists = 5, shirtNumber = 9),
    DetailedScorerBio(playerId = 502500, position = "Forward", nationality = "Brazil", preferredFoot = "Right", dateOfBirth = "26/06/2001", appearances = 19, assists = 0, shirtNumber = 9),
    DetailedScorerBio(playerId = 50175, position = "Forward", nationality = "England", preferredFoot = "Right", dateOfBirth = "26/11/1990", appearances = 15, assists = 2, shirtNumber = 18),
    DetailedScorerBio(playerId = 437730, position = "Forward", nationality = "Ghana", preferredFoot = "Right", dateOfBirth = "07/01/2000", appearances = 15, assists = 1, shirtNumber = 24),
    DetailedScorerBio(playerId = 231747, position = "Forward", nationality = "France", preferredFoot = "Right", dateOfBirth = "28/06/1997", appearances = 17, assists = 1, shirtNumber = 14),
    DetailedScorerBio(playerId = 446008, position = "Forward", nationality = "Cameroon", preferredFoot = "Left", dateOfBirth = "07/08/1999", appearances = 19, assists = 3, shirtNumber = 19),
    DetailedScorerBio(playerId = 560262, position = "Midfielder", nationality = "France", preferredFoot = "Right", dateOfBirth = "23/10/2006", appearances = 10, assists = 2, shirtNumber = 28),
    DetailedScorerBio(playerId = 475168, position = "Forward", nationality = "Brazil", preferredFoot = "Right", dateOfBirth = "15/07/1998", appearances = 16, assists = 4, shirtNumber = 11),
    DetailedScorerBio(playerId = 470313, position = "Forward", nationality = "Germany", preferredFoot = "Left", dateOfBirth = "14/02/2002", appearances = 12, assists = 0, shirtNumber = 36),
    DetailedScorerBio(playerId = 444180, position = "Midfielder", nationality = "England", preferredFoot = "Right", dateOfBirth = "01/12/1999", appearances = 14, assists = 3, shirtNumber = 15)
)