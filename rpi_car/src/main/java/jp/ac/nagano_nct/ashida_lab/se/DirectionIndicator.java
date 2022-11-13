package jp.ac.nagano_nct.ashida_lab.se;

/**
 * 方向指示器
 * @author ashida
 */
public abstract class DirectionIndicator extends Light {

	/**
	 * 点滅の間隔[ms]
	 */
	private static final int _BLINKING_INTERVAL = 250;

	/** コンストラクタ
	 * @param pin_num 端子番号
	 */
	protected DirectionIndicator(int pin_num){
		super(pin_num);
	}
	/**
	 * 点滅開始
	 */
	public void startBlinking() {

	}

	/**
	 * 点滅停止
	 */
	public void stopBlinking() {

	}

}
