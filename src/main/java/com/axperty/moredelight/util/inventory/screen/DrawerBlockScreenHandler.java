package com.axperty.moredelight.util.inventory.screen;

import com.axperty.moredelight.registry.ScreenHandlersRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;


public class DrawerBlockScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public DrawerBlockScreenHandler(int syncId, PlayerInventory playerInventory) {
        // Make sure to pass the correct slot count to the SimpleInventory and ArrayPropertyDelegate
        this(syncId, playerInventory, new SimpleInventory(9), new ArrayPropertyDelegate(9));
    }

    public DrawerBlockScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate delegate) {
        super(ScreenHandlersRegistry.DRAWER_BLOCK_HANDLER, syncId);
        checkSize(inventory, 9);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = delegate;

        this.addSlot(new Slot(inventory, 0, 8, 36));
        this.addSlot(new Slot(inventory, 1, 26, 36));
        this.addSlot(new Slot(inventory, 2, 44, 36));
        this.addSlot(new Slot(inventory, 3, 62, 36));
        this.addSlot(new Slot(inventory, 4, 80, 36));
        this.addSlot(new Slot(inventory, 5, 98, 36));
        this.addSlot(new Slot(inventory, 6, 116, 36));
        this.addSlot(new Slot(inventory, 7, 134, 36));
        this.addSlot(new Slot(inventory, 8, 152, 36));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    // This part handles how putting items inside the block works, changing anything here might cause the game to crash.
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }
            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
            if (originalStack.getCount() == newStack.getCount()) {
                return ItemStack.EMPTY;
            } else {
                slot.onTakeItem(player, originalStack);
                return newStack;
            }
        }
        return newStack;
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 67 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 125));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }


}
