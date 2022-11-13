package jp.ac.nagano_nct.ashida_lab.se;

/**
 * 右側のロータリエンコーダ
 * @author ashida
 */
public class RightRotaryEncoder extends RotaryEncoder {

	/**
	 * 端子番号
	 */
	private static final int _PIN_NUM = 20;

	/**
	 * シングルトンのインスタンス
	 */
	private static RightRotaryEncoder _instance = new RightRotaryEncoder(_PIN_NUM);

	/**
	 * コンストラクタ
	 * @param pin_num 端子番号
	 */
	private RightRotaryEncoder(int pin_num) {
		super(pin_num);
	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static RightRotaryEncoder getInstance() {
		return _instance;
	}

}
