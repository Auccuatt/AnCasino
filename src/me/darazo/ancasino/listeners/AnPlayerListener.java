/*     */ package me.darazo.ancasino.listeners;
/*     */ 
/*     */ import me.darazo.ancasino.AnCasino;
/*     */ import me.darazo.ancasino.slot.SlotMachine;
/*     */ import me.darazo.ancasino.slot.Type;
/*     */ import me.darazo.ancasino.slot.game.Game;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ 
/*     */ public class AnPlayerListener
/*     */   implements Listener
/*     */ {
/*     */   protected AnCasino plugin;
/*     */   
/*     */   public AnPlayerListener(AnCasino plugin) {
/*  23 */     this.plugin = plugin;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOW)
/*     */   public void onPlayerInteract(PlayerInteractEvent event) {
/*  30 */     if (event.getAction() != Action.LEFT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  35 */     if (this.plugin.isEnabled()) {
/*  36 */       Player player = event.getPlayer();
/*  37 */       Block b = event.getClickedBlock();
/*     */ 
/*     */       
/*  40 */       if (b.getType() == Material.NOTE_BLOCK)
/*     */       {
/*     */         
/*  43 */         for (SlotMachine slot : this.plugin.slotData.getSlots()) {
/*     */ 
/*     */           
/*  46 */           if (b.equals(slot.getController())) {
/*  47 */             Type type = this.plugin.typeData.getType(slot.getType());
/*  48 */             Double cost = type.getCost();
/*     */ 
/*     */             
/*  51 */             if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
/*     */ 
/*     */               
/*  54 */               if (this.plugin.permission.canUse(player, type).booleanValue()) {
/*     */ 
/*     */                 
/*  57 */                 if (this.plugin.economy.has(player, cost.doubleValue())) {
/*     */ 
/*     */                   
/*  60 */                   if (!slot.isBusy().booleanValue()) {
/*     */ 
/*     */                     
/*  63 */                     Game game = new Game(slot, player, this.plugin);
/*  64 */                     game.play();
/*     */ 
/*     */                     
/*     */                     return;
/*     */                   } 
/*     */ 
/*     */                   
/*  71 */                   this.plugin.sendMessage(player, (String)type.getMessages().get("inUse"));
/*     */ 
/*     */                   
/*     */                   break;
/*     */                 } 
/*     */                 
/*  77 */                 this.plugin.sendMessage(player, (String)type.getMessages().get("noFunds"));
/*     */ 
/*     */                 
/*     */                 break;
/*     */               } 
/*     */               
/*  83 */               this.plugin.sendMessage(player, (String)type.getMessages().get("noPermission"));
/*     */ 
/*     */               
/*     */               break;
/*     */             } 
/*     */             
/*  89 */             if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
/*     */               
/*  91 */               event.setCancelled(true);
/*  92 */               if (this.plugin.permission.isOwner(player, slot).booleanValue()) {
/*  93 */                 this.plugin.sendMessage(player, String.valueOf(slot.getName()) + ":");
/*  94 */                 this.plugin.sendMessage(player, "Type: " + slot.getType());
/*  95 */                 this.plugin.sendMessage(player, "Owner: " + slot.getOwner());
/*  96 */                 this.plugin.sendMessage(player, "Managed: " + slot.isManaged().toString());
/*     */                 
/*  98 */                 if (slot.isManaged().booleanValue()) {
/*  99 */                   this.plugin.sendMessage(player, "Enabled: " + slot.isEnabled().toString());
/* 100 */                   this.plugin.sendMessage(player, "Funds: " + slot.getFunds());
/* 101 */                   this.plugin.sendMessage(player, "Funds required: " + this.plugin.typeData.getMaxPrize(slot.getType()));
/*     */                 } 
/*     */                 break;
/*     */               } 
/* 105 */               for (String message : this.plugin.typeData.getType(slot.getType()).getHelpMessages()) {
/* 106 */                 this.plugin.sendMessage(player, message);
/*     */               }
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 119 */       if (event.getAction() == Action.LEFT_CLICK_BLOCK && this.plugin.slotData.isCreatingSlots(player).booleanValue()) {
/*     */         
/* 121 */         BlockFace face = event.getBlockFace();
/*     */         
/* 123 */         if (face != BlockFace.DOWN && face != BlockFace.UP) {
/* 124 */           SlotMachine slot = (SlotMachine)this.plugin.slotData.creatingSlots.get(player);
/* 125 */           this.plugin.slotData.createReel(player, face, slot);
/*     */           
/* 127 */           this.plugin.slotData.toggleCreatingSlots(player, slot);
/* 128 */           this.plugin.slotData.togglePlacingController(player, slot);
/* 129 */           this.plugin.sendMessage(player, "Punch a block to serve as the controller for this slot machine.");
/*     */         } else {
/*     */           
/* 132 */           this.plugin.sendMessage(player, "Only sides of blocks are valid targets for this operation.");
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 137 */       else if (event.getAction() == Action.LEFT_CLICK_BLOCK && this.plugin.slotData.isPlacingController(player).booleanValue()) {
/*     */         
/* 139 */         SlotMachine slot = (SlotMachine)this.plugin.slotData.placingController.get(player);
/* 140 */         slot.setController(b);
/* 141 */         this.plugin.slotData.togglePlacingController(player, slot);
/* 142 */         this.plugin.slotData.addSlot(slot);
/* 143 */         this.plugin.slotData.saveSlot(slot);
/* 144 */         this.plugin.sendMessage(player, "Slot machine set up successfully!");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\listeners\AnPlayerListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */