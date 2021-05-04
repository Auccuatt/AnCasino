/*    */ package me.darazo.ancasino.command;
/*    */ 
/*    */ import me.darazo.ancasino.AnCasino;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class Casino
/*    */   extends AnCommand
/*    */ {
/*    */   public Casino(AnCasino plugin, String[] args, Player player) {
/* 10 */     super(plugin, args, player);
/*    */   }
/*    */ 
/*    */   
/*    */   public Boolean process() {
/* 15 */     if (this.plugin.permission.isAdmin(this.player).booleanValue()) {
/* 16 */       sendMessage("Command guide:");
/* 17 */       sendMessage("/casino add - Add a new slot machine");
/* 18 */       sendMessage("/casino addmanaged - Add a new managed slot machine");
/* 19 */       sendMessage("/casino slot - Manage slot machines");
/* 20 */       sendMessage("/casino list - List slot machines and types");
/* 21 */       sendMessage("/casino reload - Reload config files from disk");
/* 22 */       sendMessage("/casino remove - Remove an existing slot machine");
/* 23 */       sendMessage("/casino stats - Global usage statistics");
/* 24 */       sendMessage("/casino type - Manage slot machine types");
/*    */     
/*    */     }
/* 27 */     else if (this.plugin.permission.canCreate(this.player).booleanValue()) {
/* 28 */       sendMessage("Command guide:");
/* 29 */       sendMessage("/casino add - Add a new slot machine");
/* 30 */       sendMessage("/casino addmanaged - Add a new managed slot machine");
/* 31 */       sendMessage("/casino slot - Manage slot machines");
/* 32 */       sendMessage("/casino list - List slot machines and types");
/* 33 */       sendMessage("/casino remove - Remove an existing slot machine");
/*    */     }
/*    */     else {
/*    */       
/* 37 */       noPermission();
/*    */     } 
/*    */     
/* 40 */     return Boolean.valueOf(true);
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\command\Casino.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */