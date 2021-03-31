package giga5.mod.enchants;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;

public class springs extends Enchantment {
    public springs(Rarity rare, EnchantmentType type, EquipmentSlotType... slot) {
        super(rare, type, slot);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return !ench.equals(Enchantments.FROST_WALKER);
    }

}
