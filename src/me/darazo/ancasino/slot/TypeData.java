package me.darazo.ancasino.slot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.darazo.ancasino.AnCasino;

public class TypeData
{
    protected AnCasino plugin;
    private HashMap<String, Type> types;

    public TypeData(AnCasino plugin)
    {
	this.plugin = plugin;
    }

    public Type getType(String name)
    {
	return this.types.get(name);
    }

    public Collection<Type> getTypes()
    {
	return this.types.values();
    }

    public void addType(Type type)
    {
	String name = type.getName();
	this.types.put(name, type);

	this.plugin.configData.config.set("types." + type.getName() + ".cost",
		type.getCost());
	this.plugin.configData.config.set(
		"types." + type.getName() + ".create-cost",
		type.getCreateCost());
	this.plugin.saveConfig();
    }

    public void removeType(String type)
    {
	this.types.remove(type);
	this.plugin.configData.config.set("types." + type, null);
	this.plugin.saveConfig();
    }

    public Boolean isType(String type)
    {
	if (this.types.containsKey(type))
	{
	    return Boolean.valueOf(true);
	}
	return Boolean.valueOf(false);
    }

    public void loadTypes()
    {
	this.types = new HashMap<String, Type>();
	Integer i = Integer.valueOf(0);

	if (this.plugin.configData.config.isConfigurationSection("types"))
	{
	    Set<String> types = this.plugin.configData.config
		    .getConfigurationSection("types").getKeys(false);
	    if (!types.isEmpty())
	    {
		for (String name : types)
		{
		    loadType(name);
		    i = Integer.valueOf(i.intValue() + 1);
		}
	    }
	}
	this.plugin.logger.info(String.valueOf(this.plugin.prefix) + " Loaded "
		+ i + " types.");
    }

    private void loadType(String name)
    {
	String path = "types." + name + ".";

	Double cost = Double.valueOf(this.plugin.configData.config
		.getDouble(String.valueOf(path) + "cost", 100.0D));
	Double createCost = Double.valueOf(this.plugin.configData.config
		.getDouble(String.valueOf(path) + "create-cost", 100.0D));
	ArrayList<String> reel = getReel(name);

	Map<String, String> messages = getMessages(name);
	List<String> helpMessages = this.plugin.configData.config
		.getStringList(String.valueOf(path) + "messages.help");
	Map<String, Reward> rewards = getRewards(name);

	Type type = new Type(name, cost, createCost, reel, messages,
		helpMessages, rewards);
	this.types.put(name, type);
    }

    private ArrayList<String> getReel(String type)
    {
	List<String> reel = this.plugin.configData.config
		.getStringList("types." + type + ".reel");

	ArrayList<String> parsedReel = new ArrayList<String>();
	for (String m : reel)
	{
	    String[] mSplit = m.split("\\,");
	    int i = Integer.parseInt(mSplit[1]);
	    while (i > 0)
	    {
		parsedReel.add(mSplit[0]);
		i--;
	    }
	}
	return parsedReel;
    }

    public Reward getReward(String type, String id)
    {
	String path = "types." + type + ".rewards." + id + ".";

	String message = this.plugin.configData.config
		.getString(String.valueOf(path) + "message");
	Double money = Double.valueOf(this.plugin.configData.config
		.getDouble(String.valueOf(path) + "money"));
	List<String> action = Arrays.asList(plugin.configData.config
		.getString(String.valueOf(path) + "action").split(" "));

	Reward reward = new Reward(message, money, action);
	return reward;
    }

    public Map<String, Reward> getRewards(String type)
    {
	Set<String> ids = this.plugin.configData.config
		.getConfigurationSection("types." + type + ".rewards")
		.getKeys(false);
	Map<String, Reward> rewards = new HashMap<String, Reward>();

	for (String itemId : ids)
	{
	    String id = itemId;
	    Reward reward = getReward(type, id);
	    rewards.put(id, reward);
	}

	return rewards;
    }

    private HashMap<String, String> getMessages(String type)
    {
	HashMap<String, String> messages = new HashMap<String, String>();
	Double cost = Double.valueOf(this.plugin.configData.config
		.getDouble("types." + type + ".cost"));

	messages.put("noFunds", this.plugin.configData.config
		.getString("types." + type + ".messages.insufficient-funds"));
	messages.put("inUse", this.plugin.configData.config
		.getString("types." + type + ".messages.in-use"));
	messages.put("noPermission", this.plugin.configData.config
		.getString("types." + type + ".messages.no-permission"));
	messages.put("noWin", this.plugin.configData.config
		.getString("types." + type + ".messages.no-win"));
	messages.put("start", this.plugin.configData.config
		.getString("types." + type + ".messages.start"));

	for (Map.Entry<String, String> entry : messages.entrySet())
	{
	    String message = entry.getValue();
	    String key = entry.getKey();
	    System.out.println(message);
	    message = message.replaceAll("\\[cost\\]", Double.toString(cost));
	    messages.put(key, message);
	}

	return messages;
    }

    public Double getMaxPrize(String type)
    {
	Map<String, Reward> rewards = getRewards(type);
	Double max = Double.valueOf(0.0D);

	for (Map.Entry<String, Reward> entry : rewards.entrySet())
	{
	    Reward reward = entry.getValue();
	    Double money = reward.getMoney();
	    if (money.doubleValue() > max.doubleValue())
	    {
		max = money;
	    }
	}
	return max;
    }

    public void newType(String name)
    {
	String path = "types." + name + ".";
	List<String> reel = Arrays.asList(new String[]
	{ "42,10", "41,5", "57,2" });
	List<String> help = Arrays.asList(new String[]
	{ "Instructions:", "Get 3 in a row to win.", "3 iron blocks: $250",
		"3 gold blocks: $500", "3 diamond blocks: $1200" });

	this.plugin.configData.config.set(String.valueOf(path) + "cost",
		Integer.valueOf(100));
	this.plugin.configData.config.set(String.valueOf(path) + "create-cost",
		Integer.valueOf(1000));
	this.plugin.configData.config.set(String.valueOf(path) + "reel", reel);

	path = String.valueOf(path) + "rewards.";

	this.plugin.configData.config.set(String.valueOf(path) + "42.message",
		"Winner!");
	this.plugin.configData.config.set(String.valueOf(path) + "42.money",
		Integer.valueOf(250));
	this.plugin.configData.config.set(String.valueOf(path) + "41.message",
		"Winner!");
	this.plugin.configData.config.set(String.valueOf(path) + "41.money",
		Integer.valueOf(500));
	this.plugin.configData.config.set(String.valueOf(path) + "57.message",
		"Winner!");
	this.plugin.configData.config.set(String.valueOf(path) + "57.money",
		Integer.valueOf(1200));

	path = "types." + name + ".messages.";

	this.plugin.configData.config.set(
		String.valueOf(path) + "insufficient-funds",
		"You can't afford to use this.");
	this.plugin.configData.config.set(String.valueOf(path) + "in-use",
		"This slot machine is already in use.");
	this.plugin.configData.config.set(String.valueOf(path) + "no-win",
		"No luck this time.");
	this.plugin.configData.config.set(String.valueOf(path) + "start",
		"[cost] removed from your account. Let's roll!");
	this.plugin.configData.config.set(String.valueOf(path) + "help", help);

	this.plugin.saveConfig();
	loadType(name);
    }
}
