package com.theredmajora.botw.proxy;

import java.util.HashMap;
import java.util.Map;

import com.theredmajora.botw.BOTW;
import com.theredmajora.botw.util.ENUMClientPacketState;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy
{
	static Map<String, boolean[]> ShouldRender = new HashMap();
	
	public static boolean[] getShouldRenders(String name) {
		
		return ShouldRender.get(name);
	}
	
	public static void addShouldRender(String Name, boolean[] shouldRender)
	{
		ShouldRender.put(Name, shouldRender);
	}
	
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
