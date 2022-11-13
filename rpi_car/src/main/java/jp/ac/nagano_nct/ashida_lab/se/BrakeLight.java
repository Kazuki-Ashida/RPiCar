package jp.ac.nagano_nct.ashida_lab.se;

/**
 * ブレーキライト
 * @author ashida
 */
public class BrakeLight extends Light {

	/**
	 * 端子番号
	 */
	private static final int _PIN_NUM = 9;

	/**
	 * シングルトンのインスタンス
	 */
	private static BrakeLight _instance = new BrakeLight(_PIN_NUM);

	/**
	 * コンストラクタ
	 * @param pin_num 端子番号
	 */
	private BrakeLight(int pin_num) {
		super(pin_num);
	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static BrakeLight getInstance() {
		return _instance;
	}

}
