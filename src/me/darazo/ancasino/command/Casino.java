package me.darazo.ancasino.command;

import me.darazo.ancasino.AnCasino;
import org.bukkit.entity.Player;

public class Casino
	extends AnCommand
{
    public Casino(AnCasino plugin, String[] args, Player player)
    {
	super(plugin, args, player);
    }

    public Boolean process()
    {
	if (this.plugin.permission.isAdmin(this.player).booleanValue())
	{
	    sendMessageDirectly("Command guide:");
	    sendMessageDirectly("/casino add - Add a new slot machine");
	    sendMessageDirectly("/casino addmanaged - Add a new managed slot machine");
	    sendMessageDirectly("/casino slot - Manage slot machines");
	    sendMessageDirectly("/casino list - List slot machines and types");
	    sendMessageDirectly("/casino reload - Reload config files from disk");
	    sendMessageDirectly("/casino remove - Remove an existing slot machine");
	    sendMessageDirectly("/casino stats - Global usage statistics");
	    sendMessageDirectly("/casino type - Manage slot machine types");

	} else if (this.plugin.permission.canCreate(this.player).booleanValue())
	{
	    sendMessageDirectly("Command guide:");
	    sendMessageDirectly("/casino add - Add a new slot machine");
	    sendMessageDirectly("/casino addmanaged - Add a new managed slot machine");
	    sendMessageDirectly("/casino slot - Manage slot machines");
	    sendMessageDirectly("/casino list - List slot machines and types");
	    sendMessageDirectly("/casino remove - Remove an existing slot machine");
	} else
	{

	    noPermission();
	}

	return Boolean.valueOf(true);
    }
}
