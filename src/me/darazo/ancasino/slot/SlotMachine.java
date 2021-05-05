 package me.darazo.ancasino.slot;
 
 import java.util.ArrayList;
 import org.bukkit.Material;
 import org.bukkit.block.Block;
 
 public class SlotMachine
 {
   private String name;
   private String type;
   private Boolean busy = Boolean.valueOf(false); private String owner; private String world; private Boolean managed; private Boolean enabled = Boolean.valueOf(true);
   
   private ArrayList<Block> blocks;
   
   private Block controller;
   private Double funds;
   
   public SlotMachine(String name, String type, String owner, String world, Boolean managed, ArrayList<Block> blocks, Block controller, Double funds) {
     this.name = name;
     this.type = type;
     this.owner = owner;
     this.world = world;
     this.managed = managed;
     this.blocks = blocks;
     this.controller = controller;
     this.funds = funds;
   }
 
 
 
   
   public SlotMachine(String name, String type, String owner, String world, Boolean managed) {
     this.name = name;
     this.type = type;
     this.owner = owner;
     this.world = world;
     this.managed = managed;
     this.funds = Double.valueOf(0.0D);
     
     if (managed.booleanValue()) {
       this.enabled = Boolean.valueOf(false);
     }
   }
 
   
   public String getName() {
     return this.name;
   }
 
   
   public String getType() {
     return this.type;
   }
 
   
   public String getOwner() {
     return this.owner;
   }
 
   
   public Boolean isManaged() {
     return this.managed;
   }
 
   
   public String getWorld() {
     return this.world;
   }
 
   
   public Boolean isBusy() {
     return this.busy;
   }
   
   public Boolean isEnabled() {
     return this.enabled;
   }
 
   
   public Double getFunds() {
     return this.funds;
   }
 
   
   public ArrayList<Block> getBlocks() {
     return this.blocks;
   }
 
   
   public Block getController() {
     return this.controller;
   }
 
   
   public void setBlocks(ArrayList<Block> blocks) {
     this.blocks = blocks;
   }
 
   
   public void setController(Block controller) {
     this.controller = controller;
     controller.setType(Material.NOTE_BLOCK);
   }
 
   
   public void setType(String type) {
     this.type = type;
   }
 
   
   public void setManaged(Boolean managed) {
     this.managed = managed;
   }
 
   
   public void setOwner(String owner) {
     this.owner = owner;
   }
 
   
   public void deposit(Double amount) {
     this.funds = Double.valueOf(this.funds.doubleValue() + amount.doubleValue());
   }
 
   
   public void withdraw(Double amount) {
     this.funds = Double.valueOf(this.funds.doubleValue() - amount.doubleValue());
   }
 
   
   public void setEnabled(Boolean enabled) {
     this.enabled = enabled;
   }
 
 
   
   public void toggleBusy() {
     if (this.busy.booleanValue()) {
       this.busy = Boolean.valueOf(false);
     } else {
       
       this.busy = Boolean.valueOf(true);
     } 
   }
 }


