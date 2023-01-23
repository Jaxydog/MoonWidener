package dev.jaxydog.widemoon;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoonWidener implements ModInitializer {

	public static final String MOD_ID = "widemoon";
	public static final Logger LOGGER = LoggerFactory.getLogger(MoonWidener.MOD_ID);

	public static final float MOON_SIZE = 16.0f;

	@Override
	public void onInitialize() {
		MoonWidener.LOGGER.info("And just like that, the moon is fat!");
	}
}
