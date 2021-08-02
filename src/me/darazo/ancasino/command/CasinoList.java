package me.darazo.ancasino.command;

import me.darazo.ancasino.AnCasino;
import me.darazo.ancasino.slot.SlotMachine;
import me.darazo.ancasino.slot.Type;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class CasinoList
	extends AnCommand
{
    public CasinoList(AnCasino plugin, String[] args, Player player)
    {
	super(plugin, args, player);
    }

    public Boolean process()
    {
	if (!this.plugin.permission.canCreate(this.player).booleanValue())
	{
	    noPermission();
	    return Boolean.valueOf(true);
	}

	if (this.args.length == 2)
	{

	    if (this.args[1].equalsIgnoreCase("slots"))
	    {
		sendMessageDirectly("Registered slot machines:");
		for (SlotMachine slot : this.plugin.slotData.getSlots())
		{

		    if (isOwner(slot).booleanValue())
		    {
			Block b = slot.getController();
			String c = String.valueOf(b.getX()) + "," + b.getY()
				+ "," + b.getZ();
			sendMessageDirectly(String.valueOf(slot.getName()) + " - type: "
				+ slot.getType() + " - owner: "
				+ slot.getOwner() + " - managed: "
				+ slot.isManaged().toString() + " @ " + c);
		    }

		}

	    } else if (this.args[1].equalsIgnoreCase("types"))
	    {
		sendMessageDirectly("Available types:");
		for (Type type : this.plugin.typeData.getTypes())
		{

		    if (this.plugin.permission.canCreate(this.player, type)
			    .booleanValue())
		    {
			sendMessage(String.valueOf(type.getName()) + " - cost: "
				+ type.getCost());
		    }
		}

	    } else
	    {
		sendMessageDirectly("Usage:");
		sendMessageDirectly("/casino list slots - List slot machines");
		sendMessageDirectly("/casino list types - List types");
	    }

	} else
	{

	    sendMessageDirectly("Usage:");
	    sendMessageDirectly("/casino list slots - List slot machines");
	    sendMessageDirectly("/casino list types - List types");
	}
	return Boolean.valueOf(true);
    }
}
