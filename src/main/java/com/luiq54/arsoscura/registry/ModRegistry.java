package com.luiq54.arsoscura.registry;

import com.hollingsworth.arsnouveau.api.sound.SpellSound;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.luiq54.arsoscura.ArsOscura.MODID;

public class ModRegistry {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);


    public static void registerRegistries(IEventBus bus) {
        SOUNDS.register(bus);
    }


    //this is an example of how to register a sound. You also need to add the sound to the sound.json file, referencing your ogg files, and a texture for the button under textures/sounds.
    //this example will use one of the existing sounds randomly
    public static RegistryObject<SoundEvent> EXAMPLE_FAMILY = SOUNDS.register("example_sound", () -> makeSound("example_sound"));
    public static SpellSound EXAMPLE_SPELL_SOUND;

    static SoundEvent makeSound(String name) {
        return new SoundEvent(new ResourceLocation(MODID, name));
    }

}
