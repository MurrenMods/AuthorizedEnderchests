# Authorized Enderchests
A minecraft plugin to allow your friends to open your enderchest!

## Features
The plugin features the function to control who can access your enderchest. No longer do you have to travel thousands of blocks just to borrow someone's pickaxe for a bit! Permission lists are persistent, so you don't have to re-permit your friends every server restart. Don't forget to revoke permissions though!
Configurable option to require players to have an enderchest in their inventory to open enderchests through commands. This keeps the plugin from becoming a 'cheat' early game.

## Commands
### /enderchest
Also working as /ec, this command will open your own enderchest.
### /aec <open|permit|revoke> <player>
#### /aec open <player>
Opens the enderchest of the specified player if permission is given.
#### /aec permit <player>
Permits the specified player to access your enderchest.
#### /aec revoke <player>
Revokes the specified player to access your enderchest, if they had access.

## Config
enable-ec: Toggles if /enderchest should be enabled.
enable-aec: Toggles if /aec should be enabled.
needs-enderchest: Toggles if the player needs an enderchest in their inventory to be able to open their own or another player's enderchest.

## Permissions
authorizedenderchests.ec: Allow player to use /ec (default on)
authorizedenderchests.aec: Allow player to use /aec (default on)
