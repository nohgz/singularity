package net.nohgz.singularity.client.screen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.nohgz.singularity.core.block.blockentity.SingularityCompressorBlockEntity;
import net.nohgz.singularity.registry.core.block.BlockRegistry;
import net.nohgz.singularity.registry.core.screens.MenuRegistry;
import org.jetbrains.annotations.Nullable;

public class SingularityCompressorMenu extends AbstractContainerMenu {

    private final Level pLevel;
    public final SingularityCompressorBlockEntity pCompressorBlockEntity;
    private final ContainerData pCompressorContainerData;

    public SingularityCompressorMenu(int pId, Inventory pInventory, FriendlyByteBuf pFriendlyByteBuf) {
        this(pId, pInventory, pInventory.player.level.getBlockEntity(pFriendlyByteBuf.readBlockPos()), new SimpleContainerData(2));
    }

    public SingularityCompressorMenu(int pId, Inventory pInventory, BlockEntity pBlockEntity, ContainerData pContainerData) {
        super(MenuRegistry.SINGULARITY_COMPRESSOR_MENU.get(), pId);
        checkContainerSize(pInventory, 3);
        pCompressorBlockEntity = (SingularityCompressorBlockEntity) pBlockEntity;
        this.pLevel = pInventory.player.level;
        this.pCompressorContainerData = pContainerData;

        addPlayerInventory(pInventory);
        addPlayerHotbar(pInventory);

        this.pCompressorBlockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 12, 15));
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 12, 15));
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 12, 15));
        });

        addDataSlots(pContainerData);

    }

    public boolean isCrafting()

    {
        return pCompressorContainerData.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.pCompressorContainerData.get(0);
        int maxProgress = this.pCompressorContainerData.get(0);
        int progressArrowSize = 26; // height in pixels of arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    protected SingularityCompressorMenu(@Nullable MenuType<?> pMenuType, int pContainerId, Level pLevel, SingularityCompressorBlockEntity pCompressorBlockEntity, ContainerData pCompressorContainerData) {
        super(pMenuType, pContainerId);
        this.pLevel = pLevel;
        this.pCompressorBlockEntity = pCompressorBlockEntity;
        this.pCompressorContainerData = pCompressorContainerData;
    }

    public static final int HOTBAR_SLOT_COUNT = 9;
    public static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    public static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    public static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    public static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    public static final int VANILLA_FIRST_SLOT_INDEX = 0;
    public static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    public static final int TE_INVENTORY_SLOT_COUNT = 3;

    // credit to dieseiben07 | https://github.com/diesieben07/SevenCommons
    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // check if slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // this is vanilla container will merge stack into tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX,
                    TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the player's inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX,
                    VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex: " + pIndex);
            return ItemStack.EMPTY;
        }

        // if the stack size == 0 (the whole stack moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(pPlayer, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(pLevel, pCompressorBlockEntity.getBlockPos()),
                pPlayer, BlockRegistry.SINGULARITY_COMPRESSOR.get());
    }

    private void addPlayerInventory(Inventory pInventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(pInventory, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory pInventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(pInventory, i, 8 + i * 18, 144));
        }
    }
}
