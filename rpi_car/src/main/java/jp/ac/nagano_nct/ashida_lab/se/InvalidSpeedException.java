package jp.ac.nagano_nct.ashida_lab.se;

/**
 * 不正なスピードを設定されたときに投げられる例外
 * @author ashida
 */
public class InvalidSpeedException extends RuntimeException {

	/**
	 * 不正なスピード
	 */
	private int _invalidSpeed;

	/**
	 * コンストラクタ
	 * @param invalid_speed 不正なスピード
	 */
	public InvalidSpeedException(int invalid_speed) {
		_invalidSpeed = invalid_speed;
	}

	/**
	 * 不正なスピードを得る
	 * @return 不正なスピード
	 */
	public int getInvalidSpeed() {
		return _invalidSpeed;
	}

}
