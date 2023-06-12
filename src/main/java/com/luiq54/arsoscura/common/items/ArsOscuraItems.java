package com.luiq54.arsoscura.common.items;

import com.luiq54.arsoscura.ArsOscura;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.ItemEntry;

public class ArsOscuraItems {
    private static final Registrate REGISTRATE = ArsOscura.registrate();

    public static final ItemEntry<ExampleCosmetic> EXAMPLE = REGISTRATE.item("star_hat", ExampleCosmetic::new)
            .lang("Example Hat")
            .tab(() -> ArsOscura.ArsOscuraTab)
            .register();

    public static final ItemEntry<Sigil> SIGIL = REGISTRATE.item("sigil", Sigil::new)
            .defaultModel()
            .lang("Sigil")
            .tab(() -> ArsOscura.ArsOscuraTab)
            .register();


    public static void register() {
    }
}
