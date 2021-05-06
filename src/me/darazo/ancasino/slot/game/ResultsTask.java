package me.darazo.ancasino.slot.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import me.darazo.ancasino.slot.Reward;
import me.darazo.ancasino.slot.SlotMachine;
import me.darazo.ancasino.slot.Type;
import me.darazo.ancasino.util.Stat;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ResultsTask
	implements Runnable
{
    private Game game;

    public ResultsTask(Game game)
    {
	this.game = game;
    }

    public void run()
    {
	Type type = this.game.getType();
	Player player = this.game.getPlayer();
	String name = type.getName();
	Double cost = type.getCost();
	Double won = Double.valueOf(0.0D);

	ArrayList<Reward> results = getResults();

	if (!results.isEmpty())
	{

	    playVictoryMusic();
	    for (Reward reward : results)
	    {
		this.game.plugin.rewardData.send(player, reward);
		won = Double.valueOf(
			won.doubleValue() + reward.getMoney().doubleValue());
	    }

	    SlotMachine slot = this.game.getSlot();
	    if (slot.isManaged().booleanValue())
	    {
		slot.withdraw(won);
		Double max = this.game.plugin.typeData
			.getMaxPrize(type.getName());
		if (slot.getFunds().doubleValue() < max.doubleValue())
		{
		    slot.setEnabled(Boolean.valueOf(false));
		}

		this.game.plugin.slotData.saveSlot(slot);
		this.game.plugin.configData.saveSlots();
	    }

	} else
	{

	    this.game.plugin.sendMessage(player,
		    (String) type.getMessages().get("noWin"));
	}

	if (this.game.plugin.configData.trackStats.booleanValue())
	{
	    Stat stat;
	    if (this.game.plugin.statsData.isStat(name).booleanValue())
	    {
		stat = this.game.plugin.statsData.getStat(name);
		stat.add(won, cost);
	    } else
	    {

		stat = new Stat(name, Integer.valueOf(1), won, cost);
	    }
	    this.game.plugin.statsData.addStat(stat);
	    if (!results.isEmpty())
	    {
		this.game.plugin.configData.saveStats();
	    }
	}

	this.game.getSlot().toggleBusy();
    }

    private ArrayList<Reward> getResults()
    {
	ArrayList<Reward> results = new ArrayList<Reward>();
	ArrayList<Block> blocks = this.game.getSlot().getBlocks();

	for (int i = 0; i < 5; i++)
	{

	    ArrayList<Material> currentId = new ArrayList<Material>();
	    List<Block> current = null;
	    if (i < 3)
	    {
		int start = 0 + 3 * i;
		int end = 3 + 3 * i;
		current = blocks.subList(start, end);

	    } else if (this.game.plugin.configData.allowDiagonals
		    .booleanValue())
	    {
		current = new ArrayList<Block>();
		for (int j = 0; j < 3; j++)
		{
		    if (i == 3)
		    {
			current.add(blocks.get(j * 4));
		    } else
		    {

			current.add(blocks.get(2 + 2 * j));
		    }
		}
	    } else
	    {
		break;
	    }

	    for (Block b : current)
	    {
		currentId.add(b.getType());
	    }

	    Set<Material> currentSet = new HashSet<Material>(currentId);
	    if (currentSet.size() == 1)
	    {

		Material prize = ((Block) current.get(0)).getType();
		Reward reward = this.game.getType().getReward(prize.toString());
		results.add(reward);
	    }
	}

	return results;
    }

    private void playVictoryMusic()
    {
	final Player p = this.game.getPlayer();

	final Location loc = this.game.getSlot().getController().getLocation();

	this.game.scheduler.scheduleSyncDelayedTask((Plugin) this.game.plugin,
		new Runnable()
		{
		    public void run()
		    {
			p.playNote(loc, Instrument.PIANO,
				new Note((byte) 0, Note.Tone.C, true));
		    }
		}, 10L);
	this.game.scheduler.scheduleSyncDelayedTask((Plugin) this.game.plugin,
		new Runnable()
		{
		    public void run()
		    {
			p.playNote(loc, Instrument.PIANO,
				new Note((byte) 1, Note.Tone.F, true));
		    }
		}, 13L);
	this.game.scheduler.scheduleSyncDelayedTask((Plugin) this.game.plugin,
		new Runnable()
		{
		    public void run()
		    {
			p.playNote(loc, Instrument.PIANO,
				new Note((byte) 1, Note.Tone.A, true));
		    }
		}, 16L);

	this.game.scheduler.scheduleSyncDelayedTask((Plugin) this.game.plugin,
		new Runnable()
		{
		    public void run()
		    {
			p.playNote(loc, Instrument.PIANO,
				new Note((byte) 0, Note.Tone.C, true));
		    }
		}, 21L);
	this.game.scheduler.scheduleSyncDelayedTask((Plugin) this.game.plugin,
		new Runnable()
		{
		    public void run()
		    {
			p.playNote(loc, Instrument.PIANO,
				new Note((byte) 1, Note.Tone.F, true));
		    }
		}, 24L);
	this.game.scheduler.scheduleSyncDelayedTask((Plugin) this.game.plugin,
		new Runnable()
		{
		    public void run()
		    {
			p.playNote(loc, Instrument.PIANO,
				new Note((byte) 1, Note.Tone.A, true));
		    }
		}, 27L);
    }
}
