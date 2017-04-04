package com.theredmajora.botw;

import com.theredmajora.botw.items.BOTWItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BOTWTab extends CreativeTabs
{
	public BOTWTab()
	{
		super(BOTW.MODID);
	}

	@Override
	public Item getTabIconItem()
	{
		return BOTWItems.sheikah_slate;
	}

}
