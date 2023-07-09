package tallflowerpots.mixin;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BambooBlock.class)
public abstract class BambooBlockMixin extends Block implements Fertilizable {

	@Shadow
	@Final
	public static IntProperty AGE;

	public BambooBlockMixin(Settings settings) {
		super(settings);
	}

	@Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
	void canPlantOnTop(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		if(world.getBlockState(pos.down()).getBlock() instanceof DecoratedPotBlock) cir.setReturnValue(true);
	}

	@Inject(method = "getPlacementState", at = @At("HEAD"), cancellable = true)
	void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
		FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
		if (fluidState.isEmpty()) {
			BlockState floor = ctx.getWorld().getBlockState(ctx.getBlockPos().down());
			if (floor.getBlock() instanceof DecoratedPotBlock) {
				BlockState state = ctx.getWorld().getBlockState(ctx.getBlockPos().up());
				cir.setReturnValue(state.isOf(Blocks.BAMBOO)
					? this.getDefaultState().with(AGE, state.get(AGE))
					: Blocks.BAMBOO_SAPLING.getDefaultState());
			}
		}
	}
}
