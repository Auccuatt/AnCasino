/*    */ package me.darazo.ancasino.command;
/*    */ 
/*    */ import me.darazo.ancasino.AnCasino;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class AnCommandExecutor
/*    */   implements CommandExecutor
/*    */ {
/*    */   protected AnCasino plugin;
/*    */   private AnCommand cmd;
/*    */   
/*    */   public AnCommandExecutor(AnCasino plugin) {
/* 16 */     this.plugin = plugin;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
/* 22 */     if (sender instanceof Player) {
/* 23 */       Player player = (Player)sender;
/*    */ 
/*    */       
/* 26 */       if (args.length >= 1) {
/*    */ 
/*    */         
/* 29 */         if (args[0].equalsIgnoreCase("add")) {
/* 30 */           this.cmd = new CasinoAdd(this.plugin, args, player);
/*    */ 
/*    */         
/*    */         }
/* 34 */         else if (args[0].equalsIgnoreCase("addmanaged")) {
/* 35 */           this.cmd = new CasinoAddManaged(this.plugin, args, player);
/*    */ 
/*    */         
/*    */         }
/* 39 */         else if (args[0].equalsIgnoreCase("slot")) {
/* 40 */           this.cmd = new CasinoSlot(this.plugin, args, player);
/*    */ 
/*    */         
/*    */         }
/* 44 */         else if (args[0].equalsIgnoreCase("type")) {
/* 45 */           this.cmd = new CasinoType(this.plugin, args, player);
/*    */ 
/*    */         
/*    */         }
/* 49 */         else if (args[0].equalsIgnoreCase("remove")) {
/* 50 */           this.cmd = new CasinoRemove(this.plugin, args, player);
/*    */ 
/*    */         
/*    */         }
/* 54 */         else if (args[0].equalsIgnoreCase("list")) {
/* 55 */           this.cmd = new CasinoList(this.plugin, args, player);
/*    */ 
/*    */         
/*    */         }
/* 59 */         else if (args[0].equalsIgnoreCase("reload")) {
/* 60 */           this.cmd = new CasinoReload(this.plugin, args, player);
/*    */ 
/*    */         
/*    */         }
/* 64 */         else if (args[0].equalsIgnoreCase("stats")) {
/* 65 */           this.cmd = new CasinoStats(this.plugin, args, player);
/*    */         
/*    */         }
/*    */         else {
/*    */           
/* 70 */           return false;
/*    */         
/*    */         }
/*    */       
/*    */       }
/*    */       else {
/*    */         
/* 77 */         this.cmd = new Casino(this.plugin, args, player);
/*    */       } 
/*    */       
/* 80 */       return this.cmd.process().booleanValue();
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 85 */     this.plugin.logger.info("This command cannot be executed as console.");
/*    */     
/* 87 */     return true;
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\command\AnCommandExecutor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */