package io.zenith391.reactop;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import net.minecraft.util.Identifier;

public class ComponentTypes {

	public static final ComponentType<HeatComponent> HEAT_COMPONENT = 
			ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("reactop", "heat_component"), HeatComponent.class);
	
}
