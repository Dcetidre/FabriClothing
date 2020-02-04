package com.software.ddk.fabriclothing.gui;

import com.software.ddk.fabriclothing.FabriClothing;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.ContainerScreen;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

public class DyeVatScreen extends ContainerScreen<DyeVatContainer> {
    private static final Identifier TEXTURE = new Identifier(FabriClothing.MOD_ID, "textures/gui/clothing_dyevat.png");
    private static final String TITLE = "Dye Vat";
    private ItemStack cloth;
    private ItemStack dye;
    private boolean canApplyDye;
    private int firstButtonId;

    public DyeVatScreen(DyeVatContainer container) {
        super(container, container.getPlayerInventory(), new LiteralText(TITLE));
        this.cloth = ItemStack.EMPTY;
        this.dye = ItemStack.EMPTY;
        this.firstButtonId = 1;
        container.setInventoryChangeListener(this::onInventoryChanged);
    }

    @Override
    public void render(int mouseX, int mouseY, float delta) {
        super.render(mouseX, mouseY, delta);
        this.drawMouseoverTooltip(mouseX, mouseY);
    }

    @Override
    protected void drawForeground(int mouseX, int mouseY) {
        this.font.draw(this.title.asFormattedString(), 8.0F, 4.0F, 4210752);
        this.font.draw(this.playerInventory.getDisplayName().asFormattedString(), 8.0F, (float)(this.containerHeight - 96 + 2), 4210752);

        //button texts
        if (this.canApplyDye) {
            this.font.draw("base", 65.0f, 29.0f, 4210752);
            this.font.draw("overlay", 65.0f, 55.0f, 4210752);
        }
    }

    @Override
    protected void drawBackground(float delta, int mouseX, int mouseY) {
        this.renderBackground();
        //this.minecraft.getTextureManager().bindTexture(TEXTURE);
        MinecraftClient.getInstance().getTextureManager().bindTexture(TEXTURE);
        int i = this.x;
        int j = this.y;
        this.blit(i, j, 0, 0, this.containerWidth, this.containerHeight);

        Slot slot = (this.container).getClothSlot();
        Slot slot2 = (this.container).getDyeSlot();
        //Slot slot4 = (this.container).getOutputSlot();

        if (!slot.hasStack()) {
            this.blit(i + slot.xPosition, j + slot.yPosition, this.containerWidth, 0, 16, 16);
        }
        if (!slot2.hasStack()) {
            this.blit(i + slot2.xPosition, j + slot2.yPosition, this.containerWidth + 16, 0, 16, 16);
        }

        DiffuseLighting.disableGuiDepthLighting();

        if (this.canApplyDye) {
            int pX = i + 60;
            int pY = j + 13;
            int m = this.firstButtonId;
            double mX = mouseX - (double)(pX + m % 4 * 14);
            double mY = mouseY - (double)(pY + m / 4 * 14);

            //dibujar botones
            if (mX >= -12.5D && mY >= 11.5D && mX < 45.0D && mY < 28.5D){
                this.blit(pX, pY + 10, 0, this.containerHeight + 40, 60, 20);
            } else {
                this.blit(pX, pY + 10, 0, this.containerHeight, 60, 20);
            }

            if (mX >= -12.5D && mY >= 36.0D && mX < 45.0D && mY < 54.0D){
                this.blit(pX, pY + 35, 0, this.containerHeight + 40, 60, 20);
            } else {
                this.blit(pX, pY + 35, 0, this.containerHeight, 60, 20);
            }
        }
        DiffuseLighting.enableGuiDepthLighting();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.canApplyDye) {
            int pX = this.x + 60;
            int pY = this.y + 13;
            int m = this.firstButtonId;
            double mX = mouseX - (double)(pX + m % 4 * 14);
            double mY = mouseY - (double)(pY + m / 4 * 14);

            //button click listeners
            if (mX >= -12.5D && mY >= 11.5D && mX < 45.0D && mY < 28.5D && (this.container).onButtonClick(this.minecraft.player, 1)){
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_LOOM_SELECT_PATTERN, 1.0F));
                this.minecraft.interactionManager.clickButton((this.container).syncId, 1);
                return true;
            }
            if (mX >= -12.5D && mY >= 36.0D && mX < 45.0D && mY < 54.0D && (this.container).onButtonClick(this.minecraft.player, 2)){
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_LOOM_SELECT_PATTERN, 1.0F));
                this.minecraft.interactionManager.clickButton((this.container).syncId, 2);
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseScrolled(double d, double e, double amount) {
        return true;
    }

    @Override
    protected boolean isClickOutsideBounds(double mouseX, double mouseY, int left, int top, int button) {
        return mouseX < (double)left || mouseY < (double)top || mouseX >= (double)(left + this.containerWidth) || mouseY >= (double)(top + this.containerHeight);
    }

    private void onInventoryChanged() {
        //ItemStack itemStack = (this.container).getOutputSlot().getStack();
        ItemStack itemStack2 = (this.container).getClothSlot().getStack();
        ItemStack itemStack3 = (this.container).getDyeSlot().getStack();

        if (!ItemStack.areEqualIgnoreDamage(itemStack2, this.cloth) || !ItemStack.areEqualIgnoreDamage(itemStack3, this.dye)) {
            this.canApplyDye = !itemStack2.isEmpty() && !itemStack3.isEmpty();
        }

        this.cloth = itemStack2.copy();
        this.dye = itemStack3.copy();
    }
}

