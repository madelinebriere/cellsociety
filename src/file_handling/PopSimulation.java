/**
 * Holds, interprets, and returns data passed 
 * in from a file specific to the Segregation Simulation.
 * 
 * @author Stone Mathers
 */

package file_handling;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import data_structures.PatchName;
import javafx.scene.paint.Color;
import patch_level.Patch;

public class PopSimulation extends SimulationType {
	
	private static final List<String> SETTING_TYPES = Arrays.asList(new String[] {
			"threshold"
	    });

	public PopSimulation(Map<String, String> values, List<String> cells) {
		super(values, cells);
		this.settingTypes = SETTING_TYPES;
		this.dataTypes = combineDataTypes();
	}
	
	public Double getThreshold(){
		return Double.parseDouble(getDataValues().get(SETTING_TYPES.get(0)));
	}

	
	public Double getSatisfiedThreshold(){
		return  Double.parseDouble(getDataValues().get(SETTING_TYPES.get(0)));
	}

	@Override
	public TreeMap<PatchName, List<Patch>> getShiftedPatches() {
		return getShiftedPatches(PatchName.EMPTY_PATCH, Color.LIGHTBLUE);
	}


}

