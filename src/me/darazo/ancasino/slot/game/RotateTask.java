/*    */ package me.darazo.ancasino.slot.game;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;

import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RotateTask
/*    */   implements Runnable
/*    */ {
/*    */   private Game game;
/*    */   private Integer i;
/*    */   
/*    */   public RotateTask(Game game, Integer i) {
/* 16 */     this.game = game;
/* 17 */     this.i = i;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 24 */     rotateColumn(this.i);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void rotateColumn(Integer column) {
/* 30 */     ArrayList<Block> blocks = this.game.getSlot().getBlocks();
/*    */     
/* 32 */     ArrayList<Material> last = new ArrayList<Material>();
/* 33 */     last.add(((blocks.get(column.intValue() + 6))).getType());
/* 34 */     last.add(((blocks.get(column.intValue() + 3))).getType());
/*    */ 
/*    */     
/* 37 */     Material id = getNext();
/* 38 */     while (id == (last.get(0))) {
/* 39 */       id = getNext();
/*    */     }
/*    */     
/* 42 */     (blocks.get(column.intValue() + 6)).setType(id);
/* 43 */     (blocks.get(column.intValue() + 3)).setType(last.get(0));
/* 44 */     (blocks.get(column.intValue())).setType((last.get(1)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Material getNext() {
/* 51 */     ArrayList<String> reel = this.game.getType().getReel();
/*    */     
/* 53 */     Random generator = new Random();
/* 54 */     int id = generator.nextInt(reel.size());
/* 55 */     return Material.getMaterial(reel.get(id));
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\slot\game\RotateTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */