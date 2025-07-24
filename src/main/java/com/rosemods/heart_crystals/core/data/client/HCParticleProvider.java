package com.rosemods.heart_crystals.core.data.client;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCParticleTypes;
import net.minecraftforge.data.event.GatherDataEvent;

public class HCParticleProvider extends ParticleProvider {

    public HCParticleProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), HeartCrystals.MOD_ID);
    }

    @Override
    protected void addParticles() {
        this.add(HCParticleTypes.CUPIDS_ARROW.get(), "cupids_arrow");
    }

}
