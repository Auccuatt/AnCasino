# AnCasino
AnCasino, Compatible with MC Ver. 1.16.5 after nearly 8 years out of development.

The original owner has abandoned this project so I've gone to the liberty of taking it over as it's under the GNU General Public License version 3.

Changelog:

Everything from the oldest version of AnCasino is now compatible with 1.16.5 Spigot and PaperMC:

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

See config.yml on the repository for an updated file format.
NOTE: having an action section under rewards is REQUIRED or the plugin WON'T work.
