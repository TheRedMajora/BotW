package com.theredmajora.botw.util;

import net.minecraft.entity.player.EntityPlayer;

public enum ENUMClientPacketState {

	OLD(0), NEW(1);

	int id;
	private ENUMClientPacketState(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public ENUMClientPacketState getENUM(int id)
	{
		switch(id)
		{
		case 1:
			return ENUMClientPacketState.NEW;
		case 0:
			return ENUMClientPacketState.OLD;
		}
		return ENUMClientPacketState.OLD;
	}
}
