package giga5.mod.event;

import giga5.mod.objectlist;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.crafting.conditions.FalseCondition;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class BouncyBoot {
    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent e){
        if(e.side == LogicalSide.CLIENT)
            return; //Checks if its client-side and if it is, return

        if(e.player == null)
            return; //Just in case someone stupid enough to pass in a null player

        PlayerEntity p = e.player;

        int level = EnchantmentHelper.getEnchantmentLevel(objectlist.SPRINGS, p.getItemStackFromSlot(EquipmentSlotType.FEET));

        //If level is greater than zero.
        if(level > 0){
            p.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST,80,level-1, false, false));
        }
    }
}
