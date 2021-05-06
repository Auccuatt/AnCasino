package me.darazo.ancasino.util;

public class Stat
{
    private String type;
    private Integer spins;
    private Double won;
    private Double lost;

    public Stat(String type, Integer spins, Double won, Double lost)
    {
	this.type = type;
	this.spins = spins;
	this.won = won;
	this.lost = lost;
    }

    public String getType()
    {
	return this.type;
    }

    public Integer getSpins()
    {
	return this.spins;
    }

    public Double getWon()
    {
	return this.won;
    }

    public Double getLost()
    {
	return this.lost;
    }

    public void add(Double won, Double lost)
    {
	this.spins = Integer.valueOf(this.spins.intValue() + 1);
	this.won = Double.valueOf(this.won.doubleValue() + won.doubleValue());
	this.lost = Double
		.valueOf(this.lost.doubleValue() + lost.doubleValue());
    }
}
