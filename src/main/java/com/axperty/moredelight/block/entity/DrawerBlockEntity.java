package com.axperty.moredelight.block.entity;

import com.axperty.moredelight.block.custom.DrawerBlock;
import com.axperty.moredelight.registry.BlockEntityRegistry;
import com.axperty.moredelight.util.inventory.ImplementedInventory;
import com.axperty.moredelight.util.inventory.screen.DrawerBlockScreenHandler;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class DrawerBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private boolean open = false;

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);
    protected final PropertyDelegate propertyDelegate;

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
        markDirty(); // Marks the block entity as dirty to trigger synchronization.
        sync();
    }

    private void sync() {
        if (world != null) {
            BlockState state = world.getBlockState(pos);
            world.updateListeners(pos, state, state, 3);
        }
    }

    private int ticksUntilClose = 20; // 20 ticks delay

    public DrawerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityRegistry.DRAWER_BLOCK_ENTITY, blockPos, blockState);

        this.propertyDelegate = new PropertyDelegate() {
            private final int[] properties = new int[9];

            public int get(int index) {
                return properties[index];
            }

            public void set(int index, int value) {
                properties[index] = value;
            }

            public int size() {
                return properties.length;
            }
        };
        // Register the server tick event listener
        ServerTickEvents.END_SERVER_TICK.register(server -> onServerTick());
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.moredelight.drawer_block");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        if (!player.getEntityWorld().isClient) {
            player.getEntityWorld().playSound(null, pos, SoundEvents.BLOCK_BARREL_OPEN, SoundCategory.BLOCKS, .5f, 1.0f);
        }
        return new DrawerBlockScreenHandler(syncId, inv, this, this.propertyDelegate) {
            @Override
            public void onClosed(PlayerEntity player) {
                super.onClosed(player);
                if (!player.getEntityWorld().isClient) {
                    player.getEntityWorld().playSound(null, pos, SoundEvents.BLOCK_BARREL_CLOSE, SoundCategory.BLOCKS, .5f, 1.0f);
                }
                assert world != null;
                if (world.getServer() != null) {
                    world.getServer().submit(() -> {
                        ticksUntilClose = 2;
                    });
                }
            }
        };
    }
    private void onServerTick() {
        if (ticksUntilClose > 0) {
            ticksUntilClose--;

            if (ticksUntilClose == 0) {
                setOpen(false);
                assert world != null;
                BlockState currentState = world.getBlockState(pos);
                if (currentState.getBlock() instanceof DrawerBlock) {
                    world.setBlockState(pos, currentState.with(Properties.OPEN, false));
                }
            }
        }
    }


    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        this.open = nbt.getBoolean("open");
        super.readNbt(nbt);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, inventory);
        nbt.putBoolean("open", this.open);
        super.writeNbt(nbt);
    }

    public DefaultedList<ItemStack> getDroppableInventory() {
        return inventory;
    }
}
