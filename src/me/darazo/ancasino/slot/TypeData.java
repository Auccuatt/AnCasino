/*     */ package me.darazo.ancasino.slot;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;

/*     */ import me.darazo.ancasino.AnCasino;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TypeData
/*     */ {
/*     */   protected AnCasino plugin;
/*     */   private HashMap<String, Type> types;
/*     */   
/*     */   public TypeData(AnCasino plugin) {
/*  20 */     this.plugin = plugin;
/*     */   }
/*     */ 
/*     */   
/*     */   public Type getType(String name) {
/*  25 */     return this.types.get(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<Type> getTypes() {
/*  30 */     return this.types.values();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addType(Type type) {
/*  35 */     String name = type.getName();
/*  36 */     this.types.put(name, type);
/*     */     
/*  38 */     this.plugin.configData.config.set("types." + type.getName() + ".cost", type.getCost());
/*  39 */     this.plugin.configData.config.set("types." + type.getName() + ".create-cost", type.getCreateCost());
/*  40 */     this.plugin.saveConfig();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeType(String type) {
/*  45 */     this.types.remove(type);
/*  46 */     this.plugin.configData.config.set("types." + type, null);
/*  47 */     this.plugin.saveConfig();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean isType(String type) {
/*  53 */     if (this.types.containsKey(type)) {
/*  54 */       return Boolean.valueOf(true);
/*     */     }
/*  56 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadTypes() {
/*  62 */     this.types = new HashMap<String, Type>();
/*  63 */     Integer i = Integer.valueOf(0);
/*     */     
/*  65 */     if (this.plugin.configData.config.isConfigurationSection("types")) {
/*  66 */       Set<String> types = this.plugin.configData.config.getConfigurationSection("types").getKeys(false);
/*  67 */       if (!types.isEmpty()) {
/*  68 */         for (String name : types) {
/*  69 */           loadType(name);
/*  70 */           i = Integer.valueOf(i.intValue() + 1);
/*     */         } 
/*     */       }
/*     */     } 
/*  74 */     this.plugin.logger.info(String.valueOf(this.plugin.prefix) + " Loaded " + i + " types.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadType(String name) {
/*  80 */     String path = "types." + name + ".";
/*     */     
/*  82 */     Double cost = Double.valueOf(this.plugin.configData.config.getDouble(String.valueOf(path) + "cost", 100.0D));
/*  83 */     Double createCost = Double.valueOf(this.plugin.configData.config.getDouble(String.valueOf(path) + "create-cost", 100.0D));
/*  84 */     ArrayList<String> reel = getReel(name);
/*     */     
/*  86 */     Map<String, String> messages = getMessages(name);
/*  87 */     List<String> helpMessages = this.plugin.configData.config.getStringList(String.valueOf(path) + "messages.help");
/*  88 */     Map<String, Reward> rewards = getRewards(name);
/*     */     
/*  90 */     Type type = new Type(name, cost, createCost, reel, messages, helpMessages, rewards);
/*  91 */     this.types.put(name, type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList<String> getReel(String type) {
/*  97 */     List<String> reel = this.plugin.configData.config.getStringList("types." + type + ".reel");
/*     */     
/*  99 */     ArrayList<String> parsedReel = new ArrayList<String>();
/* 100 */     for (String m : reel) {
/* 101 */       String[] mSplit = m.split("\\,");
/* 102 */       int i = Integer.parseInt(mSplit[1]);
/* 103 */       while (i > 0) {
/* 104 */         parsedReel.add(mSplit[0]);
/* 105 */         i--;
/*     */       } 
/*     */     } 
/* 108 */     return parsedReel;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Reward getReward(String type, String id) {
/* 114 */     String path = "types." + type + ".rewards." + id + ".";
/*     */     
/* 116 */     String message = this.plugin.configData.config.getString(String.valueOf(path) + "message");
/* 117 */     Double money = Double.valueOf(this.plugin.configData.config.getDouble(String.valueOf(path) + "money"));
/* 118 */     List<String> action = Arrays.asList(plugin.configData.config.getString(String.valueOf(path) + "action").split(" "));
/*     */     
/* 128 */     Reward reward = new Reward(message, money, action);
/* 129 */     return reward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Reward> getRewards(String type) {
/* 135 */     Set<String> ids = this.plugin.configData.config.getConfigurationSection("types." + type + ".rewards").getKeys(false);
/* 136 */     Map<String, Reward> rewards = new HashMap<String, Reward>();
/*     */     
/* 138 */     for (String itemId : ids) {
/* 139 */       String id = itemId;
/* 140 */       Reward reward = getReward(type, id);
/* 141 */       rewards.put(id, reward);
/*     */     } 
/*     */     
/* 144 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private HashMap<String, String> getMessages(String type) {
/* 150 */     HashMap<String, String> messages = new HashMap<String, String>();
/* 151 */     Double cost = Double.valueOf(this.plugin.configData.config.getDouble("types." + type + ".cost"));
/*     */     
/* 153 */     messages.put("noFunds", this.plugin.configData.config.getString("types." + type + ".messages.insufficient-funds"));
/* 154 */     messages.put("inUse", this.plugin.configData.config.getString("types." + type + ".messages.in-use"));
/* 155 */     messages.put("noPermission", this.plugin.configData.config.getString("types." + type + ".messages.no-permission"));
/* 156 */     messages.put("noWin", this.plugin.configData.config.getString("types." + type + ".messages.no-win"));
/* 157 */     messages.put("start", this.plugin.configData.config.getString("types." + type + ".messages.start"));
/*     */ 
/*     */     
/* 160 */     for (Map.Entry<String, String> entry : messages.entrySet()) {
/* 161 */       String message = entry.getValue();
/* 162 */       String key = entry.getKey();
/* 163 */       System.out.println(message);
/* 164 */       message = message.replaceAll("\\[cost\\]", Double.toString(cost));
/* 165 */       messages.put(key, message);
/*     */     } 
/*     */     
/* 168 */     return messages;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Double getMaxPrize(String type) {
/* 174 */     Map<String, Reward> rewards = getRewards(type);
/* 175 */     Double max = Double.valueOf(0.0D);
/*     */     
/* 177 */     for (Map.Entry<String, Reward> entry : rewards.entrySet()) {
/* 178 */       Reward reward = entry.getValue();
/* 179 */       Double money = reward.getMoney();
/* 180 */       if (money.doubleValue() > max.doubleValue()) {
/* 181 */         max = money;
/*     */       }
/*     */     } 
/* 184 */     return max;
/*     */   }
/*     */ 
/*     */   
/*     */   public void newType(String name) {
/* 189 */     String path = "types." + name + ".";
/* 190 */     List<String> reel = Arrays.asList(new String[] { "42,10", "41,5", "57,2" });
/* 191 */     List<String> help = Arrays.asList(new String[] { "Instructions:", "Get 3 in a row to win.", "3 iron blocks: $250", "3 gold blocks: $500", "3 diamond blocks: $1200" });
/*     */     
/* 193 */     this.plugin.configData.config.set(String.valueOf(path) + "cost", Integer.valueOf(100));
/* 194 */     this.plugin.configData.config.set(String.valueOf(path) + "create-cost", Integer.valueOf(1000));
/* 195 */     this.plugin.configData.config.set(String.valueOf(path) + "reel", reel);
/*     */     
/* 197 */     path = String.valueOf(path) + "rewards.";
/*     */     
/* 199 */     this.plugin.configData.config.set(String.valueOf(path) + "42.message", "Winner!");
/* 200 */     this.plugin.configData.config.set(String.valueOf(path) + "42.money", Integer.valueOf(250));
/* 201 */     this.plugin.configData.config.set(String.valueOf(path) + "41.message", "Winner!");
/* 202 */     this.plugin.configData.config.set(String.valueOf(path) + "41.money", Integer.valueOf(500));
/* 203 */     this.plugin.configData.config.set(String.valueOf(path) + "57.message", "Winner!");
/* 204 */     this.plugin.configData.config.set(String.valueOf(path) + "57.money", Integer.valueOf(1200));
/*     */     
/* 206 */     path = "types." + name + ".messages.";
/*     */     
/* 208 */     this.plugin.configData.config.set(String.valueOf(path) + "insufficient-funds", "You can't afford to use this.");
/* 209 */     this.plugin.configData.config.set(String.valueOf(path) + "in-use", "This slot machine is already in use.");
/* 210 */     this.plugin.configData.config.set(String.valueOf(path) + "no-win", "No luck this time.");
/* 211 */     this.plugin.configData.config.set(String.valueOf(path) + "start", "[cost] removed from your account. Let's roll!");
/* 212 */     this.plugin.configData.config.set(String.valueOf(path) + "help", help);
/*     */     
/* 214 */     this.plugin.saveConfig();
/* 215 */     loadType(name);
/*     */   }
/*     */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\slot\TypeData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */