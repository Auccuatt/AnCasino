/*    */ package me.darazo.ancasino.slot;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;

/*    */ import me.darazo.ancasino.AnCasino;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Type
/*    */ {
/*    */   protected AnCasino plugin;
/*    */   private String name;
/*    */   private Double cost;
/*    */   private Double createCost;
/*    */   private ArrayList<String> reel;
/*    */   private Map<String, String> messages;
/*    */   private List<String> helpMessages;
/*    */   private Map<String, Reward> rewards;
/*    */   
/*    */   public Type(String name, Double cost, Double createCost, ArrayList<String> reel, Map<String, String> messages, List<String> helpMessages, Map<String, Reward> rewards) {
/* 23 */     this.name = name;
/* 24 */     this.cost = cost;
/* 25 */     this.createCost = createCost;
/* 26 */     this.reel = reel;
/* 27 */     this.messages = messages;
/* 28 */     this.helpMessages = helpMessages;
/* 29 */     this.rewards = rewards;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 35 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public Double getCost() {
/* 40 */     return this.cost;
/*    */   }
/*    */   
/*    */   public Double getCreateCost() {
/* 44 */     return this.createCost;
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<String> getReel() {
/* 49 */     return this.reel;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, String> getMessages() {
/* 54 */     return this.messages;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getHelpMessages() {
/* 59 */     return this.helpMessages;
/*    */   }
/*    */ 
/*    */   
/*    */   public Reward getReward(String id) {
/* 64 */     return this.rewards.get(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCost(Double cost) {
/* 69 */     this.cost = cost;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCreateCost(Double createCost) {
/* 74 */     this.createCost = createCost;
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\slot\Type.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */