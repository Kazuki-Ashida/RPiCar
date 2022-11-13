package jp.ac.nagano_nct.ashida_lab.se;

/**
 * 左側のロータリエンコーダ
 * @author ashida
 */
public class LeftRotaryEncoder extends RotaryEncoder {

	/**
	 * 端子番号
	 */
	private static final int _PIN_NUM = 26;

	/**
	 * シングルトンのインスタンス
	 */
	private static LeftRotaryEncoder _instance = new LeftRotaryEncoder(_PIN_NUM);

	/**
	 * コンストラクタ
	 * @param pin_num 端子番号
	 */
	private LeftRotaryEncoder(int pin_num) {
		super(pin_num);
	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static LeftRotaryEncoder getInstance() {
		return _instance;
	}

}
