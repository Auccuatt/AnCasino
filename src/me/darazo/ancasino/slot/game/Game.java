package me.darazo.ancasino.slot.game;

import me.darazo.ancasino.AnCasino;
import me.darazo.ancasino.slot.SlotMachine;
import me.darazo.ancasino.slot.Type;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Game
{
    protected AnCasino plugin;
    protected BukkitScheduler scheduler;
    private SlotMachine slot;
    private Player player;

    public Game(SlotMachine slot, Player player, AnCasino plugin)
    {
	this.slot = slot;
	this.player = player;
	this.plugin = plugin;
    }

    public SlotMachine getSlot()
    {
	return this.slot;
    }

    public Type getType()
    {
	return this.plugin.typeData.getType(this.slot.getType());
    }

    public Player getPlayer()
    {
	return this.player;
    }

    public void play()
    {
	this.scheduler = this.plugin.getServer().getScheduler();
	Integer[] task = new Integer[3];
	Long[] delay =
	{ Long.valueOf(60L), Long.valueOf(80L), Long.valueOf(100L) };

	if (!this.slot.isEnabled().booleanValue())
	{
	    this.plugin.sendMessage(this.player,
		    "This slot machine is currently disabled. Deposit more funds to enable.");

	    return;
	}

	Double cost = getType().getCost();
	this.plugin.economy.withdrawPlayer(this.player, cost.doubleValue());
	if (this.slot.isManaged().booleanValue())
	{
	    this.slot.deposit(cost);
	}

	this.slot.toggleBusy();
	this.plugin.sendMessage(this.player,
		(String) getType().getMessages().get("start"));

	for (Integer i = Integer.valueOf(0); i.intValue() < 3; i = Integer
		.valueOf(i.intValue() + 1))
	{
	    task[i.intValue()] = Integer.valueOf(this.scheduler
		    .scheduleSyncRepeatingTask((Plugin) this.plugin,
			    new RotateTask(this, i), 0L, 6L));
	    this.scheduler.scheduleSyncDelayedTask((Plugin) this.plugin,
		    new StopRotateTask(this, task[i.intValue()]),
		    delay[2 - i.intValue()].longValue());
	}

	this.scheduler.scheduleSyncDelayedTask((Plugin) this.plugin,
		new ResultsTask(this), delay[2].longValue());
    }
}
