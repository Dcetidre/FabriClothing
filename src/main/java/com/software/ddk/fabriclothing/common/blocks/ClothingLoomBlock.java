package com.software.ddk.fabriclothing.common.blocks;

import com.software.ddk.fabriclothing.FabriClothing;
import com.software.ddk.fabriclothing.gui.ClothingLoomContainer;
import com.software.ddk.fabriclothing.gui.ClothingLoomScreen;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ClothingLoomBlock extends Block {

    public ClothingLoomBlock() {
        super(FabricBlockSettings.of(Material.WOOD).strength(1.0f, 1.0f).build());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (!world.isClient()){
            ContainerProviderRegistry.INSTANCE.openContainer(new Identifier(FabriClothing.MOD_ID, "clothing_loom_block"), player, buf -> buf.writeBlockPos(pos));
        }

        return ActionResult.SUCCESS;
    }

}
