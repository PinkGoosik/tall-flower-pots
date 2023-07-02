package tallflowerpots.mixin;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class BlockStateMixin {

	@Shadow
	public abstract Block getBlock();

	@Inject(method = "getModelOffset", at = @At("HEAD"), cancellable = true)
	void getModelOffset(BlockView world, BlockPos pos, CallbackInfoReturnable<Vec3d> cir) {
		var block = getBlock();
		if(block instanceof TallFlowerBlock || block.equals(Blocks.PITCHER_PLANT) && world.getBlockState(pos.down()).getBlock() instanceof DecoratedPotBlock || world.getBlockState(pos.down().down()).getBlock() instanceof DecoratedPotBlock) {
			cir.setReturnValue(Vec3d.ZERO);
		}
	}
}
