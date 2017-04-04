package com.theredmajora.botw.render.player;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSheikahSlate extends ModelBase
{
	ModelRenderer RightUpperPart;
	ModelRenderer RightLowerPart;
	ModelRenderer MiddlePart;
	ModelRenderer LeftLowerPart;
	ModelRenderer Screen;
	ModelRenderer Handle;
	ModelRenderer LeftUpperPart;
	ModelRenderer Slate;

	public ModelSheikahSlate()
	{
		this.textureWidth = 32;
		this.textureHeight = 32;
		
		RightUpperPart = new ModelRenderer(this, 0, 15);
		RightUpperPart.addBox(2.5F, -1F, -1F, 1, 2, 2);
		RightUpperPart.setRotationPoint(0F, 12F, 0F);
		RightUpperPart.setTextureSize(32, 32);
		RightLowerPart = new ModelRenderer(this, 18, 3);
		RightLowerPart.addBox(1.5F, 10F, -1F, 2, 1, 2);
		RightLowerPart.setRotationPoint(0F, 12F, 0F);
		RightLowerPart.setTextureSize(32, 32);
		MiddlePart = new ModelRenderer(this, 18, 6);
		MiddlePart.addBox(-0.5F, 10F, -1F, 1, 1, 2);
		MiddlePart.setRotationPoint(0F, 12F, 0F);
		MiddlePart.setTextureSize(32, 32);
		LeftLowerPart = new ModelRenderer(this, 18, 0);
		LeftLowerPart.addBox(-3.5F, 10F, -1F, 2, 1, 2);
		LeftLowerPart.setRotationPoint(0F, 12F, 0F);
		LeftLowerPart.setTextureSize(32, 32);
		Screen = new ModelRenderer(this, 0, 19);
		Screen.addBox(-3F, 1.5F, -0.2F, 6, 8, 1);
		Screen.setRotationPoint(0F, 12F, 0F);
		Screen.setTextureSize(32, 32);
		Handle = new ModelRenderer(this, 0, 11);
		Handle.addBox(-2.5F, -1.7F, -1F, 5, 2, 2);
		Handle.setRotationPoint(0F, 12F, 0F);
		Handle.setTextureSize(32, 32);
		LeftUpperPart = new ModelRenderer(this, 0, 15);
		LeftUpperPart.addBox(-3.5F, -1F, -1F, 1, 2, 2);
		LeftUpperPart.setRotationPoint(0F, 12F, 0F);
		LeftUpperPart.setTextureSize(32, 32);
		Slate = new ModelRenderer(this, 0, 0);
		Slate.addBox(-3.5F, 1F, -1F, 7, 9, 2);
		Slate.setRotationPoint(0F, 12F, 0F);
		Slate.setTextureSize(32, 32);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		RightUpperPart.render(f5);
		RightLowerPart.render(f5);
		MiddlePart.render(f5);
		LeftLowerPart.render(f5);
		Screen.render(f5);
		Handle.render(f5);
		LeftUpperPart.render(f5);
		Slate.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		RightUpperPart.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F / 10.0F;
		RightUpperPart.rotateAngleX = -0.2F + MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * (f1 / 1) * 0.5F / 5.0F;
		this.copyModelAngles(RightUpperPart, RightLowerPart);
		this.copyModelAngles(RightUpperPart, MiddlePart);
		this.copyModelAngles(RightUpperPart, LeftLowerPart);
		this.copyModelAngles(RightUpperPart, Screen);
		this.copyModelAngles(RightUpperPart, Handle);
		this.copyModelAngles(RightUpperPart, LeftUpperPart);
		this.copyModelAngles(RightUpperPart, Slate);
	}
}
