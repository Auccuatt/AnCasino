/*    */ package me.darazo.ancasino.command;
/*    */ 
/*    */ import me.darazo.ancasino.AnCasino;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class CasinoType
/*    */   extends AnCommand
/*    */ {
/*    */   public CasinoType(AnCasino plugin, String[] args, Player player) {
/* 11 */     super(plugin, args, player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean process() {
/* 17 */     if (!this.plugin.permission.isAdmin(this.player).booleanValue()) {
/* 18 */       noPermission();
/* 19 */       return Boolean.valueOf(true);
/*    */     } 
/*    */ 
/*    */     
/* 23 */     if (this.args.length < 3) {
/*    */       
/* 25 */       sendMessage("Usage:");
/* 26 */       sendMessage("/casino type add <type>");
/*    */       
/* 28 */       sendMessage("/casino type remove <type>");
/* 29 */       return Boolean.valueOf(true);
/*    */     } 
/*    */ 
/*    */     
/* 33 */     if (this.args[1].equalsIgnoreCase("add")) {
/*    */       
/* 35 */       if (this.plugin.typeData.isType(this.args[2]).booleanValue()) {
/* 36 */         sendMessage("Type " + this.args[2] + " already exists.");
/*    */       }
/*    */       else {
/*    */         
/* 40 */         this.plugin.typeData.newType(this.args[2]);
/* 41 */         sendMessage("Type " + this.args[2] + " created! Configure it to your needs in config.yml before using it.");
/*    */       
/*    */       }
/*    */     
/*    */     }
/* 46 */     else if (this.args[1].equalsIgnoreCase("edit")) {
/* 47 */       sendMessage("Not yet implemented. Edit types manually for now.");
/*    */ 
/*    */     
/*    */     }
/* 51 */     else if (this.args[1].equalsIgnoreCase("remove")) {
/*    */       
/* 53 */       if (this.plugin.typeData.isType(this.args[2]).booleanValue()) {
/* 54 */         this.plugin.typeData.removeType(this.args[2]);
/* 55 */         sendMessage("Type " + this.args[2] + " removed. Make sure to update any slot machines using this type.");
/*    */       } else {
/*    */         
/* 58 */         sendMessage("Invalid type " + this.args[2]);
/*    */       } 
/*    */     } 
/*    */     
/* 62 */     return Boolean.valueOf(true);
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\command\CasinoType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */