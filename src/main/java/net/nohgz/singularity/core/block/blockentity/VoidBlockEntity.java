package net.nohgz.singularity.core.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.nohgz.singularity.registry.core.block.BlockEntityRegistry;

public class VoidBlockEntity extends BlockEntity {
    protected VoidBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    public VoidBlockEntity(BlockPos pPos, BlockState pBlockState) {
        this(BlockEntityRegistry.VOID_BLOCK_ENTITY.get(), pPos, pBlockState);
        //this(BlockEntityType.END_PORTAL, pPos, pBlockState);
    }

    public boolean shouldRenderFace(Direction pFace) {
        return Block.shouldRenderFace(this.getBlockState(), this.level, this.getBlockPos(), pFace, this.getBlockPos().relative(pFace));
    }
}