package giga5.mod.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.world.World;

public class Pepper extends Item {
    public Pepper() {
        super(new Item.Properties().food(new Food.Builder().hunger(2).saturation(1.5F).build()));
    }
    public UseAction UseAction(ItemStack stack) {
        return UseAction.EAT;
    }
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
        itemstack.shrink(1);
        return stack;
    }
}
