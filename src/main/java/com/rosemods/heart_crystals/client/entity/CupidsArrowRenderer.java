package com.rosemods.heart_crystals.client.entity;

import com.rosemods.heart_crystals.common.entity.CupidsArrow;
import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CupidsArrowRenderer extends ArrowRenderer<CupidsArrow> {
    private static final ResourceLocation CUPIDS_ARROW = HeartCrystals.location("textures/entity/projectiles/cupids_arrow.png");

    public CupidsArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public ResourceLocation getTextureLocation(CupidsArrow entity) {
        return CUPIDS_ARROW;
    }

}

