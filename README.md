
# Module gameservices

The gameservice module provides simple support for the Google Play Games service allowing you to create online leader boards and achievements

(Note: gameservice are only currently available on the android target)

V1.0.1 2018-05-23
V2.0.0 2020-06-14

# Class gameservices.GameService

V2.0.0 2020-06-14

The GameService class provides simple support for the Google Play Games Service system allowing you to access online leader boards and achievements.

To use GameService in your game:

+ In your Google Play dev console, enable Game Services and create a new game. Link this Game service game to an app in your dev console. This app must have a release, whether it be in testing or published. You cannot use Game Services on a non-released app (even for testing purposes)

+ If you dont have an app thats publiccally avaiable, please make sure the list of testers includes yourself.

+ Add leaderboards and acheivemnts to your game service game. 

+ Add your game service APP_ID as #GOOGLEPLAY_APP_ID in your code before importing gameservices

+ Import the gameservices module into your game and use the GameService class to connect to Google Play Services.

## Compatibility
Tested with CerburusX version V2020-04-19


Example:
```
#GOOGLEPLAY_APP_ID="123123456789"
Import gameservices

'-----------------------------------------------------------------
Class myClass Extends App
	Field gms:GameService

	Method OnCreate:Int()
		gms = New GameService()
		Return 0
	End
End
```

## Methods

### Method SignIn:Void()

Signs the current user and app into Google Play Services

### Method SignOut:Void()

Signs the current user and app out of Google Play Services

### Method IsSignedIn:Bool()

Return True if the ser has signed into Google Play Services, or False if not signed in

### Method ShowLeaderboard:Void(leaderboard_id:String)

Displays a leaderboard. leaderboard_id is the unique ID of the leader board.
Supply a popup_message if you would like to inform the user

### Method SubmitLeaderboardScore:Void(leaderboard_id:String,score:Int, popup_message:String="")

Submits a score onto the leader board. 
Supply a popup_message if you would like to inform the user

### Method ShowAchievements:Void()

Displays a list if all the acheivements for the app.

### Method UnlockAchievement:Void(achievement_id:String, popup_message:String="")

Unlocks the achievement for this user.
Supply a popup_message if you would like to inform the user.
