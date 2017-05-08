package com.theredmajora.botw.util;

import net.minecraft.entity.player.EntityPlayer;

public class ServerPacketData {

	ENUMClientPacketState state;
	EntityPlayer player;
	
	
	public ServerPacketData(ENUMClientPacketState state, EntityPlayer player) 
	{
		this.state = state;
		this.player = player;
	}
	
	public EntityPlayer getPlayer() {
		return player;
	}
	
	public void setPlayer(EntityPlayer player) {
		this.player = player;
	}
	
	public ENUMClientPacketState getState() {
		return state;
	}
	
	public void setState(ENUMClientPacketState state) {
		this.state = state;
	}
	
}
