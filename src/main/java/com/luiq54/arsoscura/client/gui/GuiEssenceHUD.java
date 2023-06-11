package com.luiq54.arsoscura.client.gui;

import com.luiq54.arsoscura.ArsOscura;
import com.luiq54.arsoscura.client.ClientInfo;
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

        int offsetLeft = 10;
//        int offsetLeft = 10 + Config.MANABAR_X_OFFSET.get();
        int manaLength = 96;
        manaLength = (int) ((manaLength) * ((essenceCap.getCurrentEssence()) / ((double) essenceCap.getMaxEssence() - 0.0)));

        int yOffset = minecraft.getWindow().getGuiScaledHeight() - 28;
//        int yOffset = minecraft.getWindow().getGuiScaledHeight() - 5 + Config.MANABAR_Y_OFFSET.get();

        RenderSystem.setShaderTexture(0, new ResourceLocation(ArsOscura.MODID, "textures/gui/manabar_gui_border.png"));
        blit(ms, offsetLeft, yOffset - 18, 0, 0, 108, 18, 256, 256);
        int manaOffset = (int) (((ClientInfo.ticksInGame + pt) / 3 % (33))) * 6;

        // 96
        RenderSystem.setShaderTexture(0, new ResourceLocation(ArsOscura.MODID, "textures/gui/manabar_gui_mana.png"));
        blit(ms, offsetLeft + 9, yOffset - 9, 0, manaOffset, manaLength, 6, 256, 256);

        RenderSystem.setShaderTexture(0, new ResourceLocation(ArsOscura.MODID, "textures/gui/manabar_gui_border.png"));
        blit(ms, offsetLeft, yOffset - 17, 0, 18, 108, 20, 256, 256);
    }
}
