# AnCasino

AnCasino, Compatible with MC Ver. 1.17.1 after nearly 8 years out of development.

The original owner has abandoned this project so I've gone to the liberty of taking it over as it's under the GNU General Public License version 3.

Changelog:

---------------------------------------------------

Version 1.4.2:

What I discovered, when playing around with Multiverse is what was actually happening when you use slots on separate worlds, at any given time, the server only loads one world at server restart time, so what is required of AnCasino is that the the other worlds need to be preloaded to not to be considered "null" when the slots themselves get loaded into the server.

The last update (v1.4.1.2) is now redundant, as this update (v1.4.2) should now be working and you shouldn't be getting those errors anymore. And don't worry, this line of code will not delete your worlds, what it does is it checks if the world exists and if it doesn't it creates a new world to put those slots in, and it loads it regardless.

I actually tested this before releasing this as I don't want people's servers getting deleted unintentionally. If you want those weird situations where you want a fresh world with the slots in the same location (which I don't know why you would want that lol), when you find the location of the slot machine, MAKE sure the controller (the button you press to "roll it") is a note block, if it's not use world guard to change it into one, and then the slot will behave as it was created with commands.

Note: This loads EVERY world that each slow machine is in at the start of the server, use /casino reload from now on, DON'T reload the server.

---------------------------------------------------

Version 1.4.1.2:

If you have slots on another world that is not in your main server folder, but you still kept some on your current world, the plugin will no longer freak out, so instead of it not loading one slot, and disabling all slots, it will instead just skip loading that slot.

I would also check your spigot/paper/purpur server folder to make sure the world the slots are in match with the name listed in the slots.yml file.

---------------------------------------------------

Everything from the oldest version of AnCasino is now compatible with 1.17.1 Spigot and PaperMC:

Removed legacy item identifiers and now using modern Minecraft item IDs (e.c. EMERALD_BLOCK, instead of 133)
Added the "none" for a default action for a winning row.
All actions that were available before are now in working order, these include:

smite
kill
addxp amount
give material amount
tpto x,y,z
broadcast msg1 msg2 msg3 .....

Configuration:

See config.yml generated from the plugin.
