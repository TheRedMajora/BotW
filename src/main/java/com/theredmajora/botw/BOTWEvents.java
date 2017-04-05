package com.theredmajora.botw;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BOTWEvents
{
	private int ticksInAir = 0;
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onEvent(KeyInputEvent event)
	{
	    KeyBinding[] keyBindings = BOTWKeyHandler.keyBindings;
	   
	    if (keyBindings[0].isPressed()) 
	    {
	    	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	    	ItemStack stack = player.inventory.getCurrentItem();
	    	
	    	if(player.inventory.offHandInventory[0] == null)
	    	{
	    		player.inventory.offHandInventory[0] = ItemStack.copyItemStack(stack);
	    		player.inventory.deleteStack(stack);
	    	}
	    	else
	    	{
	    		ItemStack temp = ItemStack.copyItemStack(stack);
	    		stack = ItemStack.copyItemStack(player.inventory.offHandInventory[0]);
	    		player.inventory.offHandInventory[0] = ItemStack.copyItemStack(temp);
	    	}
	    }
	}
	
	@SubscribeEvent
	public void livingUpdate(LivingEvent.LivingUpdateEvent event)
	{
		if(event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer entity = (EntityPlayer)event.getEntity();
			
			if(!entity.onGround)
			{
				ticksInAir++;
			}
			else
			{
				ticksInAir = 0;
			}
			
			if(entity.isAirBorne && ticksInAir >= 60 && entity.inventory.getCurrentItem() != null && entity.inventory.getCurrentItem().getItem() instanceof ItemBow && entity.getItemInUseCount() >= 1)
			{
				if(entity.getItemInUseCount() <= 1)
				{
					ticksInAir = 0;
				}
				
				AxisAlignedBB axisalignedbb = new AxisAlignedBB(entity.posX - 15, entity.posY - 15, entity.posZ - 15, entity.posX + 15, entity.posY + 15, entity.posZ + 15);
				List<Entity> nearby = entity.worldObj.<Entity>getEntitiesWithinAABB(Entity.class, axisalignedbb);
						
				for (Entity entityFound: nearby)
				{
					if (entityFound instanceof Entity)
					{
						if(entityFound instanceof EntityArrow)
						{
							if(!(((EntityArrow)entityFound).shootingEntity instanceof EntityPlayer))
							{
								entityFound.motionX /= 2.5;
								entityFound.motionY /= 2.5;
								entityFound.motionZ /= 2.5;
							}
							else
							{
								/**I think it's because I have to override when the arrow shoots.
								 * There's a forge event for that.
								 * From what I found out, the arrow gets motionY from the player and shoots from that,
								 * so when motionY is getting divided, so is the arrows motion.
								 * 
								 * Look in the arrow class for clues!
								 */
							}
						}
						else
						{
							entityFound.motionX /= 2.5;
							entityFound.motionY /= 2.5;
							entityFound.motionZ /= 2.5;
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerJoined(PlayerEvent.PlayerLoggedInEvent event) //will update all players if it has the slate in the inventory
	{
		
	}
	
}
