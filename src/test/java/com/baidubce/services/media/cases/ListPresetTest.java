package com.baidubce.services.media.cases;

import com.baidubce.services.media.model.GetPresetResponse;
import com.baidubce.services.media.model.ListPresetsRequest;
import com.baidubce.services.media.model.ListPresetsResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ListPresetTest extends AbstractMediaTest {

    public String          prefix = AbstractMediaTest.PRE_NAME + "listpreset";

	@Before
    public void setUp() throws Exception {
        super.setUp();
        presetName = convertName(prefix);
        String container = "hls";
        presetClient.createPreset(presetName, container);
        checkPresetExist(presetName, true);
    }

    @After
    public void tearDown() throws Exception {
    	for (GetPresetResponse preset : presetClient.listPresets().getPresets()) {
            GetPresetResponse resp = presetClient.getPreset(preset.getPresetName());
            System.out.println(resp);
            assertTrue("Failed to get preset: " + preset.getPresetName(), resp
                    .getPresetName().equals(preset.getPresetName()));
            presetName = preset.getPresetName();
        	if (presetName.startsWith(prefix)) {
        		presetClient.deletePreset(presetName);
                checkPresetExist(presetName, false);
            }
           /* Pattern pattern = Pattern.compile("^bce.");
            *  Matcher m = pattern.matcher(presetName);
            *  boolean result = m.find();
            *  if (!result) {
            *  	System.out.println("delete preset " + presetName);
            *  	presetClient.deletePreset(presetName);
            *  	checkPresetExist(presetName, false);
            *  }
            */
        }
    }
    
    @Test
    public void testListPresetAddOne(){
    	ListPresetsResponse resp = presetClient.listPresets();
    	boolean exist = false;
        for (GetPresetResponse preset : resp.getPresets()) {
        	if(preset.getPresetName().equals(presetName)){
        		if(preset.getState().equals("ACTIVE")){
        			exist = true;
        		}
        		break;
        	}
        }
    	assertTrue("list presets error", exist);    	
    }
    
    @Test
    public void testListPresetDeleteOne(){
    	ListPresetsResponse resp = presetClient.listPresets();
    	boolean exist = false;
        for (GetPresetResponse preset : resp.getPresets()) {
        	if(preset.getPresetName().equals(presetName)){
        		if(preset.getState().equals("INACTIVE")){
        			exist = true;
        		}
        		break;
        	}
        }
    	assertFalse("list presets error", exist);    	
    }
    
    @Test
    /**
     * ListPresetsResponse listPresets(ListPresetsRequest request)
     */
    public void testListPresetsNormal(){
    	ListPresetsRequest request = new ListPresetsRequest();
    	ListPresetsResponse resp = presetClient.listPresets(request);
    	boolean exist = false;
        for (GetPresetResponse preset : resp.getPresets()) {
        	if(preset.getPresetName().equals(presetName)){
        		if(preset.getState().equals("INACTIVE")){
        			exist = true;
        		}
        		break;
        	}
        }
    	assertFalse("list presets error", exist);    	
    }
}
