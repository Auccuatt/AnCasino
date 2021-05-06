package me.darazo.ancasino.util;

import me.darazo.ancasino.AnCasino;
import me.darazo.ancasino.slot.SlotMachine;
import me.darazo.ancasino.slot.Type;
import org.bukkit.entity.Player;

public class Permissions
{
    protected AnCasino plugin;
    private String admin = "ancasino.admin";
    private String create = "ancasino.create";
    private String manage = "ancasino.manage";
    private String use = "ancasino.use";

    public Permissions(AnCasino plugin)
    {
	this.plugin = plugin;
    }

    public Boolean isAdmin(Player player)
    {
	if (player.isOp() || player.hasPermission(this.admin))
	{
	    return Boolean.valueOf(true);
	}
	return Boolean.valueOf(false);
    }

    public Boolean canCreate(Player player)
    {
	if (isAdmin(player).booleanValue() || player.hasPermission(this.create))
	{
	    return Boolean.valueOf(true);
	}
	return Boolean.valueOf(false);
    }

    public Boolean canCreate(Player player, Type type)
    {
	String name = type.getName();
	if (isAdmin(player).booleanValue()
		|| player
			.hasPermission(String.valueOf(this.create) + "." + name)
		|| player.hasPermission(this.create))
	{
	    return Boolean.valueOf(true);
	}
	return Boolean.valueOf(false);
    }

    public Boolean canCreate(Player player, String type)
    {
	if (isAdmin(player).booleanValue()
		|| player
			.hasPermission(String.valueOf(this.create) + "." + type)
		|| player.hasPermission(this.create))
	{
	    return Boolean.valueOf(true);
	}
	return Boolean.valueOf(false);
    }

    public Boolean canManage(Player player)
    {
	if (isAdmin(player).booleanValue() || player.hasPermission(this.manage))
	{
	    return Boolean.valueOf(true);
	}
	return Boolean.valueOf(false);
    }

    public Boolean canUse(Player player, Type type)
    {
	String name = type.getName();
	if (isAdmin(player).booleanValue()
		|| player.hasPermission(String.valueOf(this.use) + "." + name)
		|| player.hasPermission(this.use))
	{
	    return Boolean.valueOf(true);
	}
	return Boolean.valueOf(false);
    }

    public Boolean isOwner(Player player, SlotMachine slot)
    {
	if (isAdmin(player).booleanValue()
		|| slot.getOwner().equalsIgnoreCase(player.getName()))
	{
	    return Boolean.valueOf(true);
	}
	return Boolean.valueOf(false);
    }
}
