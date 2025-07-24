package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.client.entity.CupidsArrowRenderer;
import com.rosemods.heart_crystals.common.entity.CupidsArrow;
import com.rosemods.heart_crystals.core.HeartCrystals;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HeartCrystals.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HCEntityTypes {
    public static final EntitySubRegistryHelper HELPER = HeartCrystals.REGISTRY_HELPER.getEntitySubHelper();

    public static final RegistryObject<EntityType<CupidsArrow>> CUPIDS_ARROW = HELPER.createEntity("cupids_arrow", CupidsArrow::new, CupidsArrow::new, MobCategory.MISC, .5f, .5f);

    @OnlyIn(Dist.CLIENT)
    public static void registerClient() {
        EntityRenderers.register(CUPIDS_ARROW.get(), CupidsArrowRenderer::new);
    }

}
