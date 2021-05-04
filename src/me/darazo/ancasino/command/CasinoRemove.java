/*    */ package me.darazo.ancasino.command;
/*    */ 
/*    */ import me.darazo.ancasino.AnCasino;
/*    */ import me.darazo.ancasino.slot.SlotMachine;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class CasinoRemove
/*    */   extends AnCommand
/*    */ {
/*    */   public CasinoRemove(AnCasino plugin, String[] args, Player player) {
/* 12 */     super(plugin, args, player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean process() {
/* 18 */     if (!this.plugin.permission.canCreate(this.player).booleanValue()) {
/* 19 */       noPermission();
/* 20 */       return Boolean.valueOf(true);
/*    */     } 
/*    */ 
/*    */     
/* 24 */     if (this.args.length == 2) {
/*    */ 
/*    */       
/* 27 */       if (this.plugin.slotData.isSlot(this.args[1]).booleanValue()) {
/* 28 */         SlotMachine slot = this.plugin.slotData.getSlot(this.args[1]);
/*    */ 
/*    */         
/* 31 */         if (isOwner(slot).booleanValue()) {
/* 32 */           this.plugin.slotData.removeSlot(slot);
/* 33 */           sendMessage("Slot machine removed.");
/*    */         }
/*    */         else {
/*    */           
/* 37 */           this.plugin.sendMessage(this.player, "You do not own this slot machine.");
/*    */         }
/*    */       
/*    */       }
/*    */       else {
/*    */         
/* 43 */         sendMessage("Invalid slot machine.");
/*    */       }
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 49 */       sendMessage("Usage:");
/* 50 */       sendMessage("/casino remove <name>");
/*    */     } 
/* 52 */     return Boolean.valueOf(true);
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\command\CasinoRemove.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */