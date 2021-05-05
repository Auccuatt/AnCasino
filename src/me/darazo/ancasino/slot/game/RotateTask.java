 package me.darazo.ancasino.slot.game;
 
 import java.util.ArrayList;
 import java.util.Random;

import org.bukkit.Material;
 import org.bukkit.block.Block;
 
 
 
 public class RotateTask
   implements Runnable
 {
   private Game game;
   private Integer i;
   
   public RotateTask(Game game, Integer i) {
     this.game = game;
     this.i = i;
   }
 
 
 
   
   public void run() {
     rotateColumn(this.i);
   }
 
 
   
   private void rotateColumn(Integer column) {
     ArrayList<Block> blocks = this.game.getSlot().getBlocks();
     
     ArrayList<Material> last = new ArrayList<Material>();
     last.add(((blocks.get(column.intValue() + 6))).getType());
     last.add(((blocks.get(column.intValue() + 3))).getType());
 
     
     Material id = getNext();
     while (id == (last.get(0))) {
       id = getNext();
     }
     
     (blocks.get(column.intValue() + 6)).setType(id);
     (blocks.get(column.intValue() + 3)).setType(last.get(0));
     (blocks.get(column.intValue())).setType((last.get(1)));
   }
 
 
 
   
   private Material getNext() {
     ArrayList<String> reel = this.game.getType().getReel();
     
     Random generator = new Random();
     int id = generator.nextInt(reel.size());
     return Material.getMaterial(reel.get(id));
   }
 }


