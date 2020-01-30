package com.software.ddk.fabriclothing.gui;

import com.software.ddk.clothing.api.ICloth;
import com.software.ddk.fabriclothing.common.items.FabricItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.container.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;

public class ClothingLoomContainer extends Container {
    private final BlockContext context;
    private final Property selectedPattern;
    private Runnable inventoryChangeListener;
    private final Slot bannerSlot;
    private final Slot dyeSlot;
    private final Slot outputSlot;
    private long lastTakeResultTime;
    private final Inventory inputInventory;
    private final Inventory outputInventory;

    public ClothingLoomContainer(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, BlockContext.EMPTY);
    }

    public ClothingLoomContainer(int syncId, PlayerInventory playerInventory, final BlockContext blockContext) {
        super(ContainerType.LOOM, syncId);
        this.selectedPattern = Property.create();
        this.inventoryChangeListener = () -> {
        };
        this.inputInventory = new BasicInventory(3) {
            public void markDirty() {
                super.markDirty();
                ClothingLoomContainer.this.onContentChanged(this);
                ClothingLoomContainer.this.inventoryChangeListener.run();
            }
        };
        this.outputInventory = new BasicInventory(1) {
            public void markDirty() {
                super.markDirty();
                ClothingLoomContainer.this.inventoryChangeListener.run();
            }
        };
        this.context = blockContext;
        this.bannerSlot = this.addSlot(new Slot(this.inputInventory, 0, 13, 36) {
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() instanceof ICloth;
            }
        });
        this.dyeSlot = this.addSlot(new Slot(this.inputInventory, 1, 33, 36) {
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() instanceof FabricItem;
            }
        });
        this.outputSlot = this.addSlot(new Slot(this.outputInventory, 0, 143, 37) {
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
                ClothingLoomContainer.this.bannerSlot.takeStack(1);
                ClothingLoomContainer.this.dyeSlot.takeStack(1);
                if (!ClothingLoomContainer.this.bannerSlot.hasStack() || !ClothingLoomContainer.this.dyeSlot.hasStack()) {
                    ClothingLoomContainer.this.selectedPattern.set(0);
                }

                blockContext.run((world, blockPos) -> {
                    long l = world.getTime();
                    if (ClothingLoomContainer.this.lastTakeResultTime != l) {
                        world.playSound((PlayerEntity)null, blockPos, SoundEvents.UI_LOOM_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        ClothingLoomContainer.this.lastTakeResultTime = l;
                    }

                });
                return super.onTakeItem(player, stack);
            }
        });


        //inventory slots.
        int k;
        for(k = 0; k < 3; ++k) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }
        for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
        this.addProperty(this.selectedPattern);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, Blocks.LOOM);
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (id > 0 && id <= BannerPattern.LOOM_APPLICABLE_COUNT) {
            this.selectedPattern.set(id);
            this.updateOutputSlot();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        ItemStack itemStack = this.bannerSlot.getStack();
        ItemStack itemStack2 = this.dyeSlot.getStack();
        ItemStack itemStack4 = this.outputSlot.getStack();
        if (itemStack4.isEmpty() || !itemStack.isEmpty() && !itemStack2.isEmpty() && this.selectedPattern.get() > 0 && (this.selectedPattern.get() < BannerPattern.COUNT - 5)) {
           //stuff
        } else {
            this.outputSlot.setStack(ItemStack.EMPTY);
            this.selectedPattern.set(0);
        }

        this.updateOutputSlot();
        this.sendContentUpdates();
    }

    @Environment(EnvType.CLIENT)
    public void setInventoryChangeListener(Runnable inventoryChangeListener) {
        this.inventoryChangeListener = inventoryChangeListener;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slotList.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if (invSlot == this.outputSlot.id) {
                if (!this.insertItem(itemStack2, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onStackChanged(itemStack2, itemStack);
            } else if (invSlot != this.dyeSlot.id && invSlot != this.bannerSlot.id ) {
                if (itemStack2.getItem() instanceof ICloth) {
                    if (!this.insertItem(itemStack2, this.bannerSlot.id, this.bannerSlot.id + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemStack2.getItem() instanceof FabricItem) {
                    if (!this.insertItem(itemStack2, this.dyeSlot.id, this.dyeSlot.id + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (invSlot >= 4 && invSlot < 31) {
                    if (!this.insertItem(itemStack2, 31, 40, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (invSlot >= 31 && invSlot < 40 && !this.insertItem(itemStack2, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, itemStack2);
        }

        return itemStack;
    }

    @Override
    public void close(PlayerEntity player) {
        super.close(player);
        this.context.run((world, blockPos) -> {
            this.dropInventory(player, player.world, this.inputInventory);
        });
    }

    private void updateOutputSlot() {
        if (this.selectedPattern.get() > 0) {
            ItemStack itemStack = this.bannerSlot.getStack();
            ItemStack itemStack2 = this.dyeSlot.getStack();
            ItemStack itemStack3 = ItemStack.EMPTY;

            if (!itemStack.isEmpty() && !itemStack2.isEmpty()) {
                //DyeColor dyeColor = ((DyeItem) itemStack2.getItem()).getColor();
                int fabriColor = ((DyeableItem) itemStack2.getItem()).getColor(itemStack2);
                itemStack3 = itemStack.copy();

                //seleccionar segun patern 0 - base 1 - detalles
                switch (this.selectedPattern.get()){
                    case 1:
                        if (itemStack3.getItem() instanceof DyeableItem){
                            //System.out.println("color base");
                            ((DyeableItem) itemStack3.getItem()).setColor(itemStack3, fabriColor);
                        }
                        break;
                    case 2:
                        if (itemStack3.getItem() instanceof ICloth){
                            ((ICloth) itemStack3.getItem()).setColorOverlay(itemStack3, fabriColor);
                            //System.out.println("color overlay");
                        }
                        break;
                }

            }

            if (!ItemStack.areEqualIgnoreDamage(itemStack3, this.outputSlot.getStack())) {
                this.outputSlot.setStack(itemStack3);
            }
        }

    }

    @Environment(EnvType.CLIENT)
    public Slot getBannerSlot() {
        return this.bannerSlot;
    }

    @Environment(EnvType.CLIENT)
    public Slot getDyeSlot() {
        return this.dyeSlot;
    }


    @Environment(EnvType.CLIENT)
    public Slot getOutputSlot() {
        return this.outputSlot;
    }
}
