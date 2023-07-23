package com.mystchonky.arsoscura.client.integration.bloodmagic.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mystchonky.arsoscura.ArsOscura;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import wayoftime.bloodmagic.common.item.BloodOrb;
import wayoftime.bloodmagic.common.item.IBloodOrb;
import wayoftime.bloodmagic.core.data.SoulNetwork;
import wayoftime.bloodmagic.core.registry.OrbRegistry;
import wayoftime.bloodmagic.util.helper.NetworkHelper;

public class GuiLifeEssenceHUD extends GuiComponent {

    public static final IGuiOverlay OVERLAY = GuiLifeEssenceHUD::renderOverlay;

    private static final Minecraft minecraft = Minecraft.getInstance();


    public static void renderOverlay(ForgeGui gui, PoseStack ms, float pt, int width,
                                     int height) {


        SoulNetwork network = NetworkHelper.getSoulNetwork(minecraft.player.getUUID());
        int orbTier = network.getOrbTier();
        if (OrbRegistry.tierMap.get(orbTier).isEmpty()) {
            return;
        }
        ItemStack orbStack = OrbRegistry.tierMap.get(orbTier).get(0);
        BloodOrb orb = ((IBloodOrb) orbStack.getItem()).getOrb(orbStack);


        int xOffset = 10;
//        int offsetLeft = 10 + Config.MANABAR_X_OFFSET.get();
        int manaLength = 98;
        manaLength = (int) ((manaLength) * ((network.getCurrentEssence()) / ((double) orb.getCapacity() - 0.0)));

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
