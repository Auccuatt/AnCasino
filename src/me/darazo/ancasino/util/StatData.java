/*    */ package me.darazo.ancasino.util;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.Set;
/*    */ import me.darazo.ancasino.AnCasino;
/*    */ 
/*    */ 
/*    */ public class StatData
/*    */ {
/*    */   protected AnCasino plugin;
/* 12 */   private HashMap<String, Stat> stats = new HashMap<String, Stat>();
/*    */   public Integer globalSpins;
/*    */   public Double globalWon;
/*    */   public Double globalLost;
/*    */   
/*    */   public StatData(AnCasino plugin) {
/* 18 */     this.plugin = plugin;
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<Stat> getStats() {
/* 23 */     if (!this.stats.isEmpty()) {
/* 24 */       return this.stats.values();
/*    */     }
/* 26 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public Stat getStat(String type) {
/* 31 */     return this.stats.get(type);
/*    */   }
/*    */ 
/*    */   
/*    */   public Boolean isStat(String type) {
/* 36 */     if (this.stats.containsKey(type)) {
/* 37 */       return Boolean.valueOf(true);
/*    */     }
/* 39 */     return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void addStat(Stat stat) {
/* 45 */     String type = stat.getType();
/* 46 */     Double won = stat.getWon();
/* 47 */     Double lost = stat.getLost();
/*    */     
/* 49 */     this.stats.put(type, stat);
/*    */     
/* 51 */     this.globalSpins = Integer.valueOf(this.globalSpins.intValue() + 1);
/* 52 */     this.globalWon = Double.valueOf(this.globalWon.doubleValue() + won.doubleValue());
/* 53 */     this.globalLost = Double.valueOf(this.globalLost.doubleValue() + lost.doubleValue());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void loadStat(String type) {
/* 60 */     String path = "type." + type + ".";
/*    */     
/* 62 */     Integer spins = Integer.valueOf(this.plugin.configData.stats.getInt(String.valueOf(path) + "spins"));
/* 63 */     Double won = Double.valueOf(this.plugin.configData.stats.getDouble(String.valueOf(path) + "won"));
/* 64 */     Double lost = Double.valueOf(this.plugin.configData.stats.getDouble(String.valueOf(path) + "lost"));
/*    */     
/* 66 */     this.globalSpins = Integer.valueOf(this.globalSpins.intValue() + spins.intValue());
/* 67 */     this.globalWon = Double.valueOf(this.globalWon.doubleValue() + won.doubleValue());
/* 68 */     this.globalLost = Double.valueOf(this.globalLost.doubleValue() + lost.doubleValue());
/*    */     
/* 70 */     Stat stat = new Stat(type, spins, won, lost);
/* 71 */     this.stats.put(type, stat);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void loadStats() {
/* 77 */     if (this.plugin.configData.trackStats.booleanValue()) {
/*    */       
/* 79 */       this.stats = new HashMap<String, Stat>();
/*    */       
/* 81 */       this.globalSpins = Integer.valueOf(0);
/* 82 */       this.globalWon = Double.valueOf(0.0D);
/* 83 */       this.globalLost = Double.valueOf(0.0D);
/* 84 */       Integer i = Integer.valueOf(0);
/*    */       
/* 86 */       if (this.plugin.configData.stats.isConfigurationSection("types")) {
/* 87 */         Set<String> types = this.plugin.configData.stats.getConfigurationSection("types").getKeys(false);
/* 88 */         for (String type : types) {
/* 89 */           loadStat(type);
/* 90 */           i = Integer.valueOf(i.intValue() + 1);
/*    */         } 
/*    */       } 
/* 93 */       this.plugin.logger.info(String.valueOf(this.plugin.prefix) + " Loaded statistics for " + i + " types.");
/*    */     }
/*    */     else {
/*    */       
/* 97 */       this.plugin.logger.info(String.valueOf(this.plugin.prefix) + " Not tracking statistics.");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasin\\util\StatData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */