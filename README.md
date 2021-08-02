# AnCasino

AnCasino, Compatible with MC Ver. 1.17.1 after nearly 8 years out of development.

The original owner has abandoned this project so I've gone to the liberty of taking it over as it's under the GNU General Public License version 3.

Changelog:

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
