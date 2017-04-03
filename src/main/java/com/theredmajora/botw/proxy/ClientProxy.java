package com.theredmajora.botw.proxy;

import com.theredmajora.botw.BOTW;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerItemRenderer(Item item, int meta, String name)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(BOTW.MODID + ":" + name, "inventory"));
	}
	
	@Override
	public void init()
	{
	}
}
