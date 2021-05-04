/*    */ package me.darazo.ancasino.slot;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Reward
/*    */ {
/*    */   protected String message;
/*    */   protected Double money;
/*    */   protected List<String> action;
/*    */   
/*    */   public Reward(String message, Double money, List<String> action) {
/* 14 */     this.message = message;
/* 15 */     this.money = money;
/* 16 */     this.action = action;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 21 */     return this.message;
/*    */   }
/*    */ 
/*    */   
/*    */   public Double getMoney() {
/* 26 */     return this.money;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getAction() {
/* 31 */     return this.action;
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\slot\Reward.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */