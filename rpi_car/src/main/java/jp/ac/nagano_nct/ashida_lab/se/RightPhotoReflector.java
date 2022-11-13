package jp.ac.nagano_nct.ashida_lab.se;

/**
 * 右のフォトリフレクタ
 * @author ashida
 */
public class RightPhotoReflector extends PhotoReflector {

	/**
	 * 端子番号
	 */
	private static final int _PIN_NUM = 6;

	/**
	 * シングルトンのインスタンス
	 */
	private static RightPhotoReflector _singleton = new RightPhotoReflector(_PIN_NUM);

	/**
	 * コンストラクタ
	 * @param pin_num 端子番号
	 */
	private RightPhotoReflector(int pin_num) {
		super(pin_num);
	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static RightPhotoReflector getInstance() {
		return _singleton;
	}

}
