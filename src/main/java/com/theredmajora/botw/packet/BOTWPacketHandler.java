package com.theredmajora.botw.packet;

import com.theredmajora.botw.BOTW;
import com.theredmajora.botw.packet.itemTracker.UpdateClientPacket;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class BOTWPacketHandler 
{
	public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(BOTW.MODID); 

	public static void init() 
	{	
		INSTANCE.registerMessage(UpdateClientPacket.class, UpdateClientPacket.class, 0, Side.CLIENT);
	}	
}
