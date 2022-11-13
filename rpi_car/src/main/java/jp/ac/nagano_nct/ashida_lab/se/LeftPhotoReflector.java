package jp.ac.nagano_nct.ashida_lab.se;

/**
 * 左のフォトリフレクタ
 */
public class LeftPhotoReflector extends PhotoReflector {

	/**
	 * 端子番号
	 */
	private static final int _PIN_NUM = 16;

	/**
	 * シングルトンのインスタンス
	 */
	private static LeftPhotoReflector _singleton = new LeftPhotoReflector(_PIN_NUM);

	/**
	 * コンストラクタ
	 * @param pin_num 端子番号
	 */
	private LeftPhotoReflector(int pin_num) {
		super(pin_num);
	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static LeftPhotoReflector getInstance() {
		return _singleton;
	}

}
