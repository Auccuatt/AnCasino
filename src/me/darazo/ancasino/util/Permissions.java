/*    */ package me.darazo.ancasino.util;
/*    */ 
/*    */ import me.darazo.ancasino.AnCasino;
/*    */ import me.darazo.ancasino.slot.SlotMachine;
/*    */ import me.darazo.ancasino.slot.Type;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Permissions
/*    */ {
/*    */   protected AnCasino plugin;
/* 13 */   private String admin = "ancasino.admin";
/* 14 */   private String create = "ancasino.create";
/* 15 */   private String manage = "ancasino.manage";
/* 16 */   private String use = "ancasino.use";
/*    */ 
/*    */   
/*    */   public Permissions(AnCasino plugin) {
/* 20 */     this.plugin = plugin;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean isAdmin(Player player) {
/* 26 */     if (player.isOp() || player.hasPermission(this.admin)) {
/* 27 */       return Boolean.valueOf(true);
/*    */     }
/* 29 */     return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean canCreate(Player player) {
/* 35 */     if (isAdmin(player).booleanValue() || player.hasPermission(this.create)) {
/* 36 */       return Boolean.valueOf(true);
/*    */     }
/* 38 */     return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean canCreate(Player player, Type type) {
/* 44 */     String name = type.getName();
/* 45 */     if (isAdmin(player).booleanValue() || player.hasPermission(String.valueOf(this.create) + "." + name) || player.hasPermission(this.create)) {
/* 46 */       return Boolean.valueOf(true);
/*    */     }
/* 48 */     return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean canCreate(Player player, String type) {
/* 54 */     if (isAdmin(player).booleanValue() || player.hasPermission(String.valueOf(this.create) + "." + type) || player.hasPermission(this.create)) {
/* 55 */       return Boolean.valueOf(true);
/*    */     }
/* 57 */     return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean canManage(Player player) {
/* 63 */     if (isAdmin(player).booleanValue() || player.hasPermission(this.manage)) {
/* 64 */       return Boolean.valueOf(true);
/*    */     }
/* 66 */     return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean canUse(Player player, Type type) {
/* 72 */     String name = type.getName();
/* 73 */     if (isAdmin(player).booleanValue() || player.hasPermission(String.valueOf(this.use) + "." + name) || player.hasPermission(this.use)) {
/* 74 */       return Boolean.valueOf(true);
/*    */     }
/* 76 */     return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean isOwner(Player player, SlotMachine slot) {
/* 82 */     if (isAdmin(player).booleanValue() || slot.getOwner().equalsIgnoreCase(player.getName())) {
/* 83 */       return Boolean.valueOf(true);
/*    */     }
/* 85 */     return Boolean.valueOf(false);
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasin\\util\Permissions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */