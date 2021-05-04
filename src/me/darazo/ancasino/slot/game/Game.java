/*    */ package me.darazo.ancasino.slot.game;
/*    */ 
/*    */ import me.darazo.ancasino.AnCasino;
/*    */ import me.darazo.ancasino.slot.SlotMachine;
/*    */ import me.darazo.ancasino.slot.Type;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scheduler.BukkitScheduler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Game
/*    */ {
/*    */   protected AnCasino plugin;
/*    */   protected BukkitScheduler scheduler;
/*    */   private SlotMachine slot;
/*    */   private Player player;
/*    */   
/*    */   public Game(SlotMachine slot, Player player, AnCasino plugin) {
/* 21 */     this.slot = slot;
/* 22 */     this.player = player;
/* 23 */     this.plugin = plugin;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public SlotMachine getSlot() {
/* 29 */     return this.slot;
/*    */   }
/*    */ 
/*    */   
/*    */   public Type getType() {
/* 34 */     return this.plugin.typeData.getType(this.slot.getType());
/*    */   }
/*    */ 
/*    */   
/*    */   public Player getPlayer() {
/* 39 */     return this.player;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void play() {
/* 45 */     this.scheduler = this.plugin.getServer().getScheduler();
/* 46 */     Integer[] task = new Integer[3];
/* 47 */     Long[] delay = { Long.valueOf(60L), Long.valueOf(80L), Long.valueOf(100L) };
/*    */     
/* 49 */     if (!this.slot.isEnabled().booleanValue()) {
/* 50 */       this.plugin.sendMessage(this.player, "This slot machine is currently disabled. Deposit more funds to enable.");
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 55 */     Double cost = getType().getCost();
/* 56 */     this.plugin.economy.withdrawPlayer(this.player, cost.doubleValue());
/* 57 */     if (this.slot.isManaged().booleanValue()) {
/* 58 */       this.slot.deposit(cost);
/*    */     }
/*    */ 
/*    */     
/* 62 */     this.slot.toggleBusy();
/* 63 */     this.plugin.sendMessage(this.player, (String)getType().getMessages().get("start"));
/*    */ 
/*    */     
/* 66 */     for (Integer i = Integer.valueOf(0); i.intValue() < 3; i = Integer.valueOf(i.intValue() + 1)) {
/* 67 */       task[i.intValue()] = Integer.valueOf(this.scheduler.scheduleSyncRepeatingTask((Plugin)this.plugin, new RotateTask(this, i), 0L, 6L));
/* 68 */       this.scheduler.scheduleSyncDelayedTask((Plugin)this.plugin, new StopRotateTask(this, task[i.intValue()]), delay[2 - i.intValue()].longValue());
/*    */     } 
/*    */ 
/*    */     
/* 72 */     this.scheduler.scheduleSyncDelayedTask((Plugin)this.plugin, new ResultsTask(this), delay[2].longValue());
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\slot\game\Game.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */