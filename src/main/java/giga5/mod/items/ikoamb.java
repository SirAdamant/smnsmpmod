package giga5.mod.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.world.World;

public class ikoamb  extends Item {
    public ikoamb() {
        super(new Item.Properties().food(new Food.Builder().meat().saturation(1.6F).fastToEat().hunger(2).build()));
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
