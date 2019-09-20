package io.github.thebusybiscuit.extraheads;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class MobHead extends SlimefunItem {

	private Runnable runnable;
	
	public MobHead(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack recipe) {
		super(category, item, id, recipeType, new ItemStack[] {null, null, null, null, recipe, null, null, null, null});
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
