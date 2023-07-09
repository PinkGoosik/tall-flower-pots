package tallflowerpots.mixin;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractPlantPartBlock.class)
public abstract class PlantPartMixin {

	@Shadow
	@Final
	protected Direction growthDirection;

	@Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
	void canPlantOnTop(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		if(state.getBlock() instanceof TwistingVinesBlock) {
			BlockPos blockPos = pos.offset(growthDirection.getOpposite());
			BlockState floor = world.getBlockState(blockPos);
			if(floor.getBlock() instanceof DecoratedPotBlock) cir.setReturnValue(true);
		}
	}
}
