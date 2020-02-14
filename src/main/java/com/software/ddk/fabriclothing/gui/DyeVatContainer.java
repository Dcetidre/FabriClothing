package com.software.ddk.fabriclothing.gui;

import com.software.ddk.clothing.api.ICloth;
import com.software.ddk.fabriclothing.Contents;
import com.software.ddk.fabriclothing.common.items.FabricItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.container.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;

public class DyeVatContainer extends Container {
    private final BlockContext context;
    private final Property selectedOption;
    private Runnable inventoryChangeListener;
    private final Slot clothSlot;
    private final Slot dyeSlot;
    private final Slot outputSlot;
    private long lastTakeResultTime;
    private final Inventory inputInventory;
    private final Inventory outputInventory;
    private PlayerInventory playerInventory;

    public DyeVatContainer(int syncId, PlayerInventory playerInventory, final BlockContext blockContext) {
        super(ContainerType.LOOM, syncId);
        this.selectedOption = Property.create();
        this.inventoryChangeListener = () -> {};
        this.playerInventory = playerInventory;
        this.context = blockContext;

        this.inputInventory = new BasicInventory(3) {
            public void markDirty() {
                super.markDirty();
                DyeVatContainer.this.onContentChanged(this);
                DyeVatContainer.this.inventoryChangeListener.run();
            }
        };

        this.outputInventory = new BasicInventory(1) {
            public void markDirty() {
                super.markDirty();
                DyeVatContainer.this.inventoryChangeListener.run();
            }
        };

        this.clothSlot = this.addSlot(new Slot(this.inputInventory, 0, 13, 36) {
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
                DyeVatContainer.this.clothSlot.takeStack(1);
                DyeVatContainer.this.dyeSlot.takeStack(1);
                if (!DyeVatContainer.this.clothSlot.hasStack() || !DyeVatContainer.this.dyeSlot.hasStack()) {
                    DyeVatContainer.this.selectedOption.set(0);
                }

                blockContext.run((world, blockPos) -> {
                    long l = world.getTime();
                    if (DyeVatContainer.this.lastTakeResultTime != l) {
                        //world.playSound((PlayerEntity)null, blockPos, SoundEvents.UI_LOOM_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        DyeVatContainer.this.lastTakeResultTime = l;
                    }
                });
                return super.onTakeItem(player, stack);
            }
        });

        //inventory slots.
        addInventorySlots();

        this.addProperty(this.selectedOption);
    }

    private void addInventorySlots(){
        for(int k = 0; k < 3; ++k) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }
        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, Contents.CLOTHING_DYEVAT_BLOCK);
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        //if (id > 0 && id <= BannerPattern.LOOM_APPLICABLE_COUNT) {
        //            this.selectedOption.set(id);
        //            this.updateOutputSlot();
        //            return true;
        //        } else {
        //            return false;
        //        }

        this.selectedOption.set(id);
        this.updateOutputSlot();
        return true;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    @Environment(EnvType.CLIENT)
    public void setInventoryChangeListener(Runnable inventoryChangeListener) {
        this.inventoryChangeListener = inventoryChangeListener;
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        //ItemStack itemStack = this.clothSlot.getStack();
        //ItemStack itemStack2 = this.dyeSlot.getStack();
        //ItemStack itemStack4 = this.outputSlot.getStack();
        this.outputSlot.setStack(ItemStack.EMPTY);
        this.selectedOption.set(0);
        this.updateOutputSlot();
        this.sendContentUpdates();
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if (slot != null && slot.hasStack()) {
            ItemStack selectedStack = slot.getStack();
            itemStack = selectedStack.copy();
            if (invSlot == this.outputSlot.id) {
                if (!this.insertItem(selectedStack, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onStackChanged(selectedStack, itemStack);
            } else if (invSlot != this.dyeSlot.id && invSlot != this.clothSlot.id ) {
                if (selectedStack.getItem() instanceof ICloth) {
                    if (!this.insertItem(selectedStack, this.clothSlot.id, this.clothSlot.id + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (selectedStack.getItem() instanceof FabricItem) {
                    if (!this.insertItem(selectedStack, this.dyeSlot.id, this.dyeSlot.id + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (invSlot >= 4 && invSlot < 31) {
                    if (!this.insertItem(selectedStack, 31, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (invSlot >= 31 && invSlot < 40 && !this.insertItem(selectedStack, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(selectedStack, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (selectedStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
            if (selectedStack.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTakeItem(player, selectedStack);
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
        if (this.selectedOption.get() > 0) {
            ItemStack clothItem = this.clothSlot.getStack();
            ItemStack fabricItem = this.dyeSlot.getStack();
            ItemStack outputItem = ItemStack.EMPTY;

            if (!clothItem.isEmpty() && !fabricItem.isEmpty()) {
                int fabriColor = ((DyeableItem) fabricItem.getItem()).getColor(fabricItem);
                outputItem = new ItemStack(clothItem.getItem(), 1);

                switch (this.selectedOption.get()){
                    case 1:
                        if (outputItem.getItem() instanceof DyeableItem){
                            ((DyeableItem) outputItem.getItem()).setColor(outputItem, fabriColor);
                        }
                        break;
                    case 2:
                        if (outputItem.getItem() instanceof ICloth){
                            ((ICloth) outputItem.getItem()).setColorOverlay(outputItem, fabriColor);
                        }
                        break;
                }
            }
            if (!ItemStack.areEqualIgnoreDamage(outputItem, this.outputSlot.getStack())) {
                this.outputSlot.setStack(outputItem);
            }
        }
    }

    public Slot getClothSlot() {
        return this.clothSlot;
    }
    public Slot getDyeSlot() {
        return this.dyeSlot;
    }
    public Slot getOutputSlot() {
        return this.outputSlot;
    }
}
