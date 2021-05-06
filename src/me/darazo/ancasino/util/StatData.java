package me.darazo.ancasino.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import me.darazo.ancasino.AnCasino;

public class StatData
{
    protected AnCasino plugin;
    private HashMap<String, Stat> stats = new HashMap<String, Stat>();
    public Integer globalSpins;
    public Double globalWon;
    public Double globalLost;

    public StatData(AnCasino plugin)
    {
	this.plugin = plugin;
    }

    public Collection<Stat> getStats()
    {
	if (!this.stats.isEmpty())
	{
	    return this.stats.values();
	}
	return null;
    }

    public Stat getStat(String type)
    {
	return this.stats.get(type);
    }

    public Boolean isStat(String type)
    {
	if (this.stats.containsKey(type))
	{
	    return Boolean.valueOf(true);
	}
	return Boolean.valueOf(false);
    }

    public void addStat(Stat stat)
    {
	String type = stat.getType();
	Double won = stat.getWon();
	Double lost = stat.getLost();

	this.stats.put(type, stat);

	this.globalSpins = Integer.valueOf(this.globalSpins.intValue() + 1);
	this.globalWon = Double
		.valueOf(this.globalWon.doubleValue() + won.doubleValue());
	this.globalLost = Double
		.valueOf(this.globalLost.doubleValue() + lost.doubleValue());
    }

    private void loadStat(String type)
    {
	String path = "type." + type + ".";

	Integer spins = Integer.valueOf(this.plugin.configData.stats
		.getInt(String.valueOf(path) + "spins"));
	Double won = Double.valueOf(this.plugin.configData.stats
		.getDouble(String.valueOf(path) + "won"));
	Double lost = Double.valueOf(this.plugin.configData.stats
		.getDouble(String.valueOf(path) + "lost"));

	this.globalSpins = Integer
		.valueOf(this.globalSpins.intValue() + spins.intValue());
	this.globalWon = Double
		.valueOf(this.globalWon.doubleValue() + won.doubleValue());
	this.globalLost = Double
		.valueOf(this.globalLost.doubleValue() + lost.doubleValue());

	Stat stat = new Stat(type, spins, won, lost);
	this.stats.put(type, stat);
    }

    public void loadStats()
    {
	if (this.plugin.configData.trackStats.booleanValue())
	{

	    this.stats = new HashMap<String, Stat>();

	    this.globalSpins = Integer.valueOf(0);
	    this.globalWon = Double.valueOf(0.0D);
	    this.globalLost = Double.valueOf(0.0D);
	    Integer i = Integer.valueOf(0);

	    if (this.plugin.configData.stats.isConfigurationSection("types"))
	    {
		Set<String> types = this.plugin.configData.stats
			.getConfigurationSection("types").getKeys(false);
		for (String type : types)
		{
		    loadStat(type);
		    i = Integer.valueOf(i.intValue() + 1);
		}
	    }
	    this.plugin.logger.info(String.valueOf(this.plugin.prefix)
		    + " Loaded statistics for " + i + " types.");
	} else
	{

	    this.plugin.logger.info(String.valueOf(this.plugin.prefix)
		    + " Not tracking statistics.");
	}
    }
}
