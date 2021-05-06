package me.darazo.ancasino;

import java.util.logging.Logger;

import me.darazo.ancasino.command.AnCommandExecutor;
import me.darazo.ancasino.listeners.AnBlockListener;
import me.darazo.ancasino.listeners.AnPlayerListener;
import me.darazo.ancasino.slot.RewardData;
import me.darazo.ancasino.slot.SlotData;
import me.darazo.ancasino.slot.TypeData;
import me.darazo.ancasino.util.ConfigData;
import me.darazo.ancasino.util.Permissions;
import me.darazo.ancasino.util.StatData;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class AnCasino extends JavaPlugin
{
    protected AnCasino plugin;
    public String prefix = "[AnCasino] ";

    private PluginDescriptionFile pdfFile;
    private AnPlayerListener playerListener = new AnPlayerListener(this);
    private AnBlockListener blockListener = new AnBlockListener(this);

    private AnCommandExecutor commandExecutor;
    public ConfigData configData = new ConfigData(this);
    public SlotData slotData = new SlotData(this);
    public TypeData typeData = new TypeData(this);
    public StatData statsData = new StatData(this);
    public RewardData rewardData = new RewardData(this);
    public Permissions permission = new Permissions(this);

    public Economy economy = null;
    public final Logger logger = Logger.getLogger("Minecraft");

    public void onDisable()
    {
	this.logger.info(String.valueOf(this.prefix) + " Disabled.");
    }

    public void onEnable()
    {
	this.configData.load();

	PluginManager pm = getServer().getPluginManager();
	pm.registerEvents((Listener) this.playerListener, (Plugin) this);
	pm.registerEvents((Listener) this.blockListener, (Plugin) this);

	this.pdfFile = getDescription();

	this.commandExecutor = new AnCommandExecutor(this);
	getCommand("casino")
		.setExecutor((CommandExecutor) this.commandExecutor);

	this.logger.info(String.valueOf(this.prefix) + " v"
		+ this.pdfFile.getVersion() + " enabled.");

	if (!pm.isPluginEnabled("Vault"))
	{

	    this.logger.warning(String.valueOf(this.prefix)
		    + " Vault is required in order to use this plugin.");
	    this.logger.warning(String.valueOf(this.prefix)
		    + " dev.bukkit.org/server-mods/vault/");
	    pm.disablePlugin((Plugin) this);

	} else if (!setupEconomy().booleanValue())
	{
	    this.logger.warning(String.valueOf(this.prefix)
		    + " An economy plugin is required in order to use this plugin.");
	    pm.disablePlugin((Plugin) this);
	}
    }

    public void sendMessage(Player player, String message)
    {
	String prefixColorCode = this.configData.prefixColor;
	String chatColorCode = this.configData.chatColor;
	
	ChatColor pColorCode = ChatColor.getByChar(prefixColorCode.charAt(1));
	ChatColor cColorCode = ChatColor.getByChar(chatColorCode.charAt(1));
	
	message = pColorCode + this.prefix + cColorCode + message;
	player.sendMessage(message);
    }

    private Boolean setupEconomy()
    {
	RegisteredServiceProvider<Economy> economyProvider = getServer()
		.getServicesManager().getRegistration(Economy.class);
	if (economyProvider != null)
	{
	    this.economy = (Economy) economyProvider.getProvider();
	}

	return (this.economy != null) ? Boolean.valueOf(true)
		: Boolean.valueOf(false);
    }
}
