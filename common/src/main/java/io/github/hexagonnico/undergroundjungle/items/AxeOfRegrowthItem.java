package io.github.hexagonnico.undergroundjungle.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class AxeOfRegrowthItem extends ModAxeItem {

    public AxeOfRegrowthItem(Tier tier, float baseDamage, float baseSpeed, Properties properties) {
        super(tier, baseDamage, baseSpeed, properties);
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack item, @NotNull Level world, @NotNull BlockState block, @NotNull BlockPos pos, @NotNull LivingEntity entity) {
        if(block.is(BlockTags.LOGS) && world.getBlockState(pos.below()).isAir() && world.getBlockState(pos.below(2)).is(BlockTags.DIRT)) {
            ResourceLocation saplingKey = new ResourceLocation(BuiltInRegistries.BLOCK.getKey(block.getBlock()).toString().replace("log", "sapling"));
            if(BuiltInRegistries.BLOCK.containsKey(saplingKey)) {
                world.setBlock(pos.below(), BuiltInRegistries.BLOCK.get(saplingKey).defaultBlockState(), 2);
                world.playSound(null, pos.below(), SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
            }
        }
        System.out.println(world.isClientSide());
        return super.mineBlock(item, world, block, pos, entity);
    }
}
