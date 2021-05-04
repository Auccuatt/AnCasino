/*    */ package me.darazo.ancasino.command;
/*    */ 
/*    */ import me.darazo.ancasino.AnCasino;
/*    */ import me.darazo.ancasino.slot.SlotMachine;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class CasinoAdd
/*    */   extends AnCommand
/*    */ {
/*    */   private String name;
/*    */   private String type;
/*    */   private String owner;
/*    */   private String world;
/*    */   
/*    */   public CasinoAdd(AnCasino plugin, String[] args, Player player) {
/* 17 */     super(plugin, args, player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean process() {
/* 23 */     if (!this.plugin.permission.canCreate(this.player).booleanValue()) {
/* 24 */       noPermission();
/* 25 */       return Boolean.valueOf(true);
/*    */     } 
/*    */ 
/*    */     
/* 29 */     if (this.args.length >= 2 && this.args.length <= 3) {
/* 30 */       this.owner = this.player.getName();
/*    */ 
/*    */       
/* 33 */       if (!this.plugin.slotData.isSlot(this.args[1]).booleanValue())
/*    */       {
/* 35 */         this.name = this.args[1];
/*    */ 
/*    */         
/* 38 */         if (this.args.length < 3) {
/*    */           
/* 40 */           this.type = "default";
/*    */         
/*    */         }
/* 43 */         else if (this.plugin.typeData.isType(this.args[2]).booleanValue()) {
/* 44 */           String typeName = this.args[2];
/*    */ 
/*    */           
/* 47 */           if (this.plugin.permission.canCreate(this.player, typeName).booleanValue()) {
/* 48 */             this.type = typeName;
/*    */           } else {
/*    */             
/* 51 */             sendMessage("Invalid type " + typeName);
/* 52 */             return Boolean.valueOf(true);
/*    */           }
/*    */         
/*    */         }
/*    */         else {
/*    */           
/* 58 */           sendMessage("Invalid type " + this.args[2]);
/* 59 */           return Boolean.valueOf(true);
/*    */         } 
/*    */ 
/*    */         
/* 63 */         Double createCost = this.plugin.typeData.getType(this.type).getCreateCost();
/* 64 */         if (this.plugin.economy.has(this.player, createCost.doubleValue())) {
/* 65 */           this.plugin.economy.withdrawPlayer(this.player, createCost.doubleValue());
/*    */         } else {
/*    */           
/* 68 */           sendMessage("You can't afford to create this slot machine. Cost: " + createCost);
/* 69 */           return Boolean.valueOf(true);
/*    */         } 
/*    */ 
/*    */         
/* 73 */         this.world = this.player.getWorld().getName();
/* 74 */         SlotMachine slot = new SlotMachine(this.name, this.type, this.owner, this.world, Boolean.valueOf(false));
/* 75 */         this.plugin.slotData.toggleCreatingSlots(this.player, slot);
/* 76 */         sendMessage("Punch a block to serve as the base for this slot machine.");
/*    */       
/*    */       }
/*    */       else
/*    */       {
/* 81 */         sendMessage("Slot machine " + this.args[1] + " already registered.");
/*    */       }
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 87 */       sendMessage("Usage:");
/* 88 */       sendMessage("/casino add <name> (<type>)");
/*    */     } 
/* 90 */     return Boolean.valueOf(true);
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\command\CasinoAdd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */