package me.darazo.ancasino.slot;

import java.util.List;
import me.darazo.ancasino.AnCasino;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RewardData
{
    private AnCasino plugin;

    public RewardData(AnCasino plugin)
    {
	this.plugin = plugin;
    }

    public void send(Player player, Reward reward)
    {
	if (reward.message != null)
	{
	    this.plugin.sendMessage(player, reward.message);
	}

	if (reward.money != null)
	{
	    this.plugin.economy.depositPlayer(player,
		    reward.money.doubleValue());
	}

	if (reward.action != null)
	{
	    executeAction(reward.action, player);
	}
    }

    private void executeAction(List<String> actionList, Player p)
    {
	for (int i = 0; i < actionList.size(); i++)
	{

	    if (actionList.get(0).equalsIgnoreCase("none")
		    || actionList.get(0) == null)
	    {
		return;
	    }

	    if (actionList.get(0).equalsIgnoreCase("give"))
	    {

		String[] itemData = actionList.get(1).split(":");
		Material item = Material.getMaterial(itemData[0]);
		int n = Integer.parseInt(actionList.get(2));
		p.getInventory().addItem(new ItemStack(item, n));

		continue;
	    }
	    if (actionList.get(0).equalsIgnoreCase("kill"))
	    {

		p.setHealth(0.0);

		continue;
	    }
	    if (actionList.get(0).equalsIgnoreCase("addxp"))
	    {

		int exp = Integer.parseInt(actionList.get(1));
		p.giveExp(exp);

		continue;
	    }
	    if (actionList.get(0).equalsIgnoreCase("tpto"))
	    {

		String[] xyz = actionList.get(1).split("\\,");
		World world = p.getWorld();
		Location loc = new Location(world, Integer.parseInt(xyz[0]),
			Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));
		p.teleport(loc);

		continue;
	    }
	    if (actionList.get(0).equalsIgnoreCase("smite"))
	    {
		p.getWorld().strikeLightning(p.getLocation());

		continue;
	    }
	    if (actionList.get(0).equalsIgnoreCase("broadcast"))
	    {

		String message = actionList.get(1);
		for (int j = 2; j < actionList.size(); j++)
		{
		    message = String.valueOf(message) + " " + actionList.get(j);
		}

		message = message.replaceAll("(?i)&([a-f0-9])", "§$1");
		this.plugin.getServer().broadcastMessage(message);
	    }
	}
    }
}
