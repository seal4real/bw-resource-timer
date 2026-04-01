package com.github.seal4real.bwresourcetimer;

import com.github.seal4real.bwresourcetimer.config.TimerConfig;
import com.github.seal4real.bwresourcetimer.events.BwEventListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "bwresourcetimer", useMetadata=true)
public class BwResourceTimer {
    public static TimerConfig config;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        config = new TimerConfig();
        MinecraftForge.EVENT_BUS.register(new BwEventListener());
    }
}
