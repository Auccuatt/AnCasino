package me.darazo.ancasino.command;

import me.darazo.ancasino.AnCasino;
import me.darazo.ancasino.util.Stat;
import org.bukkit.entity.Player;

public class CasinoStats
	extends AnCommand
{
    public CasinoStats(AnCasino plugin, String[] args, Player player)
    {
	super(plugin, args, player);
    }

    public Boolean process()
    {
	if (!this.plugin.permission.isAdmin(this.player).booleanValue())
	{
	    noPermission();
	    return Boolean.valueOf(true);
	}

	sendMessageDirectly("Statistics for registered types:");
	for (Stat stat : this.plugin.statsData.getStats())
	{

	    String type = stat.getType();
	    Integer spins = stat.getSpins();
	    Double won = stat.getWon();
	    Double lost = stat.getLost();

	    sendMessageDirectly(String.valueOf(type) + " - spins: " + spins
		    + " - money won: " + won + " - money lost: " + lost);
	}

	return Boolean.valueOf(true);
    }
}
