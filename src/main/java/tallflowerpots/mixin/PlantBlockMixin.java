package tallflowerpots.mixin;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlantBlock.class)
public abstract class PlantBlockMixin extends Block {

	public PlantBlockMixin(Settings settings) {
		super(settings);
	}

	@Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
	void canPlantOnTop(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		var block = PlantBlock.class.cast(this);
		if(block instanceof TallFlowerBlock || block.equals(Blocks.PITCHER_PLANT)) {
			if(floor.getBlock() instanceof DecoratedPotBlock) cir.setReturnValue(true);
		}
	}
}
