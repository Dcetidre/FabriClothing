package com.software.ddk.fabriclothing.common.items.hats.generic;

import com.software.ddk.clothing.api.ClothRenderData;
import com.software.ddk.clothing.api.ICloth;
import com.software.ddk.fabriclothing.FabriClothing;
import dev.emi.trinkets.api.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class BaseTrinketsHat extends Item implements ITrinket, DyeableItem, ICloth {
    private AbstractClientPlayerEntity playerEntity;

    public BaseTrinketsHat() {
        super(new Item.Settings().group(FabriClothing.GROUP));
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group.equals(SlotGroups.HEAD) && slot.equals(Slots.MASK);
    }

    @Override
    public EquipmentSlot slotType() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public String clothId() {
        return "type0";
    }

    @Override
    public String modId() {
        return FabriClothing.MOD_ID;
    }

    @Override
    public boolean customEquip() {
        return true;
    }

    @Override
    public boolean[][] equipLayers() {
        return new boolean[][]{
                {false, false, false, false},
                {false, false, false, false}
        };
    }

    @Override
    public boolean customModel() {
        return true;
    }

    public AbstractClientPlayerEntity getPlayerEntity() {
        return this.playerEntity;
    }

    public ClothRenderData renderData() {
        //cloth render
        ClothRenderData clothRenderData = new ClothRenderData(EquipmentSlot.HEAD,
                1.3f, 1.1f, 1.2f,
                0.0f, 0.45f, 0.08f);
        clothRenderData.setRenderMode(ClothRenderData.RENDER_ITEMMODEL);
        clothRenderData.setRotable(true);
        clothRenderData.setRotation(180.0f, 0.0f, 0.0f);

        return clothRenderData;
    }

    public ClothRenderData trinketsRenderData(){
        //trinkets render
        ClothRenderData trinketsRenderData = new ClothRenderData(EquipmentSlot.HEAD,
                1.3f, 1.1f, 1.2f,
                0.0f, 0.45f, -0.18f);
        trinketsRenderData.setRenderMode(ClothRenderData.RENDER_ITEMMODEL);
        trinketsRenderData.setRotable(true);
        trinketsRenderData.setRotation(180.0f, 0.0f, 0.0f);

        return trinketsRenderData;
    }

    @Environment(EnvType.CLIENT)
    private boolean isSlotCloth(){
        return (getPlayerEntity() == null) || TrinketsApi.getTrinketComponent(getPlayerEntity()).getStack(SlotGroups.HEAD, Slots.MASK).isEmpty();
    }

    @Override
    public int getColor(ItemStack stack) {
        CompoundTag compoundTag = stack.getSubTag("display");
        return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : 0xffffff;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void render(String slot, MatrixStack matrixStack, VertexConsumerProvider vertexConsumer, int light, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = (player != null) ? TrinketsApi.getTrinketComponent(player).getStack(SlotGroups.HEAD, Slots.MASK) : this.getStackForRender();
        ITrinket.translateToFace(matrixStack, model, player, headYaw, headPitch);

        //check the slot every frame.

        ClothRenderData renderData = (isSlotCloth()) ? renderData() : trinketsRenderData();

        this.playerEntity = player;

        if (renderData.isRotable()) {
            matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(renderData.getRotation().getX()));
            matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(renderData.getRotation().getY()));
            matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(renderData.getRotation().getZ()));
        }
        matrixStack.scale(renderData.getScaleX(), renderData.getScaleY(), renderData.getScaleZ());
        matrixStack.translate(renderData.gettX(), renderData.gettY(), renderData.gettZ());

        itemRenderer.renderItem(stack, ModelTransformation.Mode.FIXED, light, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumer);

    }
}
