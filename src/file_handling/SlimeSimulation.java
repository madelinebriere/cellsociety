package file_handling;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import data_structures.PatchName;
import javafx.scene.paint.Color;
import patch_level.Patch;

public class SlimeSimulation extends SimulationType {

	private static final List<String> SETTING_TYPES = Arrays.asList(new String[] {
			"lowthreshold", "highthreshold"
	    });

	public SlimeSimulation(Map<String, String> values, List<String> cells) {
		super(values, cells);
		this.settingTypes = SETTING_TYPES;
		this.dataTypes = combineDataTypes();
	}

	public Double getLowThreshold(){
		return Double.parseDouble(getDataValues().get(SETTING_TYPES.get(0)));
	}
	
	public Double getHighThreshold(){
		return  Double.parseDouble(getDataValues().get(SETTING_TYPES.get(1)));
	}
	
	@Override
	public TreeMap<PatchName, List<Patch>> getShiftedPatches() {
		return getShiftedPatches(PatchName.SLIME_PATCH, Color.BLACK);
	}
}
