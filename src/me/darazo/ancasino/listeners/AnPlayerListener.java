package me.darazo.ancasino.listeners;

import me.darazo.ancasino.AnCasino;
import me.darazo.ancasino.slot.SlotMachine;
import me.darazo.ancasino.slot.Type;
import me.darazo.ancasino.slot.game.Game;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AnPlayerListener
	implements Listener
{
    protected AnCasino plugin;

    public AnPlayerListener(AnCasino plugin)
    {
	this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerInteract(PlayerInteractEvent event)
    {
	if (event.getAction() != Action.LEFT_CLICK_BLOCK
		&& event.getAction() != Action.RIGHT_CLICK_BLOCK)
	{
	    return;
	}

	if (this.plugin.isEnabled())
	{
	    Player player = event.getPlayer();
	    Block b = event.getClickedBlock();

	    if (b.getType() == Material.NOTE_BLOCK)
	    {

		for (SlotMachine slot : this.plugin.slotData.getSlots())
		{

		    if (b.equals(slot.getController()))
		    {
			Type type = this.plugin.typeData
				.getType(slot.getType());
			Double cost = type.getCost();

			if (event.getAction() == Action.LEFT_CLICK_BLOCK)
			{

			    if (this.plugin.permission.canUse(player, type)
				    .booleanValue())
			    {

				if (this.plugin.economy.has(player,
					cost.doubleValue()))
				{

				    if (!slot.isBusy().booleanValue())
				    {

					Game game = new Game(slot, player,
						this.plugin);
					game.play();

					return;
				    }

				    this.plugin.sendMessage(player,
					    (String) type.getMessages()
						    .get("inUse"));

				    break;
				}

				this.plugin.sendMessage(player, (String) type
					.getMessages().get("noFunds"));

				break;
			    }

			    this.plugin.sendMessage(player, (String) type
				    .getMessages().get("noPermission"));

			    break;
			}

			if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
			{

			    event.setCancelled(true);
			    if (this.plugin.permission.isOwner(player, slot)
				    .booleanValue())
			    {
				this.plugin.sendMessage(player,
					String.valueOf(slot.getName()) + ":");
				this.plugin.sendMessage(player,
					"Type: " + slot.getType());
				this.plugin.sendMessage(player,
					"Owner: " + slot.getOwner());
				this.plugin.sendMessage(player, "Managed: "
					+ slot.isManaged().toString());

				if (slot.isManaged().booleanValue())
				{
				    this.plugin.sendMessage(player, "Enabled: "
					    + slot.isEnabled().toString());
				    this.plugin.sendMessage(player,
					    "Funds: " + slot.getFunds());
				    this.plugin.sendMessage(player,
					    "Funds required: "
						    + this.plugin.typeData
							    .getMaxPrize(slot
								    .getType()));
				}
				break;
			    }
			    for (String message : this.plugin.typeData
				    .getType(slot.getType()).getHelpMessages())
			    {
				this.plugin.sendMessage(player, message);
			    }
			}

			break;
		    }
		}
	    }

	    if (event.getAction() == Action.LEFT_CLICK_BLOCK
		    && this.plugin.slotData.isCreatingSlots(player)
			    .booleanValue())
	    {

		BlockFace face = event.getBlockFace();

		if (face != BlockFace.DOWN && face != BlockFace.UP)
		{
		    SlotMachine slot = (SlotMachine) this.plugin.slotData.creatingSlots
			    .get(player);
		    this.plugin.slotData.createReel(player, face, slot);

		    this.plugin.slotData.toggleCreatingSlots(player, slot);
		    this.plugin.slotData.togglePlacingController(player, slot);
		    this.plugin.sendMessage(player,
			    "Punch a block to serve as the controller for this slot machine.");
		} else
		{

		    this.plugin.sendMessage(player,
			    "Only sides of blocks are valid targets for this operation.");

		}

	    } else if (event.getAction() == Action.LEFT_CLICK_BLOCK
		    && this.plugin.slotData.isPlacingController(player)
			    .booleanValue())
	    {

		SlotMachine slot = (SlotMachine) this.plugin.slotData.placingController
			.get(player);
		slot.setController(b);
		this.plugin.slotData.togglePlacingController(player, slot);
		this.plugin.slotData.addSlot(slot);
		this.plugin.slotData.saveSlot(slot);
		this.plugin.sendMessage(player,
			"Slot machine set up successfully!");
	    }
	}
    }
}
