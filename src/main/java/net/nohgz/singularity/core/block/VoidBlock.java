package net.nohgz.singularity.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.nohgz.singularity.core.block.blockentity.VoidBlockEntity;

public class VoidBlock extends BaseEntityBlock {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 6.0D, 0.0D, 16.0D, 12.0D, 16.0D);

    public VoidBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }

    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new VoidBlockEntity(pPos, pState);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public ItemStack getCloneItemStack(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
        return ItemStack.EMPTY;
    }

    public boolean canBeReplaced(BlockState pState, Fluid pFluid) {
        return false;
    }
}


