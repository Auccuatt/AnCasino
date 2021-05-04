/*    */ package me.darazo.ancasino.command;
/*    */ 
/*    */ import me.darazo.ancasino.AnCasino;
/*    */ import me.darazo.ancasino.slot.SlotMachine;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class CasinoAddManaged
/*    */   extends AnCommand
/*    */ {
/*    */   private String name;
/*    */   private String type;
/*    */   private String owner;
/*    */   private String world;
/*    */   
/*    */   public CasinoAddManaged(AnCasino plugin, String[] args, Player player) {
/* 17 */     super(plugin, args, player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean process() {
/* 23 */     if (!this.plugin.permission.canCreate(this.player).booleanValue() || !this.plugin.permission.canManage(this.player).booleanValue()) {
/* 24 */       noPermission();
/* 25 */       return Boolean.valueOf(true);
/*    */     } 
/*    */ 
/*    */     
/* 29 */     if (this.args.length >= 2 && this.args.length <= 3) {
/*    */ 
/*    */       
/* 32 */       if (!this.plugin.slotData.isSlot(this.args[1]).booleanValue())
/*    */       {
/* 34 */         this.name = this.args[1];
/*    */ 
/*    */         
/* 37 */         if (this.args.length < 3) {
/*    */           
/* 39 */           this.type = "default";
/*    */         
/*    */         }
/* 42 */         else if (this.plugin.typeData.isType(this.args[2]).booleanValue()) {
/* 43 */           String typeName = this.args[2];
/*    */ 
/*    */           
/* 46 */           if (this.plugin.permission.canCreate(this.player, typeName).booleanValue()) {
/* 47 */             this.type = typeName;
/* 48 */             this.owner = this.player.getName();
/*    */           } else {
/*    */             
/* 51 */             this.plugin.sendMessage(this.player, "Invalid type " + typeName);
/* 52 */             return Boolean.valueOf(true);
/*    */           }
/*    */         
/*    */         }
/*    */         else {
/*    */           
/* 58 */           this.plugin.sendMessage(this.player, "Invalid type " + this.args[2]);
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
/* 74 */         SlotMachine slot = new SlotMachine(this.name, this.type, this.owner, this.world, Boolean.valueOf(true));
/* 75 */         this.plugin.slotData.toggleCreatingSlots(this.player, slot);
/* 76 */         this.plugin.sendMessage(this.player, "Punch a block to serve as the base for this slot machine.");
/*    */       
/*    */       }
/*    */       else
/*    */       {
/* 81 */         this.plugin.sendMessage(this.player, "Slot machine " + this.args[1] + " already registered.");
/*    */       }
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 87 */       this.plugin.sendMessage(this.player, "Usage:");
/* 88 */       this.plugin.sendMessage(this.player, "/casino add <name> (<type>)");
/*    */     } 
/* 90 */     return Boolean.valueOf(true);
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\command\CasinoAddManaged.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */