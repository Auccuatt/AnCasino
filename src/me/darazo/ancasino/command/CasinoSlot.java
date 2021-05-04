/*     */ package me.darazo.ancasino.command;
/*     */ 
/*     */ import me.darazo.ancasino.AnCasino;
/*     */ import me.darazo.ancasino.slot.SlotMachine;
/*     */ import me.darazo.ancasino.slot.Type;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ public class CasinoSlot
/*     */   extends AnCommand
/*     */ {
/*     */   private SlotMachine slot;
/*     */   private Type type;
/*     */   
/*     */   public CasinoSlot(AnCasino plugin, String[] args, Player player) {
/*  16 */     super(plugin, args, player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean process() {
/*  22 */     if (!this.plugin.permission.isAdmin(this.player).booleanValue() && !this.plugin.permission.canCreate(this.player).booleanValue()) {
/*  23 */       noPermission();
/*  24 */       return Boolean.valueOf(true);
/*     */     } 
/*     */ 
/*     */     
/*  28 */     if (this.args.length < 3) {
/*  29 */       usage();
/*  30 */       return Boolean.valueOf(true);
/*     */     } 
/*     */ 
/*     */     
/*  34 */     if (!this.plugin.slotData.isSlot(this.args[1]).booleanValue()) {
/*  35 */       sendMessage("Invalid slot machine " + this.args[1]);
/*  36 */       return Boolean.valueOf(true);
/*     */     } 
/*     */     
/*  39 */     this.slot = this.plugin.slotData.getSlot(this.args[1]);
/*     */ 
/*     */ 
/*     */     
/*  43 */     if (!isOwner(this.slot).booleanValue()) {
/*  44 */       sendMessage("You do not own this slot machine.");
/*  45 */       return Boolean.valueOf(true);
/*     */     } 
/*     */ 
/*     */     
/*  49 */     if (this.args[2].equalsIgnoreCase("type") && this.args.length == 4) {
/*     */       
/*  51 */       if (this.plugin.typeData.isType(this.args[3]).booleanValue()) {
/*  52 */         this.type = this.plugin.typeData.getType(this.args[3]);
/*     */         
/*  54 */         if (this.plugin.permission.canCreate(this.player, this.type).booleanValue()) {
/*  56 */           Double cost = this.type.getCreateCost();
/*     */           
/*  58 */           if (this.plugin.economy.has(this.player, cost.doubleValue())) {
/*  59 */             this.plugin.economy.withdrawPlayer(this.player, cost.doubleValue());
/*  60 */             this.slot.setType(this.type.getName());
/*     */           } else {
/*     */             
/*  63 */             sendMessage("You don't have enough money. Cost: " + cost);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/*  68 */           noPermission();
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  73 */         sendMessage("Invalid type " + this.args[3]);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*  78 */     else if (this.args[2].equalsIgnoreCase("setmanaged") && this.args.length == 3) {
/*     */       
/*  80 */       if (this.plugin.permission.canManage(this.player).booleanValue()) {
/*     */         
/*  82 */         if (this.slot.isManaged().booleanValue()) {
/*  83 */           this.slot.setManaged(Boolean.valueOf(false));
/*  84 */           sendMessage(String.valueOf(this.slot.getName()) + " is now unmanaged.");
/*     */         } else {
/*     */           
/*  87 */           this.slot.setManaged(Boolean.valueOf(true));
/*  88 */           sendMessage(String.valueOf(this.slot.getName()) + " is now managed.");
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  93 */         noPermission();
/*     */       
/*     */       }
/*     */     
/*     */     }
/*  98 */     else if (this.args[2].equalsIgnoreCase("setcontroller") && this.args.length == 3) {
/*     */       
/* 100 */       this.plugin.slotData.togglePlacingController(this.player, this.slot);
/* 101 */       sendMessage("Punch a new block to serve as this slot machine's controller.");
/*     */     
/*     */     }
/* 104 */     else if (this.args[2].equalsIgnoreCase("deposit") && this.args.length == 4) {
/*     */       
/* 106 */       Double amount = Double.valueOf(Double.parseDouble(this.args[3]));
/*     */ 
/*     */       
/* 109 */       if (!this.plugin.economy.has(this.player, amount.doubleValue())) {
/* 110 */         sendMessage("You can't afford to deposit this much.");
/*     */       }
/*     */       else {
/*     */         
/* 114 */         if (this.slot.getFunds().doubleValue() + amount.doubleValue() > this.plugin.typeData.getMaxPrize(this.slot.getType()).doubleValue()) {
/* 115 */           this.slot.setEnabled(Boolean.valueOf(true));
/* 116 */           sendMessage("Sufficient funds. Slot machine enabled.");
/*     */         } 
/*     */         
/* 119 */         this.slot.deposit(amount);
/* 120 */         this.plugin.economy.withdrawPlayer(this.player, amount.doubleValue());
/* 121 */         sendMessage("Deposited " + amount + " to " + this.slot.getName());
/*     */       }
/*     */     
/*     */     }
/* 125 */     else if (this.args[2].equalsIgnoreCase("withdraw") && this.args.length == 4) {
/*     */       
/* 127 */       Double amount = Double.valueOf(Double.parseDouble(this.args[3]));
/*     */ 
/*     */       
/* 130 */       if (this.slot.getFunds().doubleValue() < amount.doubleValue()) {
/* 131 */         sendMessage("Not enough funds in " + this.slot.getName() + ". Withdrawing all available funds.");
/* 132 */         amount = this.slot.getFunds();
/*     */       } 
/*     */ 
/*     */       
/* 136 */       if (this.slot.getFunds().doubleValue() - amount.doubleValue() < this.plugin.typeData.getMaxPrize(this.slot.getType()).doubleValue()) {
/* 137 */         this.slot.setEnabled(Boolean.valueOf(false));
/*     */       }
/*     */       
/* 140 */       this.slot.withdraw(amount);
/* 141 */       this.plugin.economy.depositPlayer(this.player, amount.doubleValue());
/* 142 */       sendMessage("Withdrew " + amount + " from " + this.slot.getName());
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 147 */       usage();
/* 148 */       return Boolean.valueOf(true);
/*     */     } 
/*     */ 
/*     */     
/* 152 */     this.plugin.slotData.addSlot(this.slot);
/* 153 */     this.plugin.slotData.saveSlot(this.slot);
/* 154 */     this.plugin.configData.saveSlots();
/*     */     
/* 156 */     return Boolean.valueOf(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void usage() {
/* 162 */     String[] messages = {
/* 163 */         "Usage:", 
/* 164 */         "/casino slot <slot> type <type>", 
/* 165 */         "/casino slot <slot> setmanaged", 
/* 166 */         "/casino slot <slot> setcontroller", 
/* 167 */         "/casino slot <slot> deposit <amount>", 
/* 168 */         "/casino slot <slot> withdraw <amount>" }; byte b; int i;
/*     */     String[] arrayOfString1;
/* 170 */     for (i = (arrayOfString1 = messages).length, b = 0; b < i; ) { String m = arrayOfString1[b];
/* 171 */       sendMessage(m);
/*     */       b++; }
/*     */   
/*     */   }
/*     */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\command\CasinoSlot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */