package io.github.thebusybiscuit.extraheads;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class MobHead extends SlimefunItem {

	private Runnable runnable;
	
	public MobHead(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack recipe) {
		super(category, item, recipeType, new ItemStack[] {null, null, null, null, recipe, null, null, null, null});
	}

	public void register(Runnable runnable) {
		this.runnable = runnable;
		this.register();
	}
	
	@Override
	public void postRegister() {
		super.postRegister();
		
		if (!isDisabled()) runnable.run();
	}

}
