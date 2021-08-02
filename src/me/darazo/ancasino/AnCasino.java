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
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
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
    public String prefix;
    
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

    public PluginManager pm = getServer().getPluginManager();
    public final Logger logger = Logger.getLogger("Minecraft");
    public Economy economy = null;
    
    public void onDisable()
    {
	this.logger.info(String.valueOf(this.prefix) + "Disabled.");
    }

    public void onEnable()
    {
	this.configData.load();
	this.prefix = configData.prefix;
	
	boolean economySetup = setupEconomy();
	if (!economySetup)
	{
	    pm.disablePlugin(this);
	}
	else
	{	
	    pm.registerEvents((Listener) this.playerListener, (Plugin) this);
	    pm.registerEvents((Listener) this.blockListener, (Plugin) this);

	    this.pdfFile = getDescription();

	    this.commandExecutor = new AnCommandExecutor(this);
	    getCommand("casino").setExecutor((CommandExecutor) this.commandExecutor);
	    
	    this.logger.info(String.valueOf(this.prefix) + "v" + this.pdfFile.getVersion() + " enabled.");
	}
    }

    public void sendMessage(Player player, String message)
    {
	String prefixColorCode = this.configData.prefixColor;
	String chatColorCode = this.configData.chatColor;
	
	ChatColor pColorCode = ChatColor.getByChar(prefixColorCode.charAt(1));
	ChatColor cColorCode = ChatColor.getByChar(chatColorCode.charAt(1));
	
	if(this.configData.displayPrefix)
	    message = pColorCode + this.prefix + cColorCode + message;
	else
	    message = cColorCode + message;
	player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }
    
    public void sendMessageDirectly(Player player, String message)
    {
	String prefixColorCode = this.configData.prefixColor;
	String chatColorCode = this.configData.chatColor;
	
	ChatColor pColorCode = ChatColor.getByChar(prefixColorCode.charAt(1));
	ChatColor cColorCode = ChatColor.getByChar(chatColorCode.charAt(1));
	
	if(this.configData.displayPrefix)
	    message = pColorCode + this.prefix + cColorCode + message;
	else
	    message = cColorCode + message;
	player.sendMessage(message);
    }
    
    private boolean setupEconomy()
    {
	 if (pm.getPlugin("Vault") == null) 
	 {
	     this.logger.warning(String.valueOf(this.prefix) 
		     + ChatColor.LIGHT_PURPLE + "WARNING: Vault is required to manage balances in order to use AnCasino, please download Vault.");
	     return false;
	 }
	 RegisteredServiceProvider<Economy> econProvider = getServer().getServicesManager().getRegistration(Economy.class);
	 if (econProvider == null) 
	 {
	     this.logger.warning(String.valueOf(this.prefix) 
		     + ChatColor.LIGHT_PURPLE + "WARNING: An Economy plugin that stores balances (like EssentialsX) is required as AnCasino does not have it's own Economy.");
	     return false;
	 }
	 economy = econProvider.getProvider();
	 return econProvider != null;
    }
}
