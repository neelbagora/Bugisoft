# BugisoftJava
This bot is primarily for the DMC clan private discord, although it is created in a way where it can be used for any discord.
## How to use
1. Download the [Latest Release](https://github.com/LookLotsOfPeople/BugisoftJava/releases/latest).
2. Copy the files from the [Configuration Files](https://github.com/LookLotsOfPeople/BugisoftJava/tree/master/BotConfigurationFiles) to the directory with the lates release.
3. Populate the token file with the correct token.
4. ~~Modify the settings file as you see fit.~~
5. Run the bot.
6. Enjoy!

## Features
### Text Commands
* Ping
* Invite Generator

### User Settings/Properties/Data
The bot creates a property file for each and every user which uses a user-realted command. These property files are persistant through bot cycles and allow for permanent data to be saved. The user files are constantly saved every time they are written to and are not saved only on bot shutdowns. Each user property file are cached to speed up reads if they are being used. 

### Tournaments
The bot has a command set for holding local server tournaments. Right now, the tournaments/matches/players are tuned towards [Rainbow Six Siege](https://rainbow6.ubisoft.com/siege/en-US/home/index.aspx)~~, and it uses the [R6DB](https://r6db.com/) api to calculate matchmaking~~.

~~### Logging
The bot logs most events that the ~~

### Supports Music
* Can queue any song or playlist from sites listed [here](https://github.com/sedmelluq/lavaplayer#supported-formats).
  * Right now only can take in url parameters in chat, although this is going to be changed soon to integrate a searcher.
* Can be in multiple discords at the same time.
  * Working on having it be able to be in multiple channels at once, although this may be a discord limiation.
