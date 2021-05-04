/*     */ package me.darazo.ancasino;
/*     */ 
/*     */ import java.util.logging.Logger;
/*     */ import me.darazo.ancasino.command.AnCommandExecutor;
/*     */ import me.darazo.ancasino.listeners.AnBlockListener;
/*     */ import me.darazo.ancasino.listeners.AnPlayerListener;
/*     */ import me.darazo.ancasino.slot.RewardData;
/*     */ import me.darazo.ancasino.slot.SlotData;
/*     */ import me.darazo.ancasino.slot.TypeData;
/*     */ import me.darazo.ancasino.util.ConfigData;
/*     */ import me.darazo.ancasino.util.Permissions;
/*     */ import me.darazo.ancasino.util.StatData;
/*     */ import net.milkbowl.vault.economy.Economy;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginDescriptionFile;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.plugin.RegisteredServiceProvider;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class AnCasino extends JavaPlugin {
/*     */   protected AnCasino plugin;
/*  25 */   public String prefix = "[AnCasino]";
/*     */   
/*     */   private PluginDescriptionFile pdfFile;
/*  28 */   private AnPlayerListener playerListener = new AnPlayerListener(this);
/*  29 */   private AnBlockListener blockListener = new AnBlockListener(this);
/*     */   
/*     */   private AnCommandExecutor commandExecutor;
/*  32 */   public ConfigData configData = new ConfigData(this);
/*  33 */   public SlotData slotData = new SlotData(this);
/*  34 */   public TypeData typeData = new TypeData(this);
/*  35 */   public StatData statsData = new StatData(this);
/*  36 */   public RewardData rewardData = new RewardData(this);
/*  37 */   public Permissions permission = new Permissions(this);
/*     */   
/*  39 */   public Economy economy = null;
/*  40 */   public final Logger logger = Logger.getLogger("Minecraft");
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisable() {
/*  45 */     this.logger.info(String.valueOf(this.prefix) + " Saving and unloading data..");
/*     */     
/*  47 */     this.configData.save();
/*     */     
/*  49 */     this.configData = null;
/*  50 */     this.slotData = null;
/*  51 */     this.typeData = null;
/*  52 */     this.statsData = null;
/*  53 */     this.rewardData = null;
/*  54 */     this.permission = null;
/*     */     
/*  56 */     this.logger.info(String.valueOf(this.prefix) + " Disabled.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  63 */     this.configData.load();
/*     */     
/*  65 */     PluginManager pm = getServer().getPluginManager();
/*  66 */     pm.registerEvents((Listener)this.playerListener, (Plugin)this);
/*  67 */     pm.registerEvents((Listener)this.blockListener, (Plugin)this);
/*     */     
/*  69 */     this.pdfFile = getDescription();
/*     */     
/*  71 */     this.commandExecutor = new AnCommandExecutor(this);
/*  72 */     getCommand("casino").setExecutor((CommandExecutor)this.commandExecutor);
/*     */     
/*  74 */     this.logger.info(String.valueOf(this.prefix) + " v" + this.pdfFile.getVersion() + " enabled.");
/*     */     
/*  76 */     if (!pm.isPluginEnabled("Vault")) {
/*     */       
/*  78 */       this.logger.warning(String.valueOf(this.prefix) + " Vault is required in order to use this plugin.");
/*  79 */       this.logger.warning(String.valueOf(this.prefix) + " dev.bukkit.org/server-mods/vault/");
/*  80 */       pm.disablePlugin((Plugin)this);
/*     */ 
/*     */     
/*     */     }
/*  84 */     else if (!setupEconomy().booleanValue()) {
/*  85 */       this.logger.warning(String.valueOf(this.prefix) + " An economy plugin is required in order to use this plugin.");
/*  86 */       pm.disablePlugin((Plugin)this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMessage(Player player, String message) {
/*  95 */     message = String.valueOf(this.configData.prefixColor) + this.prefix + this.configData.chatColor + " " + message;
/*  96 */     message = message.replaceAll("(?i)&([a-f0-9])", "§$1");
/*  97 */     player.sendMessage(message);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Boolean setupEconomy() {
/* 103 */     RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
/* 104 */     if (economyProvider != null) {
/* 105 */       this.economy = (Economy)economyProvider.getProvider();
/*     */     }
/*     */     
/* 108 */     return (this.economy != null) ? Boolean.valueOf(true) : Boolean.valueOf(false);
/*     */   }
/*     */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\AnCasino.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */