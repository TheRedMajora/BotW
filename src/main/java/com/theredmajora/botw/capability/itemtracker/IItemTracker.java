package com.theredmajora.botw.capability.itemtracker;

import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;

import com.theredmajora.botw.util.ENUMClientPacketState;

public interface IItemTracker {

	public boolean[] hasRenderItems();
	public void setRenderItems(boolean[] bool);
	
	public ENUMClientPacketState getPacketState(EntityPlayer player);
	public void setPacketState(ENUMClientPacketState state, EntityPlayer player);

	public Map<String, ENUMClientPacketState> getMap();
}
