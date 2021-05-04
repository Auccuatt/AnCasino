/*    */ package me.darazo.ancasino.command;
/*    */ 
/*    */ import me.darazo.ancasino.AnCasino;
/*    */ import me.darazo.ancasino.util.Stat;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class CasinoStats
/*    */   extends AnCommand
/*    */ {
/*    */   public CasinoStats(AnCasino plugin, String[] args, Player player) {
/* 12 */     super(plugin, args, player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean process() {
/* 18 */     if (!this.plugin.permission.isAdmin(this.player).booleanValue()) {
/* 19 */       noPermission();
/* 20 */       return Boolean.valueOf(true);
/*    */     } 
/*    */     
/* 23 */     sendMessage("Statistics for registered types:");
/* 24 */     for (Stat stat : this.plugin.statsData.getStats()) {
/*    */       
/* 26 */       String type = stat.getType();
/* 27 */       Integer spins = stat.getSpins();
/* 28 */       Double won = stat.getWon();
/* 29 */       Double lost = stat.getLost();
/*    */       
/* 31 */       sendMessage(String.valueOf(type) + " - spins: " + spins + " - money won: " + won + " - money lost: " + lost);
/*    */     } 
/*    */     
/* 34 */     return Boolean.valueOf(true);
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\command\CasinoStats.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */