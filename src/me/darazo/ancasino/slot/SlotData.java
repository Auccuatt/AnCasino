/*     */ package me.darazo.ancasino.slot;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import me.darazo.ancasino.AnCasino;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class SlotData {
/*     */   private AnCasino plugin;
/*     */   
/*  21 */   private HashMap<String, SlotMachine> slots = new HashMap<String, SlotMachine>();
/*     */   
/*  23 */   public HashMap<Player, SlotMachine> creatingSlots = new HashMap<Player, SlotMachine>();
/*     */   
/*  24 */   public HashMap<Player, SlotMachine> placingController = new HashMap<Player, SlotMachine>();
/*     */   
/*     */   public SlotData(AnCasino plugin) {
/*  29 */     this.plugin = plugin;
/*     */   }
/*     */   
/*     */   public SlotMachine getSlot(String name) {
/*  34 */     return this.slots.get(name);
/*     */   }
/*     */   
/*     */   public Collection<SlotMachine> getSlots() {
/*  39 */     return this.slots.values();
/*     */   }
/*     */   
/*     */   public void addSlot(SlotMachine slot) {
/*  45 */     String name = slot.getName();
/*  46 */     this.slots.put(name, slot);
/*     */   }
/*     */   
/*     */   public Boolean isSlot(String name) {
/*  52 */     if (this.slots.containsKey(name))
/*  53 */       return Boolean.valueOf(true); 
/*  55 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   public void removeSlot(SlotMachine slot) {
/*  61 */     this.slots.remove(slot.getName());
/*  62 */     for (Block b : slot.getBlocks())
/*  63 */       b.setType(Material.AIR); 
/*  65 */     slot.getController().setType(Material.AIR);
/*  66 */     this.plugin.configData.slots.set("slots." + slot.getName(), null);
/*  67 */     this.plugin.configData.saveSlots();
/*     */   }
/*     */   
/*     */   public void loadSlots() {
/*  74 */     Integer i = Integer.valueOf(0);
/*  75 */     this.slots = new HashMap<String, SlotMachine>();
/*  76 */     if (this.plugin.configData.slots.isConfigurationSection("slots")) {
/*  77 */       Set<String> slots = this.plugin.configData.slots.getConfigurationSection("slots").getKeys(false);
/*  78 */       if (!slots.isEmpty())
/*  79 */         for (String name : slots) {
/*  80 */           loadSlot(name);
/*  81 */           i = Integer.valueOf(i.intValue() + 1);
/*     */         }  
/*     */     } 
/*  85 */     this.plugin.logger.info(String.valueOf(this.plugin.prefix) + " Loaded " + i + " slot machines.");
/*     */   }
/*     */   
/*     */   public void saveSlot(SlotMachine slot) {
/*  91 */     String path = "slots." + slot.getName() + ".";
/*  92 */     ArrayList<String> xyz = new ArrayList<String>();
/*  94 */     for (Block b : slot.getBlocks())
/*  95 */       xyz.add(String.valueOf(b.getX()) + "," + b.getY() + "," + b.getZ()); 
/*  98 */     Block con = slot.getController();
/*  99 */     String cXyz = String.valueOf(con.getX()) + "," + con.getY() + "," + con.getZ();
/* 101 */     this.plugin.configData.slots.set(String.valueOf(path) + "name", slot.getName());
/* 102 */     this.plugin.configData.slots.set(String.valueOf(path) + "type", slot.getType());
/* 103 */     this.plugin.configData.slots.set(String.valueOf(path) + "owner", slot.getOwner());
/* 104 */     this.plugin.configData.slots.set(String.valueOf(path) + "world", slot.getWorld());
/* 105 */     this.plugin.configData.slots.set(String.valueOf(path) + "managed", slot.isManaged());
/* 106 */     this.plugin.configData.slots.set(String.valueOf(path) + "funds", slot.getFunds());
/* 107 */     this.plugin.configData.slots.set(String.valueOf(path) + "controller", cXyz);
/* 108 */     this.plugin.configData.slots.set(String.valueOf(path) + "location", xyz);
/* 111 */     this.plugin.configData.saveSlots();
/*     */   }
/*     */   
/*     */   private void loadSlot(String name) {
/* 117 */     String path = "slots." + name + ".";
/* 119 */     String type = this.plugin.configData.slots.getString(String.valueOf(path) + "type", "default");
/* 120 */     String owner = this.plugin.configData.slots.getString(String.valueOf(path) + "owner", null);
/* 121 */     String world = this.plugin.configData.slots.getString(String.valueOf(path) + "world", "world");
/* 122 */     Boolean managed = Boolean.valueOf(this.plugin.configData.slots.getBoolean(String.valueOf(path) + "managed", false));
/* 123 */     Double funds = Double.valueOf(this.plugin.configData.slots.getDouble(String.valueOf(path) + "funds", 0.0D));
/* 124 */     ArrayList<Block> blocks = getBlocks(name);
/* 125 */     Block controller = getController(name);
/* 127 */     SlotMachine slot = new SlotMachine(name, type, owner, world, managed, blocks, controller, funds);
/* 128 */     addSlot(slot);
/*     */   }
/*     */   
/*     */   private ArrayList<Block> getBlocks(String name) {
/* 134 */     List<String> xyz = this.plugin.configData.slots.getStringList("slots." + name + ".location");
/* 135 */     ArrayList<Block> blocks = new ArrayList<Block>();
/* 136 */     World world = Bukkit.getWorld(this.plugin.configData.slots.getString("slots." + name + ".world", "world"));
/* 138 */     for (String coord : xyz) {
/* 139 */       String[] b = coord.split("\\,");
/* 140 */       Location loc = new Location(world, Integer.parseInt(b[0]), Integer.parseInt(b[1]), Integer.parseInt(b[2]));
/* 141 */       blocks.add(loc.getBlock());
/*     */     } 
/* 144 */     return blocks;
/*     */   }
/*     */   
/*     */   private Block getController(String name) {
/* 150 */     String location = this.plugin.configData.slots.getString("slots." + name + ".controller");
/* 151 */     World world = Bukkit.getWorld(this.plugin.configData.slots.getString("slots." + name + ".world"));
/* 152 */     String[] b = location.split("\\,");
/* 153 */     Location loc = new Location(world, Integer.parseInt(b[0]), Integer.parseInt(b[1]), Integer.parseInt(b[2]));
/* 155 */     Block controller = loc.getBlock();
/* 156 */     return controller;
/*     */   }
/*     */   
/*     */   public void createReel(Player player, BlockFace face, SlotMachine slot) {
/* 163 */     Block center = player.getTargetBlock(null, 0);
/* 164 */     ArrayList<Block> blocks = new ArrayList<Block>();
/* 166 */     for (int i = 0; i < 3; i++) {
/* 167 */       blocks.add(center.getRelative(getDirection(face, "left"), 2));
/* 168 */       blocks.add(center);
/* 169 */       blocks.add(center.getRelative(getDirection(face, "right"), 2));
/* 170 */       center = center.getRelative(BlockFace.UP, 1);
/*     */     } 
/* 173 */     for (Block b : blocks)
/* 174 */       b.setType(Material.DIAMOND_BLOCK); 
/* 177 */     slot.setBlocks(blocks);
/*     */   }
/*     */   
/*     */   public BlockFace getDirection(BlockFace face, String direction) {
/* 184 */     if (face == BlockFace.NORTH) {
/* 185 */       if (direction.equalsIgnoreCase("left"))
/* 186 */         return BlockFace.EAST; 
/* 188 */       if (direction.equalsIgnoreCase("right"))
/* 189 */         return BlockFace.WEST; 
/* 192 */     } else if (face == BlockFace.SOUTH) {
/* 193 */       if (direction.equalsIgnoreCase("left"))
/* 194 */         return BlockFace.WEST; 
/* 196 */       if (direction.equalsIgnoreCase("right"))
/* 197 */         return BlockFace.EAST; 
/* 200 */     } else if (face == BlockFace.WEST) {
/* 201 */       if (direction.equalsIgnoreCase("left"))
/* 202 */         return BlockFace.SOUTH; 
/* 204 */       if (direction.equalsIgnoreCase("right"))
/* 205 */         return BlockFace.NORTH; 
/* 208 */     } else if (face == BlockFace.EAST) {
/* 209 */       if (direction.equalsIgnoreCase("left"))
/* 210 */         return BlockFace.NORTH; 
/* 212 */       if (direction.equalsIgnoreCase("right"))
/* 213 */         return BlockFace.SOUTH; 
/*     */     } 
/* 216 */     return BlockFace.SELF;
/*     */   }
/*     */   
/*     */   public Boolean isCreatingSlots(Player player) {
/* 223 */     if (this.creatingSlots.containsKey(player))
/* 224 */       return Boolean.valueOf(true); 
/* 227 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   public Boolean isPlacingController(Player player) {
/* 233 */     if (this.placingController.containsKey(player))
/* 234 */       return Boolean.valueOf(true); 
/* 237 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   public void toggleCreatingSlots(Player player, SlotMachine slot) {
/* 243 */     if (this.creatingSlots.containsKey(player)) {
/* 244 */       this.creatingSlots.remove(player);
/*     */     } else {
/* 247 */       this.creatingSlots.put(player, slot);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void togglePlacingController(Player player, SlotMachine slot) {
/* 254 */     if (this.placingController.containsKey(player)) {
/* 255 */       this.placingController.remove(player);
/*     */     } else {
/* 258 */       this.placingController.put(player, slot);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\Minecraft Server\plugins\AnCasino.jar!\me\darazo\ancasino\slot\SlotData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */