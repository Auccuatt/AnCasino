package me.darazo.ancasino.command;

import me.darazo.ancasino.AnCasino;
import org.bukkit.entity.Player;

public class CasinoReload
	extends AnCommand
{
    public CasinoReload(AnCasino plugin, String[] args, Player player)
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

	this.plugin.configData.load();

	sendMessageDirectly("Configuration reloaded");
	return Boolean.valueOf(true);
    }
}
