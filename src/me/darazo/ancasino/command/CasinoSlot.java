package me.darazo.ancasino.command;

import me.darazo.ancasino.AnCasino;
import me.darazo.ancasino.slot.SlotMachine;
import me.darazo.ancasino.slot.Type;
import org.bukkit.entity.Player;

public class CasinoSlot
	extends AnCommand
{
    private SlotMachine slot;
    private Type type;

    public CasinoSlot(AnCasino plugin, String[] args, Player player)
    {
	super(plugin, args, player);
    }

    public Boolean process()
    {
	if (!this.plugin.permission.isAdmin(this.player).booleanValue()
		&& !this.plugin.permission.canCreate(this.player)
			.booleanValue())
	{
	    noPermission();
	    return Boolean.valueOf(true);
	}

	if (this.args.length < 3)
	{
	    usage();
	    return Boolean.valueOf(true);
	}

	if (!this.plugin.slotData.isSlot(this.args[1]).booleanValue())
	{
	    sendMessage("Invalid slot machine " + this.args[1]);
	    return Boolean.valueOf(true);
	}

	this.slot = this.plugin.slotData.getSlot(this.args[1]);

	if (!isOwner(this.slot).booleanValue())
	{
	    sendMessage("You do not own this slot machine.");
	    return Boolean.valueOf(true);
	}

	if (this.args[2].equalsIgnoreCase("type") && this.args.length == 4)
	{

	    if (this.plugin.typeData.isType(this.args[3]).booleanValue())
	    {
		this.type = this.plugin.typeData.getType(this.args[3]);

		if (this.plugin.permission.canCreate(this.player, this.type)
			.booleanValue())
		{
		    Double cost = this.type.getCreateCost();

		    if (this.plugin.economy.has(this.player,
			    cost.doubleValue()))
		    {
			this.plugin.economy.withdrawPlayer(this.player,
				cost.doubleValue());
			this.slot.setType(this.type.getName());
		    } else
		    {

			sendMessage(
				"You don't have enough money. Cost: " + cost);
		    }

		} else
		{

		    noPermission();
		}

	    } else
	    {

		sendMessage("Invalid type " + this.args[3]);

	    }

	} else if (this.args[2].equalsIgnoreCase("setmanaged")
		&& this.args.length == 3)
	{

	    if (this.plugin.permission.canManage(this.player).booleanValue())
	    {

		if (this.slot.isManaged().booleanValue())
		{
		    this.slot.setManaged(Boolean.valueOf(false));
		    sendMessage(String.valueOf(this.slot.getName())
			    + " is now unmanaged.");
		} else
		{

		    this.slot.setManaged(Boolean.valueOf(true));
		    sendMessage(String.valueOf(this.slot.getName())
			    + " is now managed.");
		}

	    } else
	    {

		noPermission();

	    }

	} else if (this.args[2].equalsIgnoreCase("setcontroller")
		&& this.args.length == 3)
	{

	    this.plugin.slotData.togglePlacingController(this.player,
		    this.slot);
	    sendMessage(
		    "Punch a new block to serve as this slot machine's controller.");

	} else if (this.args[2].equalsIgnoreCase("deposit")
		&& this.args.length == 4)
	{

	    Double amount = Double.valueOf(Double.parseDouble(this.args[3]));

	    if (!this.plugin.economy.has(this.player, amount.doubleValue()))
	    {
		sendMessage("You can't afford to deposit this much.");
	    } else
	    {

		if (this.slot.getFunds().doubleValue()
			+ amount.doubleValue() > this.plugin.typeData
				.getMaxPrize(this.slot.getType()).doubleValue())
		{
		    this.slot.setEnabled(Boolean.valueOf(true));
		    sendMessage("Sufficient funds. Slot machine enabled.");
		}

		this.slot.deposit(amount);
		this.plugin.economy.withdrawPlayer(this.player,
			amount.doubleValue());
		sendMessage(
			"Deposited " + amount + " to " + this.slot.getName());
	    }

	} else if (this.args[2].equalsIgnoreCase("withdraw")
		&& this.args.length == 4)
	{

	    Double amount = Double.valueOf(Double.parseDouble(this.args[3]));

	    if (this.slot.getFunds().doubleValue() < amount.doubleValue())
	    {
		sendMessage("Not enough funds in " + this.slot.getName()
			+ ". Withdrawing all available funds.");
		amount = this.slot.getFunds();
	    }

	    if (this.slot.getFunds().doubleValue()
		    - amount.doubleValue() < this.plugin.typeData
			    .getMaxPrize(this.slot.getType()).doubleValue())
	    {
		this.slot.setEnabled(Boolean.valueOf(false));
	    }

	    this.slot.withdraw(amount);
	    this.plugin.economy.depositPlayer(this.player,
		    amount.doubleValue());
	    sendMessage("Withdrew " + amount + " from " + this.slot.getName());

	} else
	{

	    usage();
	    return Boolean.valueOf(true);
	}

	this.plugin.slotData.addSlot(this.slot);
	this.plugin.slotData.saveSlot(this.slot);
	this.plugin.configData.saveSlots();

	return Boolean.valueOf(true);
    }

    private void usage()
    {
	String[] messages =
	{
		"Usage:",
		"/casino slot <slot> type <type>",
		"/casino slot <slot> setmanaged",
		"/casino slot <slot> setcontroller",
		"/casino slot <slot> deposit <amount>",
		"/casino slot <slot> withdraw <amount>" };
	byte b;
	int i;
	String[] arrayOfString1;
	for (i = (arrayOfString1 = messages).length, b = 0; b < i;)
	{
	    String m = arrayOfString1[b];
	    sendMessage(m);
	    b++;
	}

    }
}
