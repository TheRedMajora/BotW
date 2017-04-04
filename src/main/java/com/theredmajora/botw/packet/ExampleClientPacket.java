package com.theredmajora.botw.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ExampleClientPacket implements IMessage, IMessageHandler<ExampleClientPacket, IMessage> { 

	/* Ok! this is an example Packet class that will sync a simple boolean 
	 * and will be sent to the server from the client!
	 * 
	 */

	boolean hasSlate;

	//must include empty constructor 
	public ExampleClientPacket(){ }

   //this is the constructor we will use 
	public ExampleClientPacket(boolean hasSlate)
	{
		this.hasSlate = hasSlate;
	}

	
	/* This is the piece of code that will run on the side that the packet was registered on (in the packet init)
	 * 
	 * The most important thing you will want most of the time is a minecraft player instance, and the world instance
	 * This is different for the client and the server
	 * 
	 */
	
	@Override
	public IMessage onMessage(final ExampleClientPacket message, MessageContext ctx) {

       EntityPlayer player = ctx.getServerHandler().playerEntity; // this will only work when the packet is received on the client
       World world = player.worldObj;
       
     

		return null;//This is the reply message, it is used to return a message back to the other side...so since this packet will be received on the server
		//by sending a return message it would be sent back to the client
	}

	
	//this method will convert the packet back into useable data 
	//the opposite will happen
	//JUST REMEMBER that you have to read the data in the same order you write to the packet in order to read it successfully 
	//so if we sent
	// buf.writeInt(NUMBERONE);
	// buf.writeInt(NUMBERTWO);
	// buf.writeBoolean(true);
	// we must read numberone first, number 2 first, and then the boolean any other order will store the incorrect information
	//if you used ByteBufUtils then you have to use ByteBufUtils to read the data for that specific information 
	
	@Override
	public void fromBytes(ByteBuf buf) {
		//	this.id = buf.readInt(); reads and int!
		
		this.hasSlate = buf.readBoolean();
	}



	//this method will convert the data to packet bytes...to do this we will use ByteBuf which has many built in functions already
	//Note you will also use ByteBufUtils to write strings, Itemstacks, and NBTTagCompound tags
	//notice there is no entity or tile entity...to transfer that information over you can use bytes if you are a wizard or
	//For - Entity transfer the entity ID, and then use world.getEntity by id
	//TileEntity just transfer the position and then use world.getTileEntity
	@Override
	public void toBytes(ByteBuf buf) {
		//	buf.writeInt(id); write an int
	   
		buf.writeBoolean(this.hasSlate);
	}
}