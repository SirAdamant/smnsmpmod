package giga5.mod.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class chips extends Item {
    public chips() {
        super((new Item.Properties().food(new Food.Builder().hunger(5).saturation(1.4F).build()).containerItem(Items.BOWL).maxStackSize(1)));
    }    public UseAction UseAction(ItemStack stack) {
        return UseAction.EAT;
    }
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
        itemstack.shrink(1);
        return new ItemStack(Items.BOWL);
    }

}
