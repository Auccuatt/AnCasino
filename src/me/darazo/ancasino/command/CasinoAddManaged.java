package me.darazo.ancasino.command;

import me.darazo.ancasino.AnCasino;
import me.darazo.ancasino.slot.SlotMachine;
import org.bukkit.entity.Player;

public class CasinoAddManaged
	extends AnCommand
{
    private String name;
    private String type;
    private String owner;
    private String world;

    public CasinoAddManaged(AnCasino plugin, String[] args, Player player)
    {
	super(plugin, args, player);
    }

    public Boolean process()
    {
	if (!this.plugin.permission.canCreate(this.player).booleanValue()
		|| !this.plugin.permission.canManage(this.player)
			.booleanValue())
	{
	    noPermission();
	    return Boolean.valueOf(true);
	}

	if (this.args.length >= 2 && this.args.length <= 3)
	{

	    if (!this.plugin.slotData.isSlot(this.args[1]).booleanValue())
	    {
		this.name = this.args[1];

		if (this.args.length < 3)
		{

		    this.type = "default";

		} else if (this.plugin.typeData.isType(this.args[2])
			.booleanValue())
		{
		    String typeName = this.args[2];

		    if (this.plugin.permission.canCreate(this.player, typeName)
			    .booleanValue())
		    {
			this.type = typeName;
			this.owner = this.player.getName();
		    } else
		    {

			this.plugin.sendMessage(this.player,
				"Invalid type " + typeName);
			return Boolean.valueOf(true);
		    }

		} else
		{

		    this.plugin.sendMessage(this.player,
			    "Invalid type " + this.args[2]);
		    return Boolean.valueOf(true);
		}

		Double createCost = this.plugin.typeData.getType(this.type)
			.getCreateCost();
		if (this.plugin.economy.has(this.player,
			createCost.doubleValue()))
		{
		    this.plugin.economy.withdrawPlayer(this.player,
			    createCost.doubleValue());
		} else
		{

		    sendMessage(
			    "You can't afford to create this slot machine. Cost: "
				    + createCost);
		    return Boolean.valueOf(true);
		}

		this.world = this.player.getWorld().getName();
		SlotMachine slot = new SlotMachine(this.name, this.type,
			this.owner, this.world, Boolean.valueOf(true));
		this.plugin.slotData.toggleCreatingSlots(this.player, slot);
		this.plugin.sendMessage(this.player,
			"Punch a block to serve as the base for this slot machine.");

	    } else
	    {
		this.plugin.sendMessage(this.player, "Slot machine "
			+ this.args[1] + " already registered.");
	    }

	} else
	{

	    this.plugin.sendMessage(this.player, "Usage:");
	    this.plugin.sendMessage(this.player, "/casino add <name> (<type>)");
	}
	return Boolean.valueOf(true);
    }
}
