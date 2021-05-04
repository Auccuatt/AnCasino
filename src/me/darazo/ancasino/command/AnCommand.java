/*    */ package me.darazo.ancasino.command;
/*    */ 
/*    */ import me.darazo.ancasino.AnCasino;
/*    */ import me.darazo.ancasino.slot.SlotMachine;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AnCommand
/*    */ {
/*    */   public AnCasino plugin;
/*    */   public Player player;
/*    */   public String[] args;
/*    */   
/*    */   public AnCommand(AnCasino plugin, String[] args, Player player) {
/* 17 */     this.plugin = plugin;
/* 18 */     this.args = args;
/* 19 */     this.player = player;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean process() {
/* 25 */     return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean isOwner(SlotMachine slot) {
/* 31 */     if (this.plugin.permission.isAdmin(this.player).booleanValue() || slot.getOwner().equalsIgnoreCase(this.player.getName())) {
/* 32 */       return Boolean.valueOf(true);
/*    */     }
/* 34 */     return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void noPermission() {
/* 39 */     this.plugin.sendMessage(this.player, "You don't have permission to do this.");
/*    */   }
/*    */ 
/*    */   
/*    */   public void sendMessage(String message) {
/* 44 */     this.plugin.sendMessage(this.player, message);
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\command\AnCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */