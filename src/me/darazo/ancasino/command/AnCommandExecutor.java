 package me.darazo.ancasino.command;
 
 import me.darazo.ancasino.AnCasino;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.entity.Player;
 
 public class AnCommandExecutor
   implements CommandExecutor
 {
   protected AnCasino plugin;
   private AnCommand cmd;
   
   public AnCommandExecutor(AnCasino plugin) {
     this.plugin = plugin;
   }
 
 
   
   public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
     if (sender instanceof Player) {
       Player player = (Player)sender;
 
       
       if (args.length >= 1) {
 
         
         if (args[0].equalsIgnoreCase("add")) {
           this.cmd = new CasinoAdd(this.plugin, args, player);
 
         
         }
         else if (args[0].equalsIgnoreCase("addmanaged")) {
           this.cmd = new CasinoAddManaged(this.plugin, args, player);
 
         
         }
         else if (args[0].equalsIgnoreCase("slot")) {
           this.cmd = new CasinoSlot(this.plugin, args, player);
 
         
         }
         else if (args[0].equalsIgnoreCase("type")) {
           this.cmd = new CasinoType(this.plugin, args, player);
 
         
         }
         else if (args[0].equalsIgnoreCase("remove")) {
           this.cmd = new CasinoRemove(this.plugin, args, player);
 
         
         }
         else if (args[0].equalsIgnoreCase("list")) {
           this.cmd = new CasinoList(this.plugin, args, player);
 
         
         }
         else if (args[0].equalsIgnoreCase("reload")) {
           this.cmd = new CasinoReload(this.plugin, args, player);
 
         
         }
         else if (args[0].equalsIgnoreCase("stats")) {
           this.cmd = new CasinoStats(this.plugin, args, player);
         
         }
         else {
           
           return false;
         
         }
       
       }
       else {
         
         this.cmd = new Casino(this.plugin, args, player);
       } 
       
       return this.cmd.process().booleanValue();
     } 
 
 
     
     this.plugin.logger.info("This command cannot be executed as console.");
     
     return true;
   }
 }


