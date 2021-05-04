/*    */ package me.darazo.ancasino.slot.game;
/*    */ 
/*    */ import org.bukkit.Instrument;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Note;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StopRotateTask
/*    */   implements Runnable
/*    */ {
/*    */   private Game game;
/*    */   private Integer task;
/*    */   
/*    */   public StopRotateTask(Game game, Integer task) {
/* 16 */     this.game = game;
/* 17 */     this.task = task;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 25 */     Location location = this.game.getSlot().getController().getLocation();
/* 26 */     this.game.scheduler.cancelTask(this.task.intValue());
/* 27 */     this.game.getPlayer().playNote(location, Instrument.PIANO, new Note((byte)0, Note.Tone.C, false));
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\slot\game\StopRotateTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */