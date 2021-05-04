/*    */ package me.darazo.ancasino.slot;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.darazo.ancasino.AnCasino;

/*    */ import org.bukkit.Location;
import org.bukkit.Material;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RewardData
/*    */ {
/*    */   private AnCasino plugin;
/*    */   
/*    */   public RewardData(AnCasino plugin) {
/* 17 */     this.plugin = plugin;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void send(Player player, Reward reward) {
/* 23 */     if (reward.message != null) {
/* 24 */       this.plugin.sendMessage(player, reward.message);
/*    */     }
/*    */     
/* 27 */     if (reward.money != null) {
/* 28 */       this.plugin.economy.depositPlayer(player, reward.money.doubleValue());
/*    */     }
/*    */     
/* 31 */     if (reward.action != null) {
/* 32 */       executeAction(reward.action, player);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void executeAction(List<String> actionList, Player p) {
/* 39 */     for (int i = 0; i < actionList.size(); i++) {
/*    */       
/* 41 */       if (actionList.get(0).equalsIgnoreCase("none") || actionList.get(0) == null)
/*    */       {
	             return;
			   }
/*    */       
/* 44 */       if (actionList.get(0).equalsIgnoreCase("give")) {
/*    */         
/* 46 */         String[] itemData = actionList.get(1).split(":");
/* 47 */         Material item = Material.getMaterial(itemData[0]);
/* 53 */         int n = Integer.parseInt(actionList.get(2));
/* 54 */         p.getInventory().addItem(new ItemStack(item, n));
/*    */         
/*    */         continue;
/*    */       } 
/* 58 */       if (actionList.get(0).equalsIgnoreCase("kill")) {
/*    */         
/* 60 */         p.setHealth(0.0);
/*    */         
/*    */         continue;
/*    */       } 
/* 64 */       if (actionList.get(0).equalsIgnoreCase("addxp")) {
/*    */         
/* 66 */         int exp = Integer.parseInt(actionList.get(1));
/* 67 */         p.giveExp(exp);
/*    */         
/*    */         continue;
/*    */       } 
/* 71 */       if (actionList.get(0).equalsIgnoreCase("tpto")) {
/*    */         
/* 73 */         String[] xyz = actionList.get(1).split("\\,");
/* 74 */         World world = p.getWorld();
/* 75 */         Location loc = new Location(world, Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));
/* 76 */         p.teleport(loc);
/*    */         
/*    */         continue;
/*    */       } 
/* 80 */       if (actionList.get(0).equalsIgnoreCase("smite")) {
/* 81 */         p.getWorld().strikeLightning(p.getLocation());
/*    */         
/*    */         continue;
/*    */       } 
/* 85 */       if (actionList.get(0).equalsIgnoreCase("broadcast")) {
/*    */         
/* 87 */         String message = actionList.get(1);
/* 88 */         for (int j = 2; j < actionList.size(); j++) {
/* 89 */           message = String.valueOf(message) + " " + actionList.get(j);
/*    */         }
/*    */         
/* 92 */         message = message.replaceAll("(?i)&([a-f0-9])", "§$1");
/* 93 */         this.plugin.getServer().broadcastMessage(message);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\slot\RewardData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */