package com.theredmajora.botw.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ExampleServerPacket implements IMessage, IMessageHandler<ExampleServerPacket, IMessage> {


	/* This class is the exact same as the example client packet just will show you how to get the player and world instance
	 * 
	 */
	boolean hasSlate;

	//Every packet must have an empty constructor otherwise the packet will crash
	public ExampleServerPacket(){ }

	public ExampleServerPacket(boolean hasSlate)
	{
		this.hasSlate = hasSlate; 
	}

	@Override
	public IMessage onMessage(final ExampleServerPacket message, MessageContext ctx) {

		//To get the player instance you must schedule the main thread to run the runnable code
		//reason is because packets are not run on the minecraft thread! therefore Minecraft.getMinecraft().thePlayer is null! 
		IThreadListener mainThread = Minecraft.getMinecraft();
		mainThread.addScheduledTask(new Runnable() {

			@Override
			public void run() {
				EntityPlayer p = Minecraft.getMinecraft().thePlayer; //now this player instance is real
				World world = p.worldObj;	
			}
		});

		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
     	this.hasSlate = buf.readBoolean();
	
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(this.hasSlate);
	
	}
}