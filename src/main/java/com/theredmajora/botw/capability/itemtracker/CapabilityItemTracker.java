package com.theredmajora.botw.capability.itemtracker;

import javax.annotation.Nullable;

import com.theredmajora.botw.BOTW;
import com.theredmajora.botw.util.ENUMClientPacketState;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityItemTracker {

	@CapabilityInject(IItemTracker.class)
	public static final Capability<IItemTracker> BOTW_CAP = null;

	public static final EnumFacing DEFAULT_FACING = null;

	/**
	 * The ID of the capability.
	 */
	public static final ResourceLocation ID = new ResourceLocation(BOTW.MODID, "ItemTracker");

	/**
	 * Register the capability.
	 */
	public static void register() {
		CapabilityManager.INSTANCE.register(IItemTracker.class, new Storage(), ItemTracker.class);
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}


	public static class ItemTracker implements IItemTracker
	{
		boolean shouldRenderBow, shouldRenderSlate, shouldRenderGlider;
		ENUMClientPacketState packetState = ENUMClientPacketState.OLD;
		EntityPlayer clientPlayer;

		public ItemTracker(EntityPlayer player) {
			// TODO Auto-generated constructor stub
			this.clientPlayer = player;
		}

		@Override
		public boolean shouldRenderBow() {
			// TODO Auto-generated method stub
			return shouldRenderBow;
		}

		@Override
		public boolean shouldRenderSlate() {
			// TODO Auto-generated method stub
			return shouldRenderSlate;
		}

		@Override
		public boolean shouldRenderGlider() {
			// TODO Auto-generated method stub
			return shouldRenderGlider;
		}

		@Override
		public void setShouldRenderBow(boolean bool) {
			// TODO Auto-generated method stub
			this.shouldRenderBow = bool;
		}

		@Override
		public void setShouldRenderSlate(boolean bool) {
			// TODO Auto-generated method stub
			this.shouldRenderSlate = bool;
		}

		@Override
		public void setShouldRenderGlider(boolean bool) {
			// TODO Auto-generated method stub
			this.shouldRenderGlider = bool;
		}

		@Override
		public ENUMClientPacketState getPacketState() {
			// TODO Auto-generated method stub
			return packetState;
		}

		@Override
		public void setPacketState(ENUMClientPacketState state) {
			// TODO Auto-generated method stub
			this.packetState = state;
		}

		@Override
		public void updateServer() {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * Storage for the IWCustomInventory capability
	 *
	 */
	public static class Storage implements Capability.IStorage<IItemTracker>
	{

		@Override
		public NBTBase writeNBT(Capability<IItemTracker> capability, IItemTracker instance, EnumFacing side) {
			NBTTagCompound tagCompound = new NBTTagCompound();

			tagCompound.setBoolean("RenderBow", instance.shouldRenderBow());
			tagCompound.setBoolean("RenderSlate", instance.shouldRenderSlate());
			tagCompound.setBoolean("RenderGlider", instance.shouldRenderGlider());

			return tagCompound;
		}

		@Override
		public void readNBT(Capability<IItemTracker> capability, IItemTracker instance, EnumFacing side, NBTBase nbt) {

			NBTTagCompound tagCompound = (NBTTagCompound) nbt;

			instance.setShouldRenderBow(tagCompound.getBoolean("RenderBow"));
			instance.setShouldRenderSlate(tagCompound.getBoolean("RenderSlate"));
			instance.setShouldRenderGlider(tagCompound.getBoolean("RenderGlider"));	
		}

	}


	public static class EventHandler {

		@SubscribeEvent
		public void attachCapabilities(AttachCapabilitiesEvent.Entity event) {
			if (event.getEntity() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.getEntity();
				event.addCapability(ID, new Provider(player));
			}
		}

		@SubscribeEvent
		public void onPlayerCloned(PlayerEvent.Clone e) {
			NBTTagCompound nbt = new NBTTagCompound();

			if(e.isWasDeath())
			{
				if(e.getOriginal().hasCapability(BOTW_CAP, null))
				{
					IItemTracker newTracker = e.getEntityPlayer().getCapability(BOTW_CAP, null);
					newTracker.setPacketState(ENUMClientPacketState.OLD);				   
				}
			}
		}
	}

	/**
	 * Provider for the {@link IItemTracker} capability.
	 */
	public static class Provider implements ICapabilitySerializable<NBTTagCompound> {

		private final IItemTracker IItemTracker;

		public Provider(EntityPlayer player)
		{
			this(new ItemTracker(player));
		}

		public Provider(IItemTracker itemTracker) {
			this.IItemTracker = itemTracker;
		}

		@Override
		public NBTTagCompound serializeNBT() {
			return (NBTTagCompound)BOTW_CAP.getStorage().writeNBT(BOTW_CAP, IItemTracker, EnumFacing.NORTH);
		}

		@Override
		public void deserializeNBT(NBTTagCompound nbt) {
			BOTW_CAP.getStorage().readNBT(BOTW_CAP, IItemTracker, EnumFacing.NORTH, nbt);
		}

		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
			return capability == BOTW_CAP;
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

			if (capability == BOTW_CAP) {
				return BOTW_CAP.cast(this.IItemTracker);
			}
			return null;
		}
	}

}