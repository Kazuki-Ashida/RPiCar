package jp.ac.nagano_nct.ashida_lab.se;

/**
 * 左方向指示器
 * @author ashida
 */
public class LeftDirectionIndicator extends DirectionIndicator {

	/**
	 * 端子番号
	 */
	private static final int _PIN_NUM = 11;

	/**
	 * シングルトンのインスタンス
	 */
	private static LeftDirectionIndicator _instance = new LeftDirectionIndicator(_PIN_NUM);

	/**
	 * コンストラクタ
	 * @param pin_num 端子番号
	 */
	private LeftDirectionIndicator(int pin_num) {
		super(pin_num);
	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static LeftDirectionIndicator getInstance() {
		return _instance;
	}

}
