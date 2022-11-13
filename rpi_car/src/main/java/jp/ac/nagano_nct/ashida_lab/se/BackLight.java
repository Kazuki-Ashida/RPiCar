package jp.ac.nagano_nct.ashida_lab.se;

/**
 * バックライト
 * @author ashida
 */
public class BackLight extends Light {

	/**
	 * 端子番号
	 */
	private static final int _PIN_NUM = 7;

	/**
	 * シングルトンのインスタンス
	 */
	private static BackLight _instance = new BackLight(_PIN_NUM);

	/**
	 * コンストラクタ
	 * @param pin_num 端子番号
	 */
	private BackLight(int pin_num) {
		super(pin_num);
	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static BackLight getInstance() {
		return _instance;
	}

	/**
	 * 端子番号を得る
	 * @return 端子番号
	 */
	protected int _getPinNum() {
		return _PIN_NUM;
	}

}
