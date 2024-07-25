package net.nohgz.singularity.core.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.nohgz.singularity.client.screen.SingularityCompressorMenu;
import net.nohgz.singularity.registry.core.block.BlockEntityRegistry;
import net.nohgz.singularity.registry.core.item.ItemRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SingularityCompressorBlockEntity extends BlockEntity implements MenuProvider {

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public SingularityCompressorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.SINGULARITY_COMPRESSOR_BLOCK_ENTITY.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex)  {
                    case 0 -> SingularityCompressorBlockEntity.this.progress;
                    case 1 -> SingularityCompressorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> SingularityCompressorBlockEntity.this.progress = pValue;
                    case 1 -> SingularityCompressorBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    private LazyOptional<IItemHandler> itemHandlerLazyOptional = LazyOptional.empty();
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return itemHandlerLazyOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Singularity Compressor");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new SingularityCompressorMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        itemHandlerLazyOptional = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemStackHandler.serializeNBT());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemStackHandler.deserializeNBT(pTag.getCompound("inventory"));
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemHandlerLazyOptional.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, SingularityCompressorBlockEntity pBlockEntity) {
        if(pLevel.isClientSide()) {
            return;
        }

        if (hasRecipe(pBlockEntity)) {
            pBlockEntity.progress++;
            setChanged(pLevel, pPos, pState);

            if(pBlockEntity.progress >= pBlockEntity.maxProgress) {
                craftItem(pBlockEntity);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }

    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(SingularityCompressorBlockEntity pBlockEntity) {
        if (hasRecipe(pBlockEntity)) {
            pBlockEntity.itemStackHandler.extractItem(1, 1, false);
            pBlockEntity.itemStackHandler.setStackInSlot(2, new ItemStack(ItemRegistry.EVIL_BLOCK.get(),
                    pBlockEntity.itemStackHandler.getStackInSlot(2).getCount() + 1));

            pBlockEntity.resetProgress();
        }
    }


    private static boolean hasRecipe(SingularityCompressorBlockEntity pBlockEntity) {
        SimpleContainer inventory = new SimpleContainer(pBlockEntity.itemStackHandler.getSlots());
        for (int i = 0; i < pBlockEntity.itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, pBlockEntity.itemStackHandler.getStackInSlot(i));
        }

        boolean hasItemInFirstSlot = pBlockEntity.itemStackHandler.getStackInSlot(1).getItem() == ItemRegistry.BOOM_BLOCK.get();

        return hasItemInFirstSlot && canInsertAmountIntoOutput(inventory) &&
                canInsertItemIntoOutput(inventory, new ItemStack(ItemRegistry.EVIL_BLOCK.get(), 1));
    }

    private static boolean canInsertItemIntoOutput(SimpleContainer pInventory, ItemStack pItemStack) {
        return pInventory.getItem(2).getItem() == pItemStack.getItem() || pInventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutput(SimpleContainer pInventory) {
        return pInventory.getItem(2).getMaxStackSize() > pInventory.getItem(2).getCount();
    }
}
