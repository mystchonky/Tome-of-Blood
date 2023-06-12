package com.luiq54.arsoscura.client.gui;

import com.luiq54.arsoscura.ArsOscura;
import com.luiq54.arsoscura.common.capability.CapabilityRegistry;
import com.luiq54.arsoscura.common.capability.IEssenceCap;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class GuiEssenceHUD extends GuiComponent {

    public static final IGuiOverlay OVERLAY = GuiEssenceHUD::renderOverlay;

    private static final Minecraft minecraft = Minecraft.getInstance();


    public static void renderOverlay(ForgeGui gui, PoseStack ms, float pt, int width,
                                     int height) {

        IEssenceCap essenceCap = CapabilityRegistry.getEssence(minecraft.player).orElse(null);
        if (essenceCap == null) // FIXME: why does this exist, remove if unnecessary
            return;

        int xOffset = 10;
//        int offsetLeft = 10 + Config.MANABAR_X_OFFSET.get();
        int manaLength = 98;
        manaLength = (int) ((manaLength) * ((essenceCap.getCurrentEssence()) / ((double) essenceCap.getMaxEssence() - 0.0)));

        int yOffset = minecraft.getWindow().getGuiScaledHeight() - 28 - 18;
//        int yOffset = minecraft.getWindow().getGuiScaledHeight() - 5 + Config.MANABAR_Y_OFFSET.get();

        RenderSystem.setShaderTexture(0, new ResourceLocation(ArsOscura.MODID, "textures/gui/essencebar_gui.png"));
        blit(ms, xOffset, yOffset, 4, 4, 102, 18, 256, 256);

        // 96
        RenderSystem.setShaderTexture(0, new ResourceLocation(ArsOscura.MODID, "textures/gui/essencebar_gui.png"));
        blit(ms, xOffset + 2, yOffset + 6, 6, 29, manaLength, 8, 256, 256);

        RenderSystem.setShaderTexture(0, new ResourceLocation(ArsOscura.MODID, "textures/gui/essencebar_gui.png"));
        blit(ms, xOffset, yOffset, 4, 40, 102, 18, 256, 256);
    }
}
