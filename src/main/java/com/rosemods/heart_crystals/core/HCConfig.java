package com.rosemods.heart_crystals.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = HeartCrystals.MOD_ID)
public class HCConfig {
    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Integer> minimum;
        public final ForgeConfigSpec.ConfigValue<Integer> maximum;
        public final ForgeConfigSpec.ConfigValue<Integer> regenRange;
        public final ForgeConfigSpec.ConfigValue<Integer> maxYLevel;

        private Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Heart Crystals Content Tweaks").push("content");
            this.minimum = builder.comment("Minimum heart value that you start the game with; default: 5").defineInRange("Minimum Hearts", 5, 1, 1000);
            this.maximum = builder.comment("Maximum amount of hearts you can have; default: 20").defineInRange("Maximum Hearts", 20, 1, 1000);
            this.regenRange = builder.comment("The range in blocks that heart lanterns can give the regeneration effect; default: 2").defineInRange("Heart Lantern Regen Range", 2, 1, 10);
            builder.pop();
            builder.comment("Heart Crystals World Gen").push("world-gen");
            this.maxYLevel = builder.comment("Max Y Level that Heart Crystals can spawn at; default: 30").defineInRange("Max Y Level", 30, -60, 60);
            builder.pop();
        }

    }

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);

        COMMON = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
    }
}
