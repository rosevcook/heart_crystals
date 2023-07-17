package com.rosemods.heart_crystals.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = HeartCrystals.MODID)
public class HCConfig {
    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Integer> minimum;
        public final ForgeConfigSpec.ConfigValue<Integer> maximum;

        private Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Heart Crystals Content Tweaks").push("content");
            this.minimum = builder.comment("Minimum heart value that you start the game with; default: 3").defineInRange("Minimum Hearts", 5, 1, 1000);
            this.maximum = builder.comment("Maximum amount of hearts you can have; default: 20").defineInRange("Maximum Hearts", 20, 1, 1000);
            builder.pop();
        }

    }

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);

        COMMON = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
    }
}
