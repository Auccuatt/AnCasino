/*     */ package me.darazo.ancasino.slot.game;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import me.darazo.ancasino.slot.Reward;
/*     */ import me.darazo.ancasino.slot.SlotMachine;
/*     */ import me.darazo.ancasino.slot.Type;
/*     */ import me.darazo.ancasino.util.Stat;
/*     */ import org.bukkit.Instrument;
/*     */ import org.bukkit.Location;
import org.bukkit.Material;
/*     */ import org.bukkit.Note;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResultsTask
/*     */   implements Runnable
/*     */ {
/*     */   private Game game;
/*     */   
/*     */   public ResultsTask(Game game) {
/*  26 */     this.game = game;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  32 */     Type type = this.game.getType();
/*  33 */     Player player = this.game.getPlayer();
/*  34 */     String name = type.getName();
/*  35 */     Double cost = type.getCost();
/*  36 */     Double won = Double.valueOf(0.0D);
/*     */ 
/*     */     
/*  39 */     ArrayList<Reward> results = getResults();
/*     */     
/*  41 */     if (!results.isEmpty()) {
/*     */ 
/*     */       
/*  44 */       playVictoryMusic();
/*  45 */       for (Reward reward : results) {
/*  46 */         this.game.plugin.rewardData.send(player, reward);
/*  47 */         won = Double.valueOf(won.doubleValue() + reward.getMoney().doubleValue());
/*     */       } 
/*     */ 
/*     */       
/*  51 */       SlotMachine slot = this.game.getSlot();
/*  52 */       if (slot.isManaged().booleanValue())
/*     */       {
/*  54 */         slot.withdraw(won);
/*  55 */         Double max = this.game.plugin.typeData.getMaxPrize(type.getName());
/*  56 */         if (slot.getFunds().doubleValue() < max.doubleValue()) {
/*  57 */           slot.setEnabled(Boolean.valueOf(false));
/*     */         }
/*     */         
/*  60 */         this.game.plugin.slotData.saveSlot(slot);
/*  61 */         this.game.plugin.configData.saveSlots();
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  67 */       this.game.plugin.sendMessage(player, (String)type.getMessages().get("noWin"));
/*     */     } 
/*     */ 
/*     */     
/*  71 */     if (this.game.plugin.configData.trackStats.booleanValue()) {
/*  72 */       Stat stat; if (this.game.plugin.statsData.isStat(name).booleanValue()) {
/*  73 */         stat = this.game.plugin.statsData.getStat(name);
/*  74 */         stat.add(won, cost);
/*     */       } else {
/*     */         
/*  77 */         stat = new Stat(name, Integer.valueOf(1), won, cost);
/*     */       } 
/*  79 */       this.game.plugin.statsData.addStat(stat);
/*  80 */       if (!results.isEmpty()) {
/*  81 */         this.game.plugin.configData.saveStats();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  86 */     this.game.getSlot().toggleBusy();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList<Reward> getResults() {
/*  93 */     ArrayList<Reward> results = new ArrayList<Reward>();
/*  94 */     ArrayList<Block> blocks = this.game.getSlot().getBlocks();
/*     */ 
/*     */     
/*  97 */     for (int i = 0; i < 5; i++) {
/*     */       
/*  99 */       ArrayList<Material> currentId = new ArrayList<Material>();
/* 100 */       List<Block> current = null;
/* 101 */       if (i < 3) {
/* 102 */         int start = 0 + 3 * i;
/* 103 */         int end = 3 + 3 * i;
/* 104 */         current = blocks.subList(start, end);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 109 */       else if (this.game.plugin.configData.allowDiagonals.booleanValue()) {
/* 110 */         current = new ArrayList<Block>();
/* 111 */         for (int j = 0; j < 3; j++) {
/* 112 */           if (i == 3) {
/* 113 */             current.add(blocks.get(j * 4));
/*     */           } else {
/*     */             
/* 116 */             current.add(blocks.get(2 + 2 * j));
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 127 */       for (Block b : current) {
/* 128 */         currentId.add(b.getType());
/*     */       }
/*     */ 
/*     */       
/* 132 */       Set<Material> currentSet = new HashSet<Material>(currentId);
/* 133 */       if (currentSet.size() == 1) {
/*     */         
/* 135 */         Material prize = ((Block)current.get(0)).getType();
/* 136 */         Reward reward = this.game.getType().getReward(prize.toString());
/* 137 */         results.add(reward);
/*     */       } 
/*     */     } 
/*     */     
/* 141 */     return results;
/*     */   }
/*     */ 
/*     */   
/*     */   private void playVictoryMusic() {
/* 146 */     final Player p = this.game.getPlayer();
/*     */     
/* 148 */     final Location loc = this.game.getSlot().getController().getLocation();
/*     */     
/* 150 */     this.game.scheduler.scheduleSyncDelayedTask((Plugin)this.game.plugin, new Runnable() {
/*     */           public void run() {
/* 152 */             p.playNote(loc, Instrument.PIANO, new Note((byte)0, Note.Tone.C, true));
/*     */           }
/* 154 */         },10L);
/* 155 */     this.game.scheduler.scheduleSyncDelayedTask((Plugin)this.game.plugin, new Runnable() {
/*     */           public void run() {
/* 157 */             p.playNote(loc, Instrument.PIANO, new Note((byte)1, Note.Tone.F, true));
/*     */           }
/* 159 */         },13L);
/* 160 */     this.game.scheduler.scheduleSyncDelayedTask((Plugin)this.game.plugin, new Runnable() {
/*     */           public void run() {
/* 162 */             p.playNote(loc, Instrument.PIANO, new Note((byte)1, Note.Tone.A, true));
/*     */           }
/* 164 */         },16L);
/*     */     
/* 166 */     this.game.scheduler.scheduleSyncDelayedTask((Plugin)this.game.plugin, new Runnable() {
/*     */           public void run() {
/* 168 */             p.playNote(loc, Instrument.PIANO, new Note((byte)0, Note.Tone.C, true));
/*     */           }
/* 170 */         },21L);
/* 171 */     this.game.scheduler.scheduleSyncDelayedTask((Plugin)this.game.plugin, new Runnable() {
/*     */           public void run() {
/* 173 */             p.playNote(loc, Instrument.PIANO, new Note((byte)1, Note.Tone.F, true));
/*     */           }
/* 175 */         },24L);
/* 176 */     this.game.scheduler.scheduleSyncDelayedTask((Plugin)this.game.plugin, new Runnable() {
/*     */           public void run() {
/* 178 */             p.playNote(loc, Instrument.PIANO, new Note((byte)1, Note.Tone.A, true));
/*     */           }
/* 180 */         },27L);
/*     */   }
/*     */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\slot\game\ResultsTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */