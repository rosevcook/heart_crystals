package com.rosemods.heart_crystals.core.data.client;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCSoundEvents;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.minecraftforge.data.event.GatherDataEvent;

public class HCSoundProvider extends SoundDefinitionsProvider {

    public HCSoundProvider(GatherDataEvent event) {
        super(event.getGenerator(), HeartCrystals.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    public void registerSounds() {
        this.add(HCSoundEvents.HEART_CRYSTAL_USE.get(), definition().with(sound(HeartCrystals.REGISTRY_HELPER.prefix("heart_crystal")).stream()));
    }

}
