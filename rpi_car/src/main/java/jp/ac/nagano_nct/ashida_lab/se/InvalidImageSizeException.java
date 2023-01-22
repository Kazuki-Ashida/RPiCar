package jp.ac.nagano_nct.ashida_lab.se;

/**
 * 不正な画像サイズが指定された時の例外
 * @author 芦田
 */
public class InvalidImageSizeException extends RuntimeException {

	/**
	 * 不正なサイズ
	 */
	private int _invalidSize;

	/**
	 * コンストラクタ
	 * @param invalid_size 不正なサイズ
	 */
	public InvalidImageSizeException(int invalid_size) {
		_invalidSize=invalid_size;
	}

	/**
	 * 不正なサイズを得る
	 * @return 不正なサイズ
	 */
	public int getInvalidSize() {
		return _invalidSize;
	}

}
