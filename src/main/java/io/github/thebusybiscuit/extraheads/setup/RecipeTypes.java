package io.github.thebusybiscuit.extraheads.setup;

import io.github.thebusybiscuit.extraheads.ExtraHeads;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import lombok.experimental.UtilityClass;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

@UtilityClass
public class RecipeTypes {

    public static final RecipeType DECAPITATION = new RecipeType(
        new NamespacedKey(ExtraHeads.getInstance(), "decapitation"),
        new CustomItemStack(Material.IRON_SWORD, "&6Kill the specified Mob")
    );
}
