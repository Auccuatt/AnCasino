/*     */ package me.darazo.ancasino.slot;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ 
/*     */ public class SlotMachine
/*     */ {
/*     */   private String name;
/*     */   private String type;
/*  11 */   private Boolean busy = Boolean.valueOf(false); private String owner; private String world; private Boolean managed; private Boolean enabled = Boolean.valueOf(true);
/*     */   
/*     */   private ArrayList<Block> blocks;
/*     */   
/*     */   private Block controller;
/*     */   private Double funds;
/*     */   
/*     */   public SlotMachine(String name, String type, String owner, String world, Boolean managed, ArrayList<Block> blocks, Block controller, Double funds) {
/*  19 */     this.name = name;
/*  20 */     this.type = type;
/*  21 */     this.owner = owner;
/*  22 */     this.world = world;
/*  23 */     this.managed = managed;
/*  24 */     this.blocks = blocks;
/*  25 */     this.controller = controller;
/*  26 */     this.funds = funds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SlotMachine(String name, String type, String owner, String world, Boolean managed) {
/*  33 */     this.name = name;
/*  34 */     this.type = type;
/*  35 */     this.owner = owner;
/*  36 */     this.world = world;
/*  37 */     this.managed = managed;
/*  38 */     this.funds = Double.valueOf(0.0D);
/*     */     
/*  40 */     if (managed.booleanValue()) {
/*  41 */       this.enabled = Boolean.valueOf(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  47 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  52 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOwner() {
/*  57 */     return this.owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean isManaged() {
/*  62 */     return this.managed;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getWorld() {
/*  67 */     return this.world;
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean isBusy() {
/*  72 */     return this.busy;
/*     */   }
/*     */   
/*     */   public Boolean isEnabled() {
/*  76 */     return this.enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public Double getFunds() {
/*  81 */     return this.funds;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<Block> getBlocks() {
/*  86 */     return this.blocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getController() {
/*  91 */     return this.controller;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlocks(ArrayList<Block> blocks) {
/*  96 */     this.blocks = blocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setController(Block controller) {
/* 101 */     this.controller = controller;
/* 102 */     controller.setType(Material.NOTE_BLOCK);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(String type) {
/* 107 */     this.type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setManaged(Boolean managed) {
/* 112 */     this.managed = managed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(String owner) {
/* 117 */     this.owner = owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public void deposit(Double amount) {
/* 122 */     this.funds = Double.valueOf(this.funds.doubleValue() + amount.doubleValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void withdraw(Double amount) {
/* 127 */     this.funds = Double.valueOf(this.funds.doubleValue() - amount.doubleValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(Boolean enabled) {
/* 132 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void toggleBusy() {
/* 138 */     if (this.busy.booleanValue()) {
/* 139 */       this.busy = Boolean.valueOf(false);
/*     */     } else {
/*     */       
/* 142 */       this.busy = Boolean.valueOf(true);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\slot\SlotMachine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */