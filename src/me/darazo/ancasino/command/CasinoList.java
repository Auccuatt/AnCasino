/*    */ package me.darazo.ancasino.command;
/*    */ 
/*    */ import me.darazo.ancasino.AnCasino;
/*    */ import me.darazo.ancasino.slot.SlotMachine;
/*    */ import me.darazo.ancasino.slot.Type;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CasinoList
/*    */   extends AnCommand
/*    */ {
/*    */   public CasinoList(AnCasino plugin, String[] args, Player player) {
/* 13 */     super(plugin, args, player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean process() {
/* 19 */     if (!this.plugin.permission.canCreate(this.player).booleanValue()) {
/* 20 */       noPermission();
/* 21 */       return Boolean.valueOf(true);
/*    */     } 
/*    */ 
/*    */     
/* 25 */     if (this.args.length == 2) {
/*    */ 
/*    */       
/* 28 */       if (this.args[1].equalsIgnoreCase("slots"))
/*    */       {
/* 30 */         sendMessage("Registered slot machines:");
/* 31 */         for (SlotMachine slot : this.plugin.slotData.getSlots())
/*    */         {
/*    */           
/* 34 */           if (isOwner(slot).booleanValue()) {
/* 35 */             Block b = slot.getController();
/* 36 */             String c = String.valueOf(b.getX()) + "," + b.getY() + "," + b.getZ();
/* 37 */             sendMessage(String.valueOf(slot.getName()) + " - type: " + slot.getType() + " - owner: " + slot.getOwner() + " - managed: " + slot.isManaged().toString() + " @ " + c);
/*    */           }
/*    */         
/*    */         }
/*    */       
/*    */       }
/* 43 */       else if (this.args[1].equalsIgnoreCase("types"))
/*    */       {
/* 45 */         sendMessage("Available types:");
/* 46 */         for (Type type : this.plugin.typeData.getTypes())
/*    */         {
/*    */           
/* 49 */           if (this.plugin.permission.canCreate(this.player, type).booleanValue()) {
/* 50 */             sendMessage(String.valueOf(type.getName()) + " - cost: " + type.getCost());
/*    */           }
/*    */         }
/*    */       
/*    */       }
/*    */       else
/*    */       {
/* 57 */         sendMessage("Usage:");
/* 58 */         sendMessage("/casino list slots - List slot machines");
/* 59 */         sendMessage("/casino list types - List types");
/*    */       }
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 65 */       sendMessage("Usage:");
/* 66 */       sendMessage("/casino list slots - List slot machines");
/* 67 */       sendMessage("/casino list types - List types");
/*    */     } 
/* 69 */     return Boolean.valueOf(true);
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\command\CasinoList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */