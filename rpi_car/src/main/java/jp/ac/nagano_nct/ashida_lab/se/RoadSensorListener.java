package jp.ac.nagano_nct.ashida_lab.se;

import java.util.HashMap;

/**
 * 道路センサの状態が変化したときのリスナ
 * @author ashida
 */
public abstract interface RoadSensorListener {

	/**
	 * 状態が変化したとき
	 * @param status センサの状態
	 */
	public abstract void handleRoadSensorStateChangeEvent(HashMap<PhotoReflectorLocation,Boolean> status);

}
