package jp.ac.nagano_nct.ashida_lab.se;

/**
 * 中央のフォトリフレクタ
 */
public class CenterPhotoReflector extends PhotoReflector {

	/**
	 * 端子番号
	 */
	private static final int _PIN_NUM = 13;

	/**
	 * シングルトンのインスタンス
	 */
	private static CenterPhotoReflector _singleton = new CenterPhotoReflector(_PIN_NUM);


	/**
	 * コンストラクタ
	 * @param pin_num 端子番号
	 */
	private CenterPhotoReflector(int pin_num) {

		super(pin_num);
	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static CenterPhotoReflector getInstance() {
		return _singleton;
	}

}
