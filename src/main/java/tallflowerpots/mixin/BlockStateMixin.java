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
		if(block instanceof BambooSaplingBlock || block instanceof TallPlantBlock || block instanceof TwistingVinesBlock || block.equals(Blocks.PITCHER_PLANT)) {
			if(world.getBlockState(pos.down()).getBlock() instanceof DecoratedPotBlock || world.getBlockState(pos.down().down()).getBlock() instanceof DecoratedPotBlock) {
				cir.setReturnValue(Vec3d.ZERO);
			}
		}
		if(block instanceof BambooBlock) {
			for (int i = 1; i <= 319; i++) {
				if(world.getBlockState(pos.down(i)).getBlock() instanceof BambooBlock) {
					continue;
				}
				if(world.getBlockState(pos.down(i)).getBlock() instanceof DecoratedPotBlock) {
					cir.setReturnValue(Vec3d.ZERO);
				}
				else break;
			}
		}
	}
}
