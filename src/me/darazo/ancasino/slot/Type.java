package me.darazo.ancasino.slot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.darazo.ancasino.AnCasino;

public class Type
{
    protected AnCasino plugin;
    private String name;
    private Double cost;
    private Double createCost;
    private ArrayList<String> reel;
    private Map<String, String> messages;
    private List<String> helpMessages;
    private Map<String, Reward> rewards;

    public Type(String name, Double cost, Double createCost,
	    ArrayList<String> reel, Map<String, String> messages,
	    List<String> helpMessages, Map<String, Reward> rewards)
    {
	this.name = name;
	this.cost = cost;
	this.createCost = createCost;
	this.reel = reel;
	this.messages = messages;
	this.helpMessages = helpMessages;
	this.rewards = rewards;
    }

    public String getName()
    {
	return this.name;
    }

    public Double getCost()
    {
	return this.cost;
    }

    public Double getCreateCost()
    {
	return this.createCost;
    }

    public ArrayList<String> getReel()
    {
	return this.reel;
    }

    public Map<String, String> getMessages()
    {
	return this.messages;
    }

    public List<String> getHelpMessages()
    {
	return this.helpMessages;
    }

    public Reward getReward(String id)
    {
	return this.rewards.get(id);
    }

    public void setCost(Double cost)
    {
	this.cost = cost;
    }

    public void setCreateCost(Double createCost)
    {
	this.createCost = createCost;
    }
}
