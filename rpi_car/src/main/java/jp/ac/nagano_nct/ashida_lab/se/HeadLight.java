package jp.ac.nagano_nct.ashida_lab.se;

/**
 * ヘッドライト
 * @author ashida
 */
public class HeadLight extends Light {

	/**
	 * 端子番号
	 */
	private static final int _PIN_NUM = 5;

	/**
	 * シングルトンのインスタンス
	 */
	private static HeadLight _instance = new HeadLight(_PIN_NUM);

	/**
	 * コンストラクタ
	 * @param pin_num 端子番号
	 */
	private HeadLight(int pin_num) {
		super(pin_num);
	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static HeadLight getInstance() {
		return _instance;
	}

}
