/*    */ package me.darazo.ancasino.listeners;
/*    */ 
/*    */ import me.darazo.ancasino.AnCasino;
/*    */ import me.darazo.ancasino.slot.SlotMachine;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ 
/*    */ public class AnBlockListener
/*    */   implements Listener
/*    */ {
/*    */   protected AnCasino plugin;
/*    */   
/*    */   public AnBlockListener(AnCasino plugin) {
/* 17 */     this.plugin = plugin;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @EventHandler(priority = EventPriority.LOW)
/*    */   public void onBlockBreak(BlockBreakEvent event) {
/* 26 */     if (this.plugin.isEnabled())
/*    */     {
/*    */       
/* 29 */       if (this.plugin.configData.protection.booleanValue()) {
/*    */         
/* 31 */         Block b = event.getBlock();
/*    */ 
/*    */         
/* 34 */         for (SlotMachine slot : this.plugin.slotData.getSlots()) {
/*    */           
/* 36 */           for (Block block : slot.getBlocks()) {
/*    */ 
/*    */             
/* 39 */             if (b.equals(block)) {
/* 40 */               event.setCancelled(true);
/*    */               
/*    */               return;
/*    */             } 
/*    */           } 
/*    */           
/* 46 */           Block current = slot.getController();
/* 47 */           if (b.equals(current)) {
/* 48 */             event.setCancelled(true);
/*    */             return;
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\listeners\AnBlockListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */