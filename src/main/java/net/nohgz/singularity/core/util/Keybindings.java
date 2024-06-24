package net.nohgz.singularity.core.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class Keybindings {

    public static final String KEY_CATEGORY_SINGULARITY = "key.category.singularity";
    public static final String KEY_SHADER_TOGGLE = "key.singularity.toggle_shader";

    public static final KeyMapping SHADER_TOGGLE_KEY = new KeyMapping(KEY_SHADER_TOGGLE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_SINGULARITY);

}
