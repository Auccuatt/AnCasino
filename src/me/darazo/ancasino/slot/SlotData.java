 package me.darazo.ancasino.slot;
 
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Set;
 import me.darazo.ancasino.AnCasino;
 import org.bukkit.Bukkit;
 import org.bukkit.Location;
import org.bukkit.Material;
 import org.bukkit.World;
 import org.bukkit.block.Block;
 import org.bukkit.block.BlockFace;
 import org.bukkit.entity.Player;
 
 public class SlotData {
   private AnCasino plugin;
   
   private HashMap<String, SlotMachine> slots = new HashMap<String, SlotMachine>();
   
   public HashMap<Player, SlotMachine> creatingSlots = new HashMap<Player, SlotMachine>();
   
   public HashMap<Player, SlotMachine> placingController = new HashMap<Player, SlotMachine>();
   
   public SlotData(AnCasino plugin) {
     this.plugin = plugin;
   }
   
   public SlotMachine getSlot(String name) {
     return this.slots.get(name);
   }
   
   public Collection<SlotMachine> getSlots() {
     return this.slots.values();
   }
   
   public void addSlot(SlotMachine slot) {
     String name = slot.getName();
     this.slots.put(name, slot);
   }
   
   public Boolean isSlot(String name) {
     if (this.slots.containsKey(name))
       return Boolean.valueOf(true); 
     return Boolean.valueOf(false);
   }
   
   public void removeSlot(SlotMachine slot) {
     this.slots.remove(slot.getName());
     for (Block b : slot.getBlocks())
       b.setType(Material.AIR); 
     slot.getController().setType(Material.AIR);
     this.plugin.configData.slots.set("slots." + slot.getName(), null);
     this.plugin.configData.saveSlots();
   }
   
   public void loadSlots() {
     Integer i = Integer.valueOf(0);
     this.slots = new HashMap<String, SlotMachine>();
     if (this.plugin.configData.slots.isConfigurationSection("slots")) {
       Set<String> slots = this.plugin.configData.slots.getConfigurationSection("slots").getKeys(false);
       if (!slots.isEmpty())
         for (String name : slots) {
           loadSlot(name);
           i = Integer.valueOf(i.intValue() + 1);
         }  
     } 
     this.plugin.logger.info(String.valueOf(this.plugin.prefix) + " Loaded " + i + " slot machines.");
   }
   
   public void saveSlot(SlotMachine slot) {
     String path = "slots." + slot.getName() + ".";
     ArrayList<String> xyz = new ArrayList<String>();
     for (Block b : slot.getBlocks())
       xyz.add(String.valueOf(b.getX()) + "," + b.getY() + "," + b.getZ()); 
     Block con = slot.getController();
     String cXyz = String.valueOf(con.getX()) + "," + con.getY() + "," + con.getZ();
     this.plugin.configData.slots.set(String.valueOf(path) + "name", slot.getName());
     this.plugin.configData.slots.set(String.valueOf(path) + "type", slot.getType());
     this.plugin.configData.slots.set(String.valueOf(path) + "owner", slot.getOwner());
     this.plugin.configData.slots.set(String.valueOf(path) + "world", slot.getWorld());
     this.plugin.configData.slots.set(String.valueOf(path) + "managed", slot.isManaged());
     this.plugin.configData.slots.set(String.valueOf(path) + "funds", slot.getFunds());
     this.plugin.configData.slots.set(String.valueOf(path) + "controller", cXyz);
     this.plugin.configData.slots.set(String.valueOf(path) + "location", xyz);
     this.plugin.configData.saveSlots();
   }
   
   private void loadSlot(String name) {
     String path = "slots." + name + ".";
     String type = this.plugin.configData.slots.getString(String.valueOf(path) + "type", "default");
     String owner = this.plugin.configData.slots.getString(String.valueOf(path) + "owner", null);
     String world = this.plugin.configData.slots.getString(String.valueOf(path) + "world", "world");
     Boolean managed = Boolean.valueOf(this.plugin.configData.slots.getBoolean(String.valueOf(path) + "managed", false));
     Double funds = Double.valueOf(this.plugin.configData.slots.getDouble(String.valueOf(path) + "funds", 0.0D));
     ArrayList<Block> blocks = getBlocks(name);
     Block controller = getController(name);
     SlotMachine slot = new SlotMachine(name, type, owner, world, managed, blocks, controller, funds);
     addSlot(slot);
   }
   
   private ArrayList<Block> getBlocks(String name) {
     List<String> xyz = this.plugin.configData.slots.getStringList("slots." + name + ".location");
     ArrayList<Block> blocks = new ArrayList<Block>();
     World world = Bukkit.getWorld(this.plugin.configData.slots.getString("slots." + name + ".world", "world"));
     for (String coord : xyz) {
       String[] b = coord.split("\\,");
       Location loc = new Location(world, Integer.parseInt(b[0]), Integer.parseInt(b[1]), Integer.parseInt(b[2]));
       blocks.add(loc.getBlock());
     } 
     return blocks;
   }
   
   private Block getController(String name) {
     String location = this.plugin.configData.slots.getString("slots." + name + ".controller");
     World world = Bukkit.getWorld(this.plugin.configData.slots.getString("slots." + name + ".world"));
     String[] b = location.split("\\,");
     Location loc = new Location(world, Integer.parseInt(b[0]), Integer.parseInt(b[1]), Integer.parseInt(b[2]));
     Block controller = loc.getBlock();
     return controller;
   }
   
   public void createReel(Player player, BlockFace face, SlotMachine slot) {
     Block center = player.getTargetBlock(null, 0);
     ArrayList<Block> blocks = new ArrayList<Block>();
     for (int i = 0; i < 3; i++) {
       blocks.add(center.getRelative(getDirection(face, "left"), 2));
       blocks.add(center);
       blocks.add(center.getRelative(getDirection(face, "right"), 2));
       center = center.getRelative(BlockFace.UP, 1);
     } 
     for (Block b : blocks)
       b.setType(Material.DIAMOND_BLOCK); 
     slot.setBlocks(blocks);
   }
   
   public BlockFace getDirection(BlockFace face, String direction) {
     if (face == BlockFace.NORTH) {
       if (direction.equalsIgnoreCase("left"))
         return BlockFace.EAST; 
       if (direction.equalsIgnoreCase("right"))
         return BlockFace.WEST; 
     } else if (face == BlockFace.SOUTH) {
       if (direction.equalsIgnoreCase("left"))
         return BlockFace.WEST; 
       if (direction.equalsIgnoreCase("right"))
         return BlockFace.EAST; 
     } else if (face == BlockFace.WEST) {
       if (direction.equalsIgnoreCase("left"))
         return BlockFace.SOUTH; 
       if (direction.equalsIgnoreCase("right"))
         return BlockFace.NORTH; 
     } else if (face == BlockFace.EAST) {
       if (direction.equalsIgnoreCase("left"))
         return BlockFace.NORTH; 
       if (direction.equalsIgnoreCase("right"))
         return BlockFace.SOUTH; 
     } 
     return BlockFace.SELF;
   }
   
   public Boolean isCreatingSlots(Player player) {
     if (this.creatingSlots.containsKey(player))
       return Boolean.valueOf(true); 
     return Boolean.valueOf(false);
   }
   
   public Boolean isPlacingController(Player player) {
     if (this.placingController.containsKey(player))
       return Boolean.valueOf(true); 
     return Boolean.valueOf(false);
   }
   
   public void toggleCreatingSlots(Player player, SlotMachine slot) {
     if (this.creatingSlots.containsKey(player)) {
       this.creatingSlots.remove(player);
     } else {
       this.creatingSlots.put(player, slot);
     } 
   }
   
   public void togglePlacingController(Player player, SlotMachine slot) {
     if (this.placingController.containsKey(player)) {
       this.placingController.remove(player);
     } else {
       this.placingController.put(player, slot);
     } 
   }
 }


