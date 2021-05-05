 package me.darazo.ancasino.slot.game;
 
 import org.bukkit.Instrument;
 import org.bukkit.Location;
 import org.bukkit.Note;
 
 
 
 public class StopRotateTask
   implements Runnable
 {
   private Game game;
   private Integer task;
   
   public StopRotateTask(Game game, Integer task) {
     this.game = game;
     this.task = task;
   }
 
 
 
 
   
   public void run() {
     Location location = this.game.getSlot().getController().getLocation();
     this.game.scheduler.cancelTask(this.task.intValue());
     this.game.getPlayer().playNote(location, Instrument.PIANO, new Note((byte)0, Note.Tone.C, false));
   }
 }


