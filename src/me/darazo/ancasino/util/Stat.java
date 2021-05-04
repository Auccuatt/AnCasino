/*    */ package me.darazo.ancasino.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Stat
/*    */ {
/*    */   private String type;
/*    */   private Integer spins;
/*    */   private Double won;
/*    */   private Double lost;
/*    */   
/*    */   public Stat(String type, Integer spins, Double won, Double lost) {
/* 13 */     this.type = type;
/* 14 */     this.spins = spins;
/* 15 */     this.won = won;
/* 16 */     this.lost = lost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getType() {
/* 22 */     return this.type;
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getSpins() {
/* 27 */     return this.spins;
/*    */   }
/*    */ 
/*    */   
/*    */   public Double getWon() {
/* 32 */     return this.won;
/*    */   }
/*    */ 
/*    */   
/*    */   public Double getLost() {
/* 37 */     return this.lost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(Double won, Double lost) {
/* 43 */     this.spins = Integer.valueOf(this.spins.intValue() + 1);
/* 44 */     this.won = Double.valueOf(this.won.doubleValue() + won.doubleValue());
/* 45 */     this.lost = Double.valueOf(this.lost.doubleValue() + lost.doubleValue());
/*    */   }
/*    */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasin\\util\Stat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */