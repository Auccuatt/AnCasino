package me.darazo.ancasino.slot;

import java.util.List;

public class Reward
{
    protected String message;
    protected Double money;
    protected List<String> action;

    public Reward(String message, Double money, List<String> action)
    {
	this.message = message;
	this.money = money;
	this.action = action;
    }

    public String getMessage()
    {
	return this.message;
    }

    public Double getMoney()
    {
	return this.money;
    }

    public List<String> getAction()
    {
	return this.action;
    }
}
