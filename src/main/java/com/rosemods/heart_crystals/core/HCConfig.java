package com.rosemods.heart_crystals.core;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public final class HCConfig {
    public static final Common COMMON;
    public static final ModConfigSpec COMMON_SPEC;

    public static class Common {
        public final ModConfigSpec.ConfigValue<Integer> minimum;
        public final ModConfigSpec.ConfigValue<Integer> maximum;
        public final ModConfigSpec.ConfigValue<Integer> regenRange;
        public final ModConfigSpec.ConfigValue<Integer> maxYLevel;

        private Common(ModConfigSpec.Builder builder) {
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
        final Pair<Common, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(Common::new);

        COMMON = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
    }

}
