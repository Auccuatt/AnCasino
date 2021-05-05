 package me.darazo.ancasino.listeners;
 
 import me.darazo.ancasino.AnCasino;
 import me.darazo.ancasino.slot.SlotMachine;
 import org.bukkit.block.Block;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.EventPriority;
 import org.bukkit.event.Listener;
 import org.bukkit.event.block.BlockBreakEvent;
 
 public class AnBlockListener
   implements Listener
 {
   protected AnCasino plugin;
   
   public AnBlockListener(AnCasino plugin) {
     this.plugin = plugin;
   }
 
 
 
 
   
   @EventHandler(priority = EventPriority.LOW)
   public void onBlockBreak(BlockBreakEvent event) {
     if (this.plugin.isEnabled())
     {
       
       if (this.plugin.configData.protection.booleanValue()) {
         
         Block b = event.getBlock();
 
         
         for (SlotMachine slot : this.plugin.slotData.getSlots()) {
           
           for (Block block : slot.getBlocks()) {
 
             
             if (b.equals(block)) {
               event.setCancelled(true);
               
               return;
             } 
           } 
           
           Block current = slot.getController();
           if (b.equals(current)) {
             event.setCancelled(true);
             return;
           } 
         } 
       } 
     }
   }
 }


