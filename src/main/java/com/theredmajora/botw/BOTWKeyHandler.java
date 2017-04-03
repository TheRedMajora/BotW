package com.theredmajora.botw;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class BOTWKeyHandler
{
	public static KeyBinding[] keyBindings;
	
	public static void init()
	{
		keyBindings = new KeyBinding[1]; 
		  
		keyBindings[0] = new KeyBinding("key.offhandequip.desc", Keyboard.KEY_SEMICOLON, "key.gameplay.category");

		for (int i = 0; i < keyBindings.length; ++i) 
		{
		    ClientRegistry.registerKeyBinding(keyBindings[i]);
		}
	}
}
