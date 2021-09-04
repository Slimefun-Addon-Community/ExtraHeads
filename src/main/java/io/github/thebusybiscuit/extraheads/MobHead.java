package io.github.thebusybiscuit.extraheads;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public class MobHead extends SlimefunItem {

    private Runnable runnable;

    public MobHead(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack recipe) {
        super(itemGroup, item, recipeType, new ItemStack[] { null, null, null, null, recipe, null, null, null, null });
    }

    public void register(ExtraHeads plugin, Runnable runnable) {
        this.runnable = runnable;
        register(plugin);
    }

    @Override
    public void postRegister() {
        super.postRegister();

        if (!isDisabled()) {
            runnable.run();
        }
    }

}
