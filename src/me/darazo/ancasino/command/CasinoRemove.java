 package me.darazo.ancasino.command;
 
 import me.darazo.ancasino.AnCasino;
 import me.darazo.ancasino.slot.SlotMachine;
 import org.bukkit.entity.Player;
 
 
 public class CasinoRemove
   extends AnCommand
 {
   public CasinoRemove(AnCasino plugin, String[] args, Player player) {
     super(plugin, args, player);
   }
 
 
   
   public Boolean process() {
     if (!this.plugin.permission.canCreate(this.player).booleanValue()) {
       noPermission();
       return Boolean.valueOf(true);
     } 
 
     
     if (this.args.length == 2) {
 
       
       if (this.plugin.slotData.isSlot(this.args[1]).booleanValue()) {
         SlotMachine slot = this.plugin.slotData.getSlot(this.args[1]);
 
         
         if (isOwner(slot).booleanValue()) {
           this.plugin.slotData.removeSlot(slot);
           sendMessage("Slot machine removed.");
         }
         else {
           
           this.plugin.sendMessage(this.player, "You do not own this slot machine.");
         }
       
       }
       else {
         
         sendMessage("Invalid slot machine.");
       }
     
     }
     else {
       
       sendMessage("Usage:");
       sendMessage("/casino remove <name>");
     } 
     return Boolean.valueOf(true);
   }
 }


