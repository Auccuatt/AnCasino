 package me.darazo.ancasino.command;
 
 import me.darazo.ancasino.AnCasino;
 import me.darazo.ancasino.slot.SlotMachine;
 import org.bukkit.entity.Player;
 
 
 
 
 public abstract class AnCommand
 {
   public AnCasino plugin;
   public Player player;
   public String[] args;
   
   public AnCommand(AnCasino plugin, String[] args, Player player) {
     this.plugin = plugin;
     this.args = args;
     this.player = player;
   }
 
 
   
   public Boolean process() {
     return Boolean.valueOf(false);
   }
 
 
   
   public Boolean isOwner(SlotMachine slot) {
     if (this.plugin.permission.isAdmin(this.player).booleanValue() || slot.getOwner().equalsIgnoreCase(this.player.getName())) {
       return Boolean.valueOf(true);
     }
     return Boolean.valueOf(false);
   }
 
   
   public void noPermission() {
     this.plugin.sendMessage(this.player, "You don't have permission to do this.");
   }
 
   
   public void sendMessage(String message) {
     this.plugin.sendMessage(this.player, message);
   }
 }


