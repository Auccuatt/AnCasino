/*    */ package me.darazo.ancasino.command;
/*    */ 
/*    */ import me.darazo.ancasino.AnCasino;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class CasinoReload
/*    */   extends AnCommand
/*    */ {
/*    */   public CasinoReload(AnCasino plugin, String[] args, Player player) {
/* 11 */     super(plugin, args, player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean process() {
/* 17 */     if (!this.plugin.permission.isAdmin(this.player).booleanValue()) {
/* 18 */       noPermission();
/* 19 */       return Boolean.valueOf(true);
/*    */     } 
/*    */     
/* 22 */     this.plugin.configData.load();
/* 23 */     sendMessage("Configuration reloaded");
/* 24 */     return Boolean.valueOf(true);
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\command\CasinoReload.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */