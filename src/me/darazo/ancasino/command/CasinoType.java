 package me.darazo.ancasino.command;
 
 import me.darazo.ancasino.AnCasino;
 import org.bukkit.entity.Player;
 
 
 public class CasinoType
   extends AnCommand
 {
   public CasinoType(AnCasino plugin, String[] args, Player player) {
     super(plugin, args, player);
   }
 
 
   
   public Boolean process() {
     if (!this.plugin.permission.isAdmin(this.player).booleanValue()) {
       noPermission();
       return Boolean.valueOf(true);
     } 
 
     
     if (this.args.length < 3) {
       
       sendMessage("Usage:");
       sendMessage("/casino type add <type>");
       
       sendMessage("/casino type remove <type>");
       return Boolean.valueOf(true);
     } 
 
     
     if (this.args[1].equalsIgnoreCase("add")) {
       
       if (this.plugin.typeData.isType(this.args[2]).booleanValue()) {
         sendMessage("Type " + this.args[2] + " already exists.");
       }
       else {
         
         this.plugin.typeData.newType(this.args[2]);
         sendMessage("Type " + this.args[2] + " created! Configure it to your needs in config.yml before using it.");
       
       }
     
     }
     else if (this.args[1].equalsIgnoreCase("edit")) {
       sendMessage("Not yet implemented. Edit types manually for now.");
 
     
     }
     else if (this.args[1].equalsIgnoreCase("remove")) {
       
       if (this.plugin.typeData.isType(this.args[2]).booleanValue()) {
         this.plugin.typeData.removeType(this.args[2]);
         sendMessage("Type " + this.args[2] + " removed. Make sure to update any slot machines using this type.");
       } else {
         
         sendMessage("Invalid type " + this.args[2]);
       } 
     } 
     
     return Boolean.valueOf(true);
   }
 }


