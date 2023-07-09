package tallflowerpots.mixin;

import net.minecraft.block.BambooSaplingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DecoratedPotBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BambooSaplingBlock.class)
public abstract class BambooSaplingMixin {

	@Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
	void canPlantOnTop(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		if(world.getBlockState(pos.down()).getBlock() instanceof DecoratedPotBlock) cir.setReturnValue(true);
	}
}
