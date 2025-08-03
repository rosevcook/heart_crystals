package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.client.entity.CupidsArrowRenderer;
import com.rosemods.heart_crystals.common.entity.CupidsArrow;
import com.rosemods.heart_crystals.core.HeartCrystals;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class HCEntityTypes {
    public static final EntitySubRegistryHelper ENTITY_TYPES = HeartCrystals.REGISTRY_HELPER.getEntitySubHelper();

    public static final DeferredHolder<EntityType<?>, EntityType<CupidsArrow>> CUPIDS_ARROW = ENTITY_TYPES.createEntity("cupids_arrow", CupidsArrow::new, MobCategory.MISC, .5f, .5f);

    @OnlyIn(Dist.CLIENT)
    public static void registerClient() {
        EntityRenderers.register(CUPIDS_ARROW.get(), CupidsArrowRenderer::new);
    }

}
