package jp.ac.nagano_nct.ashida_lab.se;

/**
 * 定電圧検知用リスナ
 * @author ashida
 */
public abstract interface LowVoltageDetectorListener {

	/**
	 * 電圧低下イベント
	 */
	public abstract void lowVoltageEvent();

}
