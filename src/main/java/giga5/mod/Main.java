package giga5.mod;

import giga5.mod.crops.pepperplant;
import giga5.mod.enchants.springs;
import giga5.mod.event.BouncyBoot;
import giga5.mod.items.Pepper;
import giga5.mod.items.chips;
import giga5.mod.items.ikoamb;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MODID)
public class Main
{

    public static final String MODID = "smnsmpmod";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Main() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(new BouncyBoot());
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(objectlist.PEPPERPLANT.getBlock(), RenderType.getCutout());
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void serverSetupRuns(final FMLServerAboutToStartEvent event) {
    }


    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(modid=MODID, bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            blockRegistryEvent.getRegistry().registerAll(
                    new pepperplant(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().notSolid().tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP)).setRegistryName("pepperplant")
            );
            LOGGER.info("HELLO from Register Block");
        }
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            itemRegistryEvent.getRegistry().registerAll(
                    new ikoamb().setRegistryName("ikoameatball"),
                    new Item(new Item.Properties().maxStackSize(1).group(ItemGroup.MISC)).setRegistryName("bbqsauce"),
                    new BlockNamedItem(objectlist.PEPPERPLANT, new Item.Properties()).setRegistryName("pepper_seeds"),
                    new Pepper().setRegistryName("pepper"),
                    new Item(new Item.Properties()).setRegistryName("salsa"),
                    new MusicDiscItem(0, () -> objectlist.DESOLATE, new Item.Properties().maxStackSize(1).rarity(Rarity.RARE)).setRegistryName("music_disc_desolate"),
                    new MusicDiscItem(0, () -> objectlist.ASPIRATION_FOR_ABSENCE, new Item.Properties().maxStackSize(1).rarity(Rarity.RARE)).setRegistryName("music_disc_aspiration"),
                    new chips().setRegistryName("chips")
            );
            LOGGER.info("HELLO from Register Item");
        }
        @SubscribeEvent
        public static void onEnchRegistry(final RegistryEvent.Register<Enchantment> enchRegistryEvent) {
            enchRegistryEvent.getRegistry().registerAll(
                    new springs(Enchantment.Rarity.UNCOMMON, EnchantmentType.ARMOR_FEET, EquipmentSlotType.FEET).setRegistryName("springs")
            );
            LOGGER.info("HELLO from Register Ench");
        }
        @SubscribeEvent
        public static void onSoundRegistry(final RegistryEvent.Register<SoundEvent> soundRegistryEvent) {
            soundRegistryEvent.getRegistry().registerAll(

                    new SoundEvent(new ResourceLocation("smnsmpmod","desolate")).setRegistryName("desolate"),
                    new SoundEvent(new ResourceLocation("smnsmpmod","aspiration_for_absence")).setRegistryName("aspiration_for_absence")
            );
            LOGGER.info("HELLO from Register Sound");
        }
    }
}
