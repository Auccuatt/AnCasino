package me.darazo.ancasino.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import me.darazo.ancasino.AnCasino;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigData
{
    protected AnCasino plugin;
    public FileConfiguration config;
    public FileConfiguration slots;
    public FileConfiguration stats;
    public File configFile;
    public File slotsFile;
    public File statsFile;
    public String prefix;
    public String prefixColor;
    public String chatColor;
    public Boolean displayPrefix;
    public Boolean trackStats;
    public Boolean allowDiagonals;
    public Boolean protection;

    public ConfigData(AnCasino plugin)
    {
	this.plugin = plugin;
    }

    public void load()
    {

	this.configFile = new File(this.plugin.getDataFolder(), "config.yml");
	this.config = (FileConfiguration) YamlConfiguration
		.loadConfiguration(this.configFile);
	if (!configFile.exists())
	{
	    setTypeDefaults("default");
	}

	setGlobals();

	this.statsFile = new File(this.plugin.getDataFolder(), "stats.yml");
	this.stats = (FileConfiguration) YamlConfiguration
		.loadConfiguration(this.statsFile);

	this.slotsFile = new File(this.plugin.getDataFolder(), "slots.yml");
	this.slots = (FileConfiguration) YamlConfiguration
		.loadConfiguration(this.slotsFile);

	this.plugin.slotData.loadSlots();
	this.plugin.typeData.loadTypes();
	this.plugin.statsData.loadStats();
    }

    public void save()
    {
	this.plugin.saveConfig();
	saveSlots();
	saveStats();
    }

    private void setGlobals()
    {
	this.prefix = this.config
		.getString("options.chat.plugin-prefix");
	this.prefixColor = this.config
		.getString("options.chat.plugin-prefix-color");
	this.chatColor = this.config.getString("options.chat.chat-color");
	this.displayPrefix = Boolean.valueOf(this.config
		.getBoolean("options.chat.display-plugin-prefix"));
	this.trackStats = Boolean.valueOf(
		this.config.getBoolean("options.track-statistics"));
	this.allowDiagonals = Boolean.valueOf(this.config
		.getBoolean("options.allow-diagonal-winnings"));
	this.protection = Boolean.valueOf(
		this.config.getBoolean("options.enable-slot-protection"));
    }

    public void saveSlots()
    {
	try
	{
	    this.slots.save(this.slotsFile);
	} catch (IOException e)
	{
	    e.printStackTrace();
	}
    }

    public void saveStats()
    {
	Collection<Stat> stats = this.plugin.statsData.getStats();
	if (stats != null && !stats.isEmpty())
	{
	    for (Stat stat : stats)
	    {
		String path = "types." + stat.getType() + ".";
		this.stats.set(String.valueOf(path) + "spins", stat.getSpins());
		this.stats.set(String.valueOf(path) + "won", stat.getLost());
		this.stats.set(String.valueOf(path) + "lost", stat.getLost());
	    }
	}

	this.stats.set("global.spins", this.plugin.statsData.globalSpins);
	this.stats.set("global.won", this.plugin.statsData.globalWon);
	this.stats.set("global.lost", this.plugin.statsData.globalLost);

	try
	{
	    this.stats.save(this.statsFile);
	} catch (IOException e)
	{
	    e.printStackTrace();
	}
    }

    public void setTypeDefaults(String name)
    {

	this.config.options().header(""
		+ "-------------------------------------------------------------\n"
		+ "---AAAA---N----N---CCCC----AAAA----SSSS---I--N----N---OOOO---\n"
		+ "--A----A--NN---N--C----C--A----A--S----S--I--NN---N--O----O--\n"
		+ "--A----A--N-N--N--C-------A----A--S-------I--N-N--N--O----O--\n"
		+ "--AAAAAA--N--N-N--C-------AAAAAA---SSSS---I--N--N-N--O----O--\n"
		+ "--A----A--N---NN--C-------A----A-------S--I--N---NN--O----O--\n"
		+ "--A----A--N----N--C----C--A----A--S----S--I--N----N--O----O--\n"
		+ "--A----A--N----N---CCCC---A----A---SSSS---I--N----N---OOOO---\n"
		+ "-------------------------------------------------------------\n"
		+ "\n"
		+ "Welcome, firstly I want to say thank you for downloading this\n"
		+ "awesome plugin. New in this version, is an improved config-  \n"
		+ "uration file. In addition, /casino reload works properly now,\n"
		+ "so no more restarting your own server to update your slot    \n"
		+ "machines. All I want to say is if you like this plugin       \n"
		+ "and you want to support it, please consider donating. Now    \n"
		+ "onto the fun stuff. Know that Vault is required for this     \n"
		+ "plugin to function.\n"
		+ "\n"
		+ "The format is as follows:\n"
		+ "\n"
		+ "default - Machine type, many can be created in this file.    \n"
		+ "\n"
		+ "cost - How much money each roll costs.\n"
		+ "\n"
		+ "create-cost - How much money it costs to create the machine. \n"
		+ "\n"
		+ "permission - Doesn't work just yet, will be added in v1.4    \n"
		+ "\n"
		+ "reel - The chances of each block appearing on the next spin  \n"
		+ "cycle. (e.g. if you have IRON_BLOCK set to 10, and GOLD_BLOCK\n"
		+ "set to 20, there is a 20/30 (66.66%) chance that the next    \n"
		+ "block will be a GOLD_BLOCK) Format: \"Material,Freq.(ints)\"\n"
		+ "\n"
		+ "rewards - There are three values for each block in the reel\n"
		+ "that you include: action, money, message.\n"
		+ "\n"
		+ "action - There are seven (will probably be changed) values\n"
		+ "that can go here: kill, addxp, tpto, smite, give, broadcast\n"
		+ "and none, none does nothing.\n"
		+ "\n"
		+ "money - How much money you won from gambling.\n"
		+ "\n"
		+ "message - A message can be sent after you win.\n"
		+ "\n"
		+ "\n"
		+ "AnCasino v1.4.2\n"
		+ "@authors: [Auccuatt <auccuatt@gmail.com>, Darazo (Original Creator) <darazo.dawning@gmail.com>]\n"
		+ "@license: GPLv3\n");

	this.config.set("options.chat.plugin-prefix", "[AnCasino] ");
	this.config.set("options.chat.plugin-prefix-color", "&5");
	this.config.set("options.chat.chat-color", "&d");
	this.config.set("options.chat.display-plugin-prefix", true);
	this.config.set("options.track-statistics", true);
	this.config.set("options.allow-diagonal-winnings", true);
	this.config.set("options.enable-slot-protection", true);
	
	this.config.set("types." + name + ".cost", 100.0);
	this.config.set("types." + name + ".create-cost", 0.0);
	this.config.set("types." + name + ".permission",
		"ancasino.use.default");

	ArrayList<String> reel = new ArrayList<String>();
	reel.add("IRON_BLOCK,30");
	reel.add("GOLD_BLOCK,20");
	reel.add("DIAMOND_BLOCK,15");
	reel.add("EMERALD_BLOCK,5");
	reel.add("TNT,10");
	this.config.set("types." + name + ".reel", reel);

	this.config.set("types." + name + ".rewards.IRON_BLOCK.action", "none");
	this.config.set("types." + name + ".rewards.IRON_BLOCK.message",
		"Winner - 3 iron blocks! $250 awarded.");
	this.config.set("types." + name + ".rewards.IRON_BLOCK.money", 250.0);
	this.config.set("types." + name + ".rewards.GOLD_BLOCK.action", "none");
	this.config.set("types." + name + ".rewards.GOLD_BLOCK.message",
		"Winner - 3 gold blocks! $500 awarded.");
	this.config.set("types." + name + ".rewards.GOLD_BLOCK.money", 500.0);
	this.config.set("types." + name + ".rewards.DIAMOND_BLOCK.action",
		"none");
	this.config.set("types." + name + ".rewards.DIAMOND_BLOCK.message",
		"Winner - 3 diamond blocks! $1200 awarded.");
	this.config.set("types." + name + ".rewards.DIAMOND_BLOCK.money",
		1200.0);
	this.config.set("types." + name + ".rewards.EMERALD_BLOCK.action",
		"none");
	this.config.set("types." + name + ".rewards.EMERALD_BLOCK.message",
		"Winner - 3 emerald blocks! $1500 awarded.");
	this.config.set("types." + name + ".rewards.EMERALD_BLOCK.money",
		1500.0);
	this.config.set("types." + name + ".rewards.TNT.action", "kill");
	this.config.set("types." + name + ".rewards.TNT.message", "Winne-");
	this.config.set("types." + name + ".rewards.TNT.money", 0.0);

	this.config.set("types." + name + ".messages.insufficient-funds",
		"Insufficient funds.");
	this.config.set("types." + name + ".messages.in-use",
		"This slot machine is already in use.");
	this.config.set("types." + name + ".messages.no-win",
		"No luck this time.");
	this.config.set("types." + name + ".messages.start",
		"[cost] removed from your account. Let's roll!");
	this.config.set("types." + name + ".messages.no-permission",
		"You don't have permission to use this.");

	List<String> helpList = new ArrayList<String>();
	helpList.add("Instructions:");
	helpList.add(String.valueOf("Get 3 in a row in order to win, $100 per spin."));
	helpList.add("3 iron blocks: $250");
	helpList.add("3 gold blocks: $500");
	helpList.add("3 diamond blocks: $1200");
	helpList.add("3 emerald blocks: $1500");
	this.config.set("types." + name + ".messages.help", helpList);

	try
	{
	    this.config.save(configFile);
	} catch (IOException e)
	{
	    e.printStackTrace();
	}
    }
}
