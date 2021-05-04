/*     */ package me.darazo.ancasino.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import me.darazo.ancasino.AnCasino;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ 
/*     */ public class ConfigData {
/*     */   protected AnCasino plugin;
/*     */   public FileConfiguration config;
/*     */   public FileConfiguration slots;
/*     */   public FileConfiguration stats;
/*     */   public File slotsFile;
/*     */   public File statsFile;
/*     */   public String prefixColor;
/*     */   public String chatColor;
/*     */   public Boolean displayPrefix;
/*     */   public Boolean trackStats;
/*     */   public Boolean allowDiagonals;
/*     */   public Boolean protection;
/*     */   
/*     */   public ConfigData(AnCasino plugin) {
/*  26 */     this.plugin = plugin;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void load() {
/*  32 */     this.config = this.plugin.getConfig();
/*  33 */     this.config.options().copyDefaults(true);
/*  34 */     this.plugin.saveConfig();
/*     */     
/*  36 */     setGlobals();
/*     */     
/*  38 */     this.statsFile = new File(this.plugin.getDataFolder(), "stats.yml");
/*  39 */     this.stats = (FileConfiguration)YamlConfiguration.loadConfiguration(this.statsFile);
/*     */     
/*  41 */     this.slotsFile = new File(this.plugin.getDataFolder(), "slots.yml");
/*  42 */     this.slots = (FileConfiguration)YamlConfiguration.loadConfiguration(this.slotsFile);
/*     */     
/*  44 */     this.plugin.slotData.loadSlots();
/*  45 */     this.plugin.typeData.loadTypes();
/*  46 */     this.plugin.statsData.loadStats();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void save() {
/*  53 */     this.plugin.saveConfig();
/*  54 */     saveSlots();
/*  55 */     saveStats();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setGlobals() {
/*  62 */     this.prefixColor = this.config.getString("options.chat.plugin-prefix-color", "&c");
/*  63 */     this.chatColor = this.config.getString("options.chat.chat-color", "&a");
/*  64 */     this.displayPrefix = Boolean.valueOf(this.config.getBoolean("options.chat.display-plugin-prefix", true));
/*  65 */     this.trackStats = Boolean.valueOf(this.config.getBoolean("options.track-statistics", true));
/*  66 */     this.allowDiagonals = Boolean.valueOf(this.config.getBoolean("options.allow-diagonal-winnings", true));
/*  67 */     this.protection = Boolean.valueOf(this.config.getBoolean("options.enable-slot-protection", true));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveSlots() {
/*     */     try {
/*  73 */       this.slots.save(this.slotsFile);
/*  74 */     } catch (IOException e) {
/*  75 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveStats() {
/*  82 */     Collection<Stat> stats = this.plugin.statsData.getStats();
/*  83 */     if (stats != null && !stats.isEmpty()) {
/*  84 */       for (Stat stat : stats) {
/*  85 */         String path = "types." + stat.getType() + ".";
/*  86 */         this.stats.set(String.valueOf(path) + "spins", stat.getSpins());
/*  87 */         this.stats.set(String.valueOf(path) + "won", stat.getLost());
/*  88 */         this.stats.set(String.valueOf(path) + "lost", stat.getLost());
/*     */       } 
/*     */     }
/*     */     
/*  92 */     this.stats.set("global.spins", this.plugin.statsData.globalSpins);
/*  93 */     this.stats.set("global.won", this.plugin.statsData.globalWon);
/*  94 */     this.stats.set("global.lost", this.plugin.statsData.globalLost);
/*     */     
/*     */     try {
/*  97 */       this.stats.save(this.statsFile);
/*  98 */     } catch (IOException e) {
/*  99 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTypeDefaults(String name) {
/* 106 */     this.config.set("types." + name + ".price", Integer.valueOf(100));
/* 107 */     this.config.set("types." + name + ".permission", Integer.valueOf(0));
/* 108 */     this.config.set("types." + name + ".price", Integer.valueOf(100));
/*     */     
/* 110 */     ArrayList<String> reel = new ArrayList<String>();
/* 111 */     reel.add("42,10");
/* 112 */     reel.add("41,5");
/* 113 */     reel.add("57,2");
/* 114 */     this.config.set("types." + name + ".reel", reel);
/*     */     
/* 116 */     this.config.set("types." + name + ".rewards.IRON_BLOCK.message", "Winner");
/* 117 */     this.config.set("types." + name + ".rewards.IRON_BLOCK.money", Integer.valueOf(100));
/* 118 */     this.config.set("types." + name + ".rewards.GOLD_BLOCK.message", "Winner");
/* 119 */     this.config.set("types." + name + ".rewards.GOLD_BLOCK.money", Integer.valueOf(100));
/* 120 */     this.config.set("types." + name + ".rewards.DIAMOND_BLOCK.message", "Winner");
/* 121 */     this.config.set("types." + name + ".rewards.DIAMOND_BLOCK.money", Integer.valueOf(100));
/*     */     
/* 123 */     this.config.set("types." + name + ".messages.insufficient-funds", "Insufficient funds.");
/* 124 */     this.config.set("types." + name + ".messages.in-use", "In use.");
/* 125 */     this.config.set("types." + name + ".messages.no-win", "No win.");
/* 126 */     this.config.set("types." + name + ".messages.start", "Start.");
/* 127 */     this.config.set("types." + name + ".messages.help", "");
/*     */     
/* 129 */     this.plugin.saveConfig();
/*     */   }
/*     */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasin\\util\ConfigData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */