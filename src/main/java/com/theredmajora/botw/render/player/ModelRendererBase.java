package com.theredmajora.botw.render.player;

import org.lwjgl.opengl.GL11;

import com.theredmajora.botw.items.ItemParaglider;
import com.theredmajora.botw.items.ItemSheikahSlate;

import api.player.model.ModelPlayerAPI;
import api.player.model.ModelPlayerBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class ModelRendererBase extends ModelPlayerBase
{
	public ModelRendererBase(ModelPlayerAPI modelPlayerAPI)
	{
		super(modelPlayerAPI);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
	}
	
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(limbSwing, limbSwingAmount, f2, f3, f4, f5, entity);
		
		ItemStack stack = ((EntityPlayer) entity).inventory.getCurrentItem();

		 if (stack != null)
		 {
			 if (stack.getItem() instanceof ItemParaglider)
			 {
				 modelPlayer.bipedRightArm.rotateAngleY = -0.2F + modelPlayer.bipedBody.rotateAngleY;
				 modelPlayer.bipedRightArm.rotateAngleX = -((float)Math.PI) + modelPlayer.bipedBody.rotateAngleX;
				 modelPlayer.bipedLeftArm.rotateAngleY = 0.2F + modelPlayer.bipedBody.rotateAngleY;
				 modelPlayer.bipedLeftArm.rotateAngleX = -((float)Math.PI) + modelPlayer.bipedBody.rotateAngleX;

			    if (!entity.onGround)
			    {
			    	if (entity == null || !((EntityPlayer) entity).capabilities.isFlying)
			    	{
			    		modelPlayer.bipedLeftLeg.rotateAngleX = modelPlayer.bipedBody.rotateAngleX + 0.2F + MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / 30.0F;
			    		modelPlayer.bipedLeftLeg.rotateAngleY = modelPlayer.bipedBody.rotateAngleY;
			    		modelPlayer.bipedLeftLeg.rotateAngleZ = modelPlayer.bipedBody.rotateAngleZ - 0.1F - MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / 60.0F;
			    		modelPlayer.bipedRightLeg.rotateAngleX = modelPlayer.bipedBody.rotateAngleX + 0.2F + MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / 30.0F;
			    		modelPlayer.bipedRightLeg.rotateAngleY = modelPlayer.bipedBody.rotateAngleY;
			    		modelPlayer.bipedRightLeg.rotateAngleZ = modelPlayer.bipedBody.rotateAngleZ + 0.1F - MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / 60.0F;
			    	}
			    }
			 }
			 if (stack.getItem() instanceof ItemSheikahSlate)
			 {
				 modelPlayer.bipedRightArm.rotateAngleY = -0.2F + modelPlayer.bipedBody.rotateAngleY;
				 modelPlayer.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F) + modelPlayer.bipedHead.rotateAngleX + 0.075F;
				 modelPlayer.bipedLeftArm.rotateAngleY = 0.2F + modelPlayer.bipedBody.rotateAngleY;
				 modelPlayer.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F) + modelPlayer.bipedHead.rotateAngleX + 0.075F;
			 }
		}
		 
		modelPlayer.copyModelAngles(modelPlayer.bipedLeftLeg, modelPlayer.bipedLeftLegwear);
		modelPlayer.copyModelAngles(modelPlayer.bipedRightLeg, modelPlayer.bipedRightLegwear);
		modelPlayer.copyModelAngles(modelPlayer.bipedLeftArm, modelPlayer.bipedLeftArmwear);
		modelPlayer.copyModelAngles(modelPlayer.bipedRightArm, modelPlayer.bipedRightArmwear);
	}
}