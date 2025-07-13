package tallflowerpots.mixin;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DecoratedPotBlock.class)
abstract class DecoratedPotMixin {

	@Inject(method = "onUseWithItem", at = @At("HEAD"), cancellable = true)
	void onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult.PassToDefaultBlockAction> cir) {
		if(hit.getSide().equals(Direction.UP) && stack.getItem() instanceof BlockItem blockItem) {
			if(blockItem.getBlock() instanceof TallPlantBlock || blockItem.getBlock() instanceof TwistingVinesBlock || blockItem.getBlock().equals(Blocks.PITCHER_PLANT) || blockItem.getBlock() instanceof SweetBerryBushBlock) {
				cir.setReturnValue(ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION);
			}
		}
	}

}
